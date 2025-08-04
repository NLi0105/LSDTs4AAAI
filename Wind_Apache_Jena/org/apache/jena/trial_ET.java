package org.apache.jena;

import ExpressionTree.Node;
import ExpressionTree.ExpressionTree;
import ExpressionTree.ExpressionParser;

import java.util.Arrays;
import java.util.List;

public class trial_ET {
    public static void main(String[] args) {
        String[] postfix = {"3", "4", "+", "2", "*", "7", "/"};
        List<String> postfixList = Arrays.asList(postfix);
        ExpressionTree et = new ExpressionTree();
        Node root = et.constructTree(postfixList);
        double result = et.evaluate(root);
        System.out.println("Result: " + result);

        String expression = "3 + √5 * (2 - 8)^2";
        List<String> tokens = ExpressionParser.tokenize(expression);
        List<String> postfixTokens = ExpressionParser.infixToPostfix(tokens);

        ExpressionTree et2 = new ExpressionTree();
        Node root2 = et2.constructTree(postfixTokens);
        double result2 = et2.evaluate(root2);
        System.out.println("Result: " + result2);

        for (String token : postfixTokens) {
            System.out.print(token + " ");
        }
        System.out.println();

    }
}