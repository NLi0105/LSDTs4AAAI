package CSVVisitor.MSP;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;

public class NOAACsvVisitor {
    private static final String NS = "http://www.cee.umd.edu/Energy/MSP/NOAA#";
    private OntModel model;
    private OntClass noaaClass;
    private DatatypeProperty hasSiteID;
    private DatatypeProperty hasSiteName;
    private DatatypeProperty hasState;
    private DatatypeProperty hasMgmtAgency;
    private DatatypeProperty hasConsFocus;
    private DatatypeProperty hasPermanence;
    private DatatypeProperty hasYearEstablished;
    private DatatypeProperty hasGeometry;

    public NOAACsvVisitor(OntModel model) {
        this.model = model;
        noaaClass = model.getOntClass(NS + "NOAA");
        hasSiteID = model.getDatatypeProperty(NS + "hasSiteID");
        hasSiteName = model.getDatatypeProperty(NS + "hasSiteName");
        hasState = model.getDatatypeProperty(NS + "hasState");
        hasMgmtAgency = model.getDatatypeProperty(NS + "hasMgmtAgency");
        hasConsFocus = model.getDatatypeProperty(NS + "hasConsFocus");
        hasPermanence = model.getDatatypeProperty(NS + "hasPermanence");
        hasYearEstablished = model.getDatatypeProperty(NS + "hasYearEstablished");
        hasGeometry = model.getDatatypeProperty(NS + "hasGeometry");
    }

    public void visit(String[] csvRow) {
        Individual noaa = model.createIndividual(NS + csvRow[0], noaaClass);
        noaa.addProperty(hasSiteID, csvRow[0]);
        noaa.addProperty(hasSiteName, csvRow[1]);
        noaa.addProperty(hasState, csvRow[2]);
        noaa.addProperty(hasMgmtAgency, csvRow[3]);
        noaa.addProperty(hasConsFocus, csvRow[4]);
        noaa.addProperty(hasPermanence, csvRow[5]);
        noaa.addProperty(hasYearEstablished, model.createTypedLiteral(csvRow[6], XSDDatatype.XSDdateTime));
        noaa.addProperty(hasGeometry, csvRow[7]);
    }
}