package ExpressionTree;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {
    private static final Pattern TOKEN_PATTERN = Pattern.compile("\\d+|\\+|\\-|\\*|\\/|\\^|\\(|\\)|√|log|sin|cos|tan");

    public static List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = TOKEN_PATTERN.matcher(expression);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }

    public static List<String> infixToPostfix(List<String> infixTokens) {
        List<String> postfix = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        Map<String, Integer> precedence = new HashMap<>();
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);
        precedence.put("^", 3);
        precedence.put("√", 4); // Higher precedence for unary operators
        precedence.put("log", 4);
        precedence.put("sin", 4);
        precedence.put("cos", 4);
        precedence.put("tan", 4);

        for (String token : infixTokens) {
            if (token.matches("\\d+")) {
                postfix.add(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.add(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence.getOrDefault(stack.peek(), 0) >= precedence.get(token)) {
                    postfix.add(stack.pop());
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }

        return postfix;
    }
}