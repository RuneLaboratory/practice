package com.runelaboratory.module1.matrix;

public class P3_SpiralMatrix2 {

    /*
    Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.
    For example, given n = 4,
    [
        [ 1,  2,  3, 4],
        [12, 13, 14, 5],
        [11, 16, 15, 6],
        [10,  9,  8, 7]
    ]
    */

    public static void main(String[] args) {
        printMatrix(solution(9));
        printMatrix(ans(9));
    }

    private static int[][] solution(int size) {
        int[][] result = new int[size][size];

        int nextNum = 1;
        int indexR = 0, indexC = 0;
        int curSize = size;

        while (nextNum <= size * size) {

            while (indexC < curSize) {
                result[indexR][indexC] = nextNum;
                nextNum++;
                indexC++;
            }

            indexR++;
            indexC--;

            while (indexR < curSize) {
                result[indexR][indexC] = nextNum;
                nextNum++;
                indexR++;
            }

            indexR--;
            indexC--;

            while (indexC >= (size - curSize)) {
                result[indexR][indexC] = nextNum;
                nextNum++;
                indexC--;
            }

            indexR--;
            indexC++;

            while (indexR > (size - curSize)) {
                result[indexR][indexC] = nextNum;
                nextNum++;
                indexR--;
            }

            indexR++;
            indexC++;

            curSize = curSize - 1;
        }

        return result;
    }

    private static void printMatrix(int[][] matrix) {
        System.out.println("----------------------------------");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%2d,", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("----------------------------------");
    }

    private static int[][] ans(int n) {
        int[][] result = new int[n][n];

        int k = 1;
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        while (k <= n * n) {
            for (int i = left; i <= right; i++) {
                result[top][i] = k;
                k++;
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                result[i][right] = k;
                k++;
            }
            right--;

            for (int i = right; i >= left; i--) {
                result[bottom][i] = k;
                k++;
            }
            bottom--;

            for (int i = bottom; i >= top; i--) {
                result[i][left] = k;
                k++;
            }
            left++;
        }

        return result;
    }
}
