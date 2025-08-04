package SpecVisitor;

import Specification.*;
import Specification.LCASpecs.LifeCycleSpecs;
import Specification.RegulationSpecs.RegulationSpecs;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;
import Ontology.OntologyModel;
import org.locationtech.jts.io.WKTWriter;

public class TurbineSpecVisitor implements SpecVisitor {

    private static final String NS = "windfarm/";
    private static final String NS_Turbine = NS + "Turbine#";
    private OntModel model;

    // Turbine properties
    private DatatypeProperty hasTurbineID;
    private DatatypeProperty hasTurbineModel;
    private DatatypeProperty hasWindClass;
    private DatatypeProperty hasRatedAerodynamicPower;
    private DatatypeProperty hasHubHeight;
    private DatatypeProperty hasCutInWindSpeed;
    private DatatypeProperty hasRotorConeAngle;
    private DatatypeProperty hasRotorSolidity;
    private DatatypeProperty hasBladeMass;
    private DatatypeProperty hasBladeCost;
    private DatatypeProperty hasAerodynamicAEP;
    private DatatypeProperty hasICC;
    private DatatypeProperty hasRatedElectricalPower;
    private DatatypeProperty hasGenEfficiency;
    private DatatypeProperty hasRotorDiameter;
    private DatatypeProperty hasCutOutWindSpeed;
    private DatatypeProperty hasNacelleUptiltAngle;
    private DatatypeProperty hasMaxVtip;
    private DatatypeProperty hasTowerMass;
    private DatatypeProperty hasTowerCost;
    private DatatypeProperty hasElectricalAEP;
    private DatatypeProperty hasCOE;
    private DatatypeProperty hasRotorOrientation;
    private DatatypeProperty hasControl;
    private DatatypeProperty hasRatedWindSpeed;
    private DatatypeProperty hasNumberOfBlades;
    private DatatypeProperty hasAirfoilSeries;
    private DatatypeProperty hasHubDiameter;
    private DatatypeProperty hasDriveTrain;
    private DatatypeProperty hasMinRotorSpeed;
    private DatatypeProperty hasMaxRotorSpeed;
    private DatatypeProperty hasGearboxRatio;
    private DatatypeProperty hasHubOverhang;
    private DatatypeProperty hasShaftTiltAngle;
    private DatatypeProperty hasBladePrebend;
    private DatatypeProperty hasNacelleMass;
    private DatatypeProperty hasInclination;
    private DatatypeProperty hasTurbineStatus;
    private DatatypeProperty hasAvailability;
    private DatatypeProperty hasPowerOutput;
    private DatatypeProperty hasCapacityFactor;
    private DatatypeProperty hasDownTime;
    private DatatypeProperty hasTurbineType;
    private DatatypeProperty hasTurbinePositionX;
    private DatatypeProperty hasTurbinePositionY;

    // Geometry
    private DatatypeProperty hasGeometry;

    // For hurricane response
    private DatatypeProperty hasPitchAngle;
    private DatatypeProperty hasYawAngle;

