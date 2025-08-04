package org.apache.jena;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.WKTReader;

import java.util.List;

import static Utils.wktReader.extractGeometries;

public class Test_JTS {
    public static void main(String[] args) {
        // Testing JTS
        GeometryFactory geometryFactory = new GeometryFactory();
        // Create a Point
        Coordinate coord = new Coordinate(2, 2);
        Point point = geometryFactory.createPoint(coord);
        System.out.println("Point: " + point);

        // Create a Polygon
        Coordinate[] coords = new Coordinate[] {
                new Coordinate(0, 0),
                new Coordinate(10, 0),
                new Coordinate(10, 10),
                new Coordinate(0, 10),
                new Coordinate(0, 0)
        };
        LinearRing ring = geometryFactory.createLinearRing(coords);
        Polygon polygon = geometryFactory.createPolygon(ring, null);
        System.out.println("Polygon: " + polygon);

        // Check if the point is within the polygon
        boolean isWithin = point.within(polygon);
        System.out.println("Point within polygon: " + isWithin);

        try {
            WKTReader reader = new WKTReader(geometryFactory);

            // Create the point and polygon from WKT
            Point point1 = (Point) reader.read("POINT (2 2)");
            Polygon polygon1 = (Polygon) reader.read("POLYGON ((0 0, 10 0, 10 10, 0 10, 0 0))");

            // Check if the point is within 100 units of the polygon
            double distance = 100.0;
            boolean isWithinDistance = point1.isWithinDistance(polygon1, distance);

            System.out.println("Point is within " + distance + " units of the polygon: " + isWithinDistance);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        String filePath = "project_folder/bwi_aeroways.wkt";
//        List<Geometry> geometries = extractGeometries(filePath);
//
//        // Print the extracted geometries
//        for (Geometry geometry : geometries) {
//            System.out.println(geometry);
//        }
//
//        Geometry pickedGeometry = geometries.get(197);
//        System.out.println("Picked geometry: " + pickedGeometry);
    }
}
