package Builder;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

public class test {
    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel();
        m.read("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/umd-wind-turbine.owl");
        m.write(System.out, "RDF/XML-ABBREV");
    }
}
