package cse214hw2;
import java.text.DecimalFormat;
import java.util.Stack;
import java.util.StringTokenizer;
import java.lang.String;

public class PostfixEvaluator implements Evaluator {

    @Override
    public double evaluate(String expressionString) {
        double answer = 0;
        Stack<Double> stack = new Stack();
        StringTokenizer a = new StringTokenizer(expressionString);
        if(expressionString.isEmpty()){
            return 0.0;
        }
        while (a.hasMoreTokens()) {
            String token = a.nextToken();
            if (!Operator.isOperator(token)) {
                stack.push((double) Integer.parseInt(token));
            } else if (Operator.isOperator(token)) {
                double pop1 = stack.pop();
                double pop2 = stack.pop();
                double pop3 = 0;
                if (Character.toString(Operator.ADDITION.getSymbol()).equals(token)) {
                    pop3 = pop2 + pop1;
                } else if (Character.toString(Operator.SUBTRACTION.getSymbol()).equals(token)) {
                    pop3 = pop2 - pop1;
                } else if (Character.toString(Operator.DIVISION.getSymbol()).equals(token)) {
                    pop3 = pop2 / pop1;
                } else if (Character.toString(Operator.MULTIPLICATION.getSymbol()).equals(token)) {
                    pop3 = pop1 * pop2;
                }
                //push back on stack
                stack.push(pop3);
            }
        }
        answer = stack.pop();
        double z =  Math.round(answer * 100.0) / 100.0;
        System.out.println(z);
        return z;
    }

}
