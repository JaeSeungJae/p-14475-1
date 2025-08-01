package com.back;

public class Calc {
    public static int run(String expression) {
        String[] expressionBit = expression.split(" ");
        int sum = 0;
        int op_count = 0;
        int num_count = 0;
        String[] operator = new String[expressionBit.length];
        int[] num = new int[expressionBit.length];
        for (String s : expressionBit) {
            if (s.equals("+")) {
                operator[op_count++] = "+";
            }
            if (s.equals("-")) {
                operator[op_count++] = "-";
            }
            if (s.equals("*")) {
                operator[op_count++] = "*";
            }
            if (Character.isDigit(s.charAt(0)) || Character.isDigit(s.charAt(1))) {
                num[num_count++] = Integer.parseInt(s);
            }
        }

        for (int i = 0; i < num_count; i++) {
            if (i < 1) {
                sum += num[0];
            }
            else if (operator[i - 1].equals("+")) {
               sum += num[i];
            }
            else if (operator[i - 1].equals("-")) {
                sum -= num[i];
            }
            else if (operator[i - 1].equals("*")) {
                sum *= num[i];
            }
        }

        return sum;
    }
}
