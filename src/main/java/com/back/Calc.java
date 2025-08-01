package com.back;

public class Calc {
    public static int run(String expression) {
        String[] expressionBit = expression.split(" ");
        int sum = 0;
        int op_count = 0;
        String[] operator = new String[expressionBit.length];
        for (String s : expressionBit) {
            if (s.equals("+")) {
                operator[op_count] = "+";
                op_count++;
            }
            if (s.equals("-")) {
                operator[op_count] = "-";
                op_count++;
            }
        }
        int num1 = Integer.parseInt(expressionBit[0]);
        int num2 = Integer.parseInt(expressionBit[2]);
        int num3 = Integer.parseInt(expressionBit[4]);
        int num4 = Integer.parseInt(expressionBit[6]);
        if (operator[0].equals("+")) {
            if (operator[1].equals("+")) {
                if (operator[2].equals("+")) {
                    sum += num1 + num2 + num3 + num4;
                }
                else if (operator[2].equals("-")) {
                    sum += num1 + num2 + num3 - num4;
                }
            }
            else if (operator[1].equals("-")) {
                if (operator[2].equals("+")) {
                    sum += num1 + num2 - num3 + num4;
                }
                else if (operator[2].equals("-")) {
                    sum += num1 + num2 - num3 - num4;
                }
            }
        }
        if (operator[0].equals("-")) {
            if (operator[1].equals("+")) {
                if (operator[2].equals("+")) {
                    sum += num1 - num2 + num3 + num4;
                }
                else if (operator[2].equals("-")) {
                    sum += num1 - num2 + num3 - num4;
                }
            }
            else if (operator[1].equals("-")) {
                if (operator[2].equals("+")) {
                    sum += num1 - num2 - num3 + num4;
                }
                else if (operator[2].equals("-")) {
                    sum += num1 - num2 - num3 - num4;
                }
            }
        }


        return sum;
    }
}
