package com.runlaboratory.module1.matrix;

import java.util.ArrayList;

public class P2_SpiralMatrix {

    /*
    Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

    [ 1, 2, 3 ]
    [ 4, 5, 6 ]
    [ 7, 8, 9 ]

    return 1, 2, 3, 6, 9, 8, 7, 4, 5
    */

    public static void main(String[] args) {

        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int[][] matrix3 = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8}
        };
        int[][] matrix4 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8}
        };
        int[][] matrix5 = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20}
        };
        int[][] matrix6 = {
                {1},
                {2},
                {3},
                {4}
        };
        int[][] matrix7 = {
                {1, 2, 3, 4}
        };

        System.out.println("my ans  : " + solution1(matrix1));
        System.out.println("answer1 : " + answer1(matrix1));
        System.out.println("my ans  : " + solution1(matrix2));
        System.out.println("answer1 : " + answer1(matrix2));
        System.out.println("my ans  : " + solution1(matrix3));
        System.out.println("answer1 : " + answer1(matrix3));
        System.out.println("my ans  : " + solution1(matrix4));
        System.out.println("answer1 : " + answer1(matrix4));
        System.out.println("my ans  : " + solution1(matrix5));
        System.out.println("answer1 : " + answer1(matrix5));
        System.out.println("my ans  : " + solution1(matrix6));
        System.out.println("answer1 : " + answer1(matrix6));
        System.out.println("my ans  : " + solution1(matrix7));
        System.out.println("answer1 : " + answer1(matrix7));
    }

    // L shape as a loop
    // even loop (left to right then top to bottom)
    // odd loop (right to left then bottom to top)
    // use block count to skip added value
    private static ArrayList<Integer> solution1(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();

        if (matrix.length == 1) {
            for (int n = 0; n < matrix[0].length; n++) {
                result.add(matrix[0][n]);
            }
            return result;
        } else if (matrix[0].length == 1) {
            for (int n = 0; n < matrix.length; n++) {
                result.add(matrix[n][0]);
            }
            return result;
        }

        int loopCount = 0;
        int blockCount = 0;
        int i = 0, j = 0;

        while (blockCount < (double) matrix[0].length / 2) {

            if (loopCount % 2 == 0) {
                while (true) {
                    result.add(matrix[i][j]);
                    if (j == matrix[0].length - 1 - (blockCount)) {
                        break;
                    }
                    j++;
                }
                i++;

                if (blockCount == matrix.length / 2) {
                    break;
                }

                while (true) {
                    result.add(matrix[i][j]);
                    if (i == matrix.length - 1 - (blockCount)) {
                        break;
                    }
                    i++;
                }
                j--;
            } else {
                while (true) {
                    result.add(matrix[i][j]);
                    if (j == blockCount) {
                        break;
                    }
                    j--;
                }
                i--;

                blockCount++;

                if (blockCount >= (double) matrix.length / 2) {
                    break;
                }

                while (true) {
                    result.add(matrix[i][j]);
                    if (i == blockCount) {
                        break;
                    }
                    i--;
                }
                j++;
            }

            loopCount++;
        }

        return result;
    }

    private static ArrayList<Integer> answer1(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0) return result;

        int m = matrix.length;
        int n = matrix[0].length;

        int x = 0;
        int y = 0;

        while (m > 0 && n > 0) {

            //if one row/column left, no circle can be formed
            if (m == 1) {
                for (int i = 0; i < n; i++) {
                    result.add(matrix[x][y++]);
                }
                break;
            } else if (n == 1) {
                for (int i = 0; i < m; i++) {
                    result.add(matrix[x++][y]);
                }
                break;
            }

            //below, process a circle

            //top - move right
            for (int i = 0; i < n - 1; i++) {
                result.add(matrix[x][y++]);
            }

            //right - move down
            for (int i = 0; i < m - 1; i++) {
                result.add(matrix[x++][y]);
            }

            //bottom - move left
            for (int i = 0; i < n - 1; i++) {
                result.add(matrix[x][y--]);
            }

            //left - move up
            for (int i = 0; i < m - 1; i++) {
                result.add(matrix[x--][y]);
            }

            x++;
            y++;
            m = m - 2;
            n = n - 2;
        }

        return result;
    }

    // TODO more answer
}
