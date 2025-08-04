package Visitor;

import Builder.Geospatial;
import Builder.WindFarm;
import Builder.Turbine;
import Builder.WindResource;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;
import Ontology.OntologyModel;
import org.locationtech.jts.io.WKTWriter;

public class WindFarmVisitor implements Visitor {

    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_Turbine = NS + "Turbine#";
    private static final String NS_WindFarm = NS + "WindFarm#";
    private static final String NS_Geospatial = NS + "Geospatial#";
    private static final String NS_WindResource = NS + "WindResource#";
    private OntModel model;

    // Wind farm properties
    private DatatypeProperty hasWindFarmID;
    private DatatypeProperty hasSite;
    private DatatypeProperty hasAEP;
    private DatatypeProperty hasTurbineSpacing;
    private DatatypeProperty hasNumberOfTurbinesPerRow;
    private DatatypeProperty hasNumberOfTurbines;
    private DatatypeProperty hasLocation;
    private DatatypeProperty hasFarmPowerOutput;
    private ObjectProperty hasTurbine;
    private ObjectProperty hasGeospatial;

    // Geometry
    private DatatypeProperty hasGeometry;

    public void setModel(OntologyModel ontologyModel) {
        this.model = ontologyModel.getModel();
        this.hasWindFarmID = model.getDatatypeProperty(NS_WindFarm + "hasWindFarmID");
        this.hasSite = model.getDatatypeProperty(NS_WindFarm + "hasSite");
        this.hasAEP = model.getDatatypeProperty(NS_WindFarm + "hasAEP");
        this.hasTurbineSpacing = model.getDatatypeProperty(NS_WindFarm + "hasTurbineSpacing");
        this.hasNumberOfTurbinesPerRow = model.getDatatypeProperty(NS_WindFarm + "hasNumberOfTurbinesPerRow");
        this.hasNumberOfTurbines = model.getDatatypeProperty(NS_WindFarm + "hasNumberOfTurbines");
        this.hasLocation = model.getDatatypeProperty(NS_WindFarm + "hasLocation");
        this.hasFarmPowerOutput = model.getDatatypeProperty(NS_WindFarm + "hasFarmPowerOutput");
        this.hasTurbine = model.getObjectProperty(NS_WindFarm + "hasTurbine");
        this.hasGeospatial = model.getObjectProperty(NS_WindFarm + "hasGeospatial");
        // Geometry
        this.hasGeometry = model.getDatatypeProperty(NS_WindFarm + "hasGeometry");
    }

    @Override
    public void visit(WindFarm windFarm) {
        if (model == null) {
            throw new IllegalStateException("Model is not set for the wind farm visitor.");
        }

        Individual windFarmIndividual = model.createIndividual(NS_WindFarm + windFarm.getWindFarmID(), model.getOntClass(NS_WindFarm));
        windFarmIndividual.addProperty(hasWindFarmID, windFarm.getWindFarmID());
        windFarmIndividual.addProperty(hasSite, windFarm.getSite());
        windFarmIndividual.addProperty(hasAEP, model.createTypedLiteral(windFarm.getAEP(), XSDDatatype.XSDdouble));
        windFarmIndividual.addProperty(hasTurbineSpacing, model.createTypedLiteral(windFarm.getTurbineSpacing(), XSDDatatype.XSDdouble));
        windFarmIndividual.addProperty(hasNumberOfTurbinesPerRow, model.createTypedLiteral(windFarm.getNumberOfTurbinesPerRow(), XSDDatatype.XSDdouble));
        windFarmIndividual.addProperty(hasNumberOfTurbines, model.createTypedLiteral(windFarm.getNumberOfTurbines(), XSDDatatype.XSDdouble));
        windFarmIndividual.addProperty(hasLocation, model.createTypedLiteral(windFarm.getLocation(), XSDDatatype.XSDdouble));
        windFarmIndividual.addProperty(hasFarmPowerOutput, model.createTypedLiteral(windFarm.getFarmPowerOutput(), XSDDatatype.XSDdouble));

        // Connect wind farm with turbines
        for (Turbine turbine : windFarm.getTurbines()) {
            Individual turbineIndividual = model.getIndividual(NS_Turbine + turbine.getTurbineID());
            if (turbineIndividual != null) {
                windFarmIndividual.addProperty(hasTurbine, turbineIndividual);
            }
        }

        // Connect wind farm with geospatial feature
        Geospatial geospatial = windFarm.getGeospatial();
        if (geospatial != null) {
            Individual geospatialIndividual = model.getIndividual(NS_Geospatial + geospatial.getGeospatialID());
            if (geospatialIndividual != null) {
                windFarmIndividual.addProperty(hasGeospatial, geospatialIndividual);
            }
        }

        // Geometry
        WKTWriter wktWriter = new WKTWriter();
        String wkt = wktWriter.write(windFarm.getGeometry());
        windFarmIndividual.addProperty(hasGeometry, wkt, XSDDatatype.XSDstring);
    }

    @Override
    public void visit(Turbine turbine) {
        throw new UnsupportedOperationException("WindFarmVisitor does not support visiting Turbine.");
    }

    @Override
    public void visit(Geospatial geospatial) {
        throw new UnsupportedOperationException("WindFarmVisitor does not support visiting Geospatial.");
    }

    @Override
    public void visit(WindResource windResource) {
        throw new UnsupportedOperationException("WindFarmVisitor does not support visiting WindResource.");
    }
}