package CSVVisitor.Event;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HurricaneCsvVisitor implements Utils.CsvUtil.CsvVisitor {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_EVENT = NS + "Event/";
    private static final String NS_WeatherEvent = NS_EVENT + "WeatherEvent/";
    private static final String NS_Hurricane = NS_WeatherEvent + "Hurricane#";

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
    private boolean isFirstRowProcessed = false;

    public HurricaneCsvVisitor(OntModel model) {
        this.model = model;
        hurricaneClass = model.getOntClass(NS_Hurricane + "Hurricane");
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
    }

    @Override
    public void visit(String[] csvRow) {
        if (!isFirstRowProcessed) {
            Individual hurricane = model.createIndividual(NS_Hurricane + csvRow[0], hurricaneClass);
            hurricane.addProperty(hasHurricaneID, csvRow[0]);
            hurricane.addProperty(hasHurricaneName, csvRow[1]);
            hurricane.addProperty(hasStartTime, model.createTypedLiteral(formatToXSDDateTime(csvRow[2]), XSDDatatype.XSDdateTime));
            hurricane.addProperty(hasEndTime, "Unknown");
            hurricane.addProperty(hasArrivalTime, model.createTypedLiteral(formatToXSDDateTime(csvRow[4]), XSDDatatype.XSDdateTime));
            hurricane.addProperty(hasMaxWindSpeed, model.createTypedLiteral(csvRow[7], XSDDatatype.XSDdouble));
            hurricane.addProperty(hasWindSpeed, model.createTypedLiteral(csvRow[5], XSDDatatype.XSDdouble));
            hurricane.addProperty(hasWindDirection, model.createTypedLiteral(csvRow[6], XSDDatatype.XSDdouble));
            hurricane.addProperty(hasRecordTime, model.createTypedLiteral(formatToXSDDateTime(csvRow[2]), XSDDatatype.XSDdateTime));
            hurricane.addProperty(hasDistance, model.createTypedLiteral(csvRow[3], XSDDatatype.XSDdouble));
            hurricane.addProperty(hasCategory, csvRow[8]);
            hurricane.addProperty(hasPressure, model.createTypedLiteral(csvRow[9], XSDDatatype.XSDdouble));
            hurricane.addProperty(hasWindSpeedChangeRate, model.createTypedLiteral(csvRow[10], XSDDatatype.XSDdouble));
            hurricane.addProperty(hasWindDirectionChangeRate, model.createTypedLiteral(csvRow[11], XSDDatatype.XSDdouble));
            hurricane.addProperty(hasDurationAboveCutOut, model.createTypedLiteral(csvRow[12], XSDDatatype.XSDdouble));
            hurricane.addProperty(hasQuadrant, csvRow[13]);
            isFirstRowProcessed = true;
        }
    }

    private String formatToXSDDateTime(String csvDateTime) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(csvDateTime, CSV_FORMAT);
        return parsedDateTime.format(XSD_FORMAT);
    }
}