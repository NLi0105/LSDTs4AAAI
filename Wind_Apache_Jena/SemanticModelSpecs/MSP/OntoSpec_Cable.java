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

public class OntoSpec_Cable {
    private static final String NS = "http://windfarm/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_Cable = NS_MSP + "Cable#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        OntClass Cable = m.createClass(NS_Cable);

        DatatypeProperty hasLeaseNumber = m.createDatatypeProperty(NS_Cable + "hasLeaseNumber");
        hasLeaseNumber.addDomain(Cable);
        hasLeaseNumber.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasProjectName = m.createDatatypeProperty(NS_Cable + "hasProjectName");
        hasProjectName.addDomain(Cable);
        hasProjectName.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasDeveloper = m.createDatatypeProperty(NS_Cable + "hasDeveloper");
        hasDeveloper.addDomain(Cable);
        hasDeveloper.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasCableType = m.createDatatypeProperty(NS_Cable + "hasCableType");
        hasCableType.addDomain(Cable);
        hasCableType.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasStatus = m.createDatatypeProperty(NS_Cable + "hasStatus");
        hasStatus.addDomain(Cable);
        hasStatus.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasLength = m.createDatatypeProperty(NS_Cable + "hasLength");
        hasLength.addDomain(Cable);
        hasLength.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // New properties for the updated CSV structure
        DatatypeProperty hasMinKilovolt = m.createDatatypeProperty(NS_Cable + "hasMinKilovolt");
        hasMinKilovolt.addDomain(Cable);
        hasMinKilovolt.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasMaxKilovolt = m.createDatatypeProperty(NS_Cable + "hasMaxKilovolt");
        hasMaxKilovolt.addDomain(Cable);
        hasMaxKilovolt.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasMinDiameter = m.createDatatypeProperty(NS_Cable + "hasMinDiameter");
        hasMinDiameter.addDomain(Cable);
        hasMinDiameter.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasMaxDiameter = m.createDatatypeProperty(NS_Cable + "hasMaxDiameter");
        hasMaxDiameter.addDomain(Cable);
        hasMaxDiameter.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_Cable + "hasGeometry");
        hasGeometry.addDomain(Cable);
        hasGeometry.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("project_folder/Wind/src/main/java/SemanticModels/MSP/Cable.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}