package SemanticModelSpecs;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OntoSpec_Geospatial {
    private static final String NS = "http://windfarm/";
    private static final String NS_geospatial = NS + "Geospatial#";

    public static void main(String[] args) {

        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        OntClass Geospatial = m.createClass(NS_geospatial);

        // ID
        DatatypeProperty hasGeospatialID = m.createDatatypeProperty(NS_geospatial + "hasGeospatialID");
        hasGeospatialID.addDomain(Geospatial);
        hasGeospatialID.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        // Coordinates
        DatatypeProperty hasLatitude = m.createDatatypeProperty(NS_geospatial + "hasLatitude");
        hasLatitude.addDomain(Geospatial);
        hasLatitude.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasLongitude = m.createDatatypeProperty(NS_geospatial + "hasLongitude");
        hasLongitude.addDomain(Geospatial);
        hasLongitude.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Wind resource
        DatatypeProperty hasWindSpeed = m.createDatatypeProperty(NS_geospatial + "hasWindSpeed");
        hasWindSpeed.addDomain(Geospatial);
        hasWindSpeed.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasWindDirection = m.createDatatypeProperty(NS_geospatial + "hasWindDirection");
        hasWindDirection.addDomain(Geospatial);
        hasWindDirection.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasWindPowerDensity = m.createDatatypeProperty(NS_geospatial + "hasWindPowerDensity");
        hasWindPowerDensity.addDomain(Geospatial);
        hasWindPowerDensity.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Terrain and topography
        DatatypeProperty hasElevation = m.createDatatypeProperty(NS_geospatial + "hasElevation");
        hasElevation.addDomain(Geospatial);
        hasElevation.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasSlope = m.createDatatypeProperty(NS_geospatial + "hasSlope");
        hasSlope.addDomain(Geospatial);
        hasSlope.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // The direction the land faces
        DatatypeProperty hasAspect = m.createDatatypeProperty(NS_geospatial + "hasAspect");
        hasAspect.addDomain(Geospatial);
        hasAspect.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasRoughness = m.createDatatypeProperty(NS_geospatial + "hasRoughness");
        hasRoughness.addDomain(Geospatial);
        hasRoughness.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Land use and zoning
        DatatypeProperty hasLandUse = m.createDatatypeProperty(NS_geospatial + "hasLandUse");
        hasLandUse.addDomain(Geospatial);
        hasLandUse.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasProtectedAreas = m.createDatatypeProperty(NS_geospatial + "hasProtectedAreas");
        hasProtectedAreas.addDomain(Geospatial);
        hasProtectedAreas.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        // Environmental and ecological
        DatatypeProperty hasWildlife = m.createDatatypeProperty(NS_geospatial + "hasWildlife");
        hasWildlife.addDomain(Geospatial);
        hasWildlife.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasWaterBodies = m.createDatatypeProperty(NS_geospatial + "hasWaterBodies");
        hasWaterBodies.addDomain(Geospatial);
        hasWaterBodies.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasSoilType = m.createDatatypeProperty(NS_geospatial + "hasSoilType");
        hasSoilType.addDomain(Geospatial);
        hasSoilType.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#string"));

        // Transmission network
        DatatypeProperty hasDistanceToGrid = m.createDatatypeProperty(NS_geospatial + "hasDistanceToGrid");
        hasDistanceToGrid.addDomain(Geospatial);
        hasDistanceToGrid.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasDistanceToSubstation = m.createDatatypeProperty(NS_geospatial + "hasDistanceToSubstation");
        hasDistanceToSubstation.addDomain(Geospatial);
        hasDistanceToSubstation.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Transportation network
        DatatypeProperty hasDistanceToRoad = m.createDatatypeProperty(NS_geospatial + "hasDistanceToRoad");
        hasDistanceToRoad.addDomain(Geospatial);
        hasDistanceToRoad.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasDistanceToRailway = m.createDatatypeProperty(NS_geospatial + "hasDistanceToRailway");
        hasDistanceToRailway.addDomain(Geospatial);
        hasDistanceToRailway.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Population
        DatatypeProperty hasPopulationDensity = m.createDatatypeProperty(NS_geospatial + "hasPopulationDensity");
        hasPopulationDensity.addDomain(Geospatial);
        hasPopulationDensity.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        DatatypeProperty hasDistanceToPopulationCenter = m.createDatatypeProperty(NS_geospatial + "hasDistanceToPopulationCenter");
        hasDistanceToPopulationCenter.addDomain(Geospatial);
        hasDistanceToPopulationCenter.addRange(m.getResource("http://www.w3.org/2001/XMLSchema#double"));

        try (OutputStream out = new FileOutputStream("project_folder/Wind/src/main/java/SemanticModels/Geospatial.xml")) {
            RDFDataMgr.write(out, m, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
