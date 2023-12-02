import java.util.Stack;

public class ShuntingYardAlgorithm {

    public static boolean operatorOrDigit(char c) {
        if (Character.isLetterOrDigit(c)) {
            return true;
        
        } else {
            return false;
        }
    }

    public static int getPrecedence(char c){
        if (c == '+' || c == '-') {
            return 1;
        }

        else if (c == '*' || c == '/') {
            return 2;
        }

        else if (c == '^') {
            return 3;
        }

        else {
            return -1;
        }
    }

    public static boolean hasLeftAssociativity(char c) {
        if (c == '+' || c == '-' || c == '/' || c == '/' ) {
            return true; 
        }

        return false;
    }

    public static String buildPostFix(String expression) {
        Stack <Character> stack = new Stack<>();
        String result = new String("");

        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            if (operatorOrDigit(c)) {
                result += c;
            
            } else if (c == '(') {
                stack.push(c);
            
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop(); 
            }

            else {
                while (
                    !stack.isEmpty() && 
                    getPrecedence(c) <= getPrecedence(stack.peek()) &&
                    hasLeftAssociativity(c)) {
                        result += stack.pop();
                    }
                    stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "This is invalid expression";
            }
            result += stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        String expression = "5+2/(3-8)^5^2";
        System.out.println(buildPostFix(expression));
    }
}