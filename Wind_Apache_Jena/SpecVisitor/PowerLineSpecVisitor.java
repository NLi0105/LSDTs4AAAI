// Java
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

public class PowerLineSpecVisitor implements SpecVisitor {
    private static final String NS = "windfarm/";
    private static final String NS_PowerLine = NS + "PowerLine#";

    private OntModel model;

    private DatatypeProperty hasPowerLineID;
    private DatatypeProperty hasVoltage;
    private DatatypeProperty hasCapacity;
    private DatatypeProperty hasMaterial;
    private DatatypeProperty hasStatus;
    private DatatypeProperty hasGeometry;

    public void setModel(OntologyModel ontologyModel) {
        this.model = ontologyModel.getModel();

        this.hasPowerLineID = model.getDatatypeProperty(NS_PowerLine + "hasPowerLineID");
        this.hasVoltage = model.getDatatypeProperty(NS_PowerLine + "hasVoltage");
        this.hasCapacity = model.getDatatypeProperty(NS_PowerLine + "hasCapacity");
        this.hasMaterial = model.getDatatypeProperty(NS_PowerLine + "hasMaterial");
        this.hasStatus = model.getDatatypeProperty(NS_PowerLine + "hasStatus");
        this.hasGeometry = model.getDatatypeProperty(NS_PowerLine + "hasGeometry");
    }

    @Override
    public void visit(PowerLineSpecs powerLineSpecs) {
        if (model == null) {
            throw new IllegalStateException("Model is not set for the power line specs visitor.");
        }

        Individual powerLineSpecsIndividual = model.createIndividual(NS_PowerLine + powerLineSpecs.getPowerLineID(), model.getOntClass(NS_PowerLine));
        powerLineSpecsIndividual.addProperty(hasPowerLineID, powerLineSpecs.getPowerLineID());
        powerLineSpecsIndividual.addProperty(hasVoltage, model.createTypedLiteral(powerLineSpecs.getVoltage(), XSDDatatype.XSDdouble));
        powerLineSpecsIndividual.addProperty(hasCapacity, model.createTypedLiteral(powerLineSpecs.getCapacity(), XSDDatatype.XSDdouble));
        powerLineSpecsIndividual.addProperty(hasMaterial, powerLineSpecs.getMaterial());
        powerLineSpecsIndividual.addProperty(hasStatus, powerLineSpecs.getStatus());

        WKTWriter wktWriter = new WKTWriter();
        String wkt = wktWriter.write(powerLineSpecs.getGeometry());
        powerLineSpecsIndividual.addProperty(hasGeometry, wkt, XSDDatatype.XSDstring);
    }

    @Override
    public void visit(TurbineSpecs turbineSpecs) {
        throw new UnsupportedOperationException("PowerLineSpecVisitor does not support visiting TurbineSpecs.");
    }

    @Override
    public void visit(WindFarmSpecs windFarmSpecs) {
        throw new UnsupportedOperationException("PowerLineSpecVisitor does not support visiting WindFarmSpecs.");
    }

    @Override
    public void visit(WindResourceSpecs windResourceSpecs) {
        throw new UnsupportedOperationException("PowerLineSpecVisitor does not support visiting WindResourceSpecs.");
    }

    @Override
    public void visit(LifeCycleSpecs lifeCycleSpecs) {
        throw new UnsupportedOperationException("PowerLineSpecVisitor does not support visiting LifeCycle.");
    }

    @Override
    public void visit(SubstationSpecs substationSpecs) {
        throw new UnsupportedOperationException("PowerLineSpecVisitor does not support visiting SubstationSpecs.");
    }

    @Override
    public void visit(RegulationSpecs regulationSpecs) {
        throw new UnsupportedOperationException("PowerLineSpecVisitor does not support visiting RegulationSpecs.");
    }
}