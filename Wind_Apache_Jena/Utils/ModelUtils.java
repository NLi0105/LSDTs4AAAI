package Utils;

import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.datatypes.xsd.XSDDatatype;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;

/**
 * Utility class for RDF model operations with comprehensive timing metrics
 */
public class ModelUtils {

    // Default geometry truncation length for saving models
    private static final int DEFAULT_GEOMETRY_LENGTH = 100;

    /**
     * Configuration class for model saving options
     */
    public static class SaveOptions {
        private boolean truncateGeometry = false;
        private int maxGeometryLength = DEFAULT_GEOMETRY_LENGTH;
        private boolean showMetrics = true;

        public SaveOptions() {}

        public SaveOptions truncateGeometry(boolean truncate) {
            this.truncateGeometry = truncate;
            return this;
        }

        public SaveOptions maxGeometryLength(int length) {
            this.maxGeometryLength = length;
            return this;
        }

        public SaveOptions showMetrics(boolean show) {
            this.showMetrics = show;
            return this;
        }

        // Getters
        public boolean isTruncateGeometry() { return truncateGeometry; }
        public int getMaxGeometryLength() { return maxGeometryLength; }
        public boolean isShowMetrics() { return showMetrics; }
    }

    /**
     * Enhanced save method with configurable options
     */
    public static boolean saveModel(Model model, String outputFilePath, Lang format, SaveOptions options) {
        if (options.isTruncateGeometry()) {
            return saveWithTruncatedGeometries(model, outputFilePath, format,
                                             options.getMaxGeometryLength(), options.isShowMetrics());
        } else {
            return saveFullModel(model, outputFilePath, format, options.isShowMetrics());
        }
    }

    /**
     * Save RDF model to file with timing information and truncated geometries (legacy method)
     */
    public static boolean saveModel(Model model, String outputFilePath, Lang format) {
        return saveModel(model, outputFilePath, format, new SaveOptions().truncateGeometry(true));
    }

    /**
     * Save model with geometry literals truncated to a specific length (updated with metrics control)
     */
    public static boolean saveWithTruncatedGeometries(Model model, String outputFilePath,
                                                    Lang format, int maxGeometryLength) {
        return saveWithTruncatedGeometries(model, outputFilePath, format, maxGeometryLength, true);
    }

