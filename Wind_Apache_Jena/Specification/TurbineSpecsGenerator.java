package Specification;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TurbineSpecsGenerator {
    public static void main(String[] args) throws JAXBException, IOException {
        List<Coordinate> coordinates = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader("project_folder/Geospatial/MSP data preprocessing/MD turbine coordinates.txt"))) {
        try (BufferedReader br = new BufferedReader(new FileReader("project_folder/Wind/Digital_Twin/Optimization_turbine_position/final_corrected_coordinates.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                coordinates.add(new Coordinate(x, y));
            }
        }

        // Create a list to hold multiple TurbineSpecs
        List<TurbineSpecs> turbineSpecsList = new ArrayList<>();

        // Create a GeometryFactory
        GeometryFactory geometryFactory = new GeometryFactory();

        // Loop through the coordinates and create TurbineSpecs for each
        for (int i = 0; i < coordinates.size(); i++) {
            TurbineSpecs turbineSpecs = new TurbineSpecs();
            turbineSpecs.setTurbineID("Turbine" + (i + 1));
            turbineSpecs.setTurbineModel("IEA 15 MW");
            turbineSpecs.setWindClass("IEC Class 1B");
            turbineSpecs.setRatedAerodynamicPower(10);
            turbineSpecs.setHubHeight(150);
            turbineSpecs.setCutInWindSpeed(3);
            turbineSpecs.setRotorConeAngle(-4);
            turbineSpecs.setRotorSolidity(0.05);
            turbineSpecs.setBladeMass(47700);
            turbineSpecs.setBladeCost(300);
            turbineSpecs.setAerodynamicAEP(20);
            turbineSpecs.setICC(0.1);
            turbineSpecs.setRatedElectricalPower(10);
            turbineSpecs.setGenEfficiency(0.95);
            turbineSpecs.setRotorDiameter(240);
            turbineSpecs.setCutOutWindSpeed(25);
            turbineSpecs.setNacelleUptiltAngle(5);
            turbineSpecs.setMaxVtip(90);
            turbineSpecs.setTowerMass(860);
            turbineSpecs.setTowerCost(1000000);
            turbineSpecs.setElectricalAEP(0.5);
            turbineSpecs.setCOE(0.05);
            turbineSpecs.setRotorOrientation(0);
            turbineSpecs.setControl("Pitch");
            turbineSpecs.setRatedWindSpeed(10.59);
            turbineSpecs.setNumberOfBlades(3);
            turbineSpecs.setAirfoilSeries("FFA-W3");
            turbineSpecs.setHubDiameter(7.94);
            turbineSpecs.setDriveTrain("Direct Drive");
            turbineSpecs.setMinRotorSpeed(5);
            turbineSpecs.setMaxRotorSpeed(7.56);
            turbineSpecs.setGearboxRatio(100);
            turbineSpecs.setHubOverhang(1.5);
            turbineSpecs.setShaftTiltAngle(5);
            turbineSpecs.setBladePrebend(6.2);
            turbineSpecs.setNacelleMass(542.600);
            turbineSpecs.setInclination(5);
            turbineSpecs.setTurbineStatus("Operational");
            turbineSpecs.setAvailability("Available");
            turbineSpecs.setPowerOutput(12.0);
            turbineSpecs.setCapacityFactor(0.3);
            turbineSpecs.setDownTime(0.02);
            turbineSpecs.setTurbineType("OffShore");
            turbineSpecs.setTurbinePositionX(0);
            turbineSpecs.setTurbinePositionY(0);
            Point point = geometryFactory.createPoint(coordinates.get(i));
            turbineSpecs.setGeometry(point);
            turbineSpecs.setPitchAngle(8);
            turbineSpecs.setYawAngle(50);

            // Add the TurbineSpecs to the list
            turbineSpecsList.add(turbineSpecs);
        }

        // Create a TurbineSpecsList object
        TurbineSpecsList turbineSpecsListObj = new TurbineSpecsList();
        turbineSpecsListObj.setTurbineSpecsList(turbineSpecsList);

        // Marshal the TurbineSpecsList to XML
        JAXBContext context = JAXBContext.newInstance(TurbineSpecsList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(turbineSpecsListObj, new File("project_folder/Wind/src/main/java/XML_Specifications/turbineSpecsList.xml"));
    }
}