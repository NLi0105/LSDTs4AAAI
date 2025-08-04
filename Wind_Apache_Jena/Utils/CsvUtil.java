package Utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class CsvUtil {

    public static void ReadCSV(String csvFilePath, CsvVisitor visitor) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] header = reader.readNext(); // Read the header
            String[] line;
            while ((line = reader.readNext()) != null) {
                visitor.visit(line);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public interface CsvVisitor {
        void visit(String[] csvRow);
    }
}
