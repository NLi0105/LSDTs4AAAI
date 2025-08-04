package Main;

import Ontology.OntologyModel;
import SpecVisitor.*;
import Specification.*;
import Specification.LCASpecs.LifeCycleSpecs;
import Specification.RegulationSpecs.RegulationSpecs;
import Specification.RegulationSpecs.RegulationSpecsList;

public class SpecProcessor {

    public static void processSpecifications(OntologyModel model, TurbineSpecsList turbineSpecsList, WindFarmSpecs windFarmSpecs, WindResourceSpecs windResourceSpecs, SubstationSpecsList substationSpecsList, PowerLineSpecsList powerLineSpecsList, LifeCycleSpecs lifeCycle, RegulationSpecsList regulationSpecsList) {
        // Process LifeCycleSpecs
        LifeCycleSpecVisitor lifeCycleSpecVisitor = new LifeCycleSpecVisitor();
        lifeCycleSpecVisitor.setModel(model);
        lifeCycleSpecVisitor.visit(lifeCycle);

        // Process TurbineSpecs
        TurbineSpecVisitor turbineSpecVisitor = new TurbineSpecVisitor();
        turbineSpecVisitor.setModel(model);
        for (TurbineSpecs turbineSpecs : turbineSpecsList.getTurbineSpecsList()) {
            windFarmSpecs.addTurbine(turbineSpecs);
            turbineSpecVisitor.visit(turbineSpecs);
        }

        // Process WindFarmSpecs
        WindFarmSpecVisitor windFarmSpecVisitor = new WindFarmSpecVisitor();
        windFarmSpecVisitor.setModel(model);
        windFarmSpecVisitor.visit(windFarmSpecs);

        // Process WindResourceSpecs
        WindResourceSpecVisitor windResourceSpecVisitor = new WindResourceSpecVisitor();
        windResourceSpecVisitor.setModel(model);
        windResourceSpecVisitor.visit(windResourceSpecs);

        // Process SubstationSpecs
        SubstationSpecVisitor substationVisitor = new SubstationSpecVisitor();
        substationVisitor.setModel(model);
        for (SubstationSpecs substationSpec : substationSpecsList.getSubstationSpecsList()) {
            substationVisitor.visit(substationSpec);
        }

        // Process PowerLineSpecs
        PowerLineSpecVisitor powerLineSpecVisitor = new PowerLineSpecVisitor();
        powerLineSpecVisitor.setModel(model);
        for (PowerLineSpecs powerLineSpecs : powerLineSpecsList.getPowerLineSpecs()) {
            powerLineSpecVisitor.visit(powerLineSpecs);
        }

        // Process RegulationSpecs
        RegulationSpecVisitor regulationSpecVisitor = new RegulationSpecVisitor();
        regulationSpecVisitor.setModel(model);
        for (RegulationSpecs regulationSpecs : regulationSpecsList.getRegulationSpecsList()) {
            regulationSpecVisitor.visit(regulationSpecs);
        }
    }
}