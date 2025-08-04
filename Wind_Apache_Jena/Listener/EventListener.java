package Listener;

import org.apache.jena.rdf.listeners.StatementListener;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

public class EventListener extends StatementListener {

    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_EVENT = NS + "Event/";
    private static final String NS_TIME = NS + "Time#";

    private final Model model;
    private final Property hasStartTime;
    private final Property hasCurrentTime;
    private final Property hasStatus;

    // Track resources we've already processed
    private final Set<String> processedEvents = new HashSet<>();

    public EventListener(Model model) {
        this.model = model;
        this.hasStartTime = model.getProperty(NS_EVENT + "hasStartTime");
        this.hasCurrentTime = model.getProperty(NS_TIME + "hasCurrentTime");
        this.hasStatus = model.getProperty(NS_EVENT + "hasStatus");
    }

    @Override
    public void addedStatement(Statement statement) {
        if (statement.getPredicate().equals(hasStartTime) || statement.getPredicate().equals(RDF.type)) {
            Resource subject = statement.getSubject();

            if (!subject.isURIResource() || processedEvents.contains(subject.getURI())) {
                return;
            }

            if (subject.getURI().contains(NS_EVENT) && subject.hasProperty(hasStartTime)) {
                processAndPrintEvent(subject);
                processedEvents.add(subject.getURI());
            }
        }
    }

    private void processAndPrintEvent(Resource eventResource) {
        String currentTime = "Unknown";
        Resource timeResource = model.getResource(NS_TIME + "currentTime");
        if (timeResource != null) {
            Statement timeStmt = timeResource.getProperty(hasCurrentTime);
            if (timeStmt != null && timeStmt.getObject().isLiteral()) {
                currentTime = timeStmt.getObject().asLiteral().getLexicalForm();
            }
        }

        String startTime = "Unknown";
        Statement startStmt = eventResource.getProperty(hasStartTime);
        if (startStmt != null && startStmt.getObject().isLiteral()) {
            startTime = startStmt.getObject().asLiteral().getLexicalForm();
        }

        boolean isActive = isEventActive(currentTime, startTime);
        updateEventStatus(eventResource, isActive);

        String status = "Not Active";
        Statement statusStmt = eventResource.getProperty(hasStatus);
        if (statusStmt != null && statusStmt.getObject().isLiteral()) {
            status = statusStmt.getObject().asLiteral().getLexicalForm();
        }

        System.out.println("===================== EVENT DETECTED =====================");
        System.out.println("Event URI: " + eventResource.getURI());
        System.out.println("Event Name: " + getLocalName(eventResource.getURI()));
        System.out.println("Current Time: " + currentTime);
        System.out.println("Start Time: " + startTime);
        System.out.println("Status: " + status);
        System.out.println("=========================================================");
    }

    private void updateEventStatus(Resource eventResource, boolean isActive) {
        StmtIterator statusStmts = eventResource.listProperties(hasStatus);
        model.remove(statusStmts);

        String status = isActive ? "Active" : "Not Active";
        eventResource.addProperty(hasStatus, status);
    }

    private boolean isEventActive(String currentTime, String startTime) {
        if ("Unknown".equals(currentTime) || "Unknown".equals(startTime)) {
            return false;
        }

        try {
            LocalDateTime currentDateTime = parseDateTime(currentTime);
            LocalDateTime startDateTime = parseDateTime(startTime);

            return !currentDateTime.isBefore(startDateTime);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date time: " + e.getMessage());
            return false;
        }
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
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