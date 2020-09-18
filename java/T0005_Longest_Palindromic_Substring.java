package leetcode;

public class T0005_Longest_Palindromic_Substring {
    /**
     * 5. Longest Palindromic Substring
     * Medium
     * Given a string s, find the longest palindromic substring in s.
     * You may assume that the maximum length of s is 1000.
     * <p>
     * Example 1:
     * Input: "babad"
     * Output: "bab"
     * Note: "aba" is also a valid answer.
     * <p>
     * Example 2:
     * Input: "cbbd"
     * Output: "bb"
     */

    private static int idx;
    private static int len;
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        len = 0; idx = -1;
        for (int i = 0; i < s.length() - 1; ++i) {
            longestPalindromeCore(s, i, i);
            longestPalindromeCore(s, i, i + 1);
        }
        return s.substring(idx, idx + len);
    }

    private static void longestPalindromeCore(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            --i;
            ++j;
        }
        if (len < j - i - 1) {
            len = j - i - 1;
            idx = i + 1;
        }
    }

    public static String longestPalindrome2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int max = 0, start = 0, end = 0;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                boolean isSameLetter = s.charAt(i) == s.charAt(j);
                dp[i][j] = (j - i > 2) ? (dp[i + 1][j - 1] && isSameLetter) : isSameLetter;
                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public static String longestPalindrome3(String s) {
        if (s == null || s.length() < 2) return s;

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int maxLen = 1, start = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (s.charAt(j) == s.charAt(i) && (i - j < 2 || dp[i - 1][j + 1])) {
                    dp[i][j] = true;
                    if (i - j + 1 > maxLen) {
                        maxLen = i - j + 1;
                        start = j;
                    }
                }
            }
        }
        //System.out.println(start + " " + maxLen);
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("aaaa"));
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
    }

}
