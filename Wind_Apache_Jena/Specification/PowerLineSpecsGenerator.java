package Specification;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class PowerLineSpecsGenerator {
    public static void main(String[] args) throws JAXBException {
        // Create a list to hold multiple PowerLineSpecs
        List<PowerLineSpecs> powerLineSpecsList = new ArrayList<>();

        // Create a GeometryFactory
        GeometryFactory geometryFactory = new GeometryFactory();

//        // Create the first PowerLineSpecs: turbine 1 to substation 1
//        Coordinate[] coordinates1 = new Coordinate[] {
//                new Coordinate(-75.00833333333333, 38.35875),
//                new Coordinate(-74.985, 38.385) // Substation 1
//        };
//        LineString lineString1 = geometryFactory.createLineString(coordinates1);
//
//        PowerLineSpecs powerLineSpecs1 = new PowerLineSpecs();
//        powerLineSpecs1.setPowerLineID("PowerLine1");
//        powerLineSpecs1.setVoltage(230);
//        powerLineSpecs1.setCapacity(100);
//        powerLineSpecs1.setMaterial("Aluminum");
//        powerLineSpecs1.setStatus("Operational");
//        powerLineSpecs1.setGeometry(lineString1);
//
//        // Add the first PowerLineSpecs to the list
//        powerLineSpecsList.add(powerLineSpecs1);
//
//        // Create the second PowerLineSpecs: turbine 2 to substation 1
//        Coordinate[] coordinates2 = new Coordinate[] {
//                new Coordinate(-74.985, 38.35875),
//                new Coordinate(-74.985, 38.385) // Substation 1
//        };
//        LineString lineString2 = geometryFactory.createLineString(coordinates2);
//
//        PowerLineSpecs powerLineSpecs2 = new PowerLineSpecs();
//        powerLineSpecs2.setPowerLineID("PowerLine2");
//        powerLineSpecs2.setVoltage(230);
//        powerLineSpecs2.setCapacity(100);
//        powerLineSpecs2.setMaterial("Aluminum");
//        powerLineSpecs2.setStatus("Operational");
//        powerLineSpecs2.setGeometry(lineString2);
//
//        // Add the second PowerLineSpecs to the list
//        powerLineSpecsList.add(powerLineSpecs2);

        // Coordinates for the substation
        Coordinate substationCoordinate = new Coordinate(-74.985, 38.385);

        // Turbine coordinates
        Coordinate[] turbineCoordinates = new Coordinate[] {
                new Coordinate(-75.00833333333333, 38.35875),
                new Coordinate(-74.985, 38.35875),
                new Coordinate(-74.96166666666666, 38.35875),
                new Coordinate(-75.00833333333333, 38.37625),
                new Coordinate(-74.985, 38.37625),
                new Coordinate(-74.96166666666666, 38.37625),
                new Coordinate(-75.00833333333333, 38.393750000000004),
                new Coordinate(-74.985, 38.393750000000004),
                new Coordinate(-74.96166666666666, 38.393750000000004),
                new Coordinate(-75.00833333333333, 38.41125),
                new Coordinate(-74.985, 38.41125),
                new Coordinate(-74.96166666666666, 38.41125)
        };

        // Create PowerLineSpecs for each turbine
        for (int i = 0; i < turbineCoordinates.length; i++) {
            Coordinate[] coordinates = new Coordinate[] {
                    turbineCoordinates[i],
                    substationCoordinate
            };
            LineString lineString = geometryFactory.createLineString(coordinates);

            PowerLineSpecs powerLineSpecs = new PowerLineSpecs();
            powerLineSpecs.setPowerLineID("PowerLine" + (i + 1));
            powerLineSpecs.setVoltage(230);
            powerLineSpecs.setCapacity(100);
            powerLineSpecs.setMaterial("Aluminum");
            powerLineSpecs.setStatus("Operational");
            powerLineSpecs.setGeometry(lineString);

            // Add the PowerLineSpecs to the list
            powerLineSpecsList.add(powerLineSpecs);
        }


        // Create the third PowerLineSpecs: off-shore substation1 to on-shore substation 2
        Coordinate[] coordinates13 = new Coordinate[] {
                new Coordinate(-75.084908, 38.336502),
                new Coordinate(-74.985, 38.385)
        };

        LineString lineString13 = geometryFactory.createLineString(coordinates13);

        PowerLineSpecs powerLineSpecs13 = new PowerLineSpecs();
        powerLineSpecs13.setPowerLineID("PowerLine3");
        powerLineSpecs13.setVoltage(230);
        powerLineSpecs13.setCapacity(100);
        powerLineSpecs13.setMaterial("Aluminum");
        powerLineSpecs13.setStatus("Operational");
        powerLineSpecs13.setGeometry(lineString13);

        // Add the third PowerLineSpecs to the list
        powerLineSpecsList.add(powerLineSpecs13);

        // Create a PowerLineSpecsList object
        PowerLineSpecsList powerLineSpecsListObj = new PowerLineSpecsList();
        powerLineSpecsListObj.setPowerLineSpecs(powerLineSpecsList);

        // Marshal the PowerLineSpecsList object to XML
        JAXBContext context = JAXBContext.newInstance(PowerLineSpecsList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(powerLineSpecsListObj, new File("project_folder/Wind/src/main/java/XML_Specifications/powerLineSpecsList.xml"));
    }
}