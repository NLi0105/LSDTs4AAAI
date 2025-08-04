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

public class OntoSpec_Wind {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_WindSpeed = NS_MSP + "WindSpeed#";

    public static void main(String[] args) {

        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        OntClass WindSpeed = m.createClass(NS_WindSpeed);

        DatatypeProperty hasWindID = m.createDatatypeProperty(NS_WindSpeed + "hasWindID");
        hasWindID.addDomain(WindSpeed);
        hasWindID.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasMinWindSpeed = m.createDatatypeProperty(NS_WindSpeed + "hasMinWindSpeed");
        hasMinWindSpeed.addDomain(WindSpeed);
        hasMinWindSpeed.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasMaxWindSpeed = m.createDatatypeProperty(NS_WindSpeed + "hasMaxWindSpeed");
        hasMaxWindSpeed.addDomain(WindSpeed);
        hasMaxWindSpeed.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasGeomerty = m.createDatatypeProperty(NS_WindSpeed + "hasGeometry");
        hasGeomerty.addDomain(WindSpeed);
        hasGeomerty.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/MSP/Wind.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
