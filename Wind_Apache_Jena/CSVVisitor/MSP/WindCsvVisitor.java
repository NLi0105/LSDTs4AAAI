package CSVVisitor.MSP;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ResourceFactory;

public class WindCsvVisitor {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_WindSpeed = NS_MSP + "WindSpeed#";

    private OntModel model;
    private OntClass windSpeedClass;
    private DatatypeProperty hasWindID;
    private DatatypeProperty hasMinWindSpeed;
    private DatatypeProperty hasMaxWindSpeed;
    private DatatypeProperty hasGeometry;

    public WindCsvVisitor(OntModel model) {
        this.model = model;
        windSpeedClass = model.getOntClass(NS_WindSpeed + "WindSpeed");
        hasWindID = model.getDatatypeProperty(NS_WindSpeed + "hasWindID");
        hasMinWindSpeed = model.getDatatypeProperty(NS_WindSpeed + "hasMinWindSpeed");
        hasMaxWindSpeed = model.getDatatypeProperty(NS_WindSpeed + "hasMaxWindSpeed");
        hasGeometry = model.getDatatypeProperty(NS_WindSpeed + "hasGeometry");
    }

    public void visit(String[] csvRow) {
        String individualURI = NS_WindSpeed + csvRow[0];
        Individual windSpeed = model.createIndividual(individualURI, windSpeedClass);
        windSpeed.addProperty(hasWindID, csvRow[0]);
        windSpeed.addProperty(hasMinWindSpeed, ResourceFactory.createTypedLiteral(csvRow[1], XSDDatatype.XSDdouble));
        windSpeed.addProperty(hasMaxWindSpeed, ResourceFactory.createTypedLiteral(csvRow[2], XSDDatatype.XSDdouble));
        windSpeed.addProperty(hasGeometry, csvRow[3]);
    }
}