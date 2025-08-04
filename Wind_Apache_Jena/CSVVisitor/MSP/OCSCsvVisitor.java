package CSVVisitor.MSP;

import org.apache.jena.ontology.*;

public class OCSCsvVisitor {
    private static final String NS = "http://windfarm/MSP/OCS#";
    private OntModel model;
    private OntClass ocsClass;
    private DatatypeProperty hasOCSID;
    private DatatypeProperty hasAreaName;
    private DatatypeProperty hasRegion;
    private DatatypeProperty hasGeometry;

    public OCSCsvVisitor(OntModel model) {
        this.model = model;
        ocsClass = model.getOntClass(NS + "OCS");
        hasOCSID = model.getDatatypeProperty(NS + "hasOCSID");
        hasAreaName = model.getDatatypeProperty(NS + "hasAreaName");
        hasRegion = model.getDatatypeProperty(NS + "hasRegion");
        hasGeometry = model.getDatatypeProperty(NS + "hasGeometry");
    }

    public void visit(String[] csvRow) {
        Individual ocs = model.createIndividual(NS + csvRow[0], ocsClass);
        ocs.addProperty(hasOCSID, csvRow[0]);
        ocs.addProperty(hasAreaName, csvRow[1]);
        ocs.addProperty(hasRegion, csvRow[2]);
        ocs.addProperty(hasGeometry, csvRow[3]);
    }
}