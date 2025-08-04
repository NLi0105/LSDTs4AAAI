package SpecVisitor;

import Ontology.OntologyModel;
import Specification.*;
import Specification.LCASpecs.LifeCycleSpecs;
import Specification.RegulationSpecs.RegulationSpecs;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.locationtech.jts.io.WKTWriter;


public class WindResourceSpecVisitor implements SpecVisitor {
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

    // Geometry
    private DatatypeProperty hasGeometry;

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

        // Geometry
        this.hasGeometry = model.getDatatypeProperty(NS_WindResource + "hasGeometry");
    }

    @Override
    public void visit(WindResourceSpecs windResourceSpecs) {
        if (model == null) {
            throw new RuntimeException("Model has not been set for WindResourceSpecVisitor");
        }

        Individual windResourceSpecsIndividual = model.createIndividual(NS_WindResource + windResourceSpecs.getWindResourceID(), model.getOntClass(NS_WindResource));
        windResourceSpecsIndividual.addProperty(hasWindResourceID, windResourceSpecs.getWindResourceID());
        windResourceSpecsIndividual.addProperty(hasTemperature2M, model.createTypedLiteral(windResourceSpecs.getTemperature2M(), XSDDatatype.XSDdouble));
        windResourceSpecsIndividual.addProperty(hasSpecificHumidity2M, model.createTypedLiteral(windResourceSpecs.getSpecificHumidity2M(), XSDDatatype.XSDdouble));
        windResourceSpecsIndividual.addProperty(hasRelativeHumidity2M, model.createTypedLiteral(windResourceSpecs.getRelativeHumidity2M(), XSDDatatype.XSDdouble));
        windResourceSpecsIndividual.addProperty(hasPrecipitation, model.createTypedLiteral(windResourceSpecs.getPrecipitation(), XSDDatatype.XSDdouble));
        windResourceSpecsIndividual.addProperty(hasSurfacePressure, model.createTypedLiteral(windResourceSpecs.getSurfacePressure(), XSDDatatype.XSDdouble));
        windResourceSpecsIndividual.addProperty(hasWindSpeed10M, model.createTypedLiteral(windResourceSpecs.getWindSpeed10M(), XSDDatatype.XSDdouble));
        windResourceSpecsIndividual.addProperty(hasWindSpeed50M, model.createTypedLiteral(windResourceSpecs.getWindSpeed50M(), XSDDatatype.XSDdouble));

        // Geometry
        WKTWriter wktWriter = new WKTWriter();
        String wkt = wktWriter.write(windResourceSpecs.getGeometry());
        windResourceSpecsIndividual.addProperty(hasGeometry, wkt, XSDDatatype.XSDstring);
    }

    @Override
    public void visit(TurbineSpecs turbineSpecs) {
        throw new UnsupportedOperationException("TurbineSpecVisitor does not support visiting TurbineSpecs.");
    }

    @Override
    public void visit(WindFarmSpecs windFarmSpecs) {
        throw new UnsupportedOperationException("TurbineSpecVisitor does not support visiting WindFarmSpecs.");
    }

    @Override
    public void visit(PowerLineSpecs powerLineSpecs) {
        throw new UnsupportedOperationException("TurbineSpecVisitor does not support visiting PowerLineSpecs.");
    }

    @Override
    public void visit(LifeCycleSpecs lifeCycleSpecs) {
        throw new UnsupportedOperationException("TurbineSpecVisitor does not support visiting LifeCycle.");
    }

    @Override
    public void visit(SubstationSpecs substationSpecs) {
        throw new UnsupportedOperationException("TurbineSpecVisitor does not support visiting SubstationSpecs.");
    }

    @Override
    public void visit(RegulationSpecs regulationSpecs) {
        throw new UnsupportedOperationException("TurbineSpecVisitor does not support visiting RegulationSpecs.");
    }
}
