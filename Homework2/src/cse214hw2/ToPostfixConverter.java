package cse214hw2;

import java.util.Stack;
import java.util.StringTokenizer;
import java.lang.String;


public class ToPostfixConverter implements Converter {

    @Override
    public String convert(ArithmeticExpression expression) {
        String s = expression.getExpression();
        //String s = expression;
        StringTokenizer a = new StringTokenizer(s, "(,),*,/,+,-", true);
        Stack<String> stack = new Stack<>();
        StringBuilder output = new StringBuilder();

        if(s.isEmpty()){
            return "";
        }
        while (a.hasMoreTokens()) {
            String token = a.nextToken();
            //System.out.println(token);
            if (Character.toString(Operator.LEFT_PARENTHESIS.getSymbol()).equals(token)) {
            //    System.out.println("Stack: " + stack.toString());
                stack.push(token);
            }
            else if(Character.toString(Operator.RIGHT_PARENTHESIS.getSymbol()).equals(token)){
                //System.out.println("Stack: " + stack.toString());
                while(!Character.toString(Operator.LEFT_PARENTHESIS.getSymbol()).equals(stack.peek())){
                    output.append(stack.pop());
                    output.append(" ");
                }
                stack.pop();
            }
            else if(Operator.isOperator(token)) {
                //System.out.println("Im reaching here");
                //System.out.println("Stack: " + stack.toString());

                if (stack.isEmpty() || Character.toString(Operator.LEFT_PARENTHESIS.getSymbol()).equals(stack.peek())) {
              //      System.out.println("Im reaching here 1");
                    stack.push(token);
                } else if (Operator.of(token).getRank() < Operator.of(stack.peek()).getRank()) {
                //    System.out.println("Im reaching here 2");
                    stack.push(token);
                } else if (Operator.of(token).getRank() == Operator.of(stack.peek()).getRank()) {
                  //  System.out.println("Im reaching here 3");
                    output.append(stack.pop());
                    output.append(" ");
                    stack.push(token);
                } else if (Operator.of(token).getRank() > Operator.of(stack.peek()).getRank()) {
                    output.append(stack.pop());
                    output.append(" ");
                    stack.push(token);
                    //test the input against new top element
                    // i think this is where im going wrong

                }

            }
            else{
                output.append(token);
                output.append(" ");
            }
            //System.out.println("Final Output 1:" + output.toString());
            //System.out.println("Stack Output 1:"+ stack.toString());
        }

        while(!stack.isEmpty()){
            output.append(stack.pop());
            output.append(" ");
        }
        //System.out.println("Stack last: " + stack.toString());
        return output.toString();
    }


    @Override
    public String nextToken(String s, int start) {
        String str = s.substring(start, start+1);

        if(!isOperand(str)){
            return str;
        }

        String token = "";

        while(start < s.length() && isOperand(str)){
            str = s.substring(start, start+1);
        }
        return token;
    }

    @Override
    public boolean isOperand(String s) {
        return !Operator.isOperator(s);
    }
}
