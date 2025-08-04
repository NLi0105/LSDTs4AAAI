package CSVVisitor.MSP;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;

public class ECCCsvVisitor {
    private static final String NS = "http://windfarm/MSP/ECC#";
    private OntModel model;
    private OntClass eccClass;
    private DatatypeProperty hasLeaseID;
    private DatatypeProperty hasProjectName;
    private DatatypeProperty hasDeveloper;
    private DatatypeProperty hasArea;
    private DatatypeProperty hasGeometry;

    public ECCCsvVisitor(OntModel model) {
        this.model = model;
        eccClass = model.getOntClass(NS + "ECC");
        hasLeaseID = model.getDatatypeProperty(NS + "hasLeaseID");
        hasProjectName = model.getDatatypeProperty(NS + "hasProjectName");
        hasDeveloper = model.getDatatypeProperty(NS + "hasDeveloper");
        hasArea = model.getDatatypeProperty(NS + "hasArea");
        hasGeometry = model.getDatatypeProperty(NS + "hasGeometry");
    }

    public void visit(String[] csvRow) {
        Individual ecc = model.createIndividual(NS + csvRow[0], eccClass);
        ecc.addProperty(hasLeaseID, csvRow[0]);
        ecc.addProperty(hasProjectName, csvRow[1]);
        ecc.addProperty(hasDeveloper, csvRow[2]);
        ecc.addProperty(hasArea, model.createTypedLiteral(csvRow[3], XSDDatatype.XSDfloat));
        ecc.addProperty(hasGeometry, csvRow[4]);
    }
}