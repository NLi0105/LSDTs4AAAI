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

public class OntoSpec_NOAA {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_NOAA = NS_MSP + "NOAA#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        OntClass NOAA = m.createClass(NS_NOAA);

        DatatypeProperty hasSiteID = m.createDatatypeProperty(NS_NOAA + "hasSiteID");
        hasSiteID.addDomain(NOAA);
        hasSiteID.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasSiteName = m.createDatatypeProperty(NS_NOAA + "hasSiteName");
        hasSiteName.addDomain(NOAA);
        hasSiteName.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasState = m.createDatatypeProperty(NS_NOAA + "hasState");
        hasState.addDomain(NOAA);
        hasState.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasMgmtAgency = m.createDatatypeProperty(NS_NOAA + "hasMgmtAgency");
        hasMgmtAgency.addDomain(NOAA);
        hasMgmtAgency.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasConsFocus = m.createDatatypeProperty(NS_NOAA + "hasConsFocus");
        hasConsFocus.addDomain(NOAA);
        hasConsFocus.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasPermanence = m.createDatatypeProperty(NS_NOAA + "hasPermanence");
        hasPermanence.addDomain(NOAA);
        hasPermanence.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasYearEstablished = m.createDatatypeProperty(NS_NOAA + "hasYearEstablished");
        hasYearEstablished.addDomain(NOAA);
        hasYearEstablished.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#dateTime"));

        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_NOAA + "hasGeometry");
        hasGeometry.addDomain(NOAA);
        hasGeometry.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        try (OutputStream out = new FileOutputStream("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/MSP/NOAA.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
