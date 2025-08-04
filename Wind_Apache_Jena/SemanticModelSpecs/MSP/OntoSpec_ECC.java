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

public class OntoSpec_ECC {
    private static final String NS = "http://windfarm/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_ECC = NS_MSP + "ECC#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        OntClass ECC = m.createClass(NS_ECC);

        DatatypeProperty hasLeaseID = m.createDatatypeProperty(NS_ECC + "hasLeaseID");
        hasLeaseID.addDomain(ECC);
        hasLeaseID.addRange(m.createResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasProjectName = m.createDatatypeProperty(NS_ECC + "hasProjectName");
        hasProjectName.addDomain(ECC);
        hasProjectName.addRange(m.createResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasDeveloper = m.createDatatypeProperty(NS_ECC + "hasDeveloper");
        hasDeveloper.addDomain(ECC);
        hasDeveloper.addRange(m.createResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasArea = m.createDatatypeProperty(NS_ECC + "hasArea");
        hasArea.addDomain(ECC);
        hasArea.addRange(m.createResource("http://www.w3.org/2001/XMLSchema#float"));

        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_ECC + "hasGeometry");
        hasGeometry.addDomain(ECC);
        hasGeometry.addRange(m.createResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("project_folder/Wind/src/main/java/SemanticModels/MSP/ECC.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
