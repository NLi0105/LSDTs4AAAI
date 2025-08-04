package Listener;

import org.apache.jena.rdf.listeners.StatementListener;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

public class EventListener_backup extends StatementListener {

    private static final String NS = "windfarm/";
    private static final String NS_EVENT = NS + "Event/";
    private static final String NS_TIME = NS + "Time#";

    private final Model model;
    private final Property hasStartTime;
    private final Property hasEndTime;
    private final Property hasCurrentTime;
    private final Property hasStatus;

    // Track resources we've already processed
    private final Set<String> processedEvents = new HashSet<>();

    public EventListener_backup(Model model) {
        this.model = model;
        this.hasStartTime = model.getProperty(NS_EVENT + "hasStartTime");
        this.hasEndTime = model.getProperty(NS_EVENT + "hasEndTime");
        this.hasCurrentTime = model.getProperty(NS_TIME + "hasCurrentTime");
        this.hasStatus = model.getProperty(NS_EVENT + "hasStatus");
    }

    @Override
    public void addedStatement(Statement statement) {
        // Check if the statement adds a start time to an event
        if (statement.getPredicate().equals(hasStartTime) ||
            statement.getPredicate().equals(hasEndTime) ||
            statement.getPredicate().equals(RDF.type)) {

            Resource subject = statement.getSubject();

            // Skip if not a URI resource or already processed
            if (!subject.isURIResource() || processedEvents.contains(subject.getURI())) {
                return;
            }

            // Process only if this is an event resource with start/end time
            if (subject.getURI().contains(NS_EVENT) &&
                subject.hasProperty(hasStartTime) &&
                subject.hasProperty(hasEndTime)) {

                // Process and print the full event information
                processAndPrintEvent(subject);
                processedEvents.add(subject.getURI());
            }
        }
    }

    private void processAndPrintEvent(Resource eventResource) {
        // Get current time
        String currentTime = "Unknown";
        Resource timeResource = model.getResource(NS_TIME + "currentTime");
        if (timeResource != null) {
            Statement timeStmt = timeResource.getProperty(hasCurrentTime);
            if (timeStmt != null && timeStmt.getObject().isLiteral()) {
                currentTime = timeStmt.getObject().asLiteral().getLexicalForm();
            }
        }

        // Get event types
        StringBuilder types = new StringBuilder();
        StmtIterator typeIter = eventResource.listProperties(RDF.type);
        while (typeIter.hasNext()) {
            Statement typeStmt = typeIter.next();
            Resource typeResource = typeStmt.getObject().asResource();
            if (typeResource.getURI() != null &&
                typeResource.getURI().contains(NS_EVENT) &&
                !typeResource.getURI().equals(NS_EVENT)) {
                if (types.length() > 0) types.append(", ");
                types.append(typeResource.getURI());
            }
        }

        // Get start time
        String startTime = "Unknown";
        Statement startStmt = eventResource.getProperty(hasStartTime);
        if (startStmt != null && startStmt.getObject().isLiteral()) {
            startTime = startStmt.getObject().asLiteral().getLexicalForm();
        }

        // Get end time
        String endTime = "Unknown";
        Statement endStmt = eventResource.getProperty(hasEndTime);
        if (endStmt != null && endStmt.getObject().isLiteral()) {
            endTime = endStmt.getObject().asLiteral().getLexicalForm();
        }

        // Check if the event is active based on current time
        boolean isActive = isEventActive(currentTime, startTime, endTime);

        // Update the event status
        updateEventStatus(eventResource, isActive);

        // Get the current status after update
        String status = "Not Active"; // Default
        Statement statusStmt = eventResource.getProperty(hasStatus);
        if (statusStmt != null && statusStmt.getObject().isLiteral()) {
            status = statusStmt.getObject().asLiteral().getLexicalForm();
        }

        // Print the complete event information in the exact format requested
        System.out.println("===================== EVENT DETECTED =====================");
        System.out.println("Event URI: " + eventResource.getURI());
        System.out.println("Event Name: " + getLocalName(eventResource.getURI()));
        System.out.println("Event Type: " + types);
        System.out.println("Current Time: " + currentTime);
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Status: " + status);
        System.out.println("=========================================================");
    }

    private void updateEventStatus(Resource eventResource, boolean isActive) {
        // Remove any existing status statements first
        StmtIterator statusStmts = eventResource.listProperties(hasStatus);
        model.remove(statusStmts);

        // Add the new status
        String status = isActive ? "Active" : "Not Active";
        eventResource.addProperty(hasStatus, status);
    }

    private boolean isEventActive(String currentTime, String startTime, String endTime) {
        if ("Unknown".equals(currentTime) || "Unknown".equals(startTime) || "Unknown".equals(endTime)) {
            return false;
        }

        try {
            LocalDateTime currentDateTime = parseDateTime(currentTime);
            LocalDateTime startDateTime = parseDateTime(startTime);
            LocalDateTime endDateTime = parseDateTime(endTime);

            return !currentDateTime.isBefore(startDateTime) && !currentDateTime.isAfter(endDateTime);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date time: " + e.getMessage());
            return false;
        }
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        // Handle ISO-8601 format with 'Z' timezone designator
        if (dateTimeStr.endsWith("Z")) {
            dateTimeStr = dateTimeStr.substring(0, dateTimeStr.length() - 1);
        }
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME);
    }

    private String getLocalName(String uri) {
        int hashPos = uri.lastIndexOf('#');
        if (hashPos > 0 && hashPos < uri.length() - 1) {
            return uri.substring(hashPos + 1);
        }

        int slashPos = uri.lastIndexOf('/');
        if (slashPos > 0 && slashPos < uri.length() - 1) {
            return uri.substring(slashPos + 1);
        }

        return uri;
    }
}