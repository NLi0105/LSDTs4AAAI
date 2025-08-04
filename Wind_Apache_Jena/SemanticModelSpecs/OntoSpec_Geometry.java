package SemanticModelSpecs;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.geosparql.implementation.datatype.WKTDatatype;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OntoSpec_Geometry {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_Geometry = NS + "Geometry/";

    private static final String NS_Point = NS_Geometry + "Point#";
    private static final String NS_LineString = NS_Geometry + "LineString#";
    private static final String NS_Polygon = NS_Geometry + "Polygon#";
    private static final String NS_MultiPoint = NS_Geometry + "MultiPoint#";
    private static final String NS_MultiLineString = NS_Geometry + "MultiLineString#";
    private static final String NS_MultiPolygon = NS_Geometry + "MultiPolygon#";
    private static final String NS_GeometryCollection = NS_Geometry + "GeometryCollection#";

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        // Main geometry class
        OntClass Geometry = m.createClass(NS_Geometry);

        // Subclasses: concrete geometry types
        OntClass Point = m.createClass(NS_Point);
        OntClass LineString = m.createClass(NS_LineString);
        OntClass Polygon = m.createClass(NS_Polygon);
        OntClass MultiPoint = m.createClass(NS_MultiPoint);
        OntClass MultiLineString = m.createClass(NS_MultiLineString);
        OntClass MultiPolygon = m.createClass(NS_MultiPolygon);
        OntClass GeometryCollection = m.createClass(NS_GeometryCollection);

        // geometry properties
        DatatypeProperty hasGeometry = m.createDatatypeProperty(NS_Geometry + "hasGeometry");
        hasGeometry.addDomain(Geometry);
//        hasGeometry.addDomain(Point);
        hasGeometry.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasLength = m.createDatatypeProperty(NS_Geometry + "hasLength");
        hasLength.addDomain(Geometry);
        hasLength.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasPerimeter = m.createDatatypeProperty(NS_Geometry + "hasPerimeter");
        hasPerimeter.addDomain(Geometry);
        hasPerimeter.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasArea = m.createDatatypeProperty(NS_Geometry + "hasArea");
        hasArea.addDomain(Geometry);
        hasArea.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Connect subclasses to the superclass (Geometry)
        Point.addSuperClass(Geometry);
        LineString.addSuperClass(Geometry);
        Polygon.addSuperClass(Geometry);
        MultiPoint.addSuperClass(Geometry);
        MultiLineString.addSuperClass(Geometry);
        MultiPolygon.addSuperClass(Geometry);
        GeometryCollection.addSuperClass(Geometry);

//        Individual point1 = Point.createIndividual(NS_Point + "point1");
//        point1.addProperty(hasGeometry, "POINT(1 1)", XSDDatatype.XSDstring);
//
//        Individual lineString1 = LineString.createIndividual(NS_LineString + "lineString1");
//        lineString1.addProperty(hasGeometry, "LINESTRING(0 0, 1 1, 2 2)");
//        lineString1.addProperty(hasArea, m.createTypedLiteral(20.0, XSDDatatype.XSDdouble));

//        m.write(System.out, "TURTLE");
        try (OutputStream out = new FileOutputStream("project_folder/Wind/src/main/java/SemanticModels/Geometry.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