    /**
     * Save model with geometry literals truncated to a specific length with metrics control
     */
    private static boolean saveWithTruncatedGeometries(Model model, String outputFilePath,
                                                     Lang format, int maxGeometryLength, boolean showMetrics) {
        Instant startTime = Instant.now();
        long statementCount = 0;
        long geometryCount = 0;
        long truncatedCount = 0;

        try {
            // Create model with shortened geometries
            Model shortGeomModel = ModelFactory.createDefaultModel();

            // Add all statements to the new model, truncating geometries as needed
            StmtIterator iter = model.listStatements();
            while (iter.hasNext()) {
                Statement stmt = iter.nextStatement();
                statementCount++;

                // Check if this is a geometry statement and is a literal
                if (stmt.getPredicate().getLocalName().contains("hasGeometry") &&
                    stmt.getObject().isLiteral()) {
                    geometryCount++;
                    String fullGeom = stmt.getLiteral().getString();

                    // Truncate if longer than specified length
                    if (fullGeom.length() > maxGeometryLength) {
                        truncatedCount++;
                        String shortGeom = fullGeom.substring(0, maxGeometryLength) + "...";
                        shortGeomModel.add(stmt.getSubject(), stmt.getPredicate(),
                                ResourceFactory.createTypedLiteral(shortGeom, XSDDatatype.XSDstring));
                    } else {
                        shortGeomModel.add(stmt);
                    }
                } else {
                    shortGeomModel.add(stmt);
                }
            }

            // Write the truncated model to file
            try (OutputStream out = new FileOutputStream(outputFilePath)) {
                Instant writeStartTime = Instant.now();
                RDFDataMgr.write(out, shortGeomModel, format);
                Duration writeTime = Duration.between(writeStartTime, Instant.now());

                if (showMetrics) {
                    Duration totalDuration = Duration.between(startTime, Instant.now());
                    double seconds = totalDuration.toMillis() / 1000.0;
                    double statementsPerSecond = seconds > 0 ? statementCount / seconds : 0;
                    NumberFormat nf = NumberFormat.getNumberInstance();

                    System.out.println("╔═════════════════════════════════════════════════════════════╗");
                    System.out.println("║                    MODEL SAVE METRICS                       ║");
                    System.out.println("╠═════════════════════════════════════════════════════════════╣");
                    System.out.println("║ Output file: " + outputFilePath);
                    System.out.println("║ Format: " + format.getName());
                    System.out.println("║ Total time: " + formatDuration(totalDuration));
                    System.out.println("║ Processing time: " + formatDuration(totalDuration.minus(writeTime)));
                    System.out.println("║ Write time: " + formatDuration(writeTime));
                    System.out.println("║ Statements: " + nf.format(statementCount));
                    System.out.println("║ Performance: " + nf.format((int)statementsPerSecond) + " statements/sec");
                    System.out.println("║ Geometry statements: " + nf.format(geometryCount));
                    System.out.println("║ Truncated geometries: " + nf.format(truncatedCount) +
                                     " (" + (geometryCount > 0 ? nf.format(truncatedCount * 100 / geometryCount) : "0") + "%)");
                    System.out.println("║ Max geometry length: " + maxGeometryLength + " chars");
                    System.out.println("╚═════════════════════════════════════════════════════════════╝");
                }

                return true;
            }
        } catch (Exception e) {
            System.err.println("Error saving model: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Save full model without truncating geometries with enhanced metrics (updated with metrics control)
     */
    public static boolean saveFullModel(Model model, String outputFilePath, Lang format) {
        return saveFullModel(model, outputFilePath, format, true);
    }

    /**
     * Save full model without truncating geometries with metrics control
     */
    private static boolean saveFullModel(Model model, String outputFilePath, Lang format, boolean showMetrics) {
        Instant startTime = Instant.now();
        long statementCount = model.size();

        // Count geometry statements
        long geometryCount = 0;
        StmtIterator iter = model.listStatements();
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            if (stmt.getPredicate().getLocalName().contains("hasGeometry") && stmt.getObject().isLiteral()) {
                geometryCount++;
            }
        }

        try (OutputStream out = new FileOutputStream(outputFilePath)) {
            Instant writeStartTime = Instant.now();
            RDFDataMgr.write(out, model, format);
            Duration writeTime = Duration.between(writeStartTime, Instant.now());

            if (showMetrics) {
                Duration totalDuration = Duration.between(startTime, Instant.now());
                double seconds = totalDuration.toMillis() / 1000.0;
                double statementsPerSecond = seconds > 0 ? statementCount / seconds : 0;
                NumberFormat nf = NumberFormat.getNumberInstance();

                System.out.println("╔═════════════════════════════════════════════════════════════╗");
                System.out.println("║                    MODEL SAVE METRICS                       ║");
                System.out.println("╠═════════════════════════════════════════════════════════════╣");
                System.out.println("║ Output file: " + outputFilePath);
                System.out.println("║ Format: " + format.getName());
                System.out.println("║ Total time: " + formatDuration(totalDuration));
                System.out.println("║ Processing time: " + formatDuration(totalDuration.minus(writeTime)));
                System.out.println("║ Write time: " + formatDuration(writeTime));
                System.out.println("║ Statements: " + nf.format(statementCount));
                System.out.println("║ Performance: " + nf.format((int)statementsPerSecond) + " statements/sec");
                System.out.println("║ Geometry statements: " + nf.format(geometryCount));
                System.out.println("║ Geometry truncation: none (full model)");
                System.out.println("╚═════════════════════════════════════════════════════════════╝");
            }

            return true;
        } catch (IOException e) {
            System.err.println("Error saving model: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Format duration into a readable string
     */
    private static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        long millis = duration.toMillisPart();

        if (hours > 0) {
            return String.format("%d:%02d:%02d.%03d", hours, minutes, seconds, millis);
        } else if (minutes > 0) {
            return String.format("%d:%02d.%03d", minutes, seconds, millis);
        } else {
            return String.format("%d.%03d seconds", seconds, millis);
        }
    }
}