package com.runelaboratory.module1.string_array;

import java.util.stream.IntStream;

public class P1_RotateArrayToRight {

    /*
    Rotate an array of n elements to the right by k steps.
    For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. How many different ways do you know to solve this problem?
    */

    public static void main(String[] args) {
        int[] output1 = solution1(8, 4);
        int[] output2 = solution2(7, 3);
    }

    // move to right one step by step
    private static int[] solution1(int n, int k) {

        int[] array = IntStream.range(1, n + 1).toArray();
        printArray(array);

        for (int steps = 0; steps < k; steps++) {
            for (int i = array.length - 1; i > 0; i--) {
                int hold = array[i - 1];
                array[i - 1] = array[i];
                array[i] = hold;
            }
        }

        printArray(array);
        return array;
    }

    // direct copy to another array in correct position
    private static int[] solution2(int n, int k) {

        int[] input = IntStream.range(1, n + 1).toArray();
        printArray(input);

        int[] output = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            int t = (i + k) % input.length;
            output[t] = input[i];
        }

        printArray(output);
        return output;
    }

    private static void printArray(int[] array) {
        System.out.println("");
        for (int num : array) {
            System.out.print(num + ", ");
        }
    }

    // In a straightforward way, we can create a new array and then copy elements to the new array
    private static void answer1(int[] nums, int k) {
        if (k > nums.length)
            k = k % nums.length;

        int[] result = new int[nums.length];

        for (int i = 0; i < k; i++) {
            result[i] = nums[nums.length - k + i];
        }

        int j = 0;
        for (int i = k; i < nums.length; i++) {
            result[i] = nums[j];
            j++;
        }

        System.arraycopy(result, 0, nums, 0, nums.length);
    }

    // Bubble Rotate
    private static void answer2(int[] arr, int order) {
        if (arr == null || order < 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        for (int i = 0; i < order; i++) {
            for (int j = arr.length - 1; j > 0; j--) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }
    }

    // Reversal
    // 1. Divide the array two parts: 1,2,3,4 and 5, 6
    // 2. Reverse first part: 4,3,2,1,5,6
    // 3. Reverse second part: 4,3,2,1,6,5
    // 4. Reverse the whole array: 5,6,1,2,3,4
    private static void answer3(int[] arr, int order) {
        if (arr == null || arr.length == 0 || order < 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        if (order > arr.length) {
            order = order % arr.length;
        }

        //length of first part
        int a = arr.length - order;

        answer3_reverse(arr, 0, a - 1);
        answer3_reverse(arr, a, arr.length - 1);
        answer3_reverse(arr, 0, arr.length - 1);

    }

    private static void answer3_reverse(int[] arr, int left, int right) {
        if (arr == null || arr.length == 1)
            return;

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}

