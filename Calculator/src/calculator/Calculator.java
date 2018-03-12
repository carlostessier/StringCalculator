/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author max19
 */
public class Calculator {
    
    private static final String MULT = "* ";
    private static final String DIV = "/ ";
    private static final String PLUS = "+ ";
    private static final String MINUS = "- ";

    public static Double evaluate(String expression) {  
        while(true){  
            if(expression.contains(MULT) && expression.contains(DIV))
                if(expression.indexOf(MULT) < expression.indexOf(DIV))
                    expression = operation(expression, MULT);
                else
                    expression = operation(expression, DIV);
            else
                if(expression.contains(MULT))
                    expression = operation(expression, MULT);
                else
                    if(expression.contains(DIV))
                        expression = operation(expression, DIV);
                    else
                        break;
        }
        while(true){  
            if(expression.contains(PLUS) && expression.contains(MINUS))
                if(expression.indexOf(PLUS) < expression.indexOf(MINUS))
                    expression = operation(expression, PLUS);
                else
                    expression = operation(expression, MINUS);
            else
                if(expression.contains(PLUS))
                    expression = operation(expression, PLUS);
                else
                    if(expression.contains(MINUS))
                        expression = operation(expression, MINUS);
                    else
                        break;
        }    
        return Double.parseDouble(expression);
    }

    private static String operation(String expression,String operator) {
        double[][] values = getOperandsAndIndexes(expression, operator);
        double resultNumb = 0;
        switch(operator){
            case(MULT):
                resultNumb =  values[0][0] * values[0][1];
                break;
            case(DIV):
                resultNumb =  values[0][0] / values[0][1];
                break;
            case(PLUS):
                resultNumb =  values[0][0] + values[0][1];
                break;
            case(MINUS):
                resultNumb =  values[0][0] - values[0][1];
        }
        expression =  expression.replace(
                        expression.substring(
                                (int) values[1][0] + 1, 
                                ((int) values[1][1] + 1) + (int) values[1][2]
                        ),
                        " ".concat(String.valueOf(resultNumb)).concat(" ")
                    );
        return expression;
    }
    
    private static double[][] getOperandsAndIndexes(String expression, String operator){
        int currentOperIndex = expression.indexOf(operator);      
        String leftPart = expression.substring(0, currentOperIndex);
        int prevOperIndex = findPrevOperIndex(leftPart);
        double leftNumber = Double.parseDouble(leftPart.substring(prevOperIndex + 1, currentOperIndex).trim());       
        String rightPart = expression.substring(currentOperIndex + 1);
        int nextOperIndex = findNextOperIndex(rightPart);
        double rightNumber  = Double.parseDouble(rightPart.substring(0, nextOperIndex).trim());       
        double[][] values = {
                                {leftNumber, rightNumber},
                                {prevOperIndex, currentOperIndex, nextOperIndex}
                            };     
        return values;
    }

    private static int findNextOperIndex(String rightPart) {
        int nextOperIndex = rightPart.length();
        if(nextOperIndex > rightPart.indexOf(MULT) && rightPart.contains(MULT))
            nextOperIndex = rightPart.indexOf(MULT);
        if(nextOperIndex > rightPart.indexOf(DIV) && rightPart.contains(DIV))
            nextOperIndex = rightPart.indexOf(DIV);
        if(nextOperIndex > rightPart.indexOf(PLUS) && rightPart.contains(PLUS))
            nextOperIndex = rightPart.indexOf(PLUS);
        if(nextOperIndex > rightPart.indexOf(MINUS) && rightPart.contains(MINUS))
            nextOperIndex = rightPart.indexOf(MINUS);
        return nextOperIndex;
    }

    private static int findPrevOperIndex(String leftPart) {
        int prevOperIndex = -1;
        if(prevOperIndex < leftPart.lastIndexOf(MULT))
            prevOperIndex = leftPart.lastIndexOf(MULT);
        if(prevOperIndex < leftPart.lastIndexOf(DIV))
            prevOperIndex = leftPart.lastIndexOf(DIV);
        if(prevOperIndex < leftPart.lastIndexOf(PLUS))
            prevOperIndex = leftPart.lastIndexOf(PLUS);
        if(prevOperIndex < leftPart.lastIndexOf(MINUS)) 
            prevOperIndex = leftPart.lastIndexOf(MINUS);
        return prevOperIndex;
    }
    
    public static void main(String[] args) {
        System.out.println(evaluate("125 + 15 - 16 * 63 / 18 / 4 / 5 + 9 * 7 * 5 / 6"));
    }
    
}
