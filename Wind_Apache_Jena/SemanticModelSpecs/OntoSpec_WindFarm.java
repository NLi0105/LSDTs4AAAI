package SemanticModelSpecs;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OntoSpec_WindFarm {
//    private static final String NS = "http://Wind/";
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_Turbine = NS + "Turbine#";
    private static final String NS_WindFarm = NS + "WindFarm#";
    private static final String NS_Geospatial = NS + "Geospatial#";
    private static final String NS_WindResource = NS + "WindResource#";

    public static void main(String[] args) {

        // Only creates the classes and their properties.
        // No instances/individuals are created.
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        // Classes
        OntClass Turbine = m.createClass(NS_Turbine);
        OntClass WindFarm = m.createClass(NS_WindFarm);

        // WindFarm properties
        DatatypeProperty hasWindFarmID = m.createDatatypeProperty(NS_WindFarm + "hasWindFarmID");
        hasWindFarmID.addDomain(WindFarm);
        hasWindFarmID.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasSite = m.createDatatypeProperty(NS_WindFarm + "hasSite");
        hasSite.addDomain(WindFarm);
        hasSite.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasAEP = m.createDatatypeProperty(NS_WindFarm + "hasAEP");
        hasAEP.addDomain(WindFarm);
        hasAEP.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasTurbineSpacing = m.createDatatypeProperty(NS_WindFarm + "hasTurbineSpacing");
        hasTurbineSpacing.addDomain(WindFarm);
        hasTurbineSpacing.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasNumberOfTurbinesPerRow = m.createDatatypeProperty(NS_WindFarm + "hasNumberOfTurbinesPerRow");
        hasNumberOfTurbinesPerRow.addDomain(WindFarm);
        hasNumberOfTurbinesPerRow.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasNumberOfTurbines = m.createDatatypeProperty(NS_WindFarm + "hasNumberOfTurbines");
        hasNumberOfTurbines.addDomain(WindFarm);
        hasNumberOfTurbines.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasLocation = m.createDatatypeProperty(NS_WindFarm + "hasLocation");
        hasLocation.addDomain(WindFarm);
        hasLocation.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasFarmPowerOutput = m.createDatatypeProperty(NS_WindFarm + "hasFarmPowerOutput");
        hasFarmPowerOutput.addDomain(WindFarm);
        hasFarmPowerOutput.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Connecting wind farm to turbine
        ObjectProperty hasTurbine = m.createObjectProperty(NS_WindFarm + "hasTurbine");
        hasTurbine.addDomain(WindFarm);
        hasTurbine.addRange(Turbine);

        // Setting geospatial features of a wind farm site
        ObjectProperty hasGeospatial = m.createObjectProperty(NS_WindFarm + "hasGeospatial");
        hasGeospatial.addDomain(WindFarm);
        hasGeospatial.addRange(m.getResource(NS_Geospatial));

        // Setting wind resource features of a wind farm site
        ObjectProperty hasWindResource = m.createObjectProperty(NS_WindFarm + "hasWindResource");
        hasWindResource.addDomain(WindFarm);
        hasWindResource.addRange(m.getResource(NS_WindResource));

        // Geometry
        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_WindFarm + "hasGeometry");
        hasGeometry.addDomain(WindFarm);
        hasGeometry.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasWindFarmStatus = m.createDatatypeProperty(NS_WindFarm + "hasWindFarmStatus");
        hasWindFarmStatus.addDomain(WindFarm);
        hasWindFarmStatus.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("project_folder/Wind/src/main/java/SemanticModels/WindFarm.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
