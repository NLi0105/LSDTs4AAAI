package Query;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class QueryThreshold {
    public static void saveQueryToFile(String queryString, String filePath) throws IOException {
        Files.write(Paths.get(filePath), queryString.getBytes());
    }

    public static void executeQueryFromFile(String filePath, Model model) throws IOException {
        String queryString = new String(Files.readAllBytes(Paths.get(filePath)));
        executeGeneralQuery(queryString, model);
    }

    public static void executeGeneralQuery(String queryString, Model model) {
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                Resource resource = soln.getResource("resource");
                System.out.println(resource.getURI());
            }
        }
    }
}
