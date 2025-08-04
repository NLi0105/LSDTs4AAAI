package Main;

import Ontology.OntologyModel;

public class OntologyLoader {

    public static void loadOntologies(OntologyModel model) {

        // Read in the pre-defined ontology
        // Wind Farm ontologies
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/Turbine.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/WindFarm.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/Geospatial.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/WindResource.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/Substation.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/PowerLine.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/LifeCycle.xml");

        // MSP ontologies
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/WindLease.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/OCSw.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/OCS.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/NOAA.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/EFH.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/Restricted.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/Coral.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/Wind.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/SeaDepth.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/ECC.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/Cable.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/ExportCable.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/Landing.xml");
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/MSP/Interconnection.xml");



        // Event
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/Event.xml");



        // Regulation
        model.loadOntologyFromFile("project_folder/Wind/src/main/java/SemanticModels/Regulation.xml");
    }
}