package SemanticModelSpecs;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OntoSpec_Turbine {
//    private static final String NS = "http://Wind/";
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_Turbine = NS + "Turbine#";
    private static final String NS_WindFarm = NS + "WindFarm#";

    public static void main(String[] args) {

        // Only creates the classes and their properties.
        // No instances/individuals are created.
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        // Classes
        OntClass Turbine = m.createClass(NS_Turbine);

        // Turbine properties
        DatatypeProperty hasTurbineID = m.createDatatypeProperty(NS_Turbine + "hasTurbineID");
        hasTurbineID.addDomain(Turbine);
        hasTurbineID.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasTurbineModel = m.createDatatypeProperty(NS_Turbine + "hasTurbineModel");
        hasTurbineModel.addDomain(Turbine);
        hasTurbineModel.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasWindClass = m.createDatatypeProperty(NS_Turbine + "hasWindClass");
        hasWindClass.addDomain(Turbine);
        hasWindClass.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasRatedAerodynamicPower = m.createDatatypeProperty(NS_Turbine + "hasRatedAerodynamicPower");
        hasRatedAerodynamicPower.addDomain(Turbine);
        hasRatedAerodynamicPower.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasHubHeight = m.createDatatypeProperty(NS_Turbine + "hasHubHeight");
        hasHubHeight.addDomain(Turbine);
        hasHubHeight.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasCutInWindSpeed = m.createDatatypeProperty(NS_Turbine + "hasCutInWindSpeed");
        hasCutInWindSpeed.addDomain(Turbine);
        hasCutInWindSpeed.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasRotorConeAngle = m.createDatatypeProperty(NS_Turbine + "hasRotorConeAngle");
        hasRotorConeAngle.addDomain(Turbine);
        hasRotorConeAngle.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasRotorSolidity = m.createDatatypeProperty(NS_Turbine + "hasRotorSolidity");
        hasRotorSolidity.addDomain(Turbine);
        hasRotorSolidity.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasBladeMass = m.createDatatypeProperty(NS_Turbine + "hasBladeMass");
        hasBladeMass.addDomain(Turbine);
        hasBladeMass.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasBladeCost = m.createDatatypeProperty(NS_Turbine + "hasBladeCost");
        hasBladeCost.addDomain(Turbine);
        hasBladeCost.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasAerodynamicAEP = m.createDatatypeProperty(NS_Turbine + "hasAerodynamicAEP");
        hasAerodynamicAEP.addDomain(Turbine);
        hasAerodynamicAEP.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasICC = m.createDatatypeProperty(NS_Turbine + "hasICC");
        hasICC.addDomain(Turbine);
        hasICC.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasRatedElectricalPower = m.createDatatypeProperty(NS_Turbine + "hasRatedElectricalPower");
        hasRatedElectricalPower.addDomain(Turbine);
        hasRatedElectricalPower.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasGenEfficiency = m.createDatatypeProperty(NS_Turbine + "hasGenEfficiency");
        hasGenEfficiency.addDomain(Turbine);
        hasGenEfficiency.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasRotorDiameter = m.createDatatypeProperty(NS_Turbine + "hasRotorDiameter");
        hasRotorDiameter.addDomain(Turbine);
        hasRotorDiameter.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasCutOutWindSpeed = m.createDatatypeProperty(NS_Turbine + "hasCutOutWindSpeed");
        hasCutOutWindSpeed.addDomain(Turbine);
        hasCutOutWindSpeed.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasNacelleUptiltAngle = m.createDatatypeProperty(NS_Turbine + "hasNacelleUptiltAngle");
        hasNacelleUptiltAngle.addDomain(Turbine);
        hasNacelleUptiltAngle.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasMaxVtip = m.createDatatypeProperty(NS_Turbine + "hasMaxVtip");
        hasMaxVtip.addDomain(Turbine);
        hasMaxVtip.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasTowerMass = m.createDatatypeProperty(NS_Turbine + "hasTowerMass");
        hasTowerMass.addDomain(Turbine);
        hasTowerMass.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasTowerCost = m.createDatatypeProperty(NS_Turbine + "hasTowerCost");
        hasTowerCost.addDomain(Turbine);
        hasTowerCost.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasElectricalAEP = m.createDatatypeProperty(NS_Turbine + "hasElectricalAEP");
        hasElectricalAEP.addDomain(Turbine);
        hasElectricalAEP.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasCOE = m.createDatatypeProperty(NS_Turbine + "hasCOE");
        hasCOE.addDomain(Turbine);
        hasCOE.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Based on the DTU 10-MW OffShore reference wind turbine.
        DatatypeProperty hasRotorOrientation = m.createDatatypeProperty(NS_Turbine + "hasRotorOrientation");
        hasRotorOrientation.addDomain(Turbine);
        hasRotorOrientation.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasControl = m.createDatatypeProperty(NS_Turbine + "hasControl");
        hasControl.addDomain(Turbine);
        hasControl.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasRatedWindSpeed = m.createDatatypeProperty(NS_Turbine + "hasRatedWindSpeed");
        hasRatedWindSpeed.addDomain(Turbine);
        hasRatedWindSpeed.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasNumberOfBlades = m.createDatatypeProperty(NS_Turbine + "hasNumberOfBlades");
        hasNumberOfBlades.addDomain(Turbine);
        hasNumberOfBlades.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#int"));

        DatatypeProperty hasAirfoilSeries = m.createDatatypeProperty(NS_Turbine + "hasAirfoilSeries");
        hasAirfoilSeries.addDomain(Turbine);
        hasAirfoilSeries.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasHubDiameter = m.createDatatypeProperty(NS_Turbine + "hasHubDiameter");
        hasHubDiameter.addDomain(Turbine);
        hasHubDiameter.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasDriveTrain = m.createDatatypeProperty(NS_Turbine + "hasDriveTrain");
        hasDriveTrain.addDomain(Turbine);
        hasDriveTrain.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasMinRotorSpeed = m.createDatatypeProperty(NS_Turbine + "hasMinRotorSpeed");
        hasMinRotorSpeed.addDomain(Turbine);
        hasMinRotorSpeed.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasMaxRotorSpeed = m.createDatatypeProperty(NS_Turbine + "hasMaxRotorSpeed");
        hasMaxRotorSpeed.addDomain(Turbine);
        hasMaxRotorSpeed.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasGearboxRatio = m.createDatatypeProperty(NS_Turbine + "hasGearboxRatio");
        hasGearboxRatio.addDomain(Turbine);
        hasGearboxRatio.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasHubOverhang = m.createDatatypeProperty(NS_Turbine + "hasHubOverhang");
        hasHubOverhang.addDomain(Turbine);
        hasHubOverhang.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasShaftTiltAngle = m.createDatatypeProperty(NS_Turbine + "hasShaftTiltAngle");
        hasShaftTiltAngle.addDomain(Turbine);
        hasShaftTiltAngle.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasBladePrebend = m.createDatatypeProperty(NS_Turbine + "hasBladePrebend");
        hasBladePrebend.addDomain(Turbine);
        hasBladePrebend.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasNacelleMass = m.createDatatypeProperty(NS_Turbine + "hasNacelleMass");
        hasNacelleMass.addDomain(Turbine);
        hasNacelleMass.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Performance/operational parameters
        DatatypeProperty hasInclination = m.createDatatypeProperty(NS_Turbine + "hasInclination");
        hasInclination.addDomain(Turbine);
        hasInclination.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasTurbineStatus = m.createDatatypeProperty(NS_Turbine + "hasTurbineStatus");
        hasTurbineStatus.addDomain(Turbine);
        hasTurbineStatus.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasAvailability = m.createDatatypeProperty(NS_Turbine + "hasAvailability");
        hasAvailability.addDomain(Turbine);
        hasAvailability.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasPowerOutput = m.createDatatypeProperty(NS_Turbine + "hasPowerOutput");
        hasPowerOutput.addDomain(Turbine);
        hasPowerOutput.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasCapacityFactor = m.createDatatypeProperty(NS_Turbine + "hasCapacityFactor");
        hasCapacityFactor.addDomain(Turbine);
        hasCapacityFactor.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasDownTime = m.createDatatypeProperty(NS_Turbine + "hasDownTime");
        hasDownTime.addDomain(Turbine);
        hasDownTime.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasTurbineType = m.createDatatypeProperty(NS_Turbine + "hasTurbineType");
        hasTurbineType.addDomain(Turbine);
        hasTurbineType.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasTurbinePositionX = m.createDatatypeProperty(NS_Turbine + "hasTurbinePositionX");
        hasTurbinePositionX.addDomain(Turbine);
        hasTurbinePositionX.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasTurbinePositionY = m.createDatatypeProperty(NS_Turbine + "hasTurbinePositionY");
        hasTurbinePositionY.addDomain(Turbine);
        hasTurbinePositionY.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Geometry
        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_Turbine + "hasGeometry");
        hasGeometry.addDomain(Turbine);
        hasGeometry.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        // For hurricane response
        DatatypeProperty hasPitchAngle = m.createDatatypeProperty(NS_Turbine + "hasPitchAngle");
        hasPitchAngle.addDomain(Turbine);
        hasPitchAngle.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasYawAngle = m.createDatatypeProperty(NS_Turbine + "hasYawAngle");
        hasYawAngle.addDomain(Turbine);
        hasYawAngle.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        try (OutputStream out = new FileOutputStream("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/Turbine.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
