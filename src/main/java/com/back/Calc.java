package com.back;

import java.util.ArrayList;
import java.util.regex.*;

public class Calc {
    public static int run(String expression) {
        Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(expression);
        ArrayList<String> extractExpression = new ArrayList<>();
        while (matcher.find()) {
            extractExpression.add(matcher.group(1));
        }
        ArrayList<String> operator = new ArrayList<>();
        ArrayList<Integer> num = new ArrayList<>();
        for (String exp : extractExpression) {
            ArrayList<String> operatorTemp = new ArrayList<>();
            ArrayList<Integer> numTemp = new ArrayList<>();
            manipulate(exp, numTemp, operatorTemp);
            while (operatorTemp.stream().anyMatch(op -> op.equals("*"))) {
                findMul(operatorTemp, numTemp);
            }
            num.add(operation(numTemp.size(), numTemp, operatorTemp, 0, 0));
        }

        String expression2 = expression.replaceAll("\\(([^)]+)\\)", "");


        manipulate(expression2, num, operator);
        while (operator.stream().anyMatch(op -> op.equals("*"))) {
            findMul(operator, num);
        }
        return operation(num.size(), num, operator, 0, 0);
    }

    public static void manipulate(String expression, ArrayList<Integer> num, ArrayList<String> operator) {
        String[] expressionBit = expression.split(" ");
        for (String s : expressionBit) {
            if (s.isEmpty()) {
                continue;
            }
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
