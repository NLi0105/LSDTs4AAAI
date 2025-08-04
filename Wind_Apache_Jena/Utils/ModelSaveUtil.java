package Utils;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Utility class for saving RDF models with optimizations
 */
public class ModelSaveUtil {

    /**
     * Saves a model to file with truncated geometry values
     *
     * @param model The model to save
     * @param outputFilePath The path to save the file to
     * @param maxGeometryLength Maximum length for geometry values (will be truncated if longer)
     * @param format Output format (e.g., Lang.TURTLE, Lang.RDFXML)
     * @return true if save was successful, false otherwise
     */
    public static boolean saveWithTruncatedGeometries(Model model, String outputFilePath,
                                                    int maxGeometryLength, Lang format) {
        try {
            long startTime = System.currentTimeMillis();

            // Create model with shortened geometries
            Model shortGeomModel = ModelFactory.createDefaultModel();
            StmtIterator iter = model.listStatements();

            while (iter.hasNext()) {
                Statement stmt = iter.next();

                // Check if this is a geometry property
                if (stmt.getPredicate().getLocalName().contains("hasGeometry") && stmt.getObject().isLiteral()) {
                    String fullGeom = stmt.getLiteral().getString();

                    // Truncate if longer than specified length
                    if (fullGeom.length() > maxGeometryLength) {
                        String shortGeom = fullGeom.substring(0, maxGeometryLength) + "...";
                        shortGeomModel.add(stmt.getSubject(), stmt.getPredicate(),
                                ResourceFactory.createTypedLiteral(shortGeom, XSDDatatype.XSDstring));
                    } else {
                        // Keep original if already shorter than limit
                        shortGeomModel.add(stmt);
                    }
                } else {
                    // Copy all non-geometry statements as-is
                    shortGeomModel.add(stmt);
                }
            }

            // Write the truncated model to file
            try (OutputStream out = new FileOutputStream(outputFilePath)) {
                RDFDataMgr.write(out, shortGeomModel, format);

                long duration = System.currentTimeMillis() - startTime;
                System.out.println("Model saved with truncated geometries in " + (duration / 1000.0) + " seconds");
                return true;
            }
        } catch (IOException e) {
            System.err.println("Error saving model: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Overloaded method with default max geometry length of 30 characters
     */
    public static boolean saveWithTruncatedGeometries(Model model, String outputFilePath, Lang format) {
        return saveWithTruncatedGeometries(model, outputFilePath, 30, format);
    }
}
