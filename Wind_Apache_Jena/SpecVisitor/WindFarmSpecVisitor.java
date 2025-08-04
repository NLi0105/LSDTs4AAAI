package SpecVisitor;

import Specification.*;
import Specification.LCASpecs.LifeCycleSpecs;
import Specification.RegulationSpecs.RegulationSpecs;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;
import Ontology.OntologyModel;
import org.locationtech.jts.io.WKTWriter;

public class WindFarmSpecVisitor implements SpecVisitor {

    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_Turbine = NS + "Turbine#";
    private static final String NS_WindFarm = NS + "WindFarm#";
    private OntModel model;

    // Wind farm specs properties
    private DatatypeProperty hasWindFarmID;
    private DatatypeProperty hasSite;
    private DatatypeProperty hasAEP;
    private DatatypeProperty hasTurbineSpacing;
    private DatatypeProperty hasNumberOfTurbinesPerRow;
    private DatatypeProperty hasNumberOfTurbines;
    private DatatypeProperty hasLocation;
    private DatatypeProperty hasFarmPowerOutput;
    private ObjectProperty hasTurbine;
    private DatatypeProperty hasWindFarmStatus;

    // Geometry
    private DatatypeProperty hasGeometry;

    public void setModel(OntologyModel ontologyModel) {
        this.model = ontologyModel.getModel();
        this.hasWindFarmID = model.getDatatypeProperty(NS_WindFarm + "hasWindFarmID");
        this.hasSite = model.getDatatypeProperty(NS_WindFarm + "hasSite");
        this.hasAEP = model.getDatatypeProperty(NS_WindFarm + "hasAEP");
        this.hasTurbineSpacing = model.getDatatypeProperty(NS_WindFarm + "hasTurbineSpacing");
        this.hasNumberOfTurbinesPerRow = model.getDatatypeProperty(NS_WindFarm + "hasNumberOfTurbinesPerRow");
        this.hasNumberOfTurbines = model.getDatatypeProperty(NS_WindFarm + "hasNumberOfTurbines");
        this.hasLocation = model.getDatatypeProperty(NS_WindFarm + "hasLocation");
        this.hasFarmPowerOutput = model.getDatatypeProperty(NS_WindFarm + "hasFarmPowerOutput");
        this.hasTurbine = model.getObjectProperty(NS_WindFarm + "hasTurbine");
        this.hasGeometry = model.getDatatypeProperty(NS_WindFarm + "hasGeometry");
        this.hasWindFarmStatus = model.getDatatypeProperty(NS_WindFarm + "hasWindFarmStatus");
    }

    @Override
    public void visit(WindFarmSpecs windFarmSpecs) {
        if (model == null) {
            throw new IllegalStateException("Model is not set for the wind farm specs visitor.");
        }

        Individual windFarmSpecsIndividual = model.createIndividual(NS_WindFarm + windFarmSpecs.getWindFarmID(), model.getOntClass(NS_WindFarm));
        windFarmSpecsIndividual.addProperty(hasWindFarmID, windFarmSpecs.getWindFarmID());
        windFarmSpecsIndividual.addProperty(hasSite, windFarmSpecs.getSite());
        windFarmSpecsIndividual.addProperty(hasAEP, model.createTypedLiteral(windFarmSpecs.getAEP(), XSDDatatype.XSDdouble));
        windFarmSpecsIndividual.addProperty(hasTurbineSpacing, model.createTypedLiteral(windFarmSpecs.getTurbineSpacing(), XSDDatatype.XSDdouble));
        windFarmSpecsIndividual.addProperty(hasNumberOfTurbinesPerRow, model.createTypedLiteral(windFarmSpecs.getNumberOfTurbinesPerRow(), XSDDatatype.XSDdouble));
        windFarmSpecsIndividual.addProperty(hasNumberOfTurbines, model.createTypedLiteral(windFarmSpecs.getNumberOfTurbines(), XSDDatatype.XSDdouble));
        windFarmSpecsIndividual.addProperty(hasLocation, windFarmSpecs.getLocation());
        windFarmSpecsIndividual.addProperty(hasFarmPowerOutput, model.createTypedLiteral(windFarmSpecs.getFarmPowerOutput(), XSDDatatype.XSDdouble));
        windFarmSpecsIndividual.addProperty(hasWindFarmStatus, windFarmSpecs.getWindFarmStatus());

        // Connect wind farm specs with turbines
        for (TurbineSpecs turbineSpecs : windFarmSpecs.getTurbines()) {
            Individual turbineSpecsIndividual = model.getIndividual(NS_Turbine + turbineSpecs.getTurbineID());
            if (turbineSpecsIndividual != null) {
                windFarmSpecsIndividual.addProperty(hasTurbine, turbineSpecsIndividual);
            }
        }

        // Geometry
        WKTWriter wktWriter = new WKTWriter();
        String wkt = wktWriter.write(windFarmSpecs.getGeometry());
        windFarmSpecsIndividual.addProperty(hasGeometry, wkt, XSDDatatype.XSDstring);
    }

    @Override
    public void visit(TurbineSpecs turbineSpecs) {
        throw new UnsupportedOperationException("WindFarmSpecVisitor does not support visiting TurbineSpecs.");
    }

    @Override
    public void visit(WindResourceSpecs windResourceSpecs) {
        throw new UnsupportedOperationException("WindFarmSpecVisitor does not support visiting WindResourceSpecs.");
    }

    @Override
    public void visit(PowerLineSpecs powerLineSpecs) {
        throw new UnsupportedOperationException("WindFarmSpecVisitor does not support visiting PowerLineSpecs.");
    }

    @Override
    public void visit(LifeCycleSpecs lifeCycleSpecs) {
        throw new UnsupportedOperationException("WindFarmSpecVisitor does not support visiting LifeCycle.");
    }

    @Override
    public void visit(SubstationSpecs substationSpecs) {
        throw new UnsupportedOperationException("WindFarmSpecVisitor does not support visiting SubstationSpecs.");
    }

    @Override
    public void visit(RegulationSpecs regulationSpecs) {
        throw new UnsupportedOperationException("WindFarmSpecVisitor does not support visiting RegulationSpecs.");
    }
}