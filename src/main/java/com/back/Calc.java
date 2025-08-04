package com.back;

import java.util.ArrayList;

public class Calc {
    public static int run(String expression) {
        String[] expressionBit = expression.split(" ");
        int op_count = 0;
        int num_count = 0;
        //String[] operator = new String[expressionBit.length];
        ArrayList<String> operator = new ArrayList<>();
        //int[] num = new int[expressionBit.length];
        ArrayList<Integer> num = new ArrayList<>();
        for (String s : expressionBit) {
            if (s.equals("+")) {
                operator.add("+");
            } else if (s.equals("-")) {
                operator.add("-");
            } else if (s.equals("*")) {
                operator.add("*");
            } else if (Character.isDigit(s.charAt(0)) || s.charAt(0) == '-') {
                num.add(Integer.parseInt(s));
            }
        }
        while (operator.stream().anyMatch(op -> op.equals("*"))) {
            findMul(operator, num);
        }
        return operation(num.size(), num, operator, 0, 0);
    }

    public static int operation(int num_count, ArrayList<Integer> num, ArrayList<String> operator, int count, int sum) {
        if (count < 1) {
            sum += num.get(0);
        } else if (operator.get(count - 1).equals("+")) {
            sum += num.get(count);
        } else if (operator.get(count - 1).equals("-")) {
            sum -= num.get(count);
        } else if (operator.get(count - 1).equals("*")) {
            sum *= num.get(count);
        }
        count++;
        if (count < num_count) return operation(num_count, num, operator, count, sum);
        else return sum;
    }

    public static void findMul(ArrayList<String> operator, ArrayList<Integer> num) {
        for (int i = 0; i < operator.size(); i++) {
            if (operator.get(i).equals("*")) {
                int result = num.get(i) * num.get(i + 1);
                num.remove(i + 1);
                num.remove(i);
                num.add(i, result);
                operator.remove(i);
                return;
            }
        }
    }
}
