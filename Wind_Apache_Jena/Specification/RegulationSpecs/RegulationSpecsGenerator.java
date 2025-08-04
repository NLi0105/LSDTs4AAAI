package Specification.RegulationSpecs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RegulationSpecsGenerator {
    public static void main(String[] args) throws JAXBException {
        // Create a list to hold RegulationSpecs
        List<RegulationSpecs> regulationSpecsList = new ArrayList<>();

        // =============================== Environmental Regulations ==============================
        // Coral Protection Areas
        RegulationSpecs coral = new RegulationSpecs();
        coral.setRegulationName("Coral");
        coral.setRegulationDescription("Coral Protection Areas.");
        coral.setRegulationType("Environment");
        coral.setSeverity("High");
        coral.setImpactAreaURI("http://www.cee.umd.edu/Energy/MSP/Coral#");
        coral.setRemedy("Relocate");
        regulationSpecsList.add(coral);

        // Essential Fish Habitat
        RegulationSpecs efh = new RegulationSpecs();
        efh.setRegulationName("EFH");
        efh.setRegulationDescription("Essential Fish Habitat.");
        efh.setRegulationType("Environment");
        efh.setSeverity("Low");
        efh.setImpactAreaURI("http://www.cee.umd.edu/Energy/MSP/EFH#");
        efh.setRemedy("Consultation with NOAA");
        regulationSpecsList.add(efh);

        // NOAA Marine Protected Areas
        RegulationSpecs noaa = new RegulationSpecs();
        noaa.setRegulationName("NOAA");
        noaa.setRegulationDescription("NOAA Marine Protected Areas.");
        noaa.setRegulationType("Environment");
        noaa.setSeverity("Medium");
        noaa.setImpactAreaURI("http://www.cee.umd.edu/Energy/MSP/NOAA#");
        noaa.setRemedy("Consultation with NOAA");
        regulationSpecsList.add(noaa);

        // Wind Speed
        RegulationSpecs windSpeed = new RegulationSpecs();
        windSpeed.setRegulationName("WindSpeed");
        windSpeed.setRegulationDescription("Wind Speed.");
        windSpeed.setRegulationType("Environment");
        windSpeed.setSeverity("High");
        windSpeed.setImpactAreaURI("http://www.cee.umd.edu/Energy/MSP/WindSpeed#");
        windSpeed.setRemedy("Consultation with meteorologists");
        regulationSpecsList.add(windSpeed);

        // Sea Depth
        RegulationSpecs seaDepth = new RegulationSpecs();
        seaDepth.setRegulationName("SeaDepth");
        seaDepth.setRegulationDescription("Sea Depth.");
        seaDepth.setRegulationType("Environment");
        seaDepth.setSeverity("High");
        seaDepth.setImpactAreaURI("http://www.cee.umd.edu/Energy/MSP/SeaDepth#");
        seaDepth.setRemedy("Consultation with oceanographers");
        regulationSpecsList.add(seaDepth);

        // =============================== Zoning Regulations ==============================
        RegulationSpecs restricted = new RegulationSpecs();
        restricted.setRegulationName("Restricted");
        restricted.setRegulationDescription("Military restricted and danger zones.");
        restricted.setRegulationType("Zoning");
        restricted.setSeverity("Critical");
        restricted.setImpactAreaURI("http://www.cee.umd.edu/Energy/MSP/Restricted#");
        restricted.setRemedy("Relocation.");
        regulationSpecsList.add(restricted);

        // =============================== Infrastructure Regulations ==============================
        RegulationSpecs cable = new RegulationSpecs();
        cable.setRegulationName("Cable");
        cable.setRegulationDescription("Inter-array cables.");
        cable.setRegulationType("Infrastructure");
        cable.setSeverity("High");
        cable.setImpactAreaURI("http://www.cee.umd.edu/Energy/MSP/Cable#");
        cable.setRemedy("Consultation with cable operators");
        regulationSpecsList.add(cable);

        RegulationSpecs export_cable = new RegulationSpecs();
        export_cable.setRegulationName("ExportCable");
        export_cable.setRegulationDescription("Export cables.");
        export_cable.setRegulationType("Infrastructure");
        export_cable.setSeverity("High");
        export_cable.setImpactAreaURI("http://www.cee.umd.edu/Energy/MSP/ExportCable#");
        export_cable.setRemedy("Consultation with cable operators");
        regulationSpecsList.add(export_cable);

        RegulationSpecs interconnection = new RegulationSpecs();
        interconnection.setRegulationName("Interconnection");
        interconnection.setRegulationDescription("Interconnection points.");
        interconnection.setRegulationType("Infrastructure");
        interconnection.setSeverity("High");
        interconnection.setImpactAreaURI("http://www.cee.umd.edu/Energy/MSP/Interconnection#");
        interconnection.setRemedy("Consultation with grid operators");
        regulationSpecsList.add(interconnection);

        RegulationSpecs landing = new RegulationSpecs();
        landing.setRegulationName("Landing");
        landing.setRegulationDescription("Cable landing points.");
        landing.setRegulationType("Infrastructure");
        landing.setSeverity("High");
        landing.setImpactAreaURI("http://www.cee.umd.edu/Energy/MSP/Landing#");
        landing.setRemedy("Consultation with cable operators");
        regulationSpecsList.add(landing);
        
        RegulationSpecs export_cable_corridor = new RegulationSpecs();
        export_cable_corridor.setRegulationName("ECC");
        export_cable_corridor.setRegulationDescription("Export cable corridors.");
        export_cable_corridor.setRegulationType("Infrastructure");
        export_cable_corridor.setSeverity("High");
        export_cable_corridor.setImpactAreaURI("http://www.cee.umd.edu/Energy/MSP/ECC#");
        export_cable_corridor.setRemedy("Consultation with cable operators");
        regulationSpecsList.add(export_cable_corridor);

        // =============================== Operation Regulations ==============================



        // =============================== Administration Regulations ==============================
        RegulationSpecs ocsw = new RegulationSpecs();
        ocsw.setRegulationName("OCSw");
        ocsw.setRegulationDescription("Outer Continental Shelf Wind.");
        ocsw.setRegulationType("Administration");
        ocsw.setSeverity("Critical");
        ocsw.setImpactAreaURI("http://www.cee.umd.edu/Energy/MSP/OCSw#");
        ocsw.setRemedy("Consultation with BOEM");
        regulationSpecsList.add(ocsw);

        RegulationSpecs lease = new RegulationSpecs();
        lease.setRegulationName("WindLease");
        lease.setRegulationDescription("Lease areas.");
        lease.setRegulationType("Administration");
        lease.setSeverity("High");
        lease.setImpactAreaURI("http://www.cee.umd.edu/Energy/MSP/WindLease#");
        lease.setRemedy("Consultation with BOEM");
        regulationSpecsList.add(lease);

        
        // Create a RegulationSpecsList object
        RegulationSpecsList regulationSpecsListObj = new RegulationSpecsList();
        regulationSpecsListObj.setRegulationSpecsList(regulationSpecsList);

        // Marshal the RegulationSpecsList to XML
        JAXBContext context = JAXBContext.newInstance(RegulationSpecsList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(regulationSpecsListObj, new File("project_folder/Wind/src/main/java/XML_Specifications/regulationSpecsList.xml"));

        System.out.println("Regulation specifications have been successfully generated.");
    }
}