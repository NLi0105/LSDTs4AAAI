package org.apache.jena;

import Ontology.OntologyModel;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Seq;

public class TryList {
    public static void main(String[] args) {
        OntologyModel model = new OntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        OntClass c1 = model.getModel().createClass("http://example.org/Class1");
        Individual d1 = model.getModel().createIndividual("http://example.org/Individual1", c1);
        DatatypeProperty hasValuelist = model.getModel().createDatatypeProperty("http://example.org/hasValuelist");

        Seq valueSeq = model.getModel().createSeq();
        valueSeq.add(1);
        valueSeq.add(2);
        valueSeq.add(3);

        d1.addProperty(hasValuelist, valueSeq);

        model.getModel().write(System.out, "TURTLE");
    }
}
