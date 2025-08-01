package com.back;

public class Calc {
    public static int run(String expression) {
        String[] expressionBit = expression.split("\\+");
        return
                Integer.parseInt(expressionBit[0].trim()) +
                Integer.parseInt(expressionBit[1].trim());
    }
}
