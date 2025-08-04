package Visitor;

import Builder.Geospatial;
import Builder.Turbine;
import Builder.WindFarm;
import Builder.WindResource;
import Ontology.OntologyModel;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.OntModel;


public class WindResourceVisitor implements Visitor {
    private static final String NS = "windfarm/";
    private static final String NS_WindResource = NS + "WindResource#";
    private OntModel model;

    private DatatypeProperty hasWindResourceID;
    private DatatypeProperty hasTemperature2M;
    private DatatypeProperty hasSpecificHumidity2M;
    private DatatypeProperty hasRelativeHumidity2M;
    private DatatypeProperty hasPrecipitation;
    private DatatypeProperty hasSurfacePressure;
    private DatatypeProperty hasWindSpeed10M;
    private DatatypeProperty hasWindSpeed50M;


    public void setModel(OntologyModel ontologyModel) {
        this.model = ontologyModel.getModel();

        this.hasWindResourceID = model.getDatatypeProperty(NS_WindResource + "hasWindResourceID");
        this.hasTemperature2M = model.getDatatypeProperty(NS_WindResource + "hasTemperature2M");
        this.hasSpecificHumidity2M = model.getDatatypeProperty(NS_WindResource + "hasSpecificHumidity2M");
        this.hasRelativeHumidity2M = model.getDatatypeProperty(NS_WindResource + "hasRelativeHumidity2M");
        this.hasPrecipitation = model.getDatatypeProperty(NS_WindResource + "hasPrecipitation");
        this.hasSurfacePressure = model.getDatatypeProperty(NS_WindResource + "hasSurfacePressure");
        this.hasWindSpeed10M = model.getDatatypeProperty(NS_WindResource + "hasWindSpeed10M");
        this.hasWindSpeed50M = model.getDatatypeProperty(NS_WindResource + "hasWindSpeed50M");
    }

    @Override
    public void visit(WindResource windResource) {

        if (model == null) {
            throw new RuntimeException("Model has not been set");
        }
        // Create an individual of WindResource
        org.apache.jena.ontology.Individual windResourceIndividual = model.createIndividual(NS_WindResource + windResource.getWindResourceID(), model.getOntClass(NS_WindResource));

        // Set the properties of WindResource
        windResourceIndividual.addProperty(hasWindResourceID, windResource.getWindResourceID());
        windResourceIndividual.addProperty(hasTemperature2M, model.createTypedLiteral(windResource.getTemperature2M(), XSDDatatype.XSDdouble));
        windResourceIndividual.addProperty(hasSpecificHumidity2M, model.createTypedLiteral(windResource.getSpecificHumidity2M(), XSDDatatype.XSDdouble));
        windResourceIndividual.addProperty(hasRelativeHumidity2M, model.createTypedLiteral(windResource.getRelativeHumidity2M(), XSDDatatype.XSDdouble));
        windResourceIndividual.addProperty(hasPrecipitation, model.createTypedLiteral(windResource.getPrecipitation(), XSDDatatype.XSDdouble));
        windResourceIndividual.addProperty(hasSurfacePressure, model.createTypedLiteral(windResource.getSurfacePressure(), XSDDatatype.XSDdouble));
        windResourceIndividual.addProperty(hasWindSpeed10M, model.createTypedLiteral(windResource.getWindSpeed10M(), XSDDatatype.XSDdouble));
        windResourceIndividual.addProperty(hasWindSpeed50M, model.createTypedLiteral(windResource.getWindSpeed50M(), XSDDatatype.XSDdouble));
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
    public void visit(Geospatial geospatial) {
        throw new UnsupportedOperationException("GeospatialVisitor does not support visiting Geospatial.");
    }
}
