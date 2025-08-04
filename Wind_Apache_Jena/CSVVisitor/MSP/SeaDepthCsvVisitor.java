package CSVVisitor.MSP;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;

public class SeaDepthCsvVisitor {
    private static final String NS = "http://windfarm/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_SeaDepth = NS_MSP + "SeaDepth#";

    private OntModel model;
    private OntClass seaDepthClass;
    private DatatypeProperty hasDepthRange;
    private DatatypeProperty hasMinDepth;
    private DatatypeProperty hasMaxDepth;
    private DatatypeProperty hasAvgDepth;
    private DatatypeProperty hasArea;
    private DatatypeProperty hasSCG;
    private DatatypeProperty hasGeometry;

    public SeaDepthCsvVisitor(OntModel model) {
        this.model = model;
        seaDepthClass = model.getOntClass(NS_SeaDepth + "SeaDepth");
        hasDepthRange = model.getDatatypeProperty(NS_SeaDepth + "hasDepthRange");
        hasMinDepth = model.getDatatypeProperty(NS_SeaDepth + "hasMinDepth");
        hasMaxDepth = model.getDatatypeProperty(NS_SeaDepth + "hasMaxDepth");
        hasAvgDepth = model.getDatatypeProperty(NS_SeaDepth + "hasAvgDepth");
        hasArea = model.getDatatypeProperty(NS_SeaDepth + "hasArea");
        hasSCG = model.getDatatypeProperty(NS_SeaDepth + "hasSCG");
        hasGeometry = model.getDatatypeProperty(NS_SeaDepth + "hasGeometry");
    }

    public void visit(String[] csvRow) {
        String depthRangeId = csvRow[0].replaceAll("[\\[\\]\\s]", "").replace(",", "-");
        String individualURI = NS_SeaDepth + depthRangeId;
        Individual seaDepth = model.createIndividual(individualURI, seaDepthClass);
        seaDepth.addProperty(hasDepthRange, csvRow[0]);
        seaDepth.addProperty(hasMinDepth, model.createTypedLiteral(csvRow[1], XSDDatatype.XSDdouble));
        seaDepth.addProperty(hasMaxDepth, model.createTypedLiteral(csvRow[2], XSDDatatype.XSDdouble));
        seaDepth.addProperty(hasAvgDepth, model.createTypedLiteral(csvRow[3], XSDDatatype.XSDdouble));
        seaDepth.addProperty(hasArea, model.createTypedLiteral(csvRow[4], XSDDatatype.XSDdouble));
        seaDepth.addProperty(hasSCG, csvRow[5]);
        seaDepth.addProperty(hasGeometry, csvRow[6]);
    }
}