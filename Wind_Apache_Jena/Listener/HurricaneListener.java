package Listener;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.listeners.StatementListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HurricaneListener extends StatementListener {
    private static final String NS = "http://windfarm/";
    private static final String NS_EVENT = NS + "Event/";
    private static final String NS_WeatherEvent = NS_EVENT + "WeatherEvent/";
    private static final String NS_Hurricane = NS_WeatherEvent + "Hurricane#";
    private static final String NS_Time = "http://windfarm/Time#";

    private static final DateTimeFormatter CSV_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter XSD_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private OntModel model;
    private OntClass hurricaneClass;
    private DatatypeProperty hasHurricaneID;
    private DatatypeProperty hasHurricaneName;
    private DatatypeProperty hasStartTime;
    private DatatypeProperty hasEndTime;
    private DatatypeProperty hasArrivalTime;
    private DatatypeProperty hasMaxWindSpeed;
    private DatatypeProperty hasWindSpeed;
    private DatatypeProperty hasWindDirection;
    private DatatypeProperty hasRecordTime;
    private DatatypeProperty hasDistance;
    private DatatypeProperty hasCategory;
    private DatatypeProperty hasPressure;
    private DatatypeProperty hasWindSpeedChangeRate;
    private DatatypeProperty hasWindDirectionChangeRate;
    private DatatypeProperty hasDurationAboveCutOut;
    private DatatypeProperty hasQuadrant;
    private DatatypeProperty hasCurrentTime;

    private LocalDateTime currentSystemTime;

    // Store the most recent valid record for each hurricane
    private String[] mostRecentValidRecord = null;
    private LocalDateTime mostRecentValidTime = null;
    private String currentHurricaneId = null;

    public HurricaneListener(OntModel model) {
        this.model = model;
        hurricaneClass = model.getOntClass(NS_Hurricane);
        hasHurricaneID = model.getDatatypeProperty(NS_Hurricane + "hasHurricaneID");
        hasHurricaneName = model.getDatatypeProperty(NS_Hurricane + "hasHurricaneName");
        hasStartTime = model.getDatatypeProperty(NS_EVENT + "hasStartTime");
        hasEndTime = model.getDatatypeProperty(NS_EVENT + "hasEndTime");
        hasArrivalTime = model.getDatatypeProperty(NS_Hurricane + "hasArrivalTime");
        hasMaxWindSpeed = model.getDatatypeProperty(NS_Hurricane + "hasMaxWindSpeed");
        hasWindSpeed = model.getDatatypeProperty(NS_Hurricane + "hasWindSpeed");
        hasWindDirection = model.getDatatypeProperty(NS_Hurricane + "hasWindDirection");
        hasRecordTime = model.getDatatypeProperty(NS_Hurricane + "hasRecordTime");
        hasDistance = model.getDatatypeProperty(NS_Hurricane + "hasDistance");
        hasCategory = model.getDatatypeProperty(NS_Hurricane + "hasCategory");
        hasPressure = model.getDatatypeProperty(NS_Hurricane + "hasPressure");
        hasWindSpeedChangeRate = model.getDatatypeProperty(NS_Hurricane + "hasWindSpeedChangeRate");
        hasWindDirectionChangeRate = model.getDatatypeProperty(NS_Hurricane + "hasWindDirectionChangeRate");
        hasDurationAboveCutOut = model.getDatatypeProperty(NS_Hurricane + "hasDurationAboveCutOut");
        hasQuadrant = model.getDatatypeProperty(NS_Hurricane + "hasQuadrant");
        hasCurrentTime = model.getDatatypeProperty(NS_Time + "hasCurrentTime");

        // Get the current system time from the ontology
        getCurrentSystemTime();
    }

    private void getCurrentSystemTime() {
        try {
            Individual currentTimeIndividual = model.getIndividual(NS_Time + "currentTime");
            if (currentTimeIndividual != null && currentTimeIndividual.hasProperty(hasCurrentTime)) {
                String currentTimeStr = currentTimeIndividual.getPropertyValue(hasCurrentTime).asLiteral().getString();
                this.currentSystemTime = LocalDateTime.parse(currentTimeStr, XSD_FORMAT);
                System.out.println("Current system time set to: " + this.currentSystemTime);
            } else {
                System.out.println("Warning: Could not find current time in ontology");
            }
        } catch (Exception e) {
            System.out.println("Error getting current system time: " + e.getMessage());
        }
    }

    public void updateHurricane(String[] csvRow) {
        // Validate CSV row has enough columns
        if (csvRow.length < 14) {
            System.out.println("Skipping invalid CSV row - insufficient columns: " + String.join(", ", csvRow));
            return;
        }

        String hurricaneId = csvRow[0];
        String currentRecordTime = csvRow[2];

        // Validate essential fields
        if (!isValidValue(hurricaneId) || !isValidDateTime(currentRecordTime)) {
            System.out.println("Skipping row with invalid hurricane ID or record time: " + String.join(", ", csvRow));
            return;
        }

        // Parse the CSV record time
        LocalDateTime recordTime = LocalDateTime.parse(currentRecordTime, CSV_FORMAT);

        // If this is a new hurricane, finalize the previous one
        if (currentHurricaneId != null && !hurricaneId.equals(currentHurricaneId)) {
            finalizeHurricaneUpdate();
        }

        // Initialize for new hurricane
        if (!hurricaneId.equals(currentHurricaneId)) {
            currentHurricaneId = hurricaneId;
            mostRecentValidRecord = null;
            mostRecentValidTime = null;
        }

        // Stop processing if record time is after current system time
        if (currentSystemTime != null && recordTime.isAfter(currentSystemTime)) {
            System.out.println("Record time " + recordTime + " is after current time " + currentSystemTime + ". Finalizing hurricane: " + hurricaneId);
            finalizeHurricaneUpdate();
            return;
        }

        // Update the most recent valid record if this one is more recent
        if (currentSystemTime != null && !recordTime.isAfter(currentSystemTime)) {
            if (mostRecentValidTime == null || recordTime.isAfter(mostRecentValidTime) || recordTime.equals(mostRecentValidTime)) {
                mostRecentValidRecord = csvRow.clone();
                mostRecentValidTime = recordTime;
                System.out.println("Updated most recent valid record for " + hurricaneId + " at time: " + recordTime);
            }
        }

        // Always update start/end times for temporal range tracking
        updateTemporalRange(hurricaneId, currentRecordTime);
    }

    private void updateTemporalRange(String hurricaneId, String currentRecordTime) {
        // Get or create hurricane individual
        Individual hurricane = model.getIndividual(NS_Hurricane + hurricaneId);
        boolean isNewHurricane = (hurricane == null);

        if (isNewHurricane) {
            hurricane = model.createIndividual(NS_Hurricane + hurricaneId, hurricaneClass);
            System.out.println("Creating new hurricane individual: " + hurricaneId);
        }

        // Always update hurricane ID
        hurricane.removeAll(hasHurricaneID).addProperty(hasHurricaneID, hurricaneId);

        // Handle start time: only set if it's a new hurricane or if current time is earlier
        if (isNewHurricane || !hurricane.hasProperty(hasStartTime)) {
            hurricane.addProperty(hasStartTime, model.createTypedLiteral(formatToXSDDateTime(currentRecordTime), XSDDatatype.XSDdateTime));
        } else {
            try {
                String existingStartTime = hurricane.getPropertyValue(hasStartTime).asLiteral().getString();
                LocalDateTime currentTime = LocalDateTime.parse(currentRecordTime, CSV_FORMAT);
                LocalDateTime existingTime = LocalDateTime.parse(existingStartTime, XSD_FORMAT);

                if (currentTime.isBefore(existingTime)) {
                    hurricane.removeAll(hasStartTime).addProperty(hasStartTime,
                        model.createTypedLiteral(formatToXSDDateTime(currentRecordTime), XSDDatatype.XSDdateTime));
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error comparing start times for hurricane " + hurricaneId + ": " + e.getMessage());
                hurricane.removeAll(hasStartTime).addProperty(hasStartTime,
                    model.createTypedLiteral(formatToXSDDateTime(currentRecordTime), XSDDatatype.XSDdateTime));
            }
        }

        // Handle end time: always update to the latest time up to current system time
        if (!hurricane.hasProperty(hasEndTime)) {
            hurricane.addProperty(hasEndTime, model.createTypedLiteral(formatToXSDDateTime(currentRecordTime), XSDDatatype.XSDdateTime));
        } else {
            try {
                String existingEndTime = hurricane.getPropertyValue(hasEndTime).asLiteral().getString();
                LocalDateTime currentTime = LocalDateTime.parse(currentRecordTime, CSV_FORMAT);
                LocalDateTime existingTime = LocalDateTime.parse(existingEndTime, XSD_FORMAT);

                if (currentTime.isAfter(existingTime)) {
                    hurricane.removeAll(hasEndTime).addProperty(hasEndTime,
                        model.createTypedLiteral(formatToXSDDateTime(currentRecordTime), XSDDatatype.XSDdateTime));
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error comparing end times for hurricane " + hurricaneId + ": " + e.getMessage());
                hurricane.removeAll(hasEndTime).addProperty(hasEndTime,
                    model.createTypedLiteral(formatToXSDDateTime(currentRecordTime), XSDDatatype.XSDdateTime));
            }
        }
    }

    private void finalizeHurricaneUpdate() {
        if (currentHurricaneId == null || mostRecentValidRecord == null) {
            return;
        }

        System.out.println("Finalizing hurricane update for: " + currentHurricaneId + " using record from time: " + mostRecentValidTime);

        // Get the hurricane individual
        Individual hurricane = model.getIndividual(NS_Hurricane + currentHurricaneId);
        if (hurricane == null) {
            System.out.println("Error: Hurricane individual not found: " + currentHurricaneId);
            return;
        }

        // Set hurricane name if valid and not already set
        if ((!hurricane.hasProperty(hasHurricaneName)) && isValidValue(mostRecentValidRecord[1])) {
            hurricane.addProperty(hasHurricaneName, mostRecentValidRecord[1]);
        }

        // Update real-time attributes using the most recent valid record
        String[] csvRow = mostRecentValidRecord;

        // Update record time (the time of the selected record)
        hurricane.removeAll(hasRecordTime).addProperty(hasRecordTime,
            model.createTypedLiteral(formatToXSDDateTime(csvRow[2]), XSDDatatype.XSDdateTime));

        // Update arrival time if valid
        if (isValidDateTime(csvRow[4])) {
            hurricane.removeAll(hasArrivalTime).addProperty(hasArrivalTime,
                model.createTypedLiteral(formatToXSDDateTime(csvRow[4]), XSDDatatype.XSDdateTime));
        }

        // Update real-time numeric properties
        updateNumericProperty(hurricane, hasMaxWindSpeed, csvRow[7], XSDDatatype.XSDdouble);
        updateNumericProperty(hurricane, hasWindSpeed, csvRow[5], XSDDatatype.XSDdouble);
        updateNumericProperty(hurricane, hasWindDirection, csvRow[6], XSDDatatype.XSDdouble);
        updateNumericProperty(hurricane, hasDistance, csvRow[3], XSDDatatype.XSDdouble);
        updateNumericProperty(hurricane, hasPressure, csvRow[9], XSDDatatype.XSDdouble);
        updateNumericProperty(hurricane, hasWindSpeedChangeRate, csvRow[10], XSDDatatype.XSDdouble);
        updateNumericProperty(hurricane, hasWindDirectionChangeRate, csvRow[11], XSDDatatype.XSDdouble);
        updateNumericProperty(hurricane, hasDurationAboveCutOut, csvRow[12], XSDDatatype.XSDdouble);

        // Update real-time string properties
        if (isValidValue(csvRow[8])) {
            hurricane.removeAll(hasCategory).addProperty(hasCategory, csvRow[8]);
        }
        if (isValidValue(csvRow[13])) {
            hurricane.removeAll(hasQuadrant).addProperty(hasQuadrant, csvRow[13]);
        }

        System.out.println("Hurricane individual finalized: " + hurricane);
    }

    // Call this method after processing all CSV rows to finalize the last hurricane
    public void finalizeFinalHurricane() {
        finalizeHurricaneUpdate();
    }

    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty() &&
               !"Unknown".equalsIgnoreCase(value.trim()) &&
               !"null".equalsIgnoreCase(value.trim());
    }

    private boolean isValidDateTime(String dateTimeStr) {
        if (!isValidValue(dateTimeStr)) {
            return false;
        }

        try {
            LocalDateTime.parse(dateTimeStr, CSV_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid datetime format: " + dateTimeStr);
            return false;
        }
    }

    private void updateNumericProperty(Individual individual, DatatypeProperty property, String value, XSDDatatype datatype) {
        if (isValidValue(value)) {
            try {
                individual.removeAll(property).addProperty(property, model.createTypedLiteral(value, datatype));
            } catch (NumberFormatException e) {
                System.out.println("Invalid numeric value for " + property.getLocalName() + ": " + value);
            }
        }
    }

    private String formatToXSDDateTime(String csvDateTime) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(csvDateTime, CSV_FORMAT);
        return parsedDateTime.format(XSD_FORMAT);
    }
}