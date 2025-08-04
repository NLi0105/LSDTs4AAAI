package org.apache.jena;

import Ontology.OntologyModel;
import Builder.Turbine;
import Builder.TurbineBuilder;
import Builder.WindFarm;
import Visitor.TurbineVisitor;
import Visitor.WindFarmVisitor;
import org.apache.jena.ontology.OntModelSpec;

public class TestABSOnto {
    public static void main(String[] args) {
        OntologyModel model1 = new OntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        model1.setModelName("WindFarm Model");
        model1.loadOntologyFromFile("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Turbine.xml");
        model1.loadOntologyFromFile("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/WindFarm.xml");

        // Create an instance of Turbine using the builder
        Turbine turbine1 = new TurbineBuilder()
                .setTurbineID("Turbine1")
                .setTurbineModel("DTU 3.4-MW Land-Based")
                .setWindClass("3A")
                .setRatedAerodynamicPower(3.4)
                .setHubHeight(65)
                .setCutInWindSpeed(3)
                .setRotorConeAngle(5)
                .setRotorSolidity(0.05)
                .setBladeMass(11000)
                .setBladeMass(11000)
                .setAerodynamicAEP(0.5)
                .setICC(0.1)
                .setRatedElectricalPower(3.4)
                .setGenEfficiency(0.95)
                .setRotorDiameter(120)
                .setCutOutWindSpeed(25)
                .setNacelleUptiltAngle(5)
                .setMaxVtip(80)
                .setTowerMass(100000)
                .setTowerCost(1000000)
                .setElectricalAEP(0.5)
                .setCOE(0.05)
                .setRotorOrientation(0)
                .setControl("Pitch")
                .setRatedWindSpeed(12)
                .setNumberOfBlades(3)
                .setAirfoilSeries("NACA 64-618")
                .setHubDiameter(3)
                .setDriveTrain("Gearbox")
                .setMinRotorSpeed(10)
                .setMaxRotorSpeed(20)
                .setGearboxRatio(100)
                .setHubOverhang(1.5)
                .setShaftTiltAngle(5)
                .setBladePrebend(0.5)
                .setNacelleMass(10000)
                .setInclination(5)
                .setTurbineStatus("Operational")
                .setAvailability("Available")
                .setPowerOutput(3.4)
                .setCapacityFactor(0.3)
                .setDownTime(0.02)
                .setTurbineType("Onshore")
                .setTurbinePositionX(0)
                .setTurbinePositionY(0)
                .build();

        WindFarm windFarm1 = new WindFarm();
        windFarm1.addTurbine(turbine1);
        windFarm1.setWindFarmID("WindFarm1");
        windFarm1.setSite("Site1");
        windFarm1.setAEP(100);
        windFarm1.setTurbineSpacing(5);
        windFarm1.setNumberOfTurbinesPerRow(5);
        windFarm1.setNumberOfTurbines(25);
        windFarm1.setLocation(0);
        windFarm1.setFarmPowerOutput(100);

        // Create separate visitors for turbines and wind farms
        TurbineVisitor turbineVisitor = new TurbineVisitor();
        turbineVisitor.setModel(model1);
        WindFarmVisitor windFarmVisitor = new WindFarmVisitor();
        windFarmVisitor.setModel(model1);


        // Visit the turbine to build individuals
        turbine1.accept(turbineVisitor);
        windFarm1.accept(windFarmVisitor);

        model1.setNamespaceFilter("http://Wind");
        model1.printModelInfo();
        model1.printAllClasses();
//        model1.printAllIndividuals();

        model1.findIndividualByName("Turbine1");


        // Print the model to the console in Turtle format
//        model1.getModel().write(System.out, "TURTLE");
    }
}
