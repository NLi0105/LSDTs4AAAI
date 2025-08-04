package CSVVisitor.MSP;

import org.apache.jena.ontology.*;

public class CoralCsvVisitor {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_Coral = NS_MSP + "Coral#";

    private OntModel model;
    private OntClass coralClass;
    private DatatypeProperty hasCoralID;
    private DatatypeProperty hasZone;
    private DatatypeProperty hasGeometry;

    public CoralCsvVisitor(OntModel model) {
        this.model = model;
        coralClass = model.getOntClass(NS_Coral + "Coral");
        hasCoralID = model.getDatatypeProperty(NS_Coral + "hasCoralID");
        hasZone = model.getDatatypeProperty(NS_Coral + "hasZone");
        hasGeometry = model.getDatatypeProperty(NS_Coral + "hasGeometry");
    }

    public void visit(String[] csvRow) {
        Individual coral = model.createIndividual(NS_Coral + csvRow[0], coralClass);
        coral.addProperty(hasCoralID, csvRow[0]);
        coral.addProperty(hasZone, csvRow[1]);
        coral.addProperty(hasGeometry, csvRow[2]);
    }
}
