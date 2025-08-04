// Java
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

public class OntoSpec_PowerLine {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_PowerLine = NS + "PowerLine#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);

        OntClass PowerLine = m.createClass(NS_PowerLine);

        // ID
        DatatypeProperty hasPowerLineID = m.createDatatypeProperty(NS_PowerLine + "hasPowerLineID");
        hasPowerLineID.addDomain(PowerLine);
        hasPowerLineID.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#string"));

        // Attributes
        DatatypeProperty hasVoltage = m.createDatatypeProperty(NS_PowerLine + "hasVoltage");
        hasVoltage.addDomain(PowerLine);
        hasVoltage.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasCapacity = m.createDatatypeProperty(NS_PowerLine + "hasCapacity");
        hasCapacity.addDomain(PowerLine);
        hasCapacity.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasMaterial = m.createDatatypeProperty(NS_PowerLine + "hasMaterial");
        hasMaterial.addDomain(PowerLine);
        hasMaterial.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasStatus = m.createDatatypeProperty(NS_PowerLine + "hasStatus");
        hasStatus.addDomain(PowerLine);
        hasStatus.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#string"));

        // Geometry
        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_PowerLine + "hasGeometry");
        hasGeometry.addDomain(PowerLine);
        hasGeometry.addRange(m.getOntClass("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/PowerLine.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}