package org.apache.jena;

import Builder.*;
import Ontology.OntologyModel;
import Rules.RuleLoader;
import Visitor.GeospatialVisitor;
import Visitor.TurbineVisitor;
import Visitor.WindFarmVisitor;
import Visitor.WindResourceVisitor;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.WKTReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

import static Utils.wktReader.extractGeometries;

public class Main {
//    private static final String NS = "http://Wind/";
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_Turbine = NS + "Turbine#";
    private static final String NS_WindFarm = NS + "WindFarm#";

    public static void main(String[] args) {
        // Create a blank ontology model
//        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
        // Use the user-defined ontology model
        OntologyModel model = new OntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        // Read in the pre-defined ontology
        model.loadOntologyFromFile("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/Turbine.xml");
        model.loadOntologyFromFile("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/WindFarm.xml");
        model.loadOntologyFromFile("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/Geospatial.xml");
        model.loadOntologyFromFile("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/WindResource.xml");

        // Create geometries
        String filePath = "/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/GeoSpatial_manual.wkt";
        List<Geometry> geometries = extractGeometries(filePath);

        Geometry p1 = geometries.get(0);
        Geometry poly1 = geometries.get(1);

        // -------------------------------------------------------------------------
        // ------------------------------ Testing JTS ------------------------------
        // -------------------------------------------------------------------------
        GeometryFactory geometryFactory = new GeometryFactory();
        // Create a Point
        Coordinate coord = new Coordinate(2, 2);
        Point point = geometryFactory.createPoint(coord);

        // Define coordinates for the LineString
        Coordinate[] lineStringCoords = new Coordinate[] {
                new Coordinate(-5, -5),
                new Coordinate(15, 15),
                new Coordinate(20, 20)
        };

        // Create the LineString
        LineString lineString = geometryFactory.createLineString(lineStringCoords);

        // Create a Polygon
        Coordinate[] coords1 = new Coordinate[] {
                new Coordinate(0, 0),
                new Coordinate(10, 0),
                new Coordinate(10, 10),
                new Coordinate(0, 10),
                new Coordinate(0, 0)
        };
        LinearRing ring1 = geometryFactory.createLinearRing(coords1);
        Polygon polygon1 = geometryFactory.createPolygon(ring1, null);

        // Create a Polygon
        Coordinate[] coords2 = new Coordinate[] {
                new Coordinate(0 + 5, 0),
                new Coordinate(10 + 10, 0),
                new Coordinate(10 + 10, 10),
                new Coordinate(0 + 10, 10),
                new Coordinate(0 + 5, 0)
        };
        LinearRing ring2 = geometryFactory.createLinearRing(coords2);
        Polygon polygon2 = geometryFactory.createPolygon(ring2, null);

        // -----------------------------------------------------------------------------
        // -----------------------------------------------------------------------------
        // -----------------------------------------------------------------------------
        
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
                .setGeometry(point)
                .build();

        Turbine turbine2 = new TurbineBuilder()
                .setTurbineID("Turbine2")
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
                .setTurbinePositionX(250)
                .setTurbinePositionY(450)
                .setGeometry(point)
                .build();

        // Create an instance of site geospatial features
        Geospatial geospatial1 = new GeospatialBuilder()
                .setGeospatialID("Geospatial1")
                .setLatitude(38.5)
                .setLongitude(-77.0)
                .setWindSpeed(8.5)
                .setWindDirection(180)
                .setWindPowerDensity(200)
                .setElevation(100)
                .setSlope(5)
                .setAspect(180)
                .setRoughness(0.5)
                .setLandUse("Urban")
                .setProtectedAreas("No")
                .setWildlife("No")
                .setWaterBodies("No")
                .setSoilType("Sandy")
                .setDistanceToGrid(5)
                .setDistanceToSubstation(5)
                .setDistanceToRoad(5)
                .setDistanceToRailway(5)
                .setPopulationDensity(232420000)
                .setDistanceToPopulationCenter(25)
                .build();

        WindResource windResource1 = new WindResourceBuilder()
                .setWindResourceID("WindResource1")
                .setTemperature2M(20)
                .setSpecificHumidity2M(0.01)
                .setRelativeHumidity2M(0.5)
                .setPrecipitation(0.1)
                .setSurfacePressure(1000)
                .setWindSpeed10M(8.5)
                .setWindSpeed50M(100)
                .build();

        WindFarm windFarm1 = new WindFarmBuilder()
                .setWindFarmID("WindFarm1")
                .setSite("Site1")
                .setAEP(200)
                .setTurbineSpacing(5)
                .setNumberOfTurbinesPerRow(5)
                .setNumberOfTurbines(25)
                .setLocation(0)
                .setFarmPowerOutput(200)
                .setGeospatial(geospatial1)
                .setGeometry(polygon1)
                .build();


        windFarm1.addTurbine(turbine1);
        windFarm1.addTurbine(turbine2);

        // Create visitors
        TurbineVisitor turbineVisitor = new TurbineVisitor();
        turbineVisitor.setModel(model);

        WindFarmVisitor windFarmVisitor = new WindFarmVisitor();
        windFarmVisitor.setModel(model);

        GeospatialVisitor geospatialVisitor = new GeospatialVisitor();
        geospatialVisitor.setModel(model);

        WindResourceVisitor windResourceVisitor = new WindResourceVisitor();
        windResourceVisitor.setModel(model);

        // Visit the turbine to build individuals
        turbine1.accept(turbineVisitor);
        turbine2.accept(turbineVisitor);

        geospatial1.accept(geospatialVisitor);

        windResource1.accept(windResourceVisitor);

        windFarm1.accept(windFarmVisitor);

//        try (OutputStream out = new FileOutputStream("Wind_by_Visitor.ttl")) {
//            RDFDataMgr.write(out, model.getModel(), Lang.TURTLE);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        model.getModel().write(System.out, "TURTLE");


//        // Rules
//        String ruleText = "[rule1:" +
//                "(?t <http://www.cee.umd.edu/Energy/Turbine#hasRotorConeAngle> ?theta)" +
//                "greaterThan(?theta, 10)" +
//                "->" +
//                "(?t <http://www.cee.umd.edu/Energy/Turbine#hasNEW> true)]";
//
//        // Create a reasoner with the rules
//        List<Rule> ruleList = Rule.parseRules(ruleText);
//        Reasoner reasoner = new GenericRuleReasoner(ruleList);

//// Apply the reasoner to the model
//        Model infModel = ModelFactory.createInfModel(reasoner, model.getModel());
//
        String rulesFilePath = "/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/Rules/Rules_backup.txt";
//        String rulesFilePath = "/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/Rules/RulesSyn.text";
        String ruleText = RuleLoader.loadRulesFromFile(rulesFilePath);
        // Create a reasoner with the rules
        List<Rule> ruleList = Rule.parseRules(ruleText);
        Reasoner reasoner = new GenericRuleReasoner(ruleList);

        // Apply the reasoner to the model
        Model infModel = ModelFactory.createInfModel(reasoner, model.getModel());

        // Print the inferred model
        infModel.write(System.out, "TURTLE");
    }
}