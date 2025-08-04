// Java
package Specification;

import org.locationtech.jts.geom.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class WindFarmSpecsGenerator {
    public static void main(String[] args) throws JAXBException {
        // Create a Polygon
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coords1 = new Coordinate[] {
                new Coordinate(0, 0),
                new Coordinate(10, 0),
                new Coordinate(10, 10),
                new Coordinate(0, 10),
                new Coordinate(0, 0)
        };
        LinearRing ring = geometryFactory.createLinearRing(coords1);
        Polygon polygon = geometryFactory.createPolygon(ring, null);

        WindFarmSpecs windFarmSpecs = new WindFarmSpecs();
        windFarmSpecs.setWindFarmID("WindFarm1");
        windFarmSpecs.setSite("Site1");
        windFarmSpecs.setAEP(200);
        windFarmSpecs.setTurbineSpacing(5);
        windFarmSpecs.setNumberOfTurbinesPerRow(5);
        windFarmSpecs.setNumberOfTurbines(25);
        windFarmSpecs.setLocation("Salisbury");
        windFarmSpecs.setFarmPowerOutput(200);
        windFarmSpecs.setGeometry(polygon);
        windFarmSpecs.setWindFarmStatus("Operational");

        JAXBContext context = JAXBContext.newInstance(WindFarmSpecs.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(windFarmSpecs, new File("project_folder/Wind/src/main/java/XML_Specifications/windFarmSpecs.xml"));
    }
}