package com.runlaboratory.module1.string_array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class P4_IsomorphicStrings {

    /*
    Given two strings s and t, determine if they are isomorphic. Two strings are isomorphic if
    the characters in s can be replaced to get t.

    For example,"egg" and "add" are isomorphic, "foo" and "bar" are not.
    "ACAB" and "XCXY" are isomorphic as we can map 'A' —> 'X' , 'B' —> 'Y' and 'C' —> 'C'.
    */

    public static void main(String[] args) {
        String s = "egg";
        String t = "add";

        String s1 = "egz";
        String t1 = "add";

        String s2 = "foo";
        String t2 = "bar";

        String s3 = "ACAB";
        String t3 = "XCXY";

        System.out.println("===== solution 1 =====");
        System.out.println(s + " & " + t + " = " + solution1(s, t));
        System.out.println(s1 + " & " + t1 + " = " + solution1(s1, t1));
        System.out.println(s2 + " & " + t2 + " = " + solution1(s2, t2));
        System.out.println(s3 + " & " + t3 + " = " + solution1(s3, t3));

        System.out.println("===== solution 2 =====");
        System.out.println(s + " & " + t + " = " + solution2(s, t));
        System.out.println(s1 + " & " + t1 + " = " + solution2(s1, t1));
        System.out.println(s2 + " & " + t2 + " = " + solution2(s2, t2));
        System.out.println(s3 + " & " + t3 + " = " + solution2(s3, t3));

        System.out.println("===== Answer 1 =====");
        System.out.println(s + " & " + t + " = " + answer1(s, t));
        System.out.println(s1 + " & " + t1 + " = " + answer1(s1, t1));
        System.out.println(s2 + " & " + t2 + " = " + answer1(s2, t2));
        System.out.println(s3 + " & " + t3 + " = " + answer1(s3, t3));

        System.out.println("===== Answer 2 =====");
        System.out.println(s + " & " + t + " = " + answer2(s, t));
        System.out.println(s1 + " & " + t1 + " = " + answer2(s1, t1));
        System.out.println(s2 + " & " + t2 + " = " + answer2(s2, t2));
        System.out.println(s3 + " & " + t3 + " = " + answer2(s3, t3));
    }

    // Use HashMap to trace char to replace
    private static boolean solution1(String s, String t) {
        boolean isIsomorphic = true;

        HashMap<Character, Character> charMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            Character charInMap = charMap.get(s.charAt(i));

            if (charInMap != null && charInMap != t.charAt(i)) {
                isIsomorphic = false;
                break;
            } else if (charInMap == null && charMap.containsValue(t.charAt(i))) {
                isIsomorphic = false;
                break;
            }

            charMap.put(s.charAt(i), t.charAt(i));
        }

        return isIsomorphic;
    }

    // Make a copy then try to replace, then check the final result
    private static boolean solution2(String s, String t) {
        boolean isIsomorphic = false;
        String copy = s;

        Set<Character> replacedChars = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (!replacedChars.contains(t.charAt(i))) {
                copy = copy.replace(s.charAt(i), t.charAt(i));
                replacedChars.add(t.charAt(i));
            }
        }

        if (t.equals(copy)) {
            isIsomorphic = true;
        }

        return isIsomorphic;
    }

    // Use a map to tracks the char-char mappings. If a value is already mapped, it can not be mapped again.
    private static boolean answer1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (map1.containsKey(c1)) {
                if (c2 != map1.get(c1)) {
                    return false;
                }
            } else {
                if (map2.containsKey(c2)) {
                    return false;
                }

                map1.put(c1, c2);
                map2.put(c2, c1);
            }
        }

        return true;
    }

    // Simply check if a value is mapped twice by checking the number of unique elements.
    private static boolean answer2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
            } else {
                map.put(c1, c2);
            }
        }

        HashSet<Character> set = new HashSet<>(map.values());
        if (set.size() == map.values().size()) {
            return true;
        }

        return false;
    }
}
