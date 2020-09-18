package leetcode;

import java.util.HashSet;

public class T0409_Longest_Palindrome {

    /**
     * 409. Longest Palindrome
     * Easy
     *
     * Given a string which consists of lowercase or uppercase letters,
     * find the length of the longest palindromes that can be built with those letters.
     * This is case sensitive, for example "Aa" is not considered a palindrome here.
     *
     * Note:
     * Assume the length of given string will not exceed 1,010.
     *
     * Example:
     * Input:
     * "abccccdd"
     *
     * Output:
     * 7
     *
     * Explanation:
     * One longest palindrome that can be built is "dccaccd", whose length is 7.
     */

    public int longestPalindrome(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        int cnt = 0;
        HashSet<Character> set = new HashSet<>();
        char[] chs = s.toCharArray();
        for (char ch : chs) {
            if (!set.contains(ch)) {
                set.add(ch);
            } else {
                set.remove(ch);
                cnt += 2;
            }
        }
        return set.isEmpty() ? cnt : cnt + 1;
    }

    public static void main(String[] args) {

    }
}
