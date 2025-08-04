package CSVVisitor.MSP;

import org.apache.jena.ontology.*;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import org.locationtech.jts.operation.valid.IsValidOp;

public class RestrictedCsvVisitor {
    private static final String NS = "http://www.cee.umd.edu/Energy/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_Restricted = NS_MSP + "Restricted#";

    private OntModel model;
    private OntClass restrictedClass;
    private DatatypeProperty hasRestrictedType;
    private DatatypeProperty hasState;
    private DatatypeProperty hasRestrictedAgency;
    private DatatypeProperty hasGeometry;

    public RestrictedCsvVisitor(OntModel model) {
        this.model = model;
        restrictedClass = model.getOntClass(NS_Restricted + "Restricted");
        hasRestrictedType = model.getDatatypeProperty(NS_Restricted + "hasRestrictedType");
        hasState = model.getDatatypeProperty(NS_Restricted + "hasState");
        hasRestrictedAgency = model.getDatatypeProperty(NS_Restricted + "hasRestrictedAgency");
        hasGeometry = model.getDatatypeProperty(NS_Restricted + "hasGeometry");
    }

    private String validateGeometry(String wkt) {
        try {
            WKTReader reader = new WKTReader();
            Geometry geom = reader.read(wkt);

            IsValidOp validOp = new IsValidOp(geom);
            if (validOp.isValid()) {
                return wkt;
            }

            // Fix invalid geometry
            Geometry fixed = geom.buffer(0);
            WKTWriter writer = new WKTWriter();
            String fixedWkt = writer.write(fixed);
            System.err.println("Fixed invalid geometry in Restricted area");

            return fixedWkt;
        } catch (Exception e) {
            System.err.println("Error processing geometry: " + e.getMessage());
            return wkt;
        }
    }

    public void visit(String[] csvRow) {
        if (csvRow == null || csvRow.length < 4) {
            System.err.println("Invalid CSV row: insufficient columns");
            return;
        }

        String individualURI = NS_Restricted + csvRow[0].replace(" ", "");
        Individual restricted = model.createIndividual(individualURI, restrictedClass);
        restricted.addProperty(hasRestrictedType, csvRow[0]);
        restricted.addProperty(hasState, csvRow[1]);
        restricted.addProperty(hasRestrictedAgency, csvRow[2]);

        // Validate geometry before adding
        String validatedGeometry = validateGeometry(csvRow[3]);
        restricted.addProperty(hasGeometry, validatedGeometry);
    }
}