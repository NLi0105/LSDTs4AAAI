package SemanticModelSpecs;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OntoSpec_LifeCycle {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_LifeCycle = NS + "LifeCycle/";
    private static final String NS_Task = NS_LifeCycle + "Task#";

    public static void main(String[] args) {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

        // Define LifeCycle class
        OntClass lifeCycle = model.createClass(NS_LifeCycle + "LifeCycle");

        // Define Stage class
        OntClass stage = model.createClass(NS_LifeCycle + "Stage");

        // Define Task class
        OntClass task = model.createClass(NS_Task + "Task");

        // Define TurbineInstallation class
        OntClass turbineInstallation = model.createClass(NS_Task + "TurbineInstallation");

        // Define PowerMonitoring class
        OntClass powerMonitoring = model.createClass(NS_Task + "PowerMonitoring");

        // Define Dismantling class
        OntClass dismantling = model.createClass(NS_Task + "Dismantling");

        // Define properties for LifeCycle
        ObjectProperty hasStage = model.createObjectProperty(NS_LifeCycle + "hasStage");
        hasStage.addDomain(lifeCycle);
        hasStage.addRange(stage);

        // Define properties for Stage
        DatatypeProperty hasStageName = model.createDatatypeProperty(NS_LifeCycle + "hasStageName");
        hasStageName.addDomain(stage);
        hasStageName.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));

        ObjectProperty hasTask = model.createObjectProperty(NS_LifeCycle + "hasTask");
        hasTask.addDomain(stage);
        hasTask.addRange(task);

        // Define properties for Task
        DatatypeProperty hasTaskName = model.createDatatypeProperty(NS_Task + "hasTaskName");
        hasTaskName.addDomain(task);
        hasTaskName.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasInput = model.createDatatypeProperty(NS_Task + "hasInput");
        hasInput.addDomain(task);
        hasInput.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasOutput = model.createDatatypeProperty(NS_Task + "hasOutput");
        hasOutput.addDomain(task);
        hasOutput.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasMetric = model.createDatatypeProperty(NS_Task + "hasMetric");
        hasMetric.addDomain(task);
        hasMetric.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasStartDate = model.createDatatypeProperty(NS_Task + "hasStartDate");
        hasStartDate.addDomain(task);
        hasStartDate.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasEndDate = model.createDatatypeProperty(NS_Task + "hasEndDate");
        hasEndDate.addDomain(task);
        hasEndDate.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));

        DatatypeProperty hasCost = model.createDatatypeProperty(NS_Task + "hasCost");
        hasCost.addDomain(task);
        hasCost.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Define windSpeed property for WindAssessment
        DatatypeProperty hasWindSpeed = model.createDatatypeProperty(NS_Task + "hasWindSpeed");
        hasWindSpeed.addDomain(task);
        hasWindSpeed.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Define impactType property for ImpactAssessment
        DatatypeProperty hasImpactType = model.createDatatypeProperty(NS_Task + "hasImpactType");
        hasImpactType.addDomain(task);
        hasImpactType.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));

        // Define TurbineModel property for TurbineDesign
        DatatypeProperty hasTurbineModel = model.createDatatypeProperty(NS_Task + "hasTurbineModel");
        hasTurbineModel.addDomain(task);
        hasTurbineModel.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));

        // Define installationMethod property for TurbineInstallation
        DatatypeProperty hasInstallationMethod = model.createDatatypeProperty(NS_Task + "hasInstallationMethod");
        hasInstallationMethod.addDomain(turbineInstallation);
        hasInstallationMethod.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));

        // Define powerProduction property for PowerMonitoring
        DatatypeProperty hasPowerProduction = model.createDatatypeProperty(NS_Task + "hasPowerProduction");
        hasPowerProduction.addDomain(powerMonitoring);
        hasPowerProduction.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#double"));

        // Define turbineDismantling property for Dismantling
        DatatypeProperty hasTurbineDismantling = model.createDatatypeProperty(NS_Task + "hasTurbineDismantling");
        hasTurbineDismantling.addDomain(dismantling);
        hasTurbineDismantling.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));

        // Save the ontology to an RDF/XML file
        try (OutputStream out = new FileOutputStream("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/SemanticModels/LifeCycle.xml")) {
            RDFDataMgr.write(out, model, Lang.RDFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}