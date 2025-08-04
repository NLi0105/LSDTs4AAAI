package Specification;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import javax.xml.bind.JAXBContext;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class WindResourceSpecsGenerator {
    public static void main(String[] args) throws JAXBException {
        // Create a Polygon
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coords1 = new Coordinate[] {
                new Coordinate(0, 0),
                new Coordinate(100, 0),
                new Coordinate(100, 100),
                new Coordinate(0, 100),
                new Coordinate(0, 0)
        };
        LinearRing ring = geometryFactory.createLinearRing(coords1);
        Polygon polygon = geometryFactory.createPolygon(ring, null);

        WindResourceSpecs windResourceSpecs = new WindResourceSpecs();
        windResourceSpecs.setWindResourceID("WindResource1");
        windResourceSpecs.setTemperature2M(20);
        windResourceSpecs.setSpecificHumidity2M(0.01);
        windResourceSpecs.setRelativeHumidity2M(0.5);
        windResourceSpecs.setPrecipitation(0.1);
        windResourceSpecs.setSurfacePressure(1000);
        windResourceSpecs.setWindSpeed10M(10);
        windResourceSpecs.setWindSpeed50M(50);
        windResourceSpecs.setGeometry(polygon);

        JAXBContext context = JAXBContext.newInstance(WindResourceSpecs.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(windResourceSpecs, new File("project_folder/Wind/src/main/java/XML_Specifications/windResourceSpecs.xml"));
    }
}