    public void setModel(OntologyModel ontologyModel) {
        this.model = ontologyModel.getModel();
        this.hasTurbineID = model.getDatatypeProperty(NS_Turbine + "hasTurbineID");
        this.hasTurbineModel = model.getDatatypeProperty(NS_Turbine + "hasTurbineModel");
        this.hasWindClass = model.getDatatypeProperty(NS_Turbine + "hasWindClass");
        this.hasRatedAerodynamicPower = model.getDatatypeProperty(NS_Turbine + "hasRatedAerodynamicPower");
        this.hasHubHeight = model.getDatatypeProperty(NS_Turbine + "hasHubHeight");
        this.hasCutInWindSpeed = model.getDatatypeProperty(NS_Turbine + "hasCutInWindSpeed");
        this.hasRotorConeAngle = model.getDatatypeProperty(NS_Turbine + "hasRotorConeAngle");
        this.hasRotorSolidity = model.getDatatypeProperty(NS_Turbine + "hasRotorSolidity");
        this.hasBladeMass = model.getDatatypeProperty(NS_Turbine + "hasBladeMass");
        this.hasBladeCost = model.getDatatypeProperty(NS_Turbine + "hasBladeCost");
        this.hasAerodynamicAEP = model.getDatatypeProperty(NS_Turbine + "hasAerodynamicAEP");
        this.hasICC = model.getDatatypeProperty(NS_Turbine + "hasICC");
        this.hasRatedElectricalPower = model.getDatatypeProperty(NS_Turbine + "hasRatedElectricalPower");
        this.hasGenEfficiency = model.getDatatypeProperty(NS_Turbine + "hasGenEfficiency");
        this.hasRotorDiameter = model.getDatatypeProperty(NS_Turbine + "hasRotorDiameter");
        this.hasCutOutWindSpeed = model.getDatatypeProperty(NS_Turbine + "hasCutOutWindSpeed");
        this.hasNacelleUptiltAngle = model.getDatatypeProperty(NS_Turbine + "hasNacelleUptiltAngle");
        this.hasMaxVtip = model.getDatatypeProperty(NS_Turbine + "hasMaxVtip");
        this.hasTowerMass = model.getDatatypeProperty(NS_Turbine + "hasTowerMass");
        this.hasTowerCost = model.getDatatypeProperty(NS_Turbine + "hasTowerCost");
        this.hasElectricalAEP = model.getDatatypeProperty(NS_Turbine + "hasElectricalAEP");
        this.hasCOE = model.getDatatypeProperty(NS_Turbine + "hasCOE");
        this.hasRotorOrientation = model.getDatatypeProperty(NS_Turbine + "hasRotorOrientation");
        this.hasControl = model.getDatatypeProperty(NS_Turbine + "hasControl");
        this.hasPitchAngle = model.getDatatypeProperty(NS_Turbine + "hasPitchAngle");
        this.hasYawAngle = model.getDatatypeProperty(NS_Turbine + "hasYawAngle");
        this.hasRatedWindSpeed = model.getDatatypeProperty(NS_Turbine + "hasRatedWindSpeed");
        this.hasNumberOfBlades = model.getDatatypeProperty(NS_Turbine + "hasNumberOfBlades");
        this.hasAirfoilSeries = model.getDatatypeProperty(NS_Turbine + "hasAirfoilSeries");
        this.hasHubDiameter = model.getDatatypeProperty(NS_Turbine + "hasHubDiameter");
        this.hasDriveTrain = model.getDatatypeProperty(NS_Turbine + "hasDriveTrain");
        this.hasMinRotorSpeed = model.getDatatypeProperty(NS_Turbine + "hasMinRotorSpeed");
        this.hasMaxRotorSpeed = model.getDatatypeProperty(NS_Turbine + "hasMaxRotorSpeed");
        this.hasGearboxRatio = model.getDatatypeProperty(NS_Turbine + "hasGearboxRatio");
        this.hasHubOverhang = model.getDatatypeProperty(NS_Turbine + "hasHubOverhang");
        this.hasShaftTiltAngle = model.getDatatypeProperty(NS_Turbine + "hasShaftTiltAngle");
        this.hasBladePrebend = model.getDatatypeProperty(NS_Turbine + "hasBladePrebend");
        this.hasNacelleMass = model.getDatatypeProperty(NS_Turbine + "hasNacelleMass");
        this.hasInclination = model.getDatatypeProperty(NS_Turbine + "hasInclination");
        this.hasTurbineStatus = model.getDatatypeProperty(NS_Turbine + "hasTurbineStatus");
        this.hasAvailability = model.getDatatypeProperty(NS_Turbine + "hasAvailability");
        this.hasPowerOutput = model.getDatatypeProperty(NS_Turbine + "hasPowerOutput");
        this.hasCapacityFactor = model.getDatatypeProperty(NS_Turbine + "hasCapacityFactor");
        this.hasDownTime = model.getDatatypeProperty(NS_Turbine + "hasDownTime");
        this.hasTurbineType = model.getDatatypeProperty(NS_Turbine + "hasTurbineType");
        this.hasTurbinePositionX = model.getDatatypeProperty(NS_Turbine + "hasTurbinePositionX");
        this.hasTurbinePositionY = model.getDatatypeProperty(NS_Turbine + "hasTurbinePositionY");

        // Geometry
        this.hasGeometry = model.getDatatypeProperty(NS_Turbine + "hasGeometry");
    }

