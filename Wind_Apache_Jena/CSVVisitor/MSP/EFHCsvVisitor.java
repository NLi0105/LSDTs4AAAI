package CSVVisitor.MSP;

import org.apache.jena.ontology.*;

public class EFHCsvVisitor {
    private static final String NS = "http://windfarm/";
    private static final String NS_MSP = NS + "MSP/";
    private static final String NS_EFH = NS_MSP + "EFH#";

    private OntModel model;
    private OntClass fishClass;
    private DatatypeProperty hasRegion;
    private DatatypeProperty hasFishSpecies;
    private DatatypeProperty hasGeometry;

    public EFHCsvVisitor(OntModel model) {
        this.model = model;
        fishClass = model.getOntClass(NS_EFH + "Fish");
        hasRegion = model.getDatatypeProperty(NS_EFH + "hasRegion");
        hasFishSpecies = model.getDatatypeProperty(NS_EFH + "hasFishSpecies");
        hasGeometry = model.getDatatypeProperty(NS_EFH + "hasGeometry");
    }

    public void visit(String[] csvRow) {
        Individual fish = model.createIndividual(NS_EFH + csvRow[0], fishClass);
        fish.addProperty(hasRegion, csvRow[0]);
        fish.addProperty(hasFishSpecies, csvRow[1]);
        fish.addProperty(hasGeometry, csvRow[2]);
    }
}
