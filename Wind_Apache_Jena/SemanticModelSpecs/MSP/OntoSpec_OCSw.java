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

public class OntoSpec_OCSw {
    private static final String NS = "http://windfarm/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_OCSw = NS_MSP + "OCSw#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        OntClass OCSw = m.createClass(NS_OCSw);

        DatatypeProperty hasOCSID = m.createDatatypeProperty(NS_OCSw + "hasOCSID");
        hasOCSID.addDomain(OCSw);
        hasOCSID.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasOCSName = m.createDatatypeProperty(NS_OCSw + "hasOCSName");
        hasOCSName.addDomain(OCSw);
        hasOCSName.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasLeaseType = m.createDatatypeProperty(NS_OCSw + "hasLeaseType");
        hasLeaseType.addDomain(OCSw);
        hasLeaseType.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasAgency = m.createDatatypeProperty(NS_OCSw + "hasAgency");
        hasAgency.addDomain(OCSw);
        hasAgency.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasDateEstablished = m.createDatatypeProperty(NS_OCSw + "hasDateEstablished");
        hasDateEstablished.addDomain(OCSw);
        hasDateEstablished.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#date"));

        DatatypeProperty hasArea = m.createDatatypeProperty(NS_OCSw + "hasArea");
        hasArea.addDomain(OCSw);
        hasArea.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#float"));

        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_OCSw + "hasGeometry");
        hasGeometry.addDomain(OCSw);
        hasGeometry.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("project_folder/Wind/src/main/java/SemanticModels/MSP/OCSw.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
