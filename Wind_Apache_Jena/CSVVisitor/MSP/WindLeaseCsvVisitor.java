package CSVVisitor.MSP;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;

public class WindLeaseCsvVisitor {
    private static final String NS = "http://windfarm/MSP/WindLease#";
    private OntModel model;
    private OntClass windLeaseClass;
    private DatatypeProperty hasLeaseID;
    private DatatypeProperty hasLeaseType;
    private DatatypeProperty hasResource;
    private DatatypeProperty hasCompany;
    private DatatypeProperty hasLeaseNumber;
    private DatatypeProperty hasLeaseDate;
    private DatatypeProperty hasLeaseTerm;
    private DatatypeProperty hasState;
    private DatatypeProperty hasLocation;
    private DatatypeProperty hasLeaseDoc;
    private DatatypeProperty hasLeaseDocUrl;
    private DatatypeProperty hasGeometry;

    public WindLeaseCsvVisitor(OntModel model) {
        this.model = model;
        windLeaseClass = model.getOntClass(NS + "WindLease");
        hasLeaseID = model.getDatatypeProperty(NS + "hasLeaseID");
        hasLeaseType = model.getDatatypeProperty(NS + "hasLeaseType");
        hasResource = model.getDatatypeProperty(NS + "hasResource");
        hasCompany = model.getDatatypeProperty(NS + "hasCompany");
        hasLeaseNumber = model.getDatatypeProperty(NS + "hasLeaseNumber");
        hasLeaseDate = model.getDatatypeProperty(NS + "hasLeaseDate");
        hasLeaseTerm = model.getDatatypeProperty(NS + "hasLeaseTerm");
        hasState = model.getDatatypeProperty(NS + "hasState");
        hasLocation = model.getDatatypeProperty(NS + "hasLocation");
        hasLeaseDoc = model.getDatatypeProperty(NS + "hasLeaseDoc");
        hasLeaseDocUrl = model.getDatatypeProperty(NS + "hasLeaseDocUrl");
        hasGeometry = model.getDatatypeProperty(NS + "hasGeometry");
    }

    public void visit(String[] csvRow) {
        Individual windLease = model.createIndividual(NS + csvRow[0], windLeaseClass);
        windLease.addProperty(hasLeaseID, csvRow[0]);
        windLease.addProperty(hasLeaseType, csvRow[1]);
        windLease.addProperty(hasResource, csvRow[2]);
        windLease.addProperty(hasCompany, csvRow[3]);
        windLease.addProperty(hasLeaseNumber, csvRow[4]);
        windLease.addProperty(hasLeaseDate, model.createTypedLiteral(csvRow[5], XSDDatatype.XSDdate));
        windLease.addProperty(hasLeaseTerm, model.createTypedLiteral(csvRow[6], XSDDatatype.XSDinteger));
        windLease.addProperty(hasState, csvRow[7]);
        windLease.addProperty(hasLocation, csvRow[8]);
        windLease.addProperty(hasLeaseDoc, csvRow[9]);
        windLease.addProperty(hasLeaseDocUrl, csvRow[10]);
        windLease.addProperty(hasGeometry, csvRow[11]);
    }
}