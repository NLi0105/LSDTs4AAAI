package SemanticModelSpecs.MSP;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OntoSpec_SeaDepth {
    private static final String NS = "http://windfarm/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_SeaDepth = NS_MSP + "SeaDepth#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        OntClass SeaDepth = m.createClass(NS_SeaDepth);

        DatatypeProperty hasDepthRange = m.createDatatypeProperty(NS_SeaDepth + "hasDepthRange");
        hasDepthRange.addDomain(SeaDepth);
        hasDepthRange.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasMinDepth = m.createDatatypeProperty(NS_SeaDepth + "hasMinDepth");
        hasMinDepth.addDomain(SeaDepth);
        hasMinDepth.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasMaxDepth = m.createDatatypeProperty(NS_SeaDepth + "hasMaxDepth");
        hasMaxDepth.addDomain(SeaDepth);
        hasMaxDepth.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasAvgDepth = m.createDatatypeProperty(NS_SeaDepth + "hasAvgDepth");
        hasAvgDepth.addDomain(SeaDepth);
        hasAvgDepth.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasArea = m.createDatatypeProperty(NS_SeaDepth + "hasArea");
        hasArea.addDomain(SeaDepth);
        hasArea.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasSCG = m.createDatatypeProperty(NS_SeaDepth + "hasSCG");
        hasSCG.addDomain(SeaDepth);
        hasSCG.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_SeaDepth + "hasGeometry");
        hasGeometry.addDomain(SeaDepth);
        hasGeometry.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("project_folder/Wind/src/main/java/SemanticModels/MSP/SeaDepth.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
