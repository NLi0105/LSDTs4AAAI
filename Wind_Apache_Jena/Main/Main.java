package Main;

import Listener.EventListener;
import Listener.HurricaneListener;
import Ontology.OntologyModel;
import Specification.*;
import Specification.LCASpecs.LifeCycleSpecs;
import Specification.RegulationSpecs.RegulationSpecsList;
import Utils.CsvUtil;
import Utils.ModelUtils;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.apache.jena.tdb2.TDB2Factory;

import javax.xml.bind.JAXBException;
import java.io.File;

public class Main {

    private static final String TDB_DIRECTORY = "/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/Main/TDB";
    private static final String RULES_FILE_PATH = "/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/Rules/Rules_Geospatial_wRegulation.txt";
//private static final String RULES_FILE_PATH = "/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/Rules/Rules_test.txt";
    private static final String NS_Time = "http://www.cee.umd.edu/Energy/Time#";

    public static void main(String[] args) throws JAXBException {
        // Ensure the TDB directory exists
        File tdbDir = new File(TDB_DIRECTORY);
        if (!tdbDir.exists()) {
            tdbDir.mkdirs();
        }

        // Create a TDB-backed dataset
        Dataset dataset = TDB2Factory.connectDataset(TDB_DIRECTORY);

        // Begin a write transaction
        dataset.begin(ReadWrite.WRITE);
        try {
            // Use the user-defined ontology model
            OntologyModel model = new OntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF);

            // Create a Time class
            OntClass Time = model.getModel().createClass(NS_Time);
            DatatypeProperty hasCurrentTime = model.getModel().createDatatypeProperty(NS_Time + "hasCurrentTime");
            hasCurrentTime.addDomain(Time);
            hasCurrentTime.addRange(model.getModel().getResource("http://www.w3.org/2001/XMLSchema#dateTime"));
            Individual currentTime = Time.createIndividual(NS_Time + "currentTime");
            currentTime.addProperty(hasCurrentTime, "2012-8-29T21:00:00", XSDDatatype.XSDdateTime);

            // Register the EventListener
            EventListener eventListener = new EventListener(model.getModel());
            model.getModel().register(eventListener);

            // Load ontologies
            OntologyLoader.loadOntologies(model);

            // Unmarshal specifications
            TurbineSpecsList turbineSpecsList = SpecUnmarshaller.unmarshalSpecs("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/XML_Specifications/turbineSpecsList.xml", TurbineSpecsList.class);
            WindFarmSpecs windFarmSpecs = SpecUnmarshaller.unmarshalSpecs("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/XML_Specifications/windFarmSpecs.xml", WindFarmSpecs.class);
            WindResourceSpecs windResourceSpecs = SpecUnmarshaller.unmarshalSpecs("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/XML_Specifications/windResourceSpecs.xml", WindResourceSpecs.class);
            SubstationSpecsList substationSpecsList = SpecUnmarshaller.unmarshalSpecs("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/XML_Specifications/SubstationSpecsList.xml", SubstationSpecsList.class);
            PowerLineSpecsList powerLineSpecsList = SpecUnmarshaller.unmarshalSpecs("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/XML_Specifications/powerLineSpecsList.xml", PowerLineSpecsList.class);
            LifeCycleSpecs lifeCycle = SpecUnmarshaller.unmarshalSpecs("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/XML_Specifications/LifeCycleSpecs.xml", LifeCycleSpecs.class);
            RegulationSpecsList regulationSpecsList = SpecUnmarshaller.unmarshalSpecs("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/XML_Specifications/regulationSpecsList.xml", RegulationSpecsList.class);

            // Process specifications
            SpecProcessor.processSpecifications(model, turbineSpecsList, windFarmSpecs, windResourceSpecs, substationSpecsList, powerLineSpecsList, lifeCycle, regulationSpecsList);

            // Load MSP data from CSV files
            MspDataLoader.loadMspData(model);

            // Register the HurricaneListener
            HurricaneListener hurricaneListener = new HurricaneListener(model.getModel());
            model.getModel().register(hurricaneListener);

            // Load the hurricane CSV and update the hurricane individual
            CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Wind/Hurricane/hurricane_report.csv", hurricaneListener::updateHurricane);

            // Save the original model
//            ModelUtils.saveModel(model.getModel(), "/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/Main/TDB/Full_Onto.xml", Lang.TURTLE);

            // Apply rules and create the inferred model
            Model infModel = RuleApplier.applyRules(model, RULES_FILE_PATH);

            // Save the inferred model
//             ModelUtils.saveModel(infModel, "/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/Main/TDB/Full_infOnto.xml", Lang.TURTLE);
            ModelUtils.saveModel(infModel, "/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/Main/TDB/Full_infOnto_Updated_before.xml", Lang.RDFXML,
                    new ModelUtils.SaveOptions()
                            .showMetrics(false));
            // Commit the transaction
            dataset.commit();
        } catch (Exception e) {
            // Abort the transaction in case of an exception
            dataset.abort();
            throw e;
        } finally {
            // End the transaction
            dataset.end();
        }

        // Close the dataset
        dataset.close();
    }
}