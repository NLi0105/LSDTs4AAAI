package Rules;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class RuleLoader {
    public static String loadRulesFromFile(String filePath) {
        StringBuilder ruleText = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            while (scanner.hasNextLine()) {
                ruleText.append(scanner.nextLine().trim()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ruleText.toString();
    }
}
