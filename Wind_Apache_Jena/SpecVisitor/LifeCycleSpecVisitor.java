package SpecVisitor;

import Specification.*;
import Specification.LCASpecs.*;
import Ontology.OntologyModel;
import Specification.RegulationSpecs.RegulationSpecs;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;

public class LifeCycleSpecVisitor implements SpecVisitor {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_LifeCycle = NS + "LifeCycle/";
    private static final String NS_Task = NS_LifeCycle + "Task#";
    private OntModel model;

    // LifeCycle properties
    private ObjectProperty hasStage;
    private DatatypeProperty hasStageName;
    private ObjectProperty hasTask;
    private DatatypeProperty hasTaskName;
    private DatatypeProperty hasInput;
    private DatatypeProperty hasOutput;
    private DatatypeProperty hasMetric;
    private DatatypeProperty hasStartDate;
    private DatatypeProperty hasEndDate;
    private DatatypeProperty hasCost;
    private DatatypeProperty hasWindSpeed;
    private DatatypeProperty hasImpactType;
    private DatatypeProperty hasTurbineModel;
    private DatatypeProperty hasInstallationMethod;
    private DatatypeProperty hasPowerProduction;
    private DatatypeProperty hasTurbineDismantling;

    public void setModel(OntologyModel ontologyModel) {
        this.model = ontologyModel.getModel();
        this.hasStage = model.getObjectProperty(NS_LifeCycle + "hasStage");
        this.hasStageName = model.getDatatypeProperty(NS_LifeCycle + "hasStageName");
        this.hasTask = model.getObjectProperty(NS_LifeCycle + "hasTask");
        this.hasTaskName = model.getDatatypeProperty(NS_Task + "hasTaskName");
        this.hasInput = model.getDatatypeProperty(NS_Task + "hasInput");
        this.hasOutput = model.getDatatypeProperty(NS_Task + "hasOutput");
        this.hasMetric = model.getDatatypeProperty(NS_Task + "hasMetric");
        this.hasStartDate = model.getDatatypeProperty(NS_Task + "hasStartDate");
        this.hasEndDate = model.getDatatypeProperty(NS_Task + "hasEndDate");
        this.hasCost = model.getDatatypeProperty(NS_Task + "hasCost");
        this.hasWindSpeed = model.getDatatypeProperty(NS_Task + "hasWindSpeed");
        this.hasImpactType = model.getDatatypeProperty(NS_Task + "hasImpactType");
        this.hasTurbineModel = model.getDatatypeProperty(NS_Task + "hasTurbineModel");
        this.hasInstallationMethod = model.getDatatypeProperty(NS_Task + "hasInstallationMethod");
        this.hasPowerProduction = model.getDatatypeProperty(NS_Task + "hasPowerProduction");
        this.hasTurbineDismantling = model.getDatatypeProperty(NS_Task + "hasTurbineDismantling");
    }

    @Override
    public void visit(LifeCycleSpecs lifeCycleSpecs) {
        if (model == null) {
            throw new IllegalStateException("Model is not set for the life cycle visitor.");
        }

        for (Stage stage : lifeCycleSpecs.getStages()) {
            for (Task task : stage.getTasks()) {
                String taskURI = NS_LifeCycle + stage.getName() + "/Task#" + task.getName();
                Individual taskIndividual = model.createIndividual(taskURI, model.getOntClass(NS_Task + "Task"));
                taskIndividual.addProperty(hasTaskName, task.getName());
                taskIndividual.addProperty(hasInput, task.getInput());
                taskIndividual.addProperty(hasOutput, task.getOutput());
                taskIndividual.addProperty(hasMetric, task.getMetric());
                taskIndividual.addProperty(hasStartDate, model.createTypedLiteral(task.getStartDate(), XSDDatatype.XSDdate));
                taskIndividual.addProperty(hasEndDate, model.createTypedLiteral(task.getEndDate(), XSDDatatype.XSDdate));
                taskIndividual.addProperty(hasCost, model.createTypedLiteral(task.getCost(), XSDDatatype.XSDdouble));

                // Add windSpeed property if the task is an instance of WindAssessment
                if (task instanceof WindAssessment) {
                    WindAssessment windAssessment = (WindAssessment) task;
                    taskIndividual.addProperty(hasWindSpeed, model.createTypedLiteral(windAssessment.getWindSpeed(), XSDDatatype.XSDdouble));
                }

                // Add impactType property if the task is an instance of ImpactAssessment
                if (task instanceof ImpactAssessment) {
                    ImpactAssessment impactAssessment = (ImpactAssessment) task;
                    taskIndividual.addProperty(hasImpactType, impactAssessment.getImpactType());
                }

                // Add turbineModel property if the task is an instance of TurbineDesign
                if (task instanceof TurbineDesign) {
                    TurbineDesign turbineDesign = (TurbineDesign) task;
                    taskIndividual.addProperty(hasTurbineModel, turbineDesign.getTurbineModel());
                }

                // Add installationMethod property if the task is an instance of TurbineInstallation
                if (task instanceof TurbineInstallation) {
                    TurbineInstallation turbineInstallation = (TurbineInstallation) task;
                    taskIndividual.addProperty(hasInstallationMethod, turbineInstallation.getInstallationMethod());
                }

                // Add powerProduction property if the task is an instance of PowerMonitoring
                if (task instanceof PowerMonitoring) {
                    PowerMonitoring powerMonitoring = (PowerMonitoring) task;
                    taskIndividual.addProperty(hasPowerProduction, model.createTypedLiteral(powerMonitoring.getPowerProduction(), XSDDatatype.XSDdouble));
                }

                // Add turbineDismantling property if the task is an instance of Dismantling
                if (task instanceof Dismantling) {
                    Dismantling dismantling = (Dismantling) task;
                    taskIndividual.addProperty(hasTurbineDismantling, dismantling.getTurbineDismantling());
                }
            }
        }
    }

    @Override
    public void visit(TurbineSpecs turbineSpecs) {
        throw new UnsupportedOperationException("LifeCycleSpecVisitor does not support visiting TurbineSpecs.");
    }

    @Override
    public void visit(WindFarmSpecs windFarmSpecs) {
        throw new UnsupportedOperationException("LifeCycleSpecVisitor does not support visiting WindFarmSpecs.");
    }

    @Override
    public void visit(WindResourceSpecs windResourceSpecs) {
        throw new UnsupportedOperationException("LifeCycleSpecVisitor does not support visiting WindResourceSpecs.");
    }


    @Override
    public void visit(PowerLineSpecs powerLineSpecs) {
        throw new UnsupportedOperationException("LifeCycleSpecVisitor does not support visiting PowerLineSpecs.");
    }

    @Override
    public void visit(SubstationSpecs substationSpecs) {
        throw new UnsupportedOperationException("LifeCycleSpecVisitor does not support visiting SubstationSpecs.");
    }

    @Override
    public void visit(RegulationSpecs regulationSpecs) {
        throw new UnsupportedOperationException("LifeCycleSpecVisitor does not support visiting RegulationSpecs.");
    }

}