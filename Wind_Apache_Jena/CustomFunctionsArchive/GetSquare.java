package CustomFunctionsArchive;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.reasoner.rulesys.RuleContext;
import org.apache.jena.reasoner.rulesys.builtins.BaseBuiltin;

public class GetSquare extends BaseBuiltin {
    @Override
    public String getName() {
        return "getSquare";
    }

    @Override
    public int getArgLength() {
        return 2;
    }

    @Override
    public boolean bodyCall(Node[] args, int length, RuleContext context) {
        checkArgs(length, context);
        Node n1 = getArg(0, args, context);
        if (n1.isLiteral() && n1.getLiteralDatatype() == XSDDatatype.XSDdouble) {
            double value = ((Number) n1.getLiteralValue()).doubleValue();
            double squaredValue = value * value;
            Node result = NodeFactory.createLiteralByValue(squaredValue, XSDDatatype.XSDdouble);
            context.getEnv().bind(args[1], result);
            return true;
        } else {
            return false;
        }
    }
}
