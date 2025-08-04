package Main;

import Ontology.OntologyModel;
import Rules.RuleLoader;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.shacl.ShaclValidator;
import org.apache.jena.shacl.ValidationReport;
import org.apache.jena.shacl.lib.ShLib;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RuleApplier {

    public static Model applyRules(OntologyModel model, String rulesFilePath) {
        String ruleText = RuleLoader.loadRulesFromFile(rulesFilePath);
        List<Rule> ruleList = Rule.parseRules(ruleText);
        Reasoner reasoner = new GenericRuleReasoner(ruleList);
        Model infModel = ModelFactory.createInfModel(reasoner, model.getModel());

        // Perform SHACL validation
        String shaclFilePath = "project_folder/Wind/src/main/java/Rules/Shacl/Shacl.txt";
        Model shapesModel = ModelFactory.createDefaultModel();
        try (InputStream in = new FileInputStream(shaclFilePath)) {
            RDFDataMgr.read(shapesModel, in, Lang.TTL);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        ShaclValidator validator = ShaclValidator.get();
        ValidationReport report = validator.validate(shapesModel.getGraph(), model.getModel().getGraph());

        if (report.conforms()) {
            System.out.println("Semantic model is valid.");
        } else {
            System.out.println("The data model does not conform to the SHACL shapes. Validation errors:");
            ShLib.printReport(report);
        }

        return infModel;
    }
}