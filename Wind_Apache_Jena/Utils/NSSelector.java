package Utils;

import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.Model;
import java.util.ArrayList;
import java.util.List;

public class NSSelector {

    public static List<Statement> getFilteredStatements(Model model, String namespace) {
        List<Statement> filteredStatements = new ArrayList<>();
        StmtIterator iter = model.listStatements();
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            String subjectURI = stmt.getSubject().getURI();
            String predicateURI = stmt.getPredicate().getURI();
            if (subjectURI.startsWith(namespace) || predicateURI.startsWith(namespace)) {
                filteredStatements.add(stmt);
            }
        }
        return filteredStatements;
    }
}