package Ontology;

import org.apache.jena.ontology.OntModelSpec;

public interface AbstractOntologyInterface {
    void createOntologyModel(OntModelSpec spec);
    void createOntologyModel();
    void printModelInfo();
    void setModelName(String name);
    void extractIndividualStatements();
    void printAllClasses();
    void printAllIndividuals();
}