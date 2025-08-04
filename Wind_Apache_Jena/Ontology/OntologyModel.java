package Ontology;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDF;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class OntologyModel extends AbstractOntologyModel {
    private String namespaceFilter;

    public OntologyModel() {
        super();
        createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
    }

    public OntologyModel(OntModelSpec spec) {
        super();
        createOntologyModel(spec);
    }

    @Override
    public void createOntologyModel(OntModelSpec spec) {
        this.model = ModelFactory.createOntologyModel(spec);
    }

    @Override
    public void createOntologyModel() {
        this.model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
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
        model.listStatements().forEachRemaining(System.out::println);
    }

    @Override
    public void printAllClasses() {
        System.out.println("Classes:");
        System.out.println("----------------------------------------------------------------");
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

    public void loadOntologyFromFile(String filePath) {
        InputStream in = FileManager.get().open(filePath);
        if (in == null) {
            throw new IllegalArgumentException("File: " + filePath + " not found");
        }
        model.read(in, null);
    }

    public void setNamespaceFilter(String namespace) {
        this.namespaceFilter = namespace;
    }

    // Method to list all namespaces and URIs in the model
    public void listNamespacesAndURIs() {
        Set<String> namespaces = new HashSet<>();
        model.listStatements().forEachRemaining(statement -> {
            String subjectURI = statement.getSubject().getURI();
            if (subjectURI != null) {
                namespaces.add(subjectURI.substring(0, subjectURI.indexOf('#') + 1));
                System.out.println("URI: " + subjectURI);
            }
        });
        System.out.println("Namespaces:");
        namespaces.forEach(System.out::println);
    }

    // Updated method to find an individual by name and print its name and URI
    public Individual findIndividualByName(String name) {
        Individual individual = model.listIndividuals().filterKeep(ind -> ind.getLocalName().equals(name)).next();
        if (individual != null) {
            String uri = individual.getURI();
            String localName = uri.substring(uri.indexOf('#') + 1);
            System.out.println(localName + " " + uri);
//            System.out.println(uri);
        } else {
            System.out.println("Individual not found.");
        }
        return individual;
    }

    // New method to print all individuals in a specified format from a given model
    public void printIndividuals(Model model, String namespace, String format) {
        Model filteredModel = ModelFactory.createDefaultModel();

        // Iterate through all resources in the model
        ResIterator resIterator = model.listSubjects();
        while (resIterator.hasNext()) {
            Resource resource = resIterator.nextResource();
            // Check if the resource is an individual and belongs to the specified namespace
            if (resource.hasProperty(RDF.type) && resource.getURI().startsWith(namespace)) {
                // Iterate through all statements about this individual
                StmtIterator stmtIterator = resource.listProperties();
                while (stmtIterator.hasNext()) {
                    Statement stmt = stmtIterator.nextStatement();
                    // Check if the object is a literal
                    if (stmt.getObject().isLiteral()) {
                        // Add the statement to the filtered model
                        filteredModel.add(stmt);
                    }
                }
            }
        }

        // Determine the output format
        Lang lang;
        switch (format.toLowerCase()) {
            case "turtle":
                lang = Lang.TURTLE;
                break;
            case "rdfxml":
                lang = Lang.RDFXML;
                break;
            case "ntriples":
                lang = Lang.NTRIPLES;
                break;
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }

        // Print the filtered model in the specified format
        RDFDataMgr.write(System.out, filteredModel, lang);
    }

    // Java
    public void printIndividualByName(Model model, String individualName, String format) {
        Model filteredModel = ModelFactory.createDefaultModel();

        // Cast the model to OntModel if necessary
        OntModel ontModel;
        if (model instanceof OntModel) {
            ontModel = (OntModel) model;
        } else {
            ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, model);
        }

        // Iterate through all resources in the model
        ResIterator resIterator = ontModel.listSubjects();
        while (resIterator.hasNext()) {
            Resource resource = resIterator.nextResource();
            // Check if the resource is an individual and its local name matches the specified individual name
            if (resource.hasProperty(RDF.type) && resource.getLocalName().equals(individualName)) {
                // Extract the namespace from the resource URI
                String namespace = resource.getNameSpace();

                // Iterate through all statements about this individual
                StmtIterator stmtIterator = resource.listProperties();
                while (stmtIterator.hasNext()) {
                    Statement stmt = stmtIterator.nextStatement();
                    // Add the statement to the filtered model
                    filteredModel.add(stmt);
                }

                // Determine the output format
                Lang lang;
                switch (format.toLowerCase()) {
                    case "turtle":
                        lang = Lang.TURTLE;
                        break;
                    case "rdfxml":
                        lang = Lang.RDFXML;
                        break;
                    case "ntriples":
                        lang = Lang.NTRIPLES;
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported format: " + format);
                }

                // Print the filtered model in the specified format
                RDFDataMgr.write(System.out, filteredModel, lang);
                return;
            }
        }

        System.out.println("Individual not found.");
    }
}