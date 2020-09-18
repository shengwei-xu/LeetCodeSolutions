package leetcode;

import java.util.Arrays;

public class T0014_Longest_Common_Prefix {

    /**
     * 14. Longest Common Prefix
     * Easy
     * <p>
     * Write a function to find the longest common prefix string amongst an array of strings.
     * If there is no common prefix, return an empty string "".
     * <p>
     * Example 1:
     * Input: ["flower","flow","flight"]
     * Output: "fl"
     * <p>
     * Example 2:
     * Input: ["dog","racecar","car"]
     * Output: ""
     * Explanation: There is no common prefix among the input strings.
     * <p>
     * Note:
     * All given inputs are in lowercase letters a-z.
     */

    public String longestCommonPrefix(String[] strs) {
        if (!check(strs)) {
            return "";
        }

        Arrays.sort(strs);
        int minLen = Math.min(strs[0].length(), strs[strs.length - 1].length());
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < minLen; ++i) {
            if (strs[0].charAt(i) == strs[strs.length - 1].charAt(i)) {
                ans.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return ans.toString();
    }

    private boolean check(String[] strs) {
        if (strs == null || strs.length <= 0) {
            return false;
        }
        for (int i = 0; i < strs.length; ++i) {
            if (strs[i] == null || strs[i].length() <= 0) {
                return false;
            }
        }
        return true;
    }

}
