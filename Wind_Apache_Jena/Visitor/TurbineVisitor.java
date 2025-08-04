package Visitor;

import Builder.Geospatial;
import Builder.Turbine;
import Builder.WindFarm;
import Builder.WindResource;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;
import Ontology.OntologyModel;
import org.locationtech.jts.io.WKTWriter;

public class TurbineVisitor implements Visitor {

//    private static final String NS_Turbine = "http://Wind/Turbine#";
    private static final String NS = "windfarm/";
    private static final String NS_Turbine = NS + "Turbine#";
    private static final String NS_WindFarm = NS + "WindFarm#";
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
    public void visit(Turbine turbine) {
        if (model == null) {
            throw new IllegalStateException("Model is not set for the turbine visitor.");
        }

        Individual turbineIndividual = model.createIndividual(NS_Turbine + turbine.getTurbineID(), model.getOntClass(NS_Turbine));
        turbineIndividual.addProperty(hasTurbineID, turbine.getTurbineID());
        turbineIndividual.addProperty(hasTurbineModel, turbine.getTurbineModel());
        turbineIndividual.addProperty(hasWindClass, turbine.getWindClass());
        turbineIndividual.addProperty(hasRatedAerodynamicPower, model.createTypedLiteral(turbine.getRatedAerodynamicPower(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasHubHeight, model.createTypedLiteral(turbine.getHubHeight(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasCutInWindSpeed, model.createTypedLiteral(turbine.getCutInWindSpeed(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasRotorConeAngle, model.createTypedLiteral(turbine.getRotorConeAngle(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasRotorSolidity, model.createTypedLiteral(turbine.getRotorSolidity(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasBladeMass, model.createTypedLiteral(turbine.getBladeMass(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasBladeCost, model.createTypedLiteral(turbine.getBladeCost(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasAerodynamicAEP, model.createTypedLiteral(turbine.getAerodynamicAEP(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasICC, model.createTypedLiteral(turbine.getICC(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasRatedElectricalPower, model.createTypedLiteral(turbine.getRatedElectricalPower(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasGenEfficiency, model.createTypedLiteral(turbine.getGenEfficiency(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasRotorDiameter, model.createTypedLiteral(turbine.getRotorDiameter(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasCutOutWindSpeed, model.createTypedLiteral(turbine.getCutOutWindSpeed(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasNacelleUptiltAngle, model.createTypedLiteral(turbine.getNacelleUptiltAngle(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasMaxVtip, model.createTypedLiteral(turbine.getMaxVtip(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasTowerMass, model.createTypedLiteral(turbine.getTowerMass(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasTowerCost, model.createTypedLiteral(turbine.getTowerCost(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasElectricalAEP, model.createTypedLiteral(turbine.getElectricalAEP(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasCOE, model.createTypedLiteral(turbine.getCOE(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasRotorOrientation, model.createTypedLiteral(turbine.getRotorOrientation(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasControl, turbine.getControl());
        turbineIndividual.addProperty(hasRatedWindSpeed, model.createTypedLiteral(turbine.getRatedWindSpeed(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasNumberOfBlades, model.createTypedLiteral(turbine.getNumberOfBlades(), XSDDatatype.XSDint));
        turbineIndividual.addProperty(hasAirfoilSeries, turbine.getAirfoilSeries());
        turbineIndividual.addProperty(hasHubDiameter, model.createTypedLiteral(turbine.getHubDiameter(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasDriveTrain, turbine.getDriveTrain());
        turbineIndividual.addProperty(hasMinRotorSpeed, model.createTypedLiteral(turbine.getMinRotorSpeed(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasMaxRotorSpeed, model.createTypedLiteral(turbine.getMaxRotorSpeed(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasGearboxRatio, model.createTypedLiteral(turbine.getGearboxRatio(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasHubOverhang, model.createTypedLiteral(turbine.getHubOverhang(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasShaftTiltAngle, model.createTypedLiteral(turbine.getShaftTiltAngle(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasBladePrebend, model.createTypedLiteral(turbine.getBladePrebend(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasNacelleMass, model.createTypedLiteral(turbine.getNacelleMass(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasInclination, model.createTypedLiteral(turbine.getInclination(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasTurbineStatus, turbine.getTurbineStatus());
        turbineIndividual.addProperty(hasAvailability, model.createTypedLiteral(turbine.getAvailability(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasPowerOutput, model.createTypedLiteral(turbine.getPowerOutput(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasCapacityFactor, model.createTypedLiteral(turbine.getCapacityFactor(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasDownTime, model.createTypedLiteral(turbine.getDownTime(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasTurbineType, turbine.getTurbineType());
        turbineIndividual.addProperty(hasTurbinePositionX, model.createTypedLiteral(turbine.getTurbinePositionX(), XSDDatatype.XSDdouble));
        turbineIndividual.addProperty(hasTurbinePositionY, model.createTypedLiteral(turbine.getTurbinePositionY(), XSDDatatype.XSDdouble));

        // Geometry
        WKTWriter wktWriter = new WKTWriter();
        String wkt = wktWriter.write(turbine.getGeometry());
        turbineIndividual.addProperty(hasGeometry, wkt, XSDDatatype.XSDstring);
    }

    @Override
    public void visit(WindFarm windFarm) {
        throw new UnsupportedOperationException("TurbineVisitor does not support visiting WindFarm.");
    }

    @Override
    public void visit(Geospatial geospatial) {
        throw new UnsupportedOperationException("TurbineVisitor does not support visiting Geospatial.");
    }

    @Override
    public void visit(WindResource windResource) {
        throw new UnsupportedOperationException("TurbineVisitor does not support visiting WindResource.");
    }
}