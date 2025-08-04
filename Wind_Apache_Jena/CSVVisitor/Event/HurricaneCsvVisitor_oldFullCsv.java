package CSVVisitor.Event;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HurricaneCsvVisitor_oldFullCsv {
    private static final String NS = "windfarm/";
    private static final String NS_EVENT = NS + "Event/";
    private static final String NS_WeatherEvent = NS_EVENT + "WeatherEvent/";
    private static final String NS_Hurricane = NS_WeatherEvent + "Hurricane#";

    private static final DateTimeFormatter CSV_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter XSD_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private OntModel model;
    private OntClass hurricaneClass;
    private DatatypeProperty hasHurricaneID;
    private DatatypeProperty hasHurricaneName;
    private DatatypeProperty hasStartTime;
    private DatatypeProperty hasEndTime;
//    private DatatypeProperty hasStatus;
    private DatatypeProperty hasArrivalTime;
    private DatatypeProperty hasMaxWindSpeed;
    private DatatypeProperty hasWindDirection;

    public HurricaneCsvVisitor_oldFullCsv(OntModel model) {
        this.model = model;
        hurricaneClass = model.getOntClass(NS_Hurricane + "Hurricane");
        hasHurricaneID = model.getDatatypeProperty(NS_Hurricane + "hasHurricaneID");
        hasHurricaneName = model.getDatatypeProperty(NS_Hurricane + "hasHurricaneName");
        hasStartTime = model.getDatatypeProperty(NS_EVENT + "hasStartTime");
        hasEndTime = model.getDatatypeProperty(NS_EVENT + "hasEndTime");
//        hasStatus = model.getDatatypeProperty(NS_EVENT + "hasStatus");
        hasArrivalTime = model.getDatatypeProperty(NS_Hurricane + "hasArrivalTime");
        hasMaxWindSpeed = model.getDatatypeProperty(NS_Hurricane + "hasMaxWindSpeed");
        hasWindDirection = model.getDatatypeProperty(NS_Hurricane + "hasWindDirection");
    }

    public void visit(String[] csvRow) {
        Individual hurricane = model.createIndividual(NS_Hurricane + csvRow[0], hurricaneClass);
        hurricane.addProperty(hasHurricaneID, csvRow[0]);
        hurricane.addProperty(hasHurricaneName, csvRow[1]);
        hurricane.addProperty(hasStartTime, model.createTypedLiteral(formatToXSDDateTime(csvRow[2]), XSDDatatype.XSDdateTime));
        hurricane.addProperty(hasEndTime, model.createTypedLiteral(formatToXSDDateTime(csvRow[3]), XSDDatatype.XSDdateTime));
//        hurricane.addProperty(hasStatus, model.createTypedLiteral("Not Active", XSDDatatype.XSDstring));
        hurricane.addProperty(hasArrivalTime, model.createTypedLiteral(formatToXSDDateTime(csvRow[4]), XSDDatatype.XSDdateTime));
        hurricane.addProperty(hasMaxWindSpeed, model.createTypedLiteral(csvRow[5], XSDDatatype.XSDdouble));
        hurricane.addProperty(hasWindDirection, model.createTypedLiteral(csvRow[6], XSDDatatype.XSDdouble));
    }

    private String formatToXSDDateTime(String csvDateTime) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(csvDateTime, CSV_FORMAT);
        return parsedDateTime.format(XSD_FORMAT);
    }
}