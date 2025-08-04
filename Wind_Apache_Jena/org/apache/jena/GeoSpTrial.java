package org.apache.jena;

import org.apache.jena.geosparql.configuration.GeoSPARQLConfig;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.ontology.*;

public class GeoSpTrial {
    private static final String NS = "http://example.org/";
    private static final String NS_geo = NS + "geo#";

    public static void main(String[] args) {
        // Initialize GeoSPARQL
//        GeoSPARQLConfig.setup();

        // Create an ontology model
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        // Define the Geospatial class
        OntClass Geospatial = m.createClass(NS_geo + "Geospatial");

        // ID
        DatatypeProperty hasGeospatialID = m.createDatatypeProperty(NS_geo + "hasGeospatialID");
        hasGeospatialID.addDomain(Geospatial);
        hasGeospatialID.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        // Coordinates
        DatatypeProperty hasLatitude = m.createDatatypeProperty(NS_geo + "hasLatitude");
        hasLatitude.addDomain(Geospatial);
        hasLatitude.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasLongitude = m.createDatatypeProperty(NS_geo + "hasLongitude");
        hasLongitude.addDomain(Geospatial);
        hasLongitude.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Create instances
        Individual featureA = Geospatial.createIndividual(NS + "FeatureA");
        featureA.addProperty(hasGeospatialID, "FeatureA_ID");
        featureA.addProperty(hasLatitude, m.createTypedLiteral(1.0));
        featureA.addProperty(hasLongitude, m.createTypedLiteral(1.0));

        Individual featureB = Geospatial.createIndividual(NS + "FeatureB");
        featureB.addProperty(hasGeospatialID, "FeatureB_ID");
        featureB.addProperty(hasLatitude, m.createTypedLiteral(2.0));
        featureB.addProperty(hasLongitude, m.createTypedLiteral(2.0));

        // Write the model to the console in Turtle format
        m.write(System.out, "TURTLE");

        // Define and execute a GeoSPARQL query
        String queryString = """
            PREFIX geo: <http://www.opengis.net/ont/geosparql#>
            PREFIX geof: <http://www.opengis.net/def/function/geosparql/>
            PREFIX ex: <http://example.org/>

            SELECT ?feature
            WHERE {
                ?feature geo:hasGeometry ?geometry .
                ?geometry geo:asWKT ?wkt .
                FILTER(geof:sfWithin(?wkt, "POLYGON((0 0, 0 3, 3 3, 3 0, 0 0))"^^geo:wktLiteral))
            }
            """;

        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, m)) {
            ResultSet results = qexec.execSelect();
            ResultSetFormatter.out(System.out, results, query);
        }
    }
}