    @Override
    public void visit(TurbineSpecs turbineSpecs) {
        if (model == null) {
            throw new IllegalStateException("Model is not set for the turbine specs visitor.");
        }

        Individual turbineSpecsIndividual = model.createIndividual(NS_Turbine + turbineSpecs.getTurbineID(), model.getOntClass(NS_Turbine));
        turbineSpecsIndividual.addProperty(hasTurbineID, turbineSpecs.getTurbineID());
        turbineSpecsIndividual.addProperty(hasTurbineModel, turbineSpecs.getTurbineModel());
        turbineSpecsIndividual.addProperty(hasWindClass, turbineSpecs.getWindClass());
        turbineSpecsIndividual.addProperty(hasRatedAerodynamicPower, model.createTypedLiteral(turbineSpecs.getRatedAerodynamicPower(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasHubHeight, model.createTypedLiteral(turbineSpecs.getHubHeight(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasCutInWindSpeed, model.createTypedLiteral(turbineSpecs.getCutInWindSpeed(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasRotorConeAngle, model.createTypedLiteral(turbineSpecs.getRotorConeAngle(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasRotorSolidity, model.createTypedLiteral(turbineSpecs.getRotorSolidity(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasBladeMass, model.createTypedLiteral(turbineSpecs.getBladeMass(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasBladeCost, model.createTypedLiteral(turbineSpecs.getBladeCost(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasAerodynamicAEP, model.createTypedLiteral(turbineSpecs.getAerodynamicAEP(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasICC, model.createTypedLiteral(turbineSpecs.getICC(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasRatedElectricalPower, model.createTypedLiteral(turbineSpecs.getRatedElectricalPower(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasGenEfficiency, model.createTypedLiteral(turbineSpecs.getGenEfficiency(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasRotorDiameter, model.createTypedLiteral(turbineSpecs.getRotorDiameter(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasCutOutWindSpeed, model.createTypedLiteral(turbineSpecs.getCutOutWindSpeed(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasNacelleUptiltAngle, model.createTypedLiteral(turbineSpecs.getNacelleUptiltAngle(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasMaxVtip, model.createTypedLiteral(turbineSpecs.getMaxVtip(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasTowerMass, model.createTypedLiteral(turbineSpecs.getTowerMass(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasTowerCost, model.createTypedLiteral(turbineSpecs.getTowerCost(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasElectricalAEP, model.createTypedLiteral(turbineSpecs.getElectricalAEP(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasCOE, model.createTypedLiteral(turbineSpecs.getCOE(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasRotorOrientation, model.createTypedLiteral(turbineSpecs.getRotorOrientation(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasPitchAngle, model.createTypedLiteral(turbineSpecs.getPitchAngle(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasYawAngle, model.createTypedLiteral(turbineSpecs.getYawAngle(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasControl, turbineSpecs.getControl());
        turbineSpecsIndividual.addProperty(hasRatedWindSpeed, model.createTypedLiteral(turbineSpecs.getRatedWindSpeed(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasNumberOfBlades, model.createTypedLiteral(turbineSpecs.getNumberOfBlades(), XSDDatatype.XSDint));
        turbineSpecsIndividual.addProperty(hasAirfoilSeries, turbineSpecs.getAirfoilSeries());
        turbineSpecsIndividual.addProperty(hasHubDiameter, model.createTypedLiteral(turbineSpecs.getHubDiameter(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasDriveTrain, turbineSpecs.getDriveTrain());
        turbineSpecsIndividual.addProperty(hasMinRotorSpeed, model.createTypedLiteral(turbineSpecs.getMinRotorSpeed(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasMaxRotorSpeed, model.createTypedLiteral(turbineSpecs.getMaxRotorSpeed(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasGearboxRatio, model.createTypedLiteral(turbineSpecs.getGearboxRatio(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasHubOverhang, model.createTypedLiteral(turbineSpecs.getHubOverhang(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasShaftTiltAngle, model.createTypedLiteral(turbineSpecs.getShaftTiltAngle(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasBladePrebend, model.createTypedLiteral(turbineSpecs.getBladePrebend(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasNacelleMass, model.createTypedLiteral(turbineSpecs.getNacelleMass(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasInclination, model.createTypedLiteral(turbineSpecs.getInclination(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasTurbineStatus, turbineSpecs.getTurbineStatus());
        turbineSpecsIndividual.addProperty(hasAvailability, turbineSpecs.getAvailability());
        turbineSpecsIndividual.addProperty(hasPowerOutput, model.createTypedLiteral(turbineSpecs.getPowerOutput(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasCapacityFactor, model.createTypedLiteral(turbineSpecs.getCapacityFactor(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasDownTime, model.createTypedLiteral(turbineSpecs.getDownTime(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasTurbineType, turbineSpecs.getTurbineType());
        turbineSpecsIndividual.addProperty(hasTurbinePositionX, model.createTypedLiteral(turbineSpecs.getTurbinePositionX(), XSDDatatype.XSDdouble));
        turbineSpecsIndividual.addProperty(hasTurbinePositionY, model.createTypedLiteral(turbineSpecs.getTurbinePositionY(), XSDDatatype.XSDdouble));

        // Geometry
        WKTWriter wktWriter = new WKTWriter();
        String wkt = wktWriter.write(turbineSpecs.getGeometry());
        turbineSpecsIndividual.addProperty(hasGeometry, wkt, XSDDatatype.XSDstring);
    }

    @Override
    public void visit(WindFarmSpecs windFarmSpecs) {
        throw new UnsupportedOperationException("TurbineSpecVisitor does not support visiting WindFarmSpecs.");
    }

    @Override
    public void visit(WindResourceSpecs windResourceSpecs) {
        throw new UnsupportedOperationException("TurbineSpecVisitor does not support visiting WindResourceSpecs.");
    }

    @Override
    public void visit(PowerLineSpecs powerLineSpecs) {
        throw new UnsupportedOperationException("TurbineSpecVisitor does not support visiting PowerLineSpecs.");
    }

    @Override
    public void visit(LifeCycleSpecs lifeCycleSpecs) {
        throw new UnsupportedOperationException("TurbineSpecVisitor does not support visiting LifeCycle.");
    }

    @Override
    public void visit(SubstationSpecs substationSpecs) {
        throw new UnsupportedOperationException("TurbineSpecVisitor does not support visiting SubstationSpecs.");
    }

    @Override
    public void visit(RegulationSpecs regulationSpecs) {
        throw new UnsupportedOperationException("TurbineSpecVisitor does not support visiting RegulationSpecs.");
    }

}