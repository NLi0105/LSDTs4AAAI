package SemanticModelSpecs.Regulation;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OntoSpec_Regulation {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_REG = NS + "Regulation#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        OntClass Regulation = m.createClass(NS_REG + "Regulation");

        DatatypeProperty hasRegulationName = m.createDatatypeProperty(NS_REG + "hasRegulationName");
        hasRegulationName.addDomain(Regulation);
        hasRegulationName.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasRegulationDescription = m.createDatatypeProperty(NS_REG + "hasRegulationDescription");
        hasRegulationDescription.addDomain(Regulation);
        hasRegulationDescription.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasRegulationType = m.createDatatypeProperty(NS_REG + "hasRegulationType");
        hasRegulationType.addDomain(Regulation);
        hasRegulationType.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasSeverity = m.createDatatypeProperty(NS_REG + "hasSeverity");
        hasSeverity.addDomain(Regulation);
        hasSeverity.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasImpactAreaURI = m.createDatatypeProperty(NS_REG + "hasImpactAreaURI");
        hasImpactAreaURI.addDomain(Regulation);
        hasImpactAreaURI.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasRemedy = m.createDatatypeProperty(NS_REG + "hasRemedy");
        hasRemedy.addDomain(Regulation);
        hasRemedy.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/Regulation.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}