package CSVVisitor.MSP;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;

public class ExportCableCsvVisitor {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_ExportCable = NS_MSP + "ExportCable#";

    private OntModel model;
    private OntClass exportCableClass;
    private DatatypeProperty hasLeaseNumber;
    private DatatypeProperty hasProjectName;
    private DatatypeProperty hasDeveloper;
    private DatatypeProperty hasCableType;
    private DatatypeProperty hasElectricCurrent;
    private DatatypeProperty hasDiameter;
    private DatatypeProperty hasStatus;
    private DatatypeProperty hasLength;
    private DatatypeProperty hasMinKilovolt;
    private DatatypeProperty hasMaxKilovolt;
    private DatatypeProperty hasGeometry;

    public ExportCableCsvVisitor(OntModel model) {
        this.model = model;
        exportCableClass = model.getOntClass(NS_ExportCable);

        // Initialize properties
        hasLeaseNumber = model.getDatatypeProperty(NS_ExportCable + "hasLeaseNumber");
        hasProjectName = model.getDatatypeProperty(NS_ExportCable + "hasProjectName");
        hasDeveloper = model.getDatatypeProperty(NS_ExportCable + "hasDeveloper");
        hasCableType = model.getDatatypeProperty(NS_ExportCable + "hasCableType");
        hasElectricCurrent = model.getDatatypeProperty(NS_ExportCable + "hasElectricCurrent");
        hasDiameter = model.getDatatypeProperty(NS_ExportCable + "hasDiameter");
        hasStatus = model.getDatatypeProperty(NS_ExportCable + "hasStatus");
        hasLength = model.getDatatypeProperty(NS_ExportCable + "hasLength");
        hasMinKilovolt = model.getDatatypeProperty(NS_ExportCable + "hasMinKilovolt");
        hasMaxKilovolt = model.getDatatypeProperty(NS_ExportCable + "hasMaxKilovolt");
        hasGeometry = model.getDatatypeProperty(NS_ExportCable + "hasGeometry");

        // Create properties if they don't exist
        if (exportCableClass == null) {
            System.err.println("ExportCable class not found, creating it now");
            exportCableClass = model.createClass(NS_ExportCable);
        }

        // Create any properties that might be missing
        createPropertyIfNotExists(hasLeaseNumber, "hasLeaseNumber", "string");
        createPropertyIfNotExists(hasProjectName, "hasProjectName", "string");
        createPropertyIfNotExists(hasDeveloper, "hasDeveloper", "string");
        createPropertyIfNotExists(hasCableType, "hasCableType", "string");
        createPropertyIfNotExists(hasElectricCurrent, "hasElectricCurrent", "string");
        createPropertyIfNotExists(hasDiameter, "hasDiameter", "double");
        createPropertyIfNotExists(hasStatus, "hasStatus", "string");
        createPropertyIfNotExists(hasLength, "hasLength", "double");
        createPropertyIfNotExists(hasMinKilovolt, "hasMinKilovolt", "double");
        createPropertyIfNotExists(hasMaxKilovolt, "hasMaxKilovolt", "double");
        createPropertyIfNotExists(hasGeometry, "hasGeometry", "string");
    }

    private void createPropertyIfNotExists(DatatypeProperty property, String propertyName, String dataType) {
        if (property == null) {
            if ("string".equals(dataType)) {
                property = model.createDatatypeProperty(NS_ExportCable + propertyName);
                property.addDomain(exportCableClass);
                property.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));
            } else if ("double".equals(dataType)) {
                property = model.createDatatypeProperty(NS_ExportCable + propertyName);
                property.addDomain(exportCableClass);
                property.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#double"));
            }

