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

public class OntoSpec_Substation {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_Substation = NS + "Substation#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);

        OntClass Substation = m.createClass(NS_Substation);

        // ID
        DatatypeProperty hasSubstationID = m.createDatatypeProperty(NS_Substation + "hasSubstationID");
        hasSubstationID.addDomain(Substation);
        hasSubstationID.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#string"));

        // Type (Offshore/Onshore)
        DatatypeProperty hasType = m.createDatatypeProperty(NS_Substation + "hasType");
        hasType.addDomain(Substation);
        hasType.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#string"));

        // Attributes
        DatatypeProperty hasCapacity = m.createDatatypeProperty(NS_Substation + "hasCapacity");
        hasCapacity.addDomain(Substation);
        hasCapacity.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasVoltage = m.createDatatypeProperty(NS_Substation + "hasVoltage");
        hasVoltage.addDomain(Substation);
        hasVoltage.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasPowerFactor = m.createDatatypeProperty(NS_Substation + "hasPowerFactor");
        hasPowerFactor.addDomain(Substation);
        hasPowerFactor.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasLoadFactor = m.createDatatypeProperty(NS_Substation + "hasLoadFactor");
        hasLoadFactor.addDomain(Substation);
        hasLoadFactor.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasLossFactor = m.createDatatypeProperty(NS_Substation + "hasLossFactor");
        hasLossFactor.addDomain(Substation);
        hasLossFactor.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasLoad = m.createDatatypeProperty(NS_Substation + "hasLoad");
        hasLoad.addDomain(Substation);
        hasLoad.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasLoss = m.createDatatypeProperty(NS_Substation + "hasLoss");
        hasLoss.addDomain(Substation);
        hasLoss.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasPower = m.createDatatypeProperty(NS_Substation + "hasPower");
        hasPower.addDomain(Substation);
        hasPower.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasNumOfTransformers = m.createDatatypeProperty(NS_Substation + "hasNumOfTransformers");
        hasNumOfTransformers.addDomain(Substation);
        hasNumOfTransformers.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#int"));

        DatatypeProperty hasOperationalStatus = m.createDatatypeProperty(NS_Substation + "hasOperationalStatus");
        hasOperationalStatus.addDomain(Substation);
        hasOperationalStatus.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasCoolingSystem = m.createDatatypeProperty(NS_Substation + "hasCoolingSystem");
        hasCoolingSystem.addDomain(Substation);
        hasCoolingSystem.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasProtectionSystem = m.createDatatypeProperty(NS_Substation + "hasProtectionSystem");
        hasProtectionSystem.addDomain(Substation);
        hasProtectionSystem.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasFoundation = m.createDatatypeProperty(NS_Substation + "hasFoundation");
        hasFoundation.addDomain(Substation);
        hasFoundation.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#string"));

        // Geometry
        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_Substation + "hasGeometry");
        hasGeometry.addDomain(Substation);
        hasGeometry.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/Substation.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}