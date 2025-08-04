package org.apache.jena;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;

public class JTS3DExample {
    public static void main(String[] args) {
        GeometryFactory geometryFactory = new GeometryFactory();

        // Create the first 3D polygon
        Coordinate[] coords1 = new Coordinate[] {
            new Coordinate(0, 0, 10),
            new Coordinate(100, 0, 20),
            new Coordinate(100, 100, 30),
            new Coordinate(0, 100, 40),
            new Coordinate(0, 0, 10)
        };
        Polygon polygon1 = geometryFactory.createPolygon(coords1);

        // Create the second 3D polygon
        Coordinate[] coords2 = new Coordinate[] {
            new Coordinate(50, 50, 15),
            new Coordinate(150, 50, 25),
            new Coordinate(150, 150, 35),
            new Coordinate(50, 150, 45),
            new Coordinate(50, 50, 15)
        };
        Polygon polygon2 = geometryFactory.createPolygon(coords2);

        // Check if the polygons intersect (ignoring z-coordinate)
        boolean intersects = polygon1.intersects(polygon2);

        System.out.println("Do the polygons intersect? " + intersects);
    }
}