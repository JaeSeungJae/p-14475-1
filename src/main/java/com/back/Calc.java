package com.back;

public class Calc {
    public static int run(String expression) {
        String[] expressionBit = expression.split(" ");
        int op_count = 0;
        int num_count = 0;
        String[] operator = new String[expressionBit.length];
        int[] num = new int[expressionBit.length];
        for (String s : expressionBit) {
            if (s.equals("+")) {
                operator[op_count++] = "+";
            } else if (s.equals("-")) {
                operator[op_count++] = "-";
            } else if (s.equals("*")) {
                operator[op_count++] = "*";
            } else if (Character.isDigit(s.charAt(0)) || s.charAt(0) == '-') {
                num[num_count++] = Integer.parseInt(s);
            }
        }
        return operation(num_count, num, operator, 0, 0);
    }

    public static int operation(int num_count, int[] num, String[] operator, int count, int sum) {
        if (count < 1) {
            sum += num[0];
        } else if (operator[count - 1].equals("+")) {
            sum += num[count];
        } else if (operator[count - 1].equals("-")) {
            sum -= num[count];
        } else if (operator[count - 1].equals("*")) {
            sum *= num[count];
        }
        count++;
        if (count < num_count) return operation(num_count, num, operator, count, sum);
        else return sum;
    }
}
