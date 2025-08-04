package Builder;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

public class test {
    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel();
        m.read("project_folder/umd-wind-turbine.owl");
        m.write(System.out, "RDF/XML-ABBREV");
    }
}
