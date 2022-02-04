package com.runelaboratory.module1.matrix;

import java.util.Random;

public class P1_SetMatrixZeroes {

    /*
    Given a m * n matrix, if an element is 0, set its entire row and column to 0.
    For example, with 4 * 4 matrix below
    [1][1][1][0]            [0][0][0][0]
    [1][1][1][0]    ->      [0][0][0][0]
    [1][1][0][0]            [0][0][0][0]
    [1][0][0][0]            [0][0][0][0]
    */

    public static void main(String[] args) {

        int[][] matrix = genMatrix();
        System.out.println(matrix.length + " * " + matrix[0].length + " matrix");
        printMatrix(matrix);

        System.out.println("Solution 1 ============================");
        int[][] output1 = solution1(cloneMatrix(matrix));
        printMatrix(output1);

        System.out.println("Solution 2 ============================");
        int[][] output2 = solution2(cloneMatrix(matrix));
        printMatrix(output2);

        System.out.println("Answer ============================");
        int[][] answer = cloneMatrix(matrix);
        answer(answer);
        printMatrix(answer);
    }

    private static void printMatrix(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("[%d]", matrix[i][j]);
            }
            System.out.println("");
        }
    }

    private static int[][] cloneMatrix(int[][] ori) {
        int[][] newCopy = new int[ori.length][];
        for (int i = 0; i < ori.length; i++) {
            newCopy[i] = ori[i].clone();
        }
        return newCopy;
    }

    private static int[][] genMatrix() {
        int matrix[][] = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };

        return matrix;
    }

    // use row and column array to save setZero flag
    private static int[][] solution1(int[][] matrix) {

        boolean[] isRowSetZero = new boolean[matrix.length];
        boolean[] isColumnSetZero = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    isRowSetZero[i] = true;
                    isColumnSetZero[j] = true;
                }
            }
        }

        for (int r = 0; r < isRowSetZero.length; r++) {
            if (isRowSetZero[r]) {
                for (int c = 0; c < matrix[r].length; c++) {
                    matrix[r][c] = 0;
                }
            }
        }

        for (int c = 0; c < isColumnSetZero.length; c++) {
            if (isColumnSetZero[c]) {
                for (int r = 0; r < matrix.length; r++) {
                    matrix[r][c] = 0;
                }
            }
        }

        return matrix;
    }

    // use first row and column to save setZero flag
    public static int[][] solution2(int[][] matrix) {

        boolean isFirstRowZero = false;
        boolean isColumnRowZero = false;

        for (int c = 0; c < matrix[0].length; c++) {
            if (matrix[0][c] == 0) {
                isFirstRowZero = true;
            }
        }

        for (int r = 0; r < matrix.length; r++) {
            if (matrix[r][0] == 0) {
                isColumnRowZero = true;
            }
        }

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == 0) {
                    matrix[r][0] = 0;
                    matrix[0][c] = 0;
                }
            }
        }

        for (int r = 1; r < matrix.length; r++) {
            if (matrix[r][0] == 0) {
                for (int c = 1; c < matrix[r].length; c++) {
                    matrix[r][c] = 0;
                }
            }
        }

        for (int c = 1; c < matrix[0].length; c++) {
            if (matrix[0][c] == 0) {
                for (int r = 1; r < matrix.length; r++) {
                    matrix[r][c] = 0;
                }
            }
        }

        if (isFirstRowZero) {
            for (int c = 0; c < matrix[0].length; c++) {
                matrix[0][c] = 0;
            }
        }

        if (isColumnRowZero) {
            for (int r = 0; r < matrix.length; r++) {
                matrix[r][0] = 0;
            }
        }

        return matrix;
    }


    private static void answer(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColumnZero = false;

        //set first row and column zero or not
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstColumnZero = true;
                break;
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }

        //mark zeros on first row and column
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        //use mark to set elements
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        //set first column and row
        if (firstColumnZero) {
            for (int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
        }

        if (firstRowZero) {
            for (int i = 0; i < matrix[0].length; i++)
                matrix[0][i] = 0;
        }

    }
}
