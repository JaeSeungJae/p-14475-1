package com.back;

import java.util.ArrayList;
import java.util.regex.*;

public class Calc {

    public static int run(String expression) {
        return evaluate(expression);
    }

    public static int evaluate(String expression) {
        // 괄호가 존재하면 가장 안쪽 괄호부터 재귀 처리
        Pattern pattern = Pattern.compile("\\(([^()]+)\\)");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String inner = matcher.group(1);         // 괄호 안 식
            int result = evaluate(inner);            // 재귀적으로 평가
            expression = expression.replaceFirst(Pattern.quote("(" + inner + ")"), Integer.toString(result));
            matcher = pattern.matcher(expression);   // 갱신 필요
        }

        // 괄호가 더 이상 없으면 직접 계산
        ArrayList<String> operator = new ArrayList<>();
        ArrayList<Integer> num = new ArrayList<>();
        calculate(operator, num, expression);
        return operation(num.size(), num, operator, 0, 0);
    }

    public static void calculate(ArrayList<String> op, ArrayList<Integer> num, String exp) {
        manipulate(exp, num, op);
        while (op.stream().anyMatch(ope -> ope.equals("*"))) {
            findMul(op, num);
        }
    }

    public static void manipulate(String expression, ArrayList<Integer> num, ArrayList<String> operator) {
        String[] expressionBit = expression.split(" ");
        for (String s : expressionBit) {
            if (s.isEmpty()) {
                continue;
            }
            s = s.replaceAll("[()]", ""); // 혹시 남아 있을 괄호 제거

            if (s.equals("+") || s.equals("-") || s.equals("*")) {
                operator.add(s);
            } else if (Character.isDigit(s.charAt(0)) || (s.length() > 1 && s.charAt(0) == '-' && Character.isDigit(s.charAt(1)))) {
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
