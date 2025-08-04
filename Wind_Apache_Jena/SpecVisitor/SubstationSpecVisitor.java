package SpecVisitor;

import Ontology.OntologyModel;
import Specification.*;
import Specification.LCASpecs.LifeCycleSpecs;
import Specification.RegulationSpecs.RegulationSpecs;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.locationtech.jts.io.WKTWriter;

public class SubstationSpecVisitor implements SpecVisitor {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_Substation = NS + "Substation#";

    private OntModel model;

    // Substation properties
    private DatatypeProperty hasSubstationID;
    private DatatypeProperty hasType;
    private DatatypeProperty hasCapacity;
    private DatatypeProperty hasVoltage;
    private DatatypeProperty hasPowerFactor;
    private DatatypeProperty hasLoadFactor;
    private DatatypeProperty hasLossFactor;
    private DatatypeProperty hasLoad;
    private DatatypeProperty hasLoss;
    private DatatypeProperty hasPower;
    private DatatypeProperty hasNumOfTransformers;
    private DatatypeProperty hasOperationalStatus;
    private DatatypeProperty hasCoolingSystem;
    private DatatypeProperty hasProtectionSystem;
    private DatatypeProperty hasFoundation;
    private DatatypeProperty hasGeometry;

    public void setModel(OntologyModel ontologyModel) {
        this.model = ontologyModel.getModel();
        this.hasSubstationID = model.getDatatypeProperty(NS_Substation + "hasSubstationID");
        this.hasType = model.getDatatypeProperty(NS_Substation + "hasType");
        this.hasCapacity = model.getDatatypeProperty(NS_Substation + "hasCapacity");
        this.hasVoltage = model.getDatatypeProperty(NS_Substation + "hasVoltage");
        this.hasPowerFactor = model.getDatatypeProperty(NS_Substation + "hasPowerFactor");
        this.hasLoadFactor = model.getDatatypeProperty(NS_Substation + "hasLoadFactor");
        this.hasLossFactor = model.getDatatypeProperty(NS_Substation + "hasLossFactor");
        this.hasLoad = model.getDatatypeProperty(NS_Substation + "hasLoad");
        this.hasLoss = model.getDatatypeProperty(NS_Substation + "hasLoss");
        this.hasPower = model.getDatatypeProperty(NS_Substation + "hasPower");
        this.hasNumOfTransformers = model.getDatatypeProperty(NS_Substation + "hasNumOfTransformers");
        this.hasOperationalStatus = model.getDatatypeProperty(NS_Substation + "hasOperationalStatus");
        this.hasCoolingSystem = model.getDatatypeProperty(NS_Substation + "hasCoolingSystem");
        this.hasProtectionSystem = model.getDatatypeProperty(NS_Substation + "hasProtectionSystem");
        this.hasFoundation = model.getDatatypeProperty(NS_Substation + "hasFoundation");
        this.hasGeometry = model.getDatatypeProperty(NS_Substation + "hasGeometry");
    }

    @Override
    public void visit(SubstationSpecs substationSpecs) {
        if (model == null) {
            throw new IllegalStateException("Model is not set for the substation specs visitor.");
        }

        Individual substationSpecsIndividual = model.createIndividual(
                NS_Substation + substationSpecs.getSubstationID(),
                model.getOntClass(NS_Substation)
        );

        // Add properties with proper typed literals
        substationSpecsIndividual.addProperty(hasSubstationID, model.createTypedLiteral(substationSpecs.getSubstationID(), XSDDatatype.XSDstring));
        substationSpecsIndividual.addProperty(hasType, model.createTypedLiteral(substationSpecs.getType(), XSDDatatype.XSDstring));
        substationSpecsIndividual.addProperty(hasCapacity, model.createTypedLiteral(substationSpecs.getCapacity(), XSDDatatype.XSDdouble));
        substationSpecsIndividual.addProperty(hasVoltage, model.createTypedLiteral(substationSpecs.getVoltage(), XSDDatatype.XSDdouble));
        substationSpecsIndividual.addProperty(hasPowerFactor, model.createTypedLiteral(substationSpecs.getPowerFactor(), XSDDatatype.XSDdouble));
        substationSpecsIndividual.addProperty(hasLoadFactor, model.createTypedLiteral(substationSpecs.getLoadFactor(), XSDDatatype.XSDdouble));
        substationSpecsIndividual.addProperty(hasLossFactor, model.createTypedLiteral(substationSpecs.getLossFactor(), XSDDatatype.XSDdouble));
        substationSpecsIndividual.addProperty(hasLoad, model.createTypedLiteral(substationSpecs.getLoad(), XSDDatatype.XSDdouble));
        substationSpecsIndividual.addProperty(hasLoss, model.createTypedLiteral(substationSpecs.getLoss(), XSDDatatype.XSDdouble));
        substationSpecsIndividual.addProperty(hasPower, model.createTypedLiteral(substationSpecs.getPower(), XSDDatatype.XSDdouble));
        substationSpecsIndividual.addProperty(hasNumOfTransformers, model.createTypedLiteral(substationSpecs.getNumOfTransformers(), XSDDatatype.XSDint));
        substationSpecsIndividual.addProperty(hasOperationalStatus, model.createTypedLiteral(substationSpecs.getOperationalStatus(), XSDDatatype.XSDstring));
        substationSpecsIndividual.addProperty(hasCoolingSystem, model.createTypedLiteral(substationSpecs.getCoolingSystem(), XSDDatatype.XSDstring));
        substationSpecsIndividual.addProperty(hasProtectionSystem, model.createTypedLiteral(substationSpecs.getProtectionSystem(), XSDDatatype.XSDstring));
        substationSpecsIndividual.addProperty(hasFoundation, model.createTypedLiteral(substationSpecs.getFoundation(), XSDDatatype.XSDstring));

        // Add geometry with proper WKT format
        WKTWriter wktWriter = new WKTWriter();
        String wkt = wktWriter.write(substationSpecs.getGeometry());
        substationSpecsIndividual.addProperty(hasGeometry, model.createTypedLiteral(wkt, XSDDatatype.XSDstring));
    }

    @Override
    public void visit(TurbineSpecs turbineSpecs) {
        throw new UnsupportedOperationException("SubstationSpecVisitor does not support visiting TurbineSpecs.");
    }

    @Override
    public void visit(WindFarmSpecs windFarmSpecs) {
        throw new UnsupportedOperationException("SubstationSpecVisitor does not support visiting WindFarmSpecs.");
    }

    @Override
    public void visit(WindResourceSpecs windResourceSpecs) {
        throw new UnsupportedOperationException("SubstationSpecVisitor does not support visiting WindResourceSpecs.");
    }

    @Override
    public void visit(PowerLineSpecs powerLineSpecs) {
        throw new UnsupportedOperationException("SubstationSpecVisitor does not support visiting PowerLineSpecs.");
    }

    @Override
    public void visit(LifeCycleSpecs lifeCycleSpecs) {
        throw new UnsupportedOperationException("SubstationSpecVisitor does not support visiting LifeCycleSpecs.");
    }

    @Override
    public void visit(RegulationSpecs regulationSpecs) {
        throw new UnsupportedOperationException("SubstationSpecVisitor does not support visiting RegulationSpecs.");
    }
}