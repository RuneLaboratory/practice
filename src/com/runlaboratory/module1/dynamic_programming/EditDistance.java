package com.runlaboratory.module1.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EditDistance {

    /*
    There are three operations permitted on a word: replace, delete, insert. For example, the edit distance between
    "a" and "b" is 1, the edit distance between "abc" and "def" is 3. This post analyzes how to calculate edit
    distance by using dynamic programming.

    Let dp[i][j] stands for the edit distance between two strings with length i and j, i.e., word1[0,...,i-1] and word2[0,...,j-1].
    There is a relation between dp[i][j] and dp[i-1][j-1]. Let's say we transform from one string to another.
    The first string has length i and it's last character is "x"; the second string has length j and its last
    character is "y". The following diagram shows the relation.

    1. if x == y, then dp[i][j] == dp[i-1][j-1]
    2. if x != y, and we insert y for word1, then dp[i][j] = dp[i][j-1] + 1
    3. if x != y, and we delete x for word1, then dp[i][j] = dp[i-1][j] + 1
    4. if x != y, and we replace x with y for word1, then dp[i][j] = dp[i-1][j-1] + 1
    5. When x!=y, dp[i][j] is the min of the three situations.

    Initial condition: dp[i][0] = i, dp[0][j] = j
    */

    public static void main(String[] args) {

        String[] words = new String[]{
                "bcdefg", "ooobcdefoo",
                "diagram", "dial",
                "diagram", "gram",
                "dial", "diagram",
                "diagram", "g_ram",
                "diag_ram", "dia+gram",
                "idagram", "daigram",
                "diagram_xx_diagram", "_xx_dia+gram",
                "diagram_xx_diag-ram", "diagram_xx_dia+gram",
                "abcde", "icdja",
                "abcd", "efgh",
                "abcd", "ebgh",
                "abfd", "efgh",
                "abch", "afgh",
                "abcd", "dcba",
                "abcdefg", "bcdefag",
                "abcdefg", "gefcdab",
                "abcde","wcda"
        };

        for (int i = 0; i < words.length; i = i + 2) {
            System.out.println(words[i] + "\n" + words[i + 1]);
            System.out.println("My result : " + solution1(words[i], words[i + 1]) +
                    " vs Ans : " + answer1(words[i], words[i + 1]) +
                    ", Ans2 : " + answer2(words[i], words[i + 1]));
            System.out.println("================================");
        }

    }

    // Compare 2 word from center position then move left and right to find longer match group. recursive
    private static int solution1(String word1, String word2) {

        if (word1.isEmpty() || word2.isEmpty()) {
            return word1.length() + word2.length();
        }

        final String longerWord = (word2.length() > word1.length()) ? word2 : word1;
        final String shorterWord = (word2.length() > word1.length()) ? word1 : word2;

        ArrayList<Character> longestGroup = new ArrayList<>();
        int diffSize = (longerWord.length() - shorterWord.length()) / 2;
        int firstIndexMove = 0;
        int secIndexMove = diffSize;
        while (firstIndexMove < longerWord.length()) {

            ArrayList<Character> group = new ArrayList<>();

            int i = diffSize + firstIndexMove;
            int j = 0;
            while (i < longerWord.length() && j < shorterWord.length()) {
                if (longerWord.charAt(i) == shorterWord.charAt(j)) {
                    group.add(longerWord.charAt(i));
                } else if (!group.isEmpty()) {
                    break;
                }
                j++;
                i++;
            }

            if (group.size() > longestGroup.size()) {
                System.out.println("GROUP MATCH : " + group);
                longestGroup = new ArrayList<>(group);
            }
            group.clear();

            int i2 = Math.max(diffSize - firstIndexMove, 0);
            int j2 = (secIndexMove < 0) ? (secIndexMove * -1) : 0;
            while (j2 < shorterWord.length()) {
                if (longerWord.charAt(i2) == shorterWord.charAt(j2)) {
                    group.add(longerWord.charAt(i2));
                } else if (!group.isEmpty()) {
                    break;
                }
                j2++;
                i2++;
            }

            if (group.size() > longestGroup.size()) {
                System.out.println("GROUP MATCH : " + group);
                longestGroup = new ArrayList<>(group);
            }
            group.clear();

            firstIndexMove++;
            secIndexMove--;
        }

        if (!longestGroup.isEmpty()) {
            String longestGroupStr = longestGroup.stream().map(String::valueOf).collect(Collectors.joining());
            String[] word1Remain = (" " + word1 + " ").split(longestGroupStr);
            String[] word2Remain = (" " + word2 + " ").split(longestGroupStr);

            return solution1(word1Remain[0].trim(), word2Remain[0].trim()) + solution1(word1Remain[1].trim(), word2Remain[1].trim());
        } else {
            return Math.max(word1.length(), word2.length());
        }
    }

    private static int answer1(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        // len1+1, len2+1, because finally return dp[len1][len2]
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        //iterate though, and check last char
        for (int i = 0; i < len1; i++) {
            char c1 = word1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = word2.charAt(j);

                //if last two chars equal
                if (c1 == c2) {
                    //update dp value for +1 length
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    int replace = dp[i][j] + 1;
                    int insert = dp[i][j + 1] + 1;
                    int delete = dp[i + 1][j] + 1;

                    int min = replace > insert ? insert : replace;
                    min = delete > min ? min : delete;
                    dp[i + 1][j + 1] = min;
                }
                //printDP(dp);
            }
        }

        return dp[len1][len2];
    }

//    private static void printDP(int[][] dp){
//        System.out.println("-------------------------------");
//        for(int i=0;i<dp.length;i++){
//            for(int j=0;j<dp[i].length;j++){
//                System.out.print("[" + dp[i][j] + "]");
//            }
//            System.out.println("");
//        }
//    }

    private static int answer2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] mem = new int[m][n];
        for (int[] arr : mem) {
            Arrays.fill(arr, -1);
        }
        return answer2_calDistance(word1, word2, mem, m - 1, n - 1);
    }

    private static int answer2_calDistance(String word1, String word2, int[][] mem, int i, int j) {
        if (i < 0) {
            return j + 1;
        } else if (j < 0) {
            return i + 1;
        }

        if (mem[i][j] != -1) {
            return mem[i][j];
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            mem[i][j] = answer2_calDistance(word1, word2, mem, i - 1, j - 1);
        } else {
            int prevMin = Math.min(answer2_calDistance(word1, word2, mem, i, j - 1), answer2_calDistance(word1, word2, mem, i - 1, j));
            prevMin = Math.min(prevMin, answer2_calDistance(word1, word2, mem, i - 1, j - 1));
            mem[i][j] = 1 + prevMin;
        }

        return mem[i][j];
    }
}
