package SemanticModelSpecs;

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

public class OntoSpec_WindResource {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_WindResource = NS + "WindResource#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        OntClass WindResource = m.createClass(NS_WindResource);

        // ID
        DatatypeProperty hasWindResourceID = m.createDatatypeProperty(NS_WindResource + "hasWindResourceID");
        hasWindResourceID.addDomain(WindResource);
        hasWindResourceID.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#string"));

        // Temperature
        DatatypeProperty hasTemperature2M = m.createDatatypeProperty(NS_WindResource + "hasTemperature2M");
        hasTemperature2M.addDomain(WindResource);
        hasTemperature2M.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        // Humidity
        DatatypeProperty hasSpecificHumidity2M = m.createDatatypeProperty(NS_WindResource + "hasSpecificHumidity2M");
        hasSpecificHumidity2M.addDomain(WindResource);
        hasSpecificHumidity2M.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasRelativeHumidity2M = m.createDatatypeProperty(NS_WindResource + "hasRelativeHumidity2M");
        hasRelativeHumidity2M.addDomain(WindResource);
        hasRelativeHumidity2M.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasPrecipitation = m.createDatatypeProperty(NS_WindResource + "hasPrecipitation");
        hasPrecipitation.addDomain(WindResource);
        hasPrecipitation.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        // Wind speed & pressure
        DatatypeProperty hasSurfacePressure = m.createDatatypeProperty(NS_WindResource + "hasSurfacePressure");
        hasSurfacePressure.addDomain(WindResource);
        hasSurfacePressure.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasWindSpeed10M = m.createDatatypeProperty(NS_WindResource + "hasWindSpeed10M");
        hasWindSpeed10M.addDomain(WindResource);
        hasSurfacePressure.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasWindSpeed50M = m.createDatatypeProperty(NS_WindResource + "hasWindSpeed50M");
        hasWindSpeed50M.addDomain(WindResource);
        hasSurfacePressure.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        // Geometry
        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_WindResource + "hasGeometry");
        hasGeometry.addDomain(WindResource);
        hasGeometry.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#string"));


        try (OutputStream out = new FileOutputStream("project_folder/Wind/src/main/java/SemanticModels/WindResource.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
