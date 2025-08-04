package org.apache.jena;

import Specification.WindFarmSpecs;
import Specification.TurbineSpecs;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Test_JAXB {
    public static void main(String[] args) throws JAXBException {
        // Unmarshal TurbineSpecs from XML
        JAXBContext turbineContext = JAXBContext.newInstance(TurbineSpecs.class);
        Unmarshaller turbineUnmarshaller = turbineContext.createUnmarshaller();
        TurbineSpecs turbineSpecs = (TurbineSpecs) turbineUnmarshaller.unmarshal(new File("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/Specifications/turbineSpecs.xml"));
        System.out.println("TurbineID: " + turbineSpecs.getTurbineID());
        System.out.printf("--------------------\n");

//        // Set geometry for TurbineSpecs
//        GeometryFactory geometryFactory = new GeometryFactory();
//        Point point = geometryFactory.createPoint(new Coordinate(2, 2));
//        turbineSpecs.setGeometry(point);

        // Unmarshal WindFarmSpecs from XML
        JAXBContext windFarmContext = JAXBContext.newInstance(WindFarmSpecs.class);
        Unmarshaller windFarmUnmarshaller = windFarmContext.createUnmarshaller();
        WindFarmSpecs windFarmSpecs = (WindFarmSpecs) windFarmUnmarshaller.unmarshal(new File("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/Specifications/windFarmSpecs.xml"));

        // Add TurbineSpecs to WindFarmSpecs
        windFarmSpecs.addTurbine(turbineSpecs);

        // Print WindFarmSpecs details
        System.out.println("WindFarmID: " + windFarmSpecs.getWindFarmID());
        System.out.println("Geometry: " + windFarmSpecs.getGeometry());
        for (TurbineSpecs ts : windFarmSpecs.getTurbines()) {
            System.out.println("TurbineID: " + ts.getTurbineID());
            System.out.println("Geometry: " + ts.getGeometry());
        }
    }
}