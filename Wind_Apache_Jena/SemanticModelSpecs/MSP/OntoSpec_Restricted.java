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

public class OntoSpec_Restricted {
    private static final String NS = "http://windfarm/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_Restricted = NS_MSP + "Restricted#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        OntClass Restricted = m.createClass(NS_Restricted);

        DatatypeProperty hasRestrictedType = m.createDatatypeProperty(NS_Restricted + "hasRestrictedType");
        hasRestrictedType.addDomain(Restricted);
        hasRestrictedType.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasState = m.createDatatypeProperty(NS_Restricted + "hasState");
        hasState.addDomain(Restricted);
        hasState.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasRestrictedAgency = m.createDatatypeProperty(NS_Restricted + "hasRestrictedAgency");
        hasRestrictedAgency.addDomain(Restricted);
        hasRestrictedAgency.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_Restricted + "hasGeometry");
        hasGeometry.addDomain(Restricted);
        hasGeometry.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("project_folder/Wind/src/main/java/SemanticModels/MSP/Restricted.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
