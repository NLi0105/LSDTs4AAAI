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

public class SubstationSpecsGenerator {
    private static final String OFFSHORE_COORDINATES_PATH = "/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data preprocessing/OffShore_coordinates.txt";
    private static final String ONSHORE_COORDINATES_PATH = "/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data preprocessing/OnShore_coordinates.txt";
    private static final String OUTPUT_PATH = "/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/XML_Specifications/SubstationSpecsList.xml";

    private static List<Coordinate> readCoordinates(String filePath) throws IOException {
        List<Coordinate> coordinates = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                coordinates.add(new Coordinate(x, y));
            }
        }
        return coordinates;
    }

    private static SubstationSpecs createSubstation(String id, String type, Coordinate coordinate, GeometryFactory geometryFactory) {
        SubstationSpecs specs = new SubstationSpecs();

        // Set basic properties
        specs.setSubstationID(id);
        specs.setType(type);

        // Set common attributes
        specs.setCapacity(500.0);
        specs.setVoltage(230.0);
        specs.setPowerFactor(0.95);
        specs.setLoadFactor(0.8);
        specs.setLossFactor(0.02);
        specs.setLoad(400.0);
        specs.setLoss(10.0);
        specs.setPower(390.0);
        specs.setNumOfTransformers(3);
        specs.setOperationalStatus("Operational");
        specs.setCoolingSystem("Air");
        specs.setProtectionSystem("Relay");
        specs.setFoundation("Concrete");

        // Set geometry
        Point point = geometryFactory.createPoint(coordinate);
        specs.setGeometry(point);

        return specs;
    }

    public static void main(String[] args) throws JAXBException, IOException {
        // Read coordinates from both files
        List<Coordinate> offshoreCoordinates = readCoordinates(OFFSHORE_COORDINATES_PATH);
        List<Coordinate> onshoreCoordinates = readCoordinates(ONSHORE_COORDINATES_PATH);

        // Create a list to hold all SubstationSpecs
        List<SubstationSpecs> substationSpecsList = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();

        // Generate offshore substations
        int idCounter = 1;
        for (Coordinate coordinate : offshoreCoordinates) {
            SubstationSpecs specs = createSubstation(
                "Substation" + idCounter++,
                "Offshore",
                coordinate,
                geometryFactory
            );
            substationSpecsList.add(specs);
        }

        // Generate onshore substations
        for (Coordinate coordinate : onshoreCoordinates) {
            SubstationSpecs specs = createSubstation(
                "Substation" + idCounter++,
                "Onshore",
                coordinate,
                geometryFactory
            );
            substationSpecsList.add(specs);
        }

        // Create the container object
        SubstationSpecsList specsList = new SubstationSpecsList();
        specsList.setSubstationSpecsList(substationSpecsList);

        // Marshal to XML
        JAXBContext context = JAXBContext.newInstance(SubstationSpecsList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        File outputFile = new File(OUTPUT_PATH);
        marshaller.marshal(specsList, outputFile);

        System.out.println("Generated " + substationSpecsList.size() + " substation specifications");
        System.out.println("XML file created at: " + OUTPUT_PATH);
    }
}