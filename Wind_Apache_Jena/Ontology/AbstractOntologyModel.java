package Ontology;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.util.iterator.ExtendedIterator;

public abstract class AbstractOntologyModel implements AbstractOntologyInterface {
    protected OntModel model;
    protected String modelName;
    protected String namespaceFilter;

    @Override
    public void createOntologyModel(OntModelSpec spec) {
        this.model = ModelFactory.createOntologyModel(spec);
    }

    @Override
    public void createOntologyModel() {
        this.model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
    }

    public OntModel getModel() {
        return model;
    }

    @Override
    public void printModelInfo() {
        System.out.println("Model Name: " + modelName);
        System.out.println("Model Size: " + model.size());
    }

    @Override
    public void setModelName(String name) {
        this.modelName = name;
    }

    @Override
    public void extractIndividualStatements() {
        ExtendedIterator<Statement> statements = model.listStatements();
        while (statements.hasNext()) {
            System.out.println(statements.next());
        }
    }

    @Override
    public void printAllClasses() {
        model.listClasses().forEachRemaining(ontClass -> {
            if (ontClass.getURI() != null && ontClass.getURI().contains(namespaceFilter)) {
                System.out.println(ontClass.getURI());
            }
        });
    }

    @Override
    public void printAllIndividuals() {
        model.listIndividuals().forEachRemaining(individual -> {
            if (individual.getURI() != null && individual.getURI().contains(namespaceFilter)) {
                System.out.println(individual.getURI());
            }
        });
    }

    public void setNamespaceFilter(String namespace) {
        this.namespaceFilter = namespace;
    }
}