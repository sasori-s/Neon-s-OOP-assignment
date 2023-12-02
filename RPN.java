import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RPN{
    private Stack<Integer> valueStack = new Stack<>();

    public int evaluateExpression(String expression) {
        String[] tokens = expression.split("\\s+");

        for (String token : tokens) {
            if (token.matches("-?\\d+")) {
                valueStack.push(Integer.parseInt(token));
            
            } else {
                if (valueStack.size() < 2) {
                    throw new ArithmeticException("Not enough operand in the stack");
                }

                int b = valueStack.pop();
                int a = valueStack.pop();

                switch (token) {
                    case "+":
                        valueStack.push(a + b);
                        break;
                    
                    case "-":
                        valueStack.push(a - b);
                        break;

                    case "*":
                        valueStack.push(a * b);
                        break;

                    case "/":
                        if (b == 0) {
                            throw new ArithmeticException("Division by zero");
                        }

                        valueStack.push(a / b);
                        break;

                    case "^":
                    valueStack.push((int) Math.pow(a, b));
                    break;

                    default:
                        throw new ArithmeticException("Invalid operator");
                }
            }
        }

        if (valueStack.size() != 1) {
            throw new ArithmeticException("Invalid expression");
        }
        return valueStack.pop();
    }


    public String convertExpressionToRPN(String expression) {
        String[] tokens = expression.split("\\s+");
        Stack<String> operatorStack = new Stack<>();
        Queue<String> outputQueue = new LinkedList<>();

        for (String token : tokens) {
            if (token.matches("-?\\d+")) {
                outputQueue.add(token);
            
            } else if (token.matches("[+\\-*/^]")) {
                while (!operatorStack.isEmpty() && getPrecedence(token) <= getPrecedence(operatorStack.peek())) {
                    outputQueue.add(operatorStack.pop());
                }
                operatorStack.push(token);
            
            } else if (token.equals("(")) {
                operatorStack.add(token);
            
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    outputQueue.add(operatorStack.pop());
                }

                if (!operatorStack.isEmpty() && operatorStack.peek().equals("(")) {
                    operatorStack.pop();
                
                } else {
                    throw new ArithmeticException("Parentheses are unbalanced");
                }

            }

        }

        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek().equals("(")) {
                throw new ArithmeticException("Parentheses are unbalanced");
            }
            outputQueue.add(operatorStack.pop());
        }

        return String.join(" ", outputQueue);
         
    }

    private int getPrecedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 2;

            case "*":
            case "/":
                return 3;

            case "^":
                return 4;
        
            default:
                return 1;
        }
    }

    public static void main(String[] args) {
        RPN calculator = new RPN();
        String rpnExpression = calculator.convertExpressionToRPN("3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3");
        System.out.println(rpnExpression);

        int result = calculator.evaluateExpression(rpnExpression);
        System.out.println(result);
    }
}