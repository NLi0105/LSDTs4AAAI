package SpecVisitor;

import Specification.*;
import Specification.LCASpecs.LifeCycleSpecs;
import Specification.RegulationSpecs.RegulationSpecs;
import Ontology.OntologyModel;
import org.apache.jena.ontology.*;

public class RegulationSpecVisitor implements SpecVisitor {

    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_Regulation = NS + "Regulation#";
    private OntModel model;

    // Regulation properties
    private DatatypeProperty hasRegulationName;
    private DatatypeProperty hasRegulationDescription;
    private DatatypeProperty hasRegulationType;
    private DatatypeProperty hasSeverity;
    private DatatypeProperty hasImpactAreaURI;
    private DatatypeProperty hasRemedy;

    public void setModel(OntologyModel ontologyModel) {
        this.model = ontologyModel.getModel();
        this.hasRegulationName = model.getDatatypeProperty(NS_Regulation + "hasRegulationName");
        this.hasRegulationDescription = model.getDatatypeProperty(NS_Regulation + "hasRegulationDescription");
        this.hasRegulationType = model.getDatatypeProperty(NS_Regulation + "hasRegulationType");
        this.hasSeverity = model.getDatatypeProperty(NS_Regulation + "hasSeverity");
        this.hasImpactAreaURI = model.getDatatypeProperty(NS_Regulation + "hasImpactAreaURI");
        this.hasRemedy = model.getDatatypeProperty(NS_Regulation + "hasRemedy");
    }

    @Override
    public void visit(RegulationSpecs regulationSpecs) {
        if (model == null) {
            throw new IllegalStateException("Model is not set for the regulation specs visitor.");
        }

        if (hasRegulationName == null || hasRegulationDescription == null || hasRegulationType == null || hasSeverity == null || hasImpactAreaURI == null || hasRemedy == null) {
            throw new IllegalStateException("One or more properties are not initialized.");
        }

        Individual regulationSpecsIndividual = model.createIndividual(NS_Regulation + regulationSpecs.getRegulationName(), model.getOntClass(NS_Regulation + "Regulation"));
        regulationSpecsIndividual.addProperty(hasRegulationName, regulationSpecs.getRegulationName());
        regulationSpecsIndividual.addProperty(hasRegulationDescription, regulationSpecs.getRegulationDescription());
        regulationSpecsIndividual.addProperty(hasRegulationType, regulationSpecs.getRegulationType());
        regulationSpecsIndividual.addProperty(hasSeverity, regulationSpecs.getSeverity());
        regulationSpecsIndividual.addProperty(hasImpactAreaURI, regulationSpecs.getImpactAreaURI());
        regulationSpecsIndividual.addProperty(hasRemedy, regulationSpecs.getRemedy());
    }

    @Override
    public void visit(TurbineSpecs turbineSpecs) {
        throw new UnsupportedOperationException("RegulationSpecVisitor does not support visiting TurbineSpecs.");
    }

    @Override
    public void visit(WindFarmSpecs windFarmSpecs) {
        throw new UnsupportedOperationException("RegulationSpecVisitor does not support visiting WindFarmSpecs.");
    }

    @Override
    public void visit(WindResourceSpecs windResourceSpecs) {
        throw new UnsupportedOperationException("RegulationSpecVisitor does not support visiting WindResourceSpecs.");
    }

    @Override
    public void visit(PowerLineSpecs powerLineSpecs) {
        throw new UnsupportedOperationException("RegulationSpecVisitor does not support visiting PowerLineSpecs.");
    }

    @Override
    public void visit(LifeCycleSpecs lifeCycleSpecs) {
        throw new UnsupportedOperationException("RegulationSpecVisitor does not support visiting LifeCycleSpecs.");
    }

    @Override
    public void visit(SubstationSpecs substationSpecs) {
        throw new UnsupportedOperationException("RegulationSpecVisitor does not support visiting SubstationSpecs.");
    }
}