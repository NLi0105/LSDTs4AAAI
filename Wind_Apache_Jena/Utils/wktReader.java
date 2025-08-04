package Utils;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKTReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class wktReader {

    private List<Geometry> geometries;

    public static List<Geometry> readWKTFile(String filePath) throws IOException {
        List<Geometry> geometries = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Geometry geometry = reader.read(line);
                    geometries.add(geometry);
                } catch (Exception e) {
                    System.err.println("Invalid WKT: " + line);
                }
            }
        }
        return geometries;
    }

    public static List<Geometry> extractGeometries(String filePath) {
        List<Geometry> geometries = new ArrayList<>();
        WKTReader reader = new WKTReader();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Geometry geometry = reader.read(line);
                    geometries.add(geometry);
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return geometries;
    }
}
