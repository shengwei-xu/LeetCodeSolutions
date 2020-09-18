package leetcode;

import javax.xml.stream.FactoryConfigurationError;

public class T0010_Regular_Expression_Matching {

    /**
     * 10. Regular Expression Matching
     * Hard
     *
     * Given an input string (s) and a pattern (p), implement regular expression matching with
     * support for '.' and '*'.
     *
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * The matching should cover the entire input string (not partial).
     *
     * Note:
     *
     * s could be empty and contains only lowercase letters a-z.
     * p could be empty and contains only lowercase letters a-z, and characters like . or *.
     * Example 1:
     *
     * Input:
     * s = "aa"
     * p = "a"
     * Output: false
     * Explanation: "a" does not match the entire string "aa".
     * Example 2:
     *
     * Input:
     * s = "aa"
     * p = "a*"
     * Output: true
     * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
     * Example 3:
     *
     * Input:
     * s = "ab"
     * p = ".*"
     * Output: true
     * Explanation: ".*" means "zero or more (*) of any character (.)".
     * Example 4:
     *
     * Input:
     * s = "aab"
     * p = "c*a*b"
     * Output: true
     * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
     * Example 5:
     *
     * Input:
     * s = "mississippi"
     * p = "mis*is*p*."
     * Output: false
     */
    public boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                //分成空正则和非空正则两种
                if (j == 0) {
                    f[i][j] = i == 0;
                } else {
                    //非空正则分为两种情况 * 和 非*
                    if (p.charAt(j - 1) != '*') {
                        if (i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    } else {
                        //碰到 * 了，分为看和不看两种情况
                        //不看
                        if (j >= 2) {
                            f[i][j] |= f[i][j - 2];
                        }
                        //看
                        if (i >= 1 && j >= 2 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) {
                            f[i][j] |= f[i - 1][j];
                        }
                    }
                }
            }
        }
        return f[m][n];
    }

    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        return matchCore(s, 0, p, 0);
    }

    private static boolean matchCore(String s, int sIdx, String p, int pIdx) {
        if (sIdx == s.length() && pIdx == p.length()) {
            return true;
        }
        if (sIdx != s.length() && pIdx == p.length()) {
            return false;
        }
        if (pIdx + 1 < p.length() && p.charAt(pIdx + 1) == '*') {
            if (sIdx < s.length() && (p.charAt(pIdx) == s.charAt(sIdx) || p.charAt(pIdx) == '.')) {
                return matchCore(s, sIdx + 1, p, pIdx)
                        || matchCore(s, sIdx + 1, p, pIdx + 2)
                        || matchCore(s, sIdx, p, pIdx + 2);
            } else {
                return matchCore(s, sIdx, p, pIdx + 2);
            }
        }

        if (sIdx < s.length() && (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '.')) {
            return matchCore(s, sIdx + 1, p, pIdx + 1);
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a*"));
    }
}
