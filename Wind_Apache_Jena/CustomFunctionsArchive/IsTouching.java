package CustomFunctionsArchive;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.reasoner.rulesys.RuleContext;
import org.apache.jena.reasoner.rulesys.builtins.BaseBuiltin;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

import java.util.HashSet;
import java.util.Set;

public class IsTouching extends BaseBuiltin {
    private static final Set<String> completedRules = new HashSet<>();

    @Override
    public String getName() {
        return "isTouching";
    }

    @Override
    public int getArgLength() {
        return 2;
    }

    @Override
    public boolean bodyCall(Node[] args, int length, RuleContext context) {
        checkArgs(length, context);
        if (args.length < 2) {
            throw new IllegalArgumentException("IsTouching requires at least 2 arguments");
        }

        Node geomNode1 = getArg(0, args, context);
        Node geomNode2 = getArg(1, args, context);

        if (geomNode1.isLiteral() && geomNode2.isLiteral() &&
            geomNode1.getLiteralDatatype() == XSDDatatype.XSDstring &&
            geomNode2.getLiteralDatatype() == XSDDatatype.XSDstring) {

            String geomWKT1 = geomNode1.getLiteralLexicalForm();
            String geomWKT2 = geomNode2.getLiteralLexicalForm();

            try {
                WKTReader reader = new WKTReader();
                Geometry geom1 = reader.read(geomWKT1);
                Geometry geom2 = reader.read(geomWKT2);

                boolean isTouching = geom1.touches(geom2);
                Node result = NodeFactory.createLiteralByValue(isTouching, XSDDatatype.XSDboolean);
                context.getEnv().bind(args[0], result);

                String ruleName = context.getRule().getName();
                if (!completedRules.contains(ruleName)) {
                    System.out.println(ruleName + ": complete");
                    completedRules.add(ruleName);
                }

                return isTouching;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
}