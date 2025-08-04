package Main;

import CSVVisitor.Event.HurricaneCsvVisitor;
import CSVVisitor.MSP.*;
import Ontology.OntologyModel;
import Utils.CsvUtil;

public class MspDataLoader {

    public static void loadMspData(OntologyModel model) {
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/windlease.csv", new WindLeaseCsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/OCSw.csv", new OCSwCsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/NOAA.csv", new NOAACsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/EFH.csv", new EFHCsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/Restricted.csv", new RestrictedCsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/Coral.csv", new CoralCsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/Wind.csv", new WindCsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/SeaDepth.csv", new SeaDepthCsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/ECC.csv", new ECCCsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/Cable.csv", new CableCsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/Export_Cable.csv", new ExportCableCsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/Landing.csv", new LandingCsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/Interconnection.csv", new InterconnectionCsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Geospatial/MSP data csv/OCS.csv", new OCSCsvVisitor(model.getModel())::visit);
        CsvUtil.ReadCSV("/Users/li/Library/CloudStorage/OneDrive-UniversityofMaryland/PhD research/Python/Wind/Hurricane/hurricane_report.csv", new HurricaneCsvVisitor(model.getModel())::visit);
    }
}