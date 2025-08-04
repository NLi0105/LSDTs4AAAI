package CSVVisitor.MSP;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;

public class CableCsvVisitor {
    private static final String NS = "http://windfarm/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_Cable = NS_MSP + "Cable#";

    private OntModel model;
    private OntClass cableClass;
    private DatatypeProperty hasLeaseNumber;
    private DatatypeProperty hasProjectName;
    private DatatypeProperty hasDeveloper;
    private DatatypeProperty hasCableType;
    private DatatypeProperty hasStatus;
    private DatatypeProperty hasLength;
    private DatatypeProperty hasMinKilovolt;
    private DatatypeProperty hasMaxKilovolt;
    private DatatypeProperty hasMinDiameter;
    private DatatypeProperty hasMaxDiameter;
    private DatatypeProperty hasGeometry;

    public CableCsvVisitor(OntModel model) {
        this.model = model;
        cableClass = model.getOntClass(NS_Cable);

        // Initialize properties
        hasLeaseNumber = model.getDatatypeProperty(NS_Cable + "hasLeaseNumber");
        hasProjectName = model.getDatatypeProperty(NS_Cable + "hasProjectName");
        hasDeveloper = model.getDatatypeProperty(NS_Cable + "hasDeveloper");
        hasCableType = model.getDatatypeProperty(NS_Cable + "hasCableType");
        hasStatus = model.getDatatypeProperty(NS_Cable + "hasStatus");
        hasLength = model.getDatatypeProperty(NS_Cable + "hasLength");
        hasMinKilovolt = model.getDatatypeProperty(NS_Cable + "hasMinKilovolt");
        hasMaxKilovolt = model.getDatatypeProperty(NS_Cable + "hasMaxKilovolt");
        hasMinDiameter = model.getDatatypeProperty(NS_Cable + "hasMinDiameter");
        hasMaxDiameter = model.getDatatypeProperty(NS_Cable + "hasMaxDiameter");
        hasGeometry = model.getDatatypeProperty(NS_Cable + "hasGeometry");

        // Create properties if they don't exist
        if (hasMinKilovolt == null) {
            hasMinKilovolt = model.createDatatypeProperty(NS_Cable + "hasMinKilovolt");
            hasMinKilovolt.addDomain(cableClass);
            hasMinKilovolt.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#double"));
        }

        if (hasMaxKilovolt == null) {
            hasMaxKilovolt = model.createDatatypeProperty(NS_Cable + "hasMaxKilovolt");
            hasMaxKilovolt.addDomain(cableClass);
            hasMaxKilovolt.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#double"));
        }

        if (hasMinDiameter == null) {
            hasMinDiameter = model.createDatatypeProperty(NS_Cable + "hasMinDiameter");
            hasMinDiameter.addDomain(cableClass);
            hasMinDiameter.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#double"));
        }

        if (hasMaxDiameter == null) {
            hasMaxDiameter = model.createDatatypeProperty(NS_Cable + "hasMaxDiameter");
            hasMaxDiameter.addDomain(cableClass);
            hasMaxDiameter.addRange(model.getResource("http://www.w3.org/2001/XMLSchema#double"));
        }
    }

    public void visit(String[] csvRow) {
        if (cableClass == null) {
            System.err.println("Cable class not found in the ontology model");
            return;
        }

        // Format the ID in the OCS-A-0483 style
        String cleanId = formatOcsId(csvRow[0]);

        // Create individual with clean URI
        Individual cable = model.createIndividual(NS_Cable + cleanId, cableClass);

        // LEASE_NUMB - column 0
        processStringProperty(cable, hasLeaseNumber, csvRow, 0);

        // PROJECT_NA - column 1
        processStringProperty(cable, hasProjectName, csvRow, 1);

        // DEVELOPER_ - column 2
        processStringProperty(cable, hasDeveloper, csvRow, 2);

        // CABLE_TYPE - column 3
        processStringProperty(cable, hasCableType, csvRow, 3);

        // STATUS - column 4
        processStringProperty(cable, hasStatus, csvRow, 4);

        // LENGTH_KIL - column 5
        processNumericProperty(cable, hasLength, csvRow, 5);

        // MIN_KILOVOLT - column 6
        processNumericProperty(cable, hasMinKilovolt, csvRow, 6);

        // MAX_KILOVOLT - column 7
        processNumericProperty(cable, hasMaxKilovolt, csvRow, 7);

        // MIN_DIAMETER - column 8
        processNumericProperty(cable, hasMinDiameter, csvRow, 8);

        // MAX_DIAMETER - column 9
        processNumericProperty(cable, hasMaxDiameter, csvRow, 9);

        // geometry - column 10
        processStringProperty(cable, hasGeometry, csvRow, 10);
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

    private void processStringProperty(Individual cable, DatatypeProperty property, String[] csvRow, int index) {
        if (property == null) return;

        if (csvRow.length > index) {
            String value = csvRow[index];
            // Check for null, empty or "NaN" values
            if (value == null || value.isEmpty() || value.equalsIgnoreCase("NaN")) {
                System.out.println("Replaced NaN/null " + property.getLocalName() + " with None for Cable: " + csvRow[0]);
                cable.addProperty(property, "None");
            } else {
                cable.addProperty(property, value);
            }
        } else {
            System.out.println("Missing " + property.getLocalName() + " column for Cable: " + csvRow[0] + ", using None");
            cable.addProperty(property, "None");
        }
    }

    private void processNumericProperty(Individual cable, DatatypeProperty property, String[] csvRow, int index) {
        if (property == null) return;

        if (csvRow.length > index) {
            String value = csvRow[index];
            // Check for null, empty or "NaN" values
            if (value == null || value.isEmpty() || value.equalsIgnoreCase("NaN")) {
                System.out.println("Replaced NaN/null " + property.getLocalName() + " with None for Cable: " + csvRow[0]);
                cable.addProperty(property, "None");
            } else {
                try {
                    double numValue = Double.parseDouble(value);
                    cable.addProperty(property, model.createTypedLiteral(numValue, XSDDatatype.XSDdouble));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numeric value for " + property.getLocalName() +
                                      " for Cable: " + csvRow[0] + ", value: " + value + ", replaced with None");
                    cable.addProperty(property, "None");
                }
            }
        } else {
            System.out.println("Missing " + property.getLocalName() + " column for Cable: " + csvRow[0] + ", using None");
            cable.addProperty(property, "None");
        }
    }
}