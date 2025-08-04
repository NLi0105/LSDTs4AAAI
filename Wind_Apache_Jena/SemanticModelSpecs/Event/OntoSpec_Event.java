package SemanticModelSpecs.Event;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import javax.xml.crypto.Data;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OntoSpec_Event {
    private static final String NS = "http://windfarm/";
    private static final String NS_EVENT = NS + "Event/";
    private static final String NS_WeatherEvent = NS_EVENT + "WeatherEvent/";
    private static final String NS_Hurricane = NS_WeatherEvent + "Hurricane#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        // Define the Event class
        OntClass Event = m.createClass(NS_EVENT);

        // Define subclasses of Event
        OntClass OperationalEvent = m.createClass(NS_EVENT + "OperationalEvent");
        OperationalEvent.addSuperClass(Event);

        OntClass MaintenanceEvent = m.createClass(NS_EVENT + "MaintenanceEvent");
        MaintenanceEvent.addSuperClass(Event);

        OntClass FailureEvent = m.createClass(NS_EVENT + "FailureEvent");
        FailureEvent.addSuperClass(Event);

        OntClass WeatherEvent = m.createClass(NS_WeatherEvent);
        WeatherEvent.addSuperClass(Event);

        OntClass ExternalEvent = m.createClass(NS_EVENT + "ExternalEvent");
        ExternalEvent.addSuperClass(Event);

        // =========================== Adding attributes to the superclass Event ===========================

        // Core Attributes
//        DatatypeProperty hasEventID = m.createDatatypeProperty(NS_EVENT + "hasEventID");
//        hasEventID.addDomain(Event);
//        hasEventID.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
        DatatypeProperty hasEventType = m.createDatatypeProperty(NS_EVENT + "hasEventType");
        hasEventType.addDomain(Event);
        hasEventType.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasStartTime = m.createDatatypeProperty(NS_EVENT + "hasStartTime");
        hasStartTime.addDomain(Event);
        hasStartTime.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#dateTime"));

        DatatypeProperty hasEndTime = m.createDatatypeProperty(NS_EVENT + "hasEndTime");
        hasEndTime.addDomain(Event);
        hasEndTime.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#dateTime"));

//        DatatypeProperty hasStatus = m.createDatatypeProperty(NS_EVENT + "hasStatus");
//        hasStatus.addDomain(Event);
//        hasStatus.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
//        DatatypeProperty hasTimeStamp = m.createDatatypeProperty(NS_EVENT + "hasTimeStamp");
//        hasTimeStamp.addDomain(Event);
//        hasTimeStamp.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#dateTime"));

//        DatatypeProperty hasLocation = m.createDatatypeProperty(NS_EVENT + "hasLocation");
//        hasLocation.addDomain(Event);
//        hasLocation.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
//        DatatypeProperty hasDuration = m.createDatatypeProperty(NS_EVENT + "hasDuration");
//        hasDuration.addDomain(Event);
//        hasDuration.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#duration"));

//        // Causality & Detection (Essential for Simulation)
//        DatatypeProperty hasTrigger = m.createDatatypeProperty(NS_EVENT + "hasTrigger");
//        hasTrigger.addDomain(Event);
//        hasTrigger.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
//        DatatypeProperty hasCause = m.createDatatypeProperty(NS_EVENT + "hasCause");
//        hasCause.addDomain(Event);
//        hasCause.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
//        DatatypeProperty hasDetection = m.createDatatypeProperty(NS_EVENT + "hasDetection");
//        hasDetection.addDomain(Event);
//        hasDetection.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
//        DatatypeProperty hasCertainty = m.createDatatypeProperty(NS_EVENT + "hasCertainty");
//        hasCertainty.addDomain(Event);
//        hasCertainty.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
//        // Impact & Severity (Crucial for Risk Assessment & Response Planning):
//        DatatypeProperty hasSeverity = m.createDatatypeProperty(NS_EVENT + "hasSeverity");
//        hasSeverity.addDomain(Event);
//        hasSeverity.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
//        DatatypeProperty haasAffectedAsset = m.createDatatypeProperty(NS_EVENT + "hasAffectedAssets");
//        haasAffectedAsset.addDomain(Event);
//        haasAffectedAsset.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
//        DatatypeProperty hasImpact = m.createDatatypeProperty(NS_EVENT + "hasImpact");
//        hasImpact.addDomain(Event);
//        hasImpact.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
//        //Response & Mitigation (Necessary for Event Simulation & Digital Twin Predictions):
//        DatatypeProperty hasResponse = m.createDatatypeProperty(NS_EVENT + "hasResponse");
//        hasResponse.addDomain(Event);
//        hasResponse.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
//        DatatypeProperty hasResolutionTime = m.createDatatypeProperty(NS_EVENT + "hasResolutionTime");
//        hasResolutionTime.addDomain(Event);
//        hasResolutionTime.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#duration"));
//
//        DatatypeProperty hasRestoration = m.createDatatypeProperty(NS_EVENT + "hasRestoration");
//        hasRestoration.addDomain(Event);
//        hasRestoration.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
//        // Relationships & Dependencies (Essential for Predictive Analytics & Future Simulations):
//        DatatypeProperty hasPrecedingEvent = m.createDatatypeProperty(NS_EVENT + "hasPrecedingEvent");
//        hasPrecedingEvent.addDomain(Event);
//        hasPrecedingEvent.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
//        DatatypeProperty hasFollowingEvent = m.createDatatypeProperty(NS_EVENT + "hasFollowingEvent");
//        hasFollowingEvent.addDomain(Event);
//        hasFollowingEvent.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));
//
//        DatatypeProperty hasCorrelation = m.createDatatypeProperty(NS_EVENT + "hasCorrelation");
//        hasCorrelation.addDomain(Event);
//        hasCorrelation.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        // Define subclasses of WeatherEvent
        OntClass Hurricane = m.createClass(NS_Hurricane);
        Hurricane.addSuperClass(WeatherEvent);

        // Define attributes for HurricaneEvent
        DatatypeProperty hasHurricaneID = m.createDatatypeProperty(NS_Hurricane + "hasHurricaneID");
        hasHurricaneID.addDomain(Hurricane);
        hasHurricaneID.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasHurricaneName = m.createDatatypeProperty(NS_Hurricane + "hasHurricaneName");
        hasHurricaneName.addDomain(Hurricane);
        hasHurricaneName.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasRecordTime = m.createDatatypeProperty(NS_Hurricane + "hasRecordTime");
        hasRecordTime.addDomain(Hurricane);
        hasRecordTime.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#dateTime"));

        DatatypeProperty hasDistance = m.createDatatypeProperty(NS_Hurricane + "hasDistance");
        hasDistance.addDomain(Hurricane);
        hasDistance.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasArrivalTime = m.createDatatypeProperty(NS_Hurricane + "hasArrivalTime");
        hasArrivalTime.addDomain(Hurricane);
        hasArrivalTime.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#dateTime"));

        DatatypeProperty hasWindSpeed = m.createDatatypeProperty(NS_Hurricane + "hasWindSpeed");
        hasWindSpeed.addDomain(Hurricane);
        hasWindSpeed.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasWindDirection = m.createDatatypeProperty(NS_Hurricane + "hasWindDirection");
        hasWindDirection.addDomain(Hurricane);
        hasWindDirection.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasMaxWindSpeed = m.createDatatypeProperty(NS_Hurricane + "hasMaxWindSpeed");
        hasMaxWindSpeed.addDomain(Hurricane);
        hasMaxWindSpeed.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasCategory = m.createDatatypeProperty(NS_Hurricane + "hasCategory");
        hasCategory.addDomain(Hurricane);
        hasCategory.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasPressure = m.createDatatypeProperty(NS_Hurricane + "hasPressure");
        hasPressure.addDomain(Hurricane);
        hasPressure.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasWindSpeedChangeRate = m.createDatatypeProperty(NS_Hurricane + "hasWindSpeedChangeRate");
        hasWindSpeedChangeRate.addDomain(Hurricane);
        hasWindSpeedChangeRate.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasWindDirectionChangeRate = m.createDatatypeProperty(NS_Hurricane + "hasWindDirectionChangeRate");
        hasWindDirectionChangeRate.addDomain(Hurricane);
        hasWindDirectionChangeRate.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasDurationAboveCutOut = m.createDatatypeProperty(NS_Hurricane + "hasDurationAboveCutOut");
        hasDurationAboveCutOut.addDomain(Hurricane);
        hasDurationAboveCutOut.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasQuadrant = m.createDatatypeProperty(NS_Hurricane + "hasQuadrant");
        hasQuadrant.addDomain(Hurricane);
        hasQuadrant.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        // Save the ontology to a file
        try (OutputStream out = new FileOutputStream("project_folder/Wind/src/main/java/SemanticModels/Event.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}