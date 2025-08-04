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

public class OntoSpec_WindLease {
    //    private static final String NS = "http://Wind/";
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_WindLease = NS_MSP + "WindLease#";

    public static void main(String[] args) {

        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        OntClass WindLease = m.createClass(NS_WindLease);

        // WindLease properties
        DatatypeProperty hasLeaseID = m.createDatatypeProperty(NS_WindLease + "hasLeaseID");
        hasLeaseID.addDomain(WindLease);
        hasLeaseID.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasLeaseType = m.createDatatypeProperty(NS_WindLease + "hasLeaseType");
        hasLeaseType.addDomain(WindLease);
        hasLeaseType.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasResource = m.createDatatypeProperty(NS_WindLease + "hasResource");
        hasResource.addDomain(WindLease);
        hasResource.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasCompany = m.createDatatypeProperty(NS_WindLease + "hasCompany");
        hasCompany.addDomain(WindLease);
        hasCompany.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasLeaseNumber = m.createDatatypeProperty(NS_WindLease + "hasLeaseNumber");
        hasLeaseNumber.addDomain(WindLease);
        hasLeaseNumber.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasLeaseDate = m.createDatatypeProperty(NS_WindLease + "hasLeaseDate");
        hasLeaseDate.addDomain(WindLease);
        hasLeaseDate.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#date"));

        DatatypeProperty hasLeaseTerm = m.createDatatypeProperty(NS_WindLease + "hasLeaseTerm");
        hasLeaseTerm.addDomain(WindLease);
        hasLeaseTerm.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#integer"));

        DatatypeProperty hasState = m.createDatatypeProperty(NS_WindLease + "hasState");
        hasState.addDomain(WindLease);
        hasState.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasLocation = m.createDatatypeProperty(NS_WindLease + "hasLocation");
        hasLocation.addDomain(WindLease);
        hasLocation.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasLeaseDoc = m.createDatatypeProperty(NS_WindLease + "hasLeaseDoc");
        hasLeaseDoc.addDomain(WindLease);
        hasLeaseDoc.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasLeaseDocUrl = m.createDatatypeProperty(NS_WindLease + "hasLeaseDocUrl");
        hasLeaseDocUrl.addDomain(WindLease);
        hasLeaseDocUrl.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_WindLease + "hasGeometry");
        hasGeometry.addDomain(WindLease);
        hasGeometry.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("project_folder/Wind/src/main/java/SemanticModels/MSP/WindLease.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
