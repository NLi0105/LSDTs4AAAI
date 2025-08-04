package CSVVisitor.MSP;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;

public class InterconnectionCsvVisitor {
    private static final String NS = "http://windfarm/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_Interconnection = NS_MSP + "Interconnection#";

    private OntModel model;
    private OntClass interconnectionClass;
    private DatatypeProperty hasLeaseNumber;
    private DatatypeProperty hasProjectName;
    private DatatypeProperty hasFacilityName;
    private DatatypeProperty hasDeveloper;
    private DatatypeProperty hasElectricCapacity;
    private DatatypeProperty hasInjectedMegawatt;
    private DatatypeProperty hasStatus;
    private DatatypeProperty hasGeometry;

    public InterconnectionCsvVisitor(OntModel model) {
        this.model = model;
        interconnectionClass = model.getOntClass(NS_Interconnection);

        // Initialize properties
        hasLeaseNumber = model.getDatatypeProperty(NS_Interconnection + "hasLeaseNumber");
        hasProjectName = model.getDatatypeProperty(NS_Interconnection + "hasProjectName");
        hasFacilityName = model.getDatatypeProperty(NS_Interconnection + "hasFacilityName");
        hasDeveloper = model.getDatatypeProperty(NS_Interconnection + "hasDeveloper");
        hasElectricCapacity = model.getDatatypeProperty(NS_Interconnection + "hasElectricCapacity");
        hasInjectedMegawatt = model.getDatatypeProperty(NS_Interconnection + "hasInjectedMegawatt");
        hasStatus = model.getDatatypeProperty(NS_Interconnection + "hasStatus");
        hasGeometry = model.getDatatypeProperty(NS_Interconnection + "hasGeometry");

        // Create class if it doesn't exist
        if (interconnectionClass == null) {
            System.err.println("Interconnection class not found, creating it now");
            interconnectionClass = model.createClass(NS_Interconnection);
        }

        // Create any properties that might be missing
        createPropertyIfNotExists(hasLeaseNumber, "hasLeaseNumber", "string");
        createPropertyIfNotExists(hasProjectName, "hasProjectName", "string");
        createPropertyIfNotExists(hasFacilityName, "hasFacilityName", "string");
        createPropertyIfNotExists(hasDeveloper, "hasDeveloper", "string");
        createPropertyIfNotExists(hasElectricCapacity, "hasElectricCapacity", "string");
        createPropertyIfNotExists(hasInjectedMegawatt, "hasInjectedMegawatt", "double");
        createPropertyIfNotExists(hasStatus, "hasStatus", "string");
        createPropertyIfNotExists(hasGeometry, "hasGeometry", "string");
    }

    private void createPropertyIfNotExists(DatatypeProperty property, String propertyName, String dataType) {
        if (property == null) {
            if ("string".equals(dataType)) {
                property = model.createDatatypeProperty(NS_Interconnection + propertyName);
                property.addDomain(interconnectionClass);
                property.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));
            } else if ("double".equals(dataType)) {
                property = model.createDatatypeProperty(NS_Interconnection + propertyName);
                property.addDomain(interconnectionClass);
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
        if (interconnectionClass == null) {
            System.err.println("Interconnection class not found in the ontology model");
            return;
        }

        // Format the ID in the OCS-A-0483 style
        String cleanId = formatOcsId(csvRow[0]);

        // Create individual with clean URI
        Individual interconnection = model.createIndividual(NS_Interconnection + cleanId, interconnectionClass);

        // LEASE_NUMB - column 0
        processStringProperty(interconnection, hasLeaseNumber, csvRow, 0);

        // PROJECT_NA - column 1
        processStringProperty(interconnection, hasProjectName, csvRow, 1);

        // FACILITY_N - column 2
        processStringProperty(interconnection, hasFacilityName, csvRow, 2);

        // DEVELOPER_ - column 3
        processStringProperty(interconnection, hasDeveloper, csvRow, 3);

        // ELECTRIC_C - column 4 (as string)
        processStringProperty(interconnection, hasElectricCapacity, csvRow, 4);

        // INJECTED_M - column 5
        processNumericProperty(interconnection, hasInjectedMegawatt, csvRow, 5);

        // STATUS - column 6
        processStringProperty(interconnection, hasStatus, csvRow, 6);

        // geometry - column 7
        processStringProperty(interconnection, hasGeometry, csvRow, 7);
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

    private void processStringProperty(Individual interconnection, DatatypeProperty property, String[] csvRow, int index) {
        if (property == null) return;

        if (csvRow.length > index) {
            String value = csvRow[index];
            // Check for null, empty or "NaN" values
            if (value == null || value.isEmpty() || value.equalsIgnoreCase("NaN")) {
                System.out.println("Replaced NaN/null " + property.getLocalName() + " with None for Interconnection: " + csvRow[0]);
                interconnection.addProperty(property, "None");
            } else {
                interconnection.addProperty(property, value);
            }
        } else {
            System.out.println("Missing " + property.getLocalName() + " column for Interconnection: " + csvRow[0] + ", using None");
            interconnection.addProperty(property, "None");
        }
    }

    private void processNumericProperty(Individual interconnection, DatatypeProperty property, String[] csvRow, int index) {
        if (property == null) return;

        if (csvRow.length > index) {
            String value = csvRow[index];
            // Check for null, empty or "NaN" values
            if (value == null || value.isEmpty() || value.equalsIgnoreCase("NaN")) {
                System.out.println("Replaced NaN/null " + property.getLocalName() + " with None for Interconnection: " + csvRow[0]);
                interconnection.addProperty(property, "None");
            } else {
                try {
                    double numValue = Double.parseDouble(value);
                    interconnection.addProperty(property, model.createTypedLiteral(numValue, XSDDatatype.XSDdouble));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numeric value for " + property.getLocalName() +
                                      " for Interconnection: " + csvRow[0] + ", value: " + value + ", replaced with None");
                    interconnection.addProperty(property, "None");
                }
            }
        } else {
            System.out.println("Missing " + property.getLocalName() + " column for Interconnection: " + csvRow[0] + ", using None");
            interconnection.addProperty(property, "None");
        }
    }
}