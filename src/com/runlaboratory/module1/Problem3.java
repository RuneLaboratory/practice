package com.runlaboratory.module1;

import java.util.Stack;

public class Problem3 {

    /*
    Evaluate the value of an arithmetic expression in Reverse Polish Notation. Valid operators are +, -, *, /.
    Each operand may be an integer or another expression. For example:
        ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
        ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
    */

    public static void main(String[] args) {
        String[] tokens1 = new String[]{"2", "1", "+", "3", "*"};
        String[] tokens2 = new String[]{"4", "13", "5", "/", "+"};

        System.out.println("Solution 1 ============================");
        System.out.println(solution1(tokens1));
        System.out.println(solution1(tokens2));

        System.out.println("Solution 2 ============================");
        System.out.println(solution2(tokens1));
        System.out.println(solution2(tokens2));

        System.out.println("Answer 1 ============================");
        System.out.println(answer1(tokens1));
        System.out.println(answer1(tokens2));
    }

    // use stack object
    public static int solution1(String[] tokens) {

        String operators = "+-*/";

        Stack<Integer> stack = new Stack<Integer>();

        for (String t : tokens) {
            if (!operators.contains(t)) {
                stack.push(Integer.valueOf(t));
            } else {
                int secondOperand = stack.pop();
                int firstOperand = stack.pop();
                switch (t) {
                    case "+": {
                        stack.push(firstOperand + secondOperand);
                    }
                    break;
                    case "-": {
                        stack.push(firstOperand - secondOperand);
                    }
                    break;
                    case "*": {
                        stack.push(firstOperand * secondOperand);
                    }
                    break;
                    case "/": {
                        stack.push(firstOperand / secondOperand);
                    }
                    break;
                }
            }
        }

        return stack.peek();
    }

    // use array
    public static int solution2(String[] tokens) {

        String operators = "+-*/";

        int[] stack = new int[tokens.length];
        int stackCount = 0;

        for (String t : tokens) {
            if (!operators.contains(t)) {
                stack[stackCount] = Integer.valueOf(t);
                stackCount++;
            } else {
                stackCount--;
                int secondOperand = stack[stackCount];
                int firstOperand = stack[stackCount - 1];
                switch (t) {
                    case "+": {
                        stack[stackCount - 1] = firstOperand + secondOperand;
                    }
                    break;
                    case "-": {
                        stack[stackCount - 1] = firstOperand - secondOperand;
                    }
                    break;
                    case "*": {
                        stack[stackCount - 1] = firstOperand * secondOperand;
                    }
                    break;
                    case "/": {
                        stack[stackCount - 1] = firstOperand / secondOperand;
                    }
                    break;
                }
            }
        }
        return stack[stackCount - 1];
    }

    public static int answer1(String[] tokens) {
        int returnValue = 0;
        String operators = "+-*/";

        Stack<String> stack = new Stack<String>();

        for (String t : tokens) {
            if (!operators.contains(t)) { //push to stack if it is a number
                stack.push(t);
            } else {//pop numbers from stack if it is an operator
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                switch (t) {
                    case "+":
                        stack.push(String.valueOf(a + b));
                        break;
                    case "-":
                        stack.push(String.valueOf(b - a));
                        break;
                    case "*":
                        stack.push(String.valueOf(a * b));
                        break;
                    case "/":
                        stack.push(String.valueOf(b / a));
                        break;
                }
            }
        }
        returnValue = Integer.valueOf(stack.pop());
        return returnValue;
    }
}
