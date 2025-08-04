package Visitor;

import Builder.Geospatial;
import Builder.Turbine;
import Builder.WindFarm;
import Builder.WindResource;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import Ontology.OntologyModel;

public class GeospatialVisitor implements Visitor {

    private static final String NS = "windfarm/";
    private static final String NS_Geospatial = NS + "Geospatial#";
    private OntModel model;

    // Geospatial properties
    private DatatypeProperty hasGeospatialID;
    private DatatypeProperty hasLatitude;
    private DatatypeProperty hasLongitude;
    private DatatypeProperty hasWindSpeed;
    private DatatypeProperty hasWindDirection;
    private DatatypeProperty hasWindPowerDensity;
    private DatatypeProperty hasElevation;
    private DatatypeProperty hasSlope;
    private DatatypeProperty hasAspect;
    private DatatypeProperty hasRoughness;
    private DatatypeProperty hasLandUse;
    private DatatypeProperty hasProtectedAreas;
    private DatatypeProperty hasWildlife;
    private DatatypeProperty hasWaterBodies;
    private DatatypeProperty hasSoilType;
    private DatatypeProperty hasDistanceToGrid;
    private DatatypeProperty hasDistanceToSubstation;
    private DatatypeProperty hasDistanceToRoad;
    private DatatypeProperty hasDistanceToRailway;
    private DatatypeProperty hasPopulationDensity;
    private DatatypeProperty hasDistanceToPopulationCenter;

    public void setModel(OntologyModel ontologyModel) {
        this.model = ontologyModel.getModel();
        this.hasGeospatialID = model.getDatatypeProperty(NS_Geospatial + "hasGeospatialID");
        this.hasLatitude = model.getDatatypeProperty(NS_Geospatial + "hasLatitude");
        this.hasLongitude = model.getDatatypeProperty(NS_Geospatial + "hasLongitude");
        this.hasWindSpeed = model.getDatatypeProperty(NS_Geospatial + "hasWindSpeed");
        this.hasWindDirection = model.getDatatypeProperty(NS_Geospatial + "hasWindDirection");
        this.hasWindPowerDensity = model.getDatatypeProperty(NS_Geospatial + "hasWindPowerDensity");
        this.hasElevation = model.getDatatypeProperty(NS_Geospatial + "hasElevation");
        this.hasSlope = model.getDatatypeProperty(NS_Geospatial + "hasSlope");
        this.hasAspect = model.getDatatypeProperty(NS_Geospatial + "hasAspect");
        this.hasRoughness = model.getDatatypeProperty(NS_Geospatial + "hasRoughness");
        this.hasLandUse = model.getDatatypeProperty(NS_Geospatial + "hasLandUse");
        this.hasProtectedAreas = model.getDatatypeProperty(NS_Geospatial + "hasProtectedAreas");
        this.hasWildlife = model.getDatatypeProperty(NS_Geospatial + "hasWildlife");
        this.hasWaterBodies = model.getDatatypeProperty(NS_Geospatial + "hasWaterBodies");
        this.hasSoilType = model.getDatatypeProperty(NS_Geospatial + "hasSoilType");
        this.hasDistanceToGrid = model.getDatatypeProperty(NS_Geospatial + "hasDistanceToGrid");
        this.hasDistanceToSubstation = model.getDatatypeProperty(NS_Geospatial + "hasDistanceToSubstation");
        this.hasDistanceToRoad = model.getDatatypeProperty(NS_Geospatial + "hasDistanceToRoad");
        this.hasDistanceToRailway = model.getDatatypeProperty(NS_Geospatial + "hasDistanceToRailway");
        this.hasPopulationDensity = model.getDatatypeProperty(NS_Geospatial + "hasPopulationDensity");
        this.hasDistanceToPopulationCenter = model.getDatatypeProperty(NS_Geospatial + "hasDistanceToPopulationCenter");
    }

    @Override
    public void visit(Geospatial geospatial) {
        if (model == null) {
            throw new IllegalStateException("Model is not set for the geospatial visitor.");
        }

        Individual geospatialIndividual = model.createIndividual(NS_Geospatial + geospatial.getGeospatialID(), model.getOntClass(NS_Geospatial));
        geospatialIndividual.addProperty(hasGeospatialID, geospatial.getGeospatialID());
        geospatialIndividual.addProperty(hasLatitude, model.createTypedLiteral(geospatial.getLatitude(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasLongitude, model.createTypedLiteral(geospatial.getLongitude(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasWindSpeed, model.createTypedLiteral(geospatial.getWindSpeed(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasWindDirection, model.createTypedLiteral(geospatial.getWindDirection(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasWindPowerDensity, model.createTypedLiteral(geospatial.getWindPowerDensity(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasElevation, model.createTypedLiteral(geospatial.getElevation(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasSlope, model.createTypedLiteral(geospatial.getSlope(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasAspect, model.createTypedLiteral(geospatial.getAspect(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasRoughness, model.createTypedLiteral(geospatial.getRoughness(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasLandUse, geospatial.getLandUse());
        geospatialIndividual.addProperty(hasProtectedAreas, geospatial.getProtectedAreas());
        geospatialIndividual.addProperty(hasWildlife, geospatial.getWildlife());
        geospatialIndividual.addProperty(hasWaterBodies, geospatial.getWaterBodies());
        geospatialIndividual.addProperty(hasSoilType, geospatial.getSoilType());
        geospatialIndividual.addProperty(hasDistanceToGrid, model.createTypedLiteral(geospatial.getDistanceToGrid(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasDistanceToSubstation, model.createTypedLiteral(geospatial.getDistanceToSubstation(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasDistanceToRoad, model.createTypedLiteral(geospatial.getDistanceToRoad(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasDistanceToRailway, model.createTypedLiteral(geospatial.getDistanceToRailway(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasPopulationDensity, model.createTypedLiteral(geospatial.getPopulationDensity(), XSDDatatype.XSDdouble));
        geospatialIndividual.addProperty(hasDistanceToPopulationCenter, model.createTypedLiteral(geospatial.getDistanceToPopulationCenter(), XSDDatatype.XSDdouble));
    }

    @Override
    public void visit(Turbine turbine) {
        throw new UnsupportedOperationException("GeospatialVisitor does not support visiting Turbine.");
    }

    @Override
    public void visit(WindFarm windFarm) {
        throw new UnsupportedOperationException("GeospatialVisitor does not support visiting WindFarm.");
    }

    @Override
    public void visit(WindResource windResource) {
        throw new UnsupportedOperationException("GeospatialVisitor does not support visiting WindResource.");
    }
}