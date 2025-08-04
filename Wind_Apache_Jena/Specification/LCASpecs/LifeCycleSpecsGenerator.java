package Specification.LCASpecs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Arrays;

public class LifeCycleSpecsGenerator {
    public static void main(String[] args) throws JAXBException {
        LifeCycleSpecs lifeCycleSpecs = new LifeCycleSpecs();

        Stage planning = new Stage();
        planning.setName("Planning");

        WindAssessment windAssessment = new WindAssessment();
        windAssessment.setName("WindAssessment");
        windAssessment.setInput("Input data");
        windAssessment.setOutput("Output data");
        windAssessment.setMetric("Metric data");
        windAssessment.setStartDate("2023-01-01");
        windAssessment.setEndDate("2023-12-31");
        windAssessment.setCost(1000.0);
        windAssessment.setWindSpeed(12.5);

        ImpactAssessment impactAssessment = new ImpactAssessment();
        impactAssessment.setName("ImpactAssessment");
        impactAssessment.setInput("Input data");
        impactAssessment.setOutput("Output data");
        impactAssessment.setMetric("Metric data");
        impactAssessment.setStartDate("2023-01-01");
        impactAssessment.setEndDate("2023-12-31");
        impactAssessment.setCost(2000.0);
        impactAssessment.setImpactType("Marine Impact Assessment");

        Stage design = new Stage();
        design.setName("Design");

        TurbineDesign turbineDesign = new TurbineDesign();
        turbineDesign.setName("TurbineDesign");
        turbineDesign.setInput("Design input data");
        turbineDesign.setOutput("Design output data");
        turbineDesign.setMetric("Design metric data");
        turbineDesign.setStartDate("2023-01-01");
        turbineDesign.setEndDate("2023-12-31");
        turbineDesign.setCost(5000.0);
        turbineDesign.setTurbineModel("Model A");

        Stage installation = new Stage();
        installation.setName("Installation");

        TurbineInstallation turbineInstallation = new TurbineInstallation();
        turbineInstallation.setName("TurbineInstallation");
        turbineInstallation.setInput("Installation input data");
        turbineInstallation.setOutput("Installation output data");
        turbineInstallation.setMetric("Installation metric data");
        turbineInstallation.setStartDate("2023-01-01");
        turbineInstallation.setEndDate("2023-12-31");
        turbineInstallation.setCost(7000.0);
        turbineInstallation.setInstallationMethod("Vessel installation");

        Stage om = new Stage();
        om.setName("OM");

        PowerMonitoring powerMonitoring = new PowerMonitoring();
        powerMonitoring.setName("PowerMonitoring");
        powerMonitoring.setInput("Monitoring input data");
        powerMonitoring.setOutput("Monitoring output data");
        powerMonitoring.setMetric("Monitoring metric data");
        powerMonitoring.setStartDate("2023-01-01");
        powerMonitoring.setEndDate("2023-12-31");
        powerMonitoring.setCost(3000.0);
        powerMonitoring.setPowerProduction(1500.0);

        Stage decommission = new Stage();
        decommission.setName("Decommission");

        Dismantling dismantling = new Dismantling();
        dismantling.setName("Dismantling");
        dismantling.setInput("Dismantling input data");
        dismantling.setOutput("Dismantling output data");
        dismantling.setMetric("Dismantling metric data");
        dismantling.setStartDate("2023-01-01");
        dismantling.setEndDate("2023-12-31");
        dismantling.setCost(4000.0);
        dismantling.setTurbineDismantling("Crane removal");


        planning.setTasks(Arrays.asList(windAssessment, impactAssessment));
        design.setTasks(Arrays.asList(turbineDesign));
        installation.setTasks(Arrays.asList(turbineInstallation));
        om.setTasks(Arrays.asList(powerMonitoring));
        decommission.setTasks(Arrays.asList(dismantling));

        lifeCycleSpecs.setStages(Arrays.asList(planning, design, installation, om, decommission));

        JAXBContext context = JAXBContext.newInstance(LifeCycleSpecs.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(lifeCycleSpecs, new File("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Java/Jena/jena-5.1.0/Wind/src/main/java/XML_Specifications/LifeCycleSpecs.xml"));
    }
}