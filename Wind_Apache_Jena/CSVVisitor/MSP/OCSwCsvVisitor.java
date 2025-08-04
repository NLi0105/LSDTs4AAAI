package CSVVisitor.MSP;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;

public class OCSwCsvVisitor {
    private static final String NS = "http://windfarm/MSP/OCSw#";
    private OntModel model;
    private OntClass ocswClass;
    private DatatypeProperty hasOCSID;
    private DatatypeProperty hasOCSName;
    private DatatypeProperty hasLeaseType;
    private DatatypeProperty hasAgency;
    private DatatypeProperty hasDateEstablished;
    private DatatypeProperty hasArea;
    private DatatypeProperty hasGeometry;

    public OCSwCsvVisitor(OntModel model) {
        this.model = model;
        ocswClass = model.getOntClass(NS + "OCSw");
        hasOCSID = model.getDatatypeProperty(NS + "hasOCSID");
        hasOCSName = model.getDatatypeProperty(NS + "hasOCSName");
        hasLeaseType = model.getDatatypeProperty(NS + "hasLeaseType");
        hasAgency = model.getDatatypeProperty(NS + "hasAgency");
        hasDateEstablished = model.getDatatypeProperty(NS + "hasDateEstablished");
        hasArea = model.getDatatypeProperty(NS + "hasArea");
        hasGeometry = model.getDatatypeProperty(NS + "hasGeometry");
    }

    public void visit(String[] csvRow) {
        Individual ocsw = model.createIndividual(NS + csvRow[1], ocswClass);
        ocsw.addProperty(hasOCSName, csvRow[0]);
        ocsw.addProperty(hasOCSID, csvRow[1]);
        ocsw.addProperty(hasLeaseType, csvRow[2]);
        ocsw.addProperty(hasAgency, csvRow[3]);
        ocsw.addProperty(hasDateEstablished, model.createTypedLiteral(csvRow[4], XSDDatatype.XSDdate));
        ocsw.addProperty(hasArea, model.createTypedLiteral(csvRow[5], XSDDatatype.XSDfloat));
        ocsw.addProperty(hasGeometry, csvRow[6]);
    }
}