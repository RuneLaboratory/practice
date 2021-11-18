package com.runlaboratory.module1.math;

public class P1_ReverseInteger {

    /*
    Reverse digits of an integer.
    We can convert the integer to a string/char array, reverse the order, and convert the string/char array back to an
    integer. However, this will require extra space for the string. It doesn't seem to be the right way, if you come
    with such a solution.

    Example1: x = 123, return 321
    Example2: x = -123, return -321
    */

    public static void main(String[] args) {
        int x = 85497;

        System.out.println("solution1 : " + solution1(x));
        System.out.println("answer1 : " + answer1(x));
        System.out.println("answer2 : " + answer2(x));
    }

    // Find out the length of the number, take out front digit and push to result (do asc)
    private static int solution1(int input) {

        boolean isNegative = false;
        if(input<0){
            input *= -1;
            isNegative = true;
        }

        int result = 0;
        int digit = 1;
        while (digit * 10 <= input) {
            digit = digit * 10;
        }

        int switchDigit = 1;
        for (; digit > 0; digit /= 10) {
            result = result + (switchDigit * (input / digit));
            input = input % digit;
            switchDigit *= 10;
        }

        if(isNegative){
            result *= -1;
        }
        return result;
    }

    // Get the last digit behind out and push to result * 10 (do desc)
    private static int answer1(int x) {
        //flag marks if x is negative
        boolean flag = false;
        if (x < 0) {
            x = 0 - x;
            flag = true;
        }

        int res = 0;
        int p = x;

        while (p > 0) {
            int mod = p % 10;
            p = p / 10;
            res = res * 10 + mod;
        }

        if (flag) {
            res = 0 - res;
        }

        return res;
    }

    // Even better, no need to extra handle negative number
    private static int answer2(int x) {
        int rev = 0;
        while(x != 0){
            rev = rev*10 + x%10;
            x = x/10;
        }
        return rev;
    }
}
