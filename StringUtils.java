package com.framework.utils;

/**
 * String utility class providing common string manipulation operations.
 */
public class StringUtils {

    private StringUtils() {}

    public static boolean isPalindrome(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        String clean = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return clean.equals(new StringBuilder(clean).reverse().toString());
    }

    public static String reverse(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        return new StringBuilder(s).reverse().toString();
    }

    public static int countVowels(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        return (int) s.toLowerCase().chars().filter("aeiou"::indexOf).count();
    }

    public static String capitalize(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        if (s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }

    public static boolean isAnagram(String a, String b) {
        if (a == null || b == null) throw new IllegalArgumentException("Inputs cannot be null");
        char[] ca = a.replaceAll("\\s", "").toLowerCase().toCharArray();
        char[] cb = b.replaceAll("\\s", "").toLowerCase().toCharArray();
        java.util.Arrays.sort(ca);
        java.util.Arrays.sort(cb);
        return java.util.Arrays.equals(ca, cb);
    }

    public static int wordCount(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        if (s.isBlank()) return 0;
        return s.trim().split("\\s+").length;
    }

    public static String truncate(String s, int maxLength) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        if (maxLength < 0) throw new IllegalArgumentException("Max length cannot be negative");
        if (s.length() <= maxLength) return s;
        return s.substring(0, maxLength) + "...";
    }
}
