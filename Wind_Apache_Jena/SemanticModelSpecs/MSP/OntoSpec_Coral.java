package SemanticModelSpecs.MSP;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import javax.xml.crypto.Data;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OntoSpec_Coral {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_Coral = NS_MSP + "Coral#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        OntClass Coral = m.createClass(NS_Coral);

        DatatypeProperty hasCoralID = m.createDatatypeProperty(NS_Coral + "hasCoralID");
        hasCoralID.addDomain(Coral);
        hasCoralID.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasZone = m.createDatatypeProperty(NS_Coral + "hasZone");
        hasZone.addDomain(Coral);
        hasZone.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_Coral + "hasGeometry");
        hasGeometry.addDomain(Coral);
        hasGeometry.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/MSP/Coral.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
