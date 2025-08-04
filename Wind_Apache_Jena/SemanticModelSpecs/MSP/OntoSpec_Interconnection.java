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

public class OntoSpec_Interconnection {
    private static final String NS = "http://windfarm/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_Interconnection = NS_MSP + "Interconnection#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        OntClass Interconnection = m.createClass(NS_Interconnection);

        DatatypeProperty hasLeaseNumber = m.createDatatypeProperty(NS_Interconnection + "hasLeaseNumber");
        hasLeaseNumber.addDomain(Interconnection);
        hasLeaseNumber.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasProjectName = m.createDatatypeProperty(NS_Interconnection + "hasProjectName");
        hasProjectName.addDomain(Interconnection);
        hasProjectName.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasFacilityName = m.createDatatypeProperty(NS_Interconnection + "hasFacilityName");
        hasFacilityName.addDomain(Interconnection);
        hasFacilityName.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasDeveloper = m.createDatatypeProperty(NS_Interconnection + "hasDeveloper");
        hasDeveloper.addDomain(Interconnection);
        hasDeveloper.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasElectricCapacity = m.createDatatypeProperty(NS_Interconnection + "hasElectricCapacity");
        hasElectricCapacity.addDomain(Interconnection);
        hasElectricCapacity.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasInjectedMegawatt = m.createDatatypeProperty(NS_Interconnection + "hasInjectedMegawatt");
        hasInjectedMegawatt.addDomain(Interconnection);
        hasInjectedMegawatt.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasStatus = m.createDatatypeProperty(NS_Interconnection + "hasStatus");
        hasStatus.addDomain(Interconnection);
        hasStatus.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_Interconnection + "hasGeometry");
        hasGeometry.addDomain(Interconnection);
        hasGeometry.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("project_folder/Wind/src/main/java/SemanticModels/MSP/Interconnection.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}