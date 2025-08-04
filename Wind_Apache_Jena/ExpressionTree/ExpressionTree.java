package ExpressionTree;
import java.util.Stack;
import java.util.List;


public class ExpressionTree {
    private boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^") || c.equals("%") ||
               c.equals("√") || c.equals("log") || c.equals("sin") || c.equals("cos") || c.equals("tan");
    }

    public Node constructTree(List<String> postfix) {
        Stack<Node> stack = new Stack<>();
        Node t, t1, t2;

        for (String s : postfix) {
            if (!isOperator(s)) {
                t = new Node(s);
                stack.push(t);
            } else {
                t = new Node(s);
                if (s.equals("√") || s.equals("log") || s.equals("sin") || s.equals("cos") || s.equals("tan")) {
                    t1 = stack.pop();
                    t.left = t1;
                } else {
                    t1 = stack.pop();
                    t2 = stack.pop();
                    t.right = t1;
                    t.left = t2;
                }
                stack.push(t);
            }
        }
        t = stack.peek();
        stack.pop();
        return t;
    }

    public double evaluate(Node root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return Double.parseDouble(root.value);
        double leftEval = evaluate(root.left);
        double rightEval = evaluate(root.right);
        switch (root.value) {
            case "+": return leftEval + rightEval;
            case "-": return leftEval - rightEval;
            case "*": return leftEval * rightEval;
            case "/": return leftEval / rightEval;
            case "^": return Math.pow(leftEval, rightEval);
            case "%": return leftEval % rightEval;
            case "√": return Math.sqrt(leftEval);
            case "log": return Math.log(leftEval);
            case "sin": return Math.sin(Math.toRadians(leftEval));
            case "cos": return Math.cos(Math.toRadians(leftEval));
            case "tan": return Math.tan(Math.toRadians(leftEval));
            default: throw new UnsupportedOperationException("Unsupported operator: " + root.value);
        }
    }
}