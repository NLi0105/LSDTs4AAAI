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

public class OntoSpec_ExportCable {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_ExportCable = NS_MSP + "ExportCable#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        OntClass ExportCable = m.createClass(NS_ExportCable);

        DatatypeProperty hasLeaseNumber = m.createDatatypeProperty(NS_ExportCable + "hasLeaseNumber");
        hasLeaseNumber.addDomain(ExportCable);
        hasLeaseNumber.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasProjectName = m.createDatatypeProperty(NS_ExportCable + "hasProjectName");
        hasProjectName.addDomain(ExportCable);
        hasProjectName.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasDeveloper = m.createDatatypeProperty(NS_ExportCable + "hasDeveloper");
        hasDeveloper.addDomain(ExportCable);
        hasDeveloper.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasCableType = m.createDatatypeProperty(NS_ExportCable + "hasCableType");
        hasCableType.addDomain(ExportCable);
        hasCableType.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasElectricCurrent = m.createDatatypeProperty(NS_ExportCable + "hasElectricCurrent");
        hasElectricCurrent.addDomain(ExportCable);
        hasElectricCurrent.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasDiameter = m.createDatatypeProperty(NS_ExportCable + "hasDiameter");
        hasDiameter.addDomain(ExportCable);
        hasDiameter.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasStatus = m.createDatatypeProperty(NS_ExportCable + "hasStatus");
        hasStatus.addDomain(ExportCable);
        hasStatus.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasLength = m.createDatatypeProperty(NS_ExportCable + "hasLength");
        hasLength.addDomain(ExportCable);
        hasLength.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasMinKilovolt = m.createDatatypeProperty(NS_ExportCable + "hasMinKilovolt");
        hasMinKilovolt.addDomain(ExportCable);
        hasMinKilovolt.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasMaxKilovolt = m.createDatatypeProperty(NS_ExportCable + "hasMaxKilovolt");
        hasMaxKilovolt.addDomain(ExportCable);
        hasMaxKilovolt.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_ExportCable + "hasGeometry");
        hasGeometry.addDomain(ExportCable);
        hasGeometry.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/MSP/ExportCable.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}