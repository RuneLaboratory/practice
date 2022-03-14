package com.runelaboratory.module1.string_array;

import java.util.Arrays;

public class P7_MedianOfTwoSortedArrays {

    /*
    There are two sorted arrays A[] and B[] of size n each, write a program to find the median of the array obtained
    after merging both the arrays i.e. merged array of size 2n.

        - The median of a sorted array of size n is defined as the middle element when n is odd and the average of the
        middle two elements when n is even.
        - After merging both arrays, the size of the larger array will be 2n.

    Input: A[] = [1, 3, 6], B[] = [2, 8, 12]
    Output: 4.5

    Input: A[] = [1, 3, 4, 6, 9], B[] = [2, 5, 7, 8, 10]
    Output: 5.5
     */

    public static void main(String[] args) {
        int[] a1 = {1, 3, 6};
        int[] b1 = {2, 8, 12};
        System.out.println(Arrays.toString(a1) + " + " + Arrays.toString(b1));
        System.out.println("median = " + solution1(a1, b1));
        System.out.println("ans = " + answer1(a1, b1));
        System.out.println(Arrays.toString(b1) + " + " + Arrays.toString(a1));
        System.out.println("median = " + solution1(b1, a1));
        System.out.println("ans = " + answer1(b1, a1));


        int[] a2 = {1, 3, 4, 6, 9};
        int[] b2 = {2, 5, 7, 8, 10};
        System.out.println(Arrays.toString(a2) + " + " + Arrays.toString(b2));
        System.out.println("median = " + solution1(a2, b2));
        System.out.println("ans = " + answer1(a2, b2));
        System.out.println(Arrays.toString(b2) + " + " + Arrays.toString(a2));
        System.out.println("median = " + solution1(b2, a2));
        System.out.println("ans = " + answer1(b2, a2));


        int[] a3 = {3, 7, 8, 9, 10};
        int[] b3 = {1, 24, 26, 31};
        System.out.println(Arrays.toString(a3) + " + " + Arrays.toString(b3));
        System.out.println("median = " + solution1(a3, b3));
        System.out.println("ans = " + answer1(a3, b3));
        System.out.println(Arrays.toString(b3) + " + " + Arrays.toString(a3));
        System.out.println("median = " + solution1(b3, a3));
        System.out.println("ans = " + answer1(b3, a3));
    }

    private static double solution1(int[] a, int[] b) {

        double median = 0;
        int medianIndex = (a.length + b.length) / 2;
        boolean isEven = false;

        if ((a.length + b.length) % 2 == 0) {
            isEven = true;
        } else {
            medianIndex = medianIndex + 1;
        }

        int aIndex = 0, bIndex = 0;
        int loopCount = 0;

        while (loopCount < medianIndex) {

            if (a[aIndex] < b[bIndex]) {
                median = a[aIndex];
                aIndex++;
            } else {
                median = b[bIndex];
                bIndex++;
            }

            loopCount++;
        }

        if (isEven) {
            int next = a[aIndex] < b[bIndex] ? a[aIndex] : b[bIndex];
            median = (median + next) / 2;
        }

        return median;
    }

    private static double answer1(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total % 2 == 0) {
            return (getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, total / 2)
                    + getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, total / 2 - 1)) / 2.0;
        } else {
            return getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, total / 2);
        }
    }

    //k is the index starting from 0
    private static int getKth(int[] nums1, int i1, int j1, int[] nums2, int i2, int j2, int k) {

        if (j1 < i1) {
            return nums2[i2 + k];
        }
        if (j2 < i2) {
            return nums1[i1 + k];
        }

        if (k == 0) {
            return Math.min(nums1[i1], nums2[i2]);
        }

        int len1 = j1 - i1 + 1;
        int len2 = j2 - i2 + 1;

        int m1 = k * len1 / (len1 + len2);
        int m2 = k - m1 - 1;

        m1 += i1;
        m2 += i2;

        if (nums1[m1] < nums2[m2]) {
            k = k - (m1 - i1 + 1);
            j2 = m2;
            i1 = m1 + 1;
        } else {
            k = k - (m2 - i2 + 1);
            j1 = m1;
            i2 = m2 + 1;
        }

        return getKth(nums1, i1, j1, nums2, i2, j2, k);
    }
}
