package com.runlaboratory.module1;

public class Problem2 {

    /*
    Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
    The input string does not contain leading or trailing spaces and the words are always separated by a single space.
    For example,
    Given s = "the sky is blue",
    return "blue is sky the".

    Could you do it in-place without allocating extra space?
    */

    public static void main(String[] args) {
        String input = "the sky is blue";
        System.out.println("Input : " + input);

        System.out.println("Solution 1 ============================");
        char[] output1 = solution1(input.toCharArray());
        System.out.println("output : " + String.valueOf(output1));

        System.out.println("Solution 2 ============================");
        char[] output2 = solution2(input.toCharArray());
        System.out.println("output : " + String.valueOf(output2));

        System.out.println("Solution 3 ============================");
        char[] output3 = solution3(input.toCharArray());
        System.out.println("output : " + String.valueOf(output3));

        System.out.println("Answer 1 ============================");
        char[] output4 = answer1(input.toCharArray());
        System.out.println("output : " + String.valueOf(output4));
    }

    // move char by char to right until meet a space, then ignore the completed part behind
    // move the space next to the check point, then repeat the rest
    public static char[] solution1(char[] input) {

        int checkPoint = input.length - 1;
        int wordLength = 0;

        for (int steps = 0; steps < input.length; steps++) {

            boolean isSpace = input[0] == ' ';
            if (isSpace) {
                checkPoint = checkPoint - wordLength;
                wordLength = 0;
            }

            for (int i = 0; i < checkPoint; i++) {
                char temp = input[i];
                input[i] = input[i + 1];
                input[i + 1] = temp;
            }

            if (isSpace) {
                checkPoint--;
            } else {
                wordLength++;
            }

            System.out.println(String.valueOf(input));
        }

        return input;
    }


    // reverse the input, then flip back each word
    public static char[] solution2(char[] input) {

        for (int i = 0; i < input.length / 2; i++) {
            char temp = input[i];
            input[i] = input[input.length - i - 1];
            input[input.length - i - 1] = temp;
        }

        System.out.println(String.valueOf(input));

        int checkPoint = 0;
        for (int i = 0; i < input.length; i++) {
            if (i == input.length - 1 || input[i + 1] == ' ') {
                int wordEnd = i;
                int loopCount = 0;
                int s = checkPoint;
                while (loopCount <= (wordEnd - checkPoint) / 2) {
                    char temp = input[s];
                    input[s] = input[wordEnd - loopCount];
                    input[wordEnd - loopCount] = temp;
                    loopCount++;
                    s++;
                    System.out.println(String.valueOf(input));
                }
                checkPoint = wordEnd + 2;
            }
        }

        return input;
    }

    // get word by word backward, and put into a new char array
    public static char[] solution3(char[] input) {
        char[] output = new char[input.length];
        int outputComplete = 0;
        int checkpoint = input.length - 1;
        for (int i = input.length - 1; i >= 0; i--) {
            if (i == 0 || input[i - 1] == ' ') {
                char[] word = splitChar(input, i, checkpoint);

                for (char w : word) {
                    output[outputComplete] = w;
                    outputComplete++;
                }
                if (outputComplete < output.length) {
                    output[outputComplete] = ' ';
                    outputComplete++;
                }
                System.out.println(String.valueOf(output));
                checkpoint = checkpoint - word.length - 1;
            }
        }

        return output;
    }

    public static char[] splitChar(char[] input, int startIndex, int endIndex) {

        char[] output = new char[endIndex - startIndex + 1];

        for (int i = 0; i < output.length; i++) {
            output[i] = input[startIndex + i];
        }

        return output;
    }

    // reverse each word then reverse all
    public static char[] answer1(char[] s) {
        int i = 0;
        for (int j = 0; j < s.length; j++) {
            if (s[j] == ' ') {
                answer1_reverse(s, i, j - 1);
                i = j + 1;
                System.out.println(String.valueOf(s));
            }
        }

        answer1_reverse(s, i, s.length - 1);
        System.out.println(String.valueOf(s));

        answer1_reverse(s, 0, s.length - 1);
        System.out.println(String.valueOf(s));

        return s;
    }

    public static char[] answer1_reverse(char[] s, int i, int j) {
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }

        return s;
    }

}
