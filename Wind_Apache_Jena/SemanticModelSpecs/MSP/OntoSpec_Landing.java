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

public class OntoSpec_Landing {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_Landing = NS_MSP + "Landing#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        OntClass Landing = m.createClass(NS_Landing);

        DatatypeProperty hasLeaseNumber = m.createDatatypeProperty(NS_Landing + "hasLeaseNumber");
        hasLeaseNumber.addDomain(Landing);
        hasLeaseNumber.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasProjectName = m.createDatatypeProperty(NS_Landing + "hasProjectName");
        hasProjectName.addDomain(Landing);
        hasProjectName.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasLandingSite = m.createDatatypeProperty(NS_Landing + "hasLandingSite");
        hasLandingSite.addDomain(Landing);
        hasLandingSite.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasDeveloper = m.createDatatypeProperty(NS_Landing + "hasDeveloper");
        hasDeveloper.addDomain(Landing);
        hasDeveloper.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasStatus = m.createDatatypeProperty(NS_Landing + "hasStatus");
        hasStatus.addDomain(Landing);
        hasStatus.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_Landing + "hasGeometry");
        hasGeometry.addDomain(Landing);
        hasGeometry.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/MSP/Landing.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}