            // Update the instance field with the newly created property
            try {
                this.getClass().getDeclaredField(propertyName).set(this, property);
            } catch (Exception e) {
                System.err.println("Failed to update property field: " + propertyName);
            }
        }
    }

    public void visit(String[] csvRow) {
        if (exportCableClass == null) {
            System.err.println("ExportCable class not found in the ontology model");
            return;
        }

        // Format the ID in the OCS-A-0483 style
        String cleanId = formatOcsId(csvRow[0]);

        // Create individual with clean URI
        Individual exportCable = model.createIndividual(NS_ExportCable + cleanId, exportCableClass);

        // LEASE_NUMB - column 0
        processStringProperty(exportCable, hasLeaseNumber, csvRow, 0);

        // PROJECT_NA - column 1
        processStringProperty(exportCable, hasProjectName, csvRow, 1);

        // DEVELOPER_ - column 2
        processStringProperty(exportCable, hasDeveloper, csvRow, 2);

        // CABLE_TYPE - column 3
        processStringProperty(exportCable, hasCableType, csvRow, 3);

        // ELECTRIC_C - column 4
        processStringProperty(exportCable, hasElectricCurrent, csvRow, 4);

        // DIAMETER_M - column 5
        processNumericProperty(exportCable, hasDiameter, csvRow, 5);

        // STATUS - column 6
        processStringProperty(exportCable, hasStatus, csvRow, 6);

        // LENGTH_KIL - column 7
        processNumericProperty(exportCable, hasLength, csvRow, 7);

        // MIN_KILOVOLT - column 8
        processNumericProperty(exportCable, hasMinKilovolt, csvRow, 8);

        // MAX_KILOVOLT - column 9
        processNumericProperty(exportCable, hasMaxKilovolt, csvRow, 9);

        // geometry - column 10
        processStringProperty(exportCable, hasGeometry, csvRow, 10);
    }

    /**
     * Formats lease IDs in the standard OCS-A-#### pattern
     */
    private String formatOcsId(String originalId) {
        if (originalId == null || originalId.isEmpty()) {
            return "Unknown-ID";
        }

        // Trim whitespace
        String cleanId = originalId.trim();

        // Check if it contains OCS to attempt formatting
        if (cleanId.toUpperCase().contains("OCS")) {
            // Extract parts splitting by whitespace or existing hyphens
            String[] parts = cleanId.split("[-\\s]+");

            if (parts.length >= 3) {
                // Case like "OCS A 0483" or "OCS-A-0483"
                String prefix = parts[0].toUpperCase();
                String middle = parts[1].toUpperCase();
                String number = parts[2];

                // Ensure number has leading zeros if needed
                while (number.length() < 4) {
                    number = "0" + number;
                }

                return prefix + "-" + middle + "-" + number;
            } else if (parts.length == 2) {
                // Case like "OCS 0483"
                String prefix = parts[0].toUpperCase();
                String number = parts[1];

                // Ensure number has leading zeros if needed
                while (number.length() < 4) {
                    number = "0" + number;
                }

                return prefix + "-A-" + number;
            }
        }

        // For all other cases, simply replace spaces with hyphens
        return cleanId.replaceAll("\\s+", "-");
    }

    private void processStringProperty(Individual exportCable, DatatypeProperty property, String[] csvRow, int index) {
        if (property == null) return;

        if (csvRow.length > index) {
            String value = csvRow[index];
            // Check for null, empty or "NaN" values
            if (value == null || value.isEmpty() || value.equalsIgnoreCase("NaN")) {
                System.out.println("Replaced NaN/null " + property.getLocalName() + " with None for ExportCable: " + csvRow[0]);
                exportCable.addProperty(property, "None");
            } else {
                exportCable.addProperty(property, value);
            }
        } else {
            System.out.println("Missing " + property.getLocalName() + " column for ExportCable: " + csvRow[0] + ", using None");
            exportCable.addProperty(property, "None");
        }
    }

    private void processNumericProperty(Individual exportCable, DatatypeProperty property, String[] csvRow, int index) {
        if (property == null) return;

        if (csvRow.length > index) {
            String value = csvRow[index];
            // Check for null, empty or "NaN" values
            if (value == null || value.isEmpty() || value.equalsIgnoreCase("NaN")) {
                System.out.println("Replaced NaN/null " + property.getLocalName() + " with None for ExportCable: " + csvRow[0]);
                exportCable.addProperty(property, "None");
            } else {
                try {
                    double numValue = Double.parseDouble(value);
                    exportCable.addProperty(property, model.createTypedLiteral(numValue, XSDDatatype.XSDdouble));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numeric value for " + property.getLocalName() +
                                      " for ExportCable: " + csvRow[0] + ", value: " + value + ", replaced with None");
                    exportCable.addProperty(property, "None");
                }
            }
        } else {
            System.out.println("Missing " + property.getLocalName() + " column for ExportCable: " + csvRow[0] + ", using None");
            exportCable.addProperty(property, "None");
        }
    }
}