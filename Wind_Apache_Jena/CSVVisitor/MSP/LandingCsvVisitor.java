package CSVVisitor.MSP;

import org.apache.jena.ontology.*;

public class LandingCsvVisitor {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_Landing = NS_MSP + "Landing#";

    private OntModel model;
    private OntClass landingClass;
    private DatatypeProperty hasLeaseNumber;
    private DatatypeProperty hasProjectName;
    private DatatypeProperty hasLandingSite;
    private DatatypeProperty hasDeveloper;
    private DatatypeProperty hasStatus;
    private DatatypeProperty hasGeometry;

    public LandingCsvVisitor(OntModel model) {
        this.model = model;
        landingClass = model.getOntClass(NS_Landing);

        // Initialize properties
        hasLeaseNumber = model.getDatatypeProperty(NS_Landing + "hasLeaseNumber");
        hasProjectName = model.getDatatypeProperty(NS_Landing + "hasProjectName");
        hasLandingSite = model.getDatatypeProperty(NS_Landing + "hasLandingSite");
        hasDeveloper = model.getDatatypeProperty(NS_Landing + "hasDeveloper");
        hasStatus = model.getDatatypeProperty(NS_Landing + "hasStatus");
        hasGeometry = model.getDatatypeProperty(NS_Landing + "hasGeometry");

        // Create properties if they don't exist
        if (landingClass == null) {
            System.err.println("Landing class not found, creating it now");
            landingClass = model.createClass(NS_Landing);
        }

        // Create any properties that might be missing
        createPropertyIfNotExists(hasLeaseNumber, "hasLeaseNumber", "string");
        createPropertyIfNotExists(hasProjectName, "hasProjectName", "string");
        createPropertyIfNotExists(hasLandingSite, "hasLandingSite", "string");
        createPropertyIfNotExists(hasDeveloper, "hasDeveloper", "string");
        createPropertyIfNotExists(hasStatus, "hasStatus", "string");
        createPropertyIfNotExists(hasGeometry, "hasGeometry", "string");
    }

    private void createPropertyIfNotExists(DatatypeProperty property, String propertyName, String dataType) {
        if (property == null) {
            if ("string".equals(dataType)) {
                property = model.createDatatypeProperty(NS_Landing + propertyName);
                property.addDomain(landingClass);
                property.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#string"));
            } else if ("double".equals(dataType)) {
                property = model.createDatatypeProperty(NS_Landing + propertyName);
                property.addDomain(landingClass);
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
        if (landingClass == null) {
            System.err.println("Landing class not found in the ontology model");
            return;
        }

        // Format the ID in the OCS-A-0483 style
        String cleanId = formatOcsId(csvRow[0]);

        // Create individual with clean URI
        Individual landing = model.createIndividual(NS_Landing + cleanId, landingClass);

        // LEASE_NUMB - column 0
        processStringProperty(landing, hasLeaseNumber, csvRow, 0);

        // PROJECT_NA - column 1
        processStringProperty(landing, hasProjectName, csvRow, 1);

        // LANDING_SI - column 2
        processStringProperty(landing, hasLandingSite, csvRow, 2);

        // DEVELOPER_ - column 3
        processStringProperty(landing, hasDeveloper, csvRow, 3);

        // STATUS - column 4
        processStringProperty(landing, hasStatus, csvRow, 4);

        // geometry - column 5
        processStringProperty(landing, hasGeometry, csvRow, 5);
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

    private void processStringProperty(Individual landing, DatatypeProperty property, String[] csvRow, int index) {
        if (property == null) return;

        if (csvRow.length > index) {
            String value = csvRow[index];
            // Check for null, empty or "NaN" values
            if (value == null || value.isEmpty() || value.equalsIgnoreCase("NaN")) {
                System.out.println("Replaced NaN/null " + property.getLocalName() + " with None for Landing: " + csvRow[0]);
                landing.addProperty(property, "None");
            } else {
                landing.addProperty(property, value);
            }
        } else {
            System.out.println("Missing " + property.getLocalName() + " column for Landing: " + csvRow[0] + ", using None");
            landing.addProperty(property, "None");
        }
    }
}