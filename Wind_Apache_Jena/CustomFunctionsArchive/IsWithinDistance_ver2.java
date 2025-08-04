package CustomFunctionsArchive;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.reasoner.rulesys.RuleContext;
import org.apache.jena.reasoner.rulesys.builtins.BaseBuiltin;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.util.AffineTransformation;
import org.locationtech.jts.io.WKTReader;

import java.util.HashSet;
import java.util.Set;

public class IsWithinDistance_ver2 extends BaseBuiltin {
    private static final Set<String> completedRules = new HashSet<>();

    @Override
    public String getName() {
        return "isWithinDistance";
    }

    @Override
    public int getArgLength() {
        return 4;
    }

    @Override
    public boolean bodyCall(Node[] args, int length, RuleContext context) {
        checkArgs(length, context);
        if (args.length < 4) {
            throw new IllegalArgumentException("IsWithinDistance requires 4 arguments");
        }

        Node geomNode1 = getArg(0, args, context);
        Node geomNode2 = getArg(1, args, context);
        Node distanceNode = getArg(2, args, context);
        Node unitNode = getArg(3, args, context);

        if (geomNode1.isLiteral() && geomNode2.isLiteral() &&
            geomNode1.getLiteralDatatype() == XSDDatatype.XSDstring &&
            geomNode2.getLiteralDatatype() == XSDDatatype.XSDstring &&
            distanceNode.isLiteral() && (distanceNode.getLiteralDatatype() == XSDDatatype.XSDdouble ||
                                         distanceNode.getLiteralDatatype() == XSDDatatype.XSDfloat ||
                                         distanceNode.getLiteralDatatype() == XSDDatatype.XSDint) &&
            unitNode.isLiteral() && unitNode.getLiteralDatatype() == XSDDatatype.XSDstring) {

            String geomWKT1 = geomNode1.getLiteralLexicalForm();
            String geomWKT2 = geomNode2.getLiteralLexicalForm();
            double distance = ((Number) distanceNode.getLiteralValue()).doubleValue();
            String unit = unitNode.getLiteralLexicalForm();

            // Convert distance to meters if necessary
            if (unit.equalsIgnoreCase("km")) {
                distance *= 1000;
            } else if (!unit.equalsIgnoreCase("m")) {
                throw new IllegalArgumentException("Unsupported unit: " + unit);
            }

            try {
                WKTReader reader = new WKTReader();
                Geometry geom1 = reader.read(geomWKT1);
                Geometry geom2 = reader.read(geomWKT2);

                // Transform coordinates from EPSG:4326 to EPSG:3857
                geom1 = transformToEPSG3857(geom1);
                geom2 = transformToEPSG3857(geom2);

                boolean isWithinDistance = geom1.isWithinDistance(geom2, distance);

                Node result = NodeFactory.createLiteralByValue(isWithinDistance, XSDDatatype.XSDboolean);
                context.getEnv().bind(args[0], result);

                String ruleName = context.getRule().getName();
                if (!completedRules.contains(ruleName)) {
                    System.out.println(ruleName + ": complete");
                    completedRules.add(ruleName);
                }

                return isWithinDistance;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    private Geometry transformToEPSG3857(Geometry geometry) {
        AffineTransformation transform = new AffineTransformation();
        transform.translate(20037508.34 / 180, 0);
        transform.scale(20037508.34 / 180, 20037508.34 / 180);
        return transform.transform(geometry);
    }
}