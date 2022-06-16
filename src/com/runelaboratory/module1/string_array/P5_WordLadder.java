package com.runelaboratory.module1.string_array;

import java.util.*;

public class P5_WordLadder {

    /*
    Given two words (start and end), and a dictionary, find the length of the shortest transformation sequence from start to end,
    such that only one letter can be changed at a time and each intermediate word must exist in the dictionary.
    For example, given:

        start = "hit"
        end = "cog"
        dict = ["hot","dot","dog","lot","log"]

        One shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", the program should return its length 5.
        hit > hot > lot > log > cog
    */

    public static void main(String[] args) {

        String start = "hit";
        String end = "cog";
        Set<String> disc = new HashSet<>(Arrays.asList("hot", "dot", "dog", "hit", "lot", "log"));
        System.out.println("solution : " + solution(start, end, new HashSet<>(disc)));
        System.out.println("solution2 : " + solution2(start, end, new HashSet<>(disc)));
        System.out.println("answer : " + answer(start, end, new HashSet<>(disc)));
        System.out.println("\n");

        String start2 = "toon";
        String end2 = "plea";
        Set<String> disc2 = new HashSet<>(Arrays.asList("poon", "plee", "same", "poie", "plea", "plie", "poin"));
        System.out.println("solution : " + solution(start2, end2, new HashSet<>(disc2)));
        System.out.println("solution2 : " + solution2(start2, end2, new HashSet<>(disc2)));
        System.out.println("answer : " + answer(start2, end2, new HashSet<>(disc2)));
        System.out.println("\n");

        String start3 = "abcv";
        String end3 = "ebad";
        Set<String> disc3 = new HashSet<>(Arrays.asList("abcd", "ebad", "ebcd", "xyza"));
        System.out.println("solution : " + solution(start3, end3, new HashSet<>(disc3)));
        System.out.println("solution2 : " + solution2(start3, end3, new HashSet<>(disc3)));
        System.out.println("answer : " + answer(start3, end3, new HashSet<>(disc3)));
        System.out.println("\n");

        String start4 = "fool";
        String end4 = "sage";
        Set<String> disc4 = new HashSet<>(Arrays.asList("fool", "pool", "poll", "pole", "pale", "sale", "sage"));
        System.out.println("solution : " + solution(start4, end4, new HashSet<>(disc4)));
        System.out.println("solution2 : " + solution2(start4, end4, new HashSet<>(disc4)));
        System.out.println("answer : " + answer(start4, end4, new HashSet<>(disc4)));
        System.out.println("\n");

        String start5 = "der";
        String end5 = "dfs";
        Set<String> disc5 = new HashSet<>(Arrays.asList("des","der","dfr","dgt","dfs"));
        System.out.println("solution : " + solution(start5, end5, new HashSet<>(disc5)));
        System.out.println("solution2 : " + solution2(start5, end5, new HashSet<>(disc5)));
        System.out.println("answer : " + answer(start5, end5, new HashSet<>(disc5)));
        System.out.println("\n");

        String start6 = "gedk";
        String end6 = "geek";
        Set<String> disc6 = new HashSet<>(Arrays.asList("geek", "gefk"));
        System.out.println("solution : " + solution(start6, end6, new HashSet<>(disc6)));
        System.out.println("solution2 : " + solution2(start6, end6, new HashSet<>(disc6)));
        System.out.println("answer : " + answer(start6, end6, new HashSet<>(disc6)));
        System.out.println("\n");
    }

    // recursive solution
    private static int solution(String beginWord, String endWord, Set<String> wordDict){
        List<String> steps = new ArrayList<>();
        steps.add(beginWord);
        return countSteps(beginWord, endWord, wordDict, steps);
    }

    // BFS solution > better
    private static int solution2(String beginWord, String endWord, Set<String> wordDict){
        Queue<String> queue = new LinkedList<>();
        Queue<Integer> counts = new LinkedList<>();
        counts.add(1);
        queue.add(beginWord);

        while(!queue.isEmpty()){
            String workingWord = queue.remove();
            int workingCount = counts.remove();
            System.out.println(workingWord + " - workingCount : " + workingCount + ", Queue: " + queue);
            if(isOneCharDiff(workingWord,endWord)){
                return workingCount + 1;
            }
            for(String dict : new HashSet<>(wordDict)){
                if(isOneCharDiff(workingWord, dict)){
                    queue.add(dict);
                    counts.add(workingCount + 1);
                    wordDict.remove(dict);
                }
            }
        }
        return 0;
    }

    private static int  countSteps(String start, String end, Set<String> ladder, List<String> steps){
        int smallerSize = 0;
        if(isOneCharDiff(start, end)){
            steps.add(end);
            System.out.println("Steps : " + steps);
            return steps.size();
        }
        for(String step : ladder){
            if(isOneCharDiff(start, step)){
                ArrayList<String> newBranch = new ArrayList<>(steps);
                newBranch.add(step);
                Set<String> filteredLadder = new HashSet<>(ladder);
                filteredLadder.remove(step);
                int branchSize = countSteps(step, end, filteredLadder, newBranch);
                smallerSize = branchSize < smallerSize || smallerSize == 0 ? branchSize : smallerSize;
            }
        }
        return smallerSize;
    }

    private static boolean isOneCharDiff(String string1, String string2) {
        if (string1 == null || string2 == null || string1.length() != string2.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < string1.length() && count <=1 ; i++) {
            if (string1.charAt(i) != string2.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }

    private static int answer(String beginWord, String endWord, Set<String> wordDict) {
        LinkedList<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1));

        wordDict.add(endWord);

        while (!queue.isEmpty()) {
            WordNode top = queue.remove();
            String word = top.word;

            if (word.equals(endWord)) {
                return top.numSteps;
            }

            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char temp = arr[i];
                    if (arr[i] != c) {
                        arr[i] = c;
                    }

                    String newWord = new String(arr);
                    if (wordDict.contains(newWord)) {
                        queue.add(new WordNode(newWord, top.numSteps + 1));
                        wordDict.remove(newWord);
                    }

                    arr[i] = temp;
                }
            }
        }

        return 0;
    }
}

class WordNode {
    String word;
    int numSteps;

    public WordNode(String word, int numSteps) {
        this.word = word;
        this.numSteps = numSteps;
    }
}
