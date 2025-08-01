package com.back;

public class Calc {
    public static int run(String expression) {
        String[] expressionBit = expression.split(" ");
        String operator = " ";
        for (String s : expressionBit) {
            if (s.equals("+")) {
                operator = "+";
            }
            if (s.equals("-")) {
                operator = "-";
            }
        }
        int num1 = Integer.parseInt(expressionBit[0]);
        int num2 = Integer.parseInt(expressionBit[2]);
        if (operator.equals("+")) {
            return num1 + num2;
        }
        if (operator.equals("-")) {
            return num1 - num2;
        }
        return 0;
    }
}
