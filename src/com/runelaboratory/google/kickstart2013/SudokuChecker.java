package com.runelaboratory.google.kickstart2013;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class SudokuChecker {

    // reference link https://codingcompetitions.withgoogle.com/kickstart/round/0000000000434ad7/00000000004347b3

    /*
    Given a completed N2xN2 Sudoku matrix, your task is to determine whether it is a valid solution. A valid solution must satisfy the following criteria:

    - Each row contains each number from 1 to N2, once each.
    - Each column contains each number from 1 to N2, once each.
    - Divide the N2xN2 matrix into N2 non-overlapping NxN sub-matrices. Each sub-matrix contains each number from 1 to N2, once each.

    You don't need to worry about the uniqueness of the problem. Just check if the given matrix is a valid solution.

    Input
    The first line of the input gives the number of test cases, T. T test cases follow. Each test case starts with an
    integer N. The next N2 lines describe a completed Sudoku solution, with each line contains exactly N2 integers.
    All input integers are positive and less than 1000.

    T = number of sudoku games
    N = size of each sudoku box (usually is 3 x 3)
    N^2 = size of sudoku (usually is 3 ^ 2 = 9 x 9)

    Output
    For each test case, output one line containing "Case #x: y", where x is the case number (starting from 1) and y
    is "Yes" (quotes for clarity only) if it is a valid solution, or "No" (quotes for clarity only) if it is invalid.
    Note that the judge is case-sensitive, so answers of "yes" and "no" will not be accepted.

    Limits
    Time limit: 30 seconds per test set.
    Memory limit: 1GB.
    1 ≤ T ≤ 100.
    */

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numberOfTestCase = scan.nextInt();

        boolean[] results = new boolean[numberOfTestCase];

        IntStream.range(0, numberOfTestCase).forEach(t -> {
            int n = scan.nextInt();
            int length = (int) Math.pow(n, 2);
            int[][] sudoku = new int[length][length];

            for (int r = 0; r < sudoku.length; r++) {
                for (int c = 0; c < sudoku[0].length; c++) {
                    sudoku[r][c] = scan.nextInt();
                }
            }
            boolean isValid = isValidSudoku(sudoku);
            results[t] = isValid;
        });

        scan.close();

        for (int i = 0; i < results.length; i++) {
            System.out.println("Case #" + (i + 1) + ": " + (results[i] ? "Yes" : "No"));
        }
    }

    private static boolean isValidSudoku(int[][] sudoku) {
        int subMatrixSize = (int) Math.sqrt(sudoku.length);
        Set<Integer> duplicateValue = new HashSet<>();

        // Check row by row
        for (int r = 0; r < sudoku.length; r++) {
            for (int c = 0; c < sudoku[r].length; c++) {
                if (sudoku[r][c] <= 0 || sudoku[r][c] >= 1000 || sudoku[r][c] > sudoku.length) {
                    return false;
                }
                if (!duplicateValue.add(sudoku[r][c])) {
                    return false;
                }
            }
            duplicateValue.clear();
        }

        // Check column by column
        for (int c = 0; c < sudoku[0].length; c++) {
            for (int r = 0; r < sudoku[0].length; r++) {
                if (!duplicateValue.add(sudoku[r][c])) {
                    return false;
                }
            }
            duplicateValue.clear();
        }

        // Check sub matrix
        int subMatrixRow = 0;
        int subMatrixColumn = 0;
        while (subMatrixRow < sudoku.length) {
            while (subMatrixColumn < sudoku.length) {

                for (int i = subMatrixRow; i < subMatrixRow + subMatrixSize; i++) {
                    for (int j = subMatrixColumn; j < subMatrixColumn + subMatrixSize; j++) {
                        if (!duplicateValue.add(sudoku[i][j])) {
                            return false;
                        }
                    }
                }

                duplicateValue.clear();
                subMatrixColumn += subMatrixSize;
            }
            subMatrixColumn = 0;
            subMatrixRow += subMatrixSize;
        }

        return true;
    }
}
