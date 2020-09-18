package leetcode;

import java.util.HashMap;
import java.util.Map;

public class T0003_Longest_Substring_Without_Repeating_Characters {

    /**
     * 3. Longest Substring Without Repeating Characters
     * Medium
     * Given a string, find the length of the longest substring without repeating characters.
     *
     * Example 1:
     * Input: "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     *
     * Example 2:
     * Input: "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     *
     * Example 3:
     * Input: "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     *              Note that the answer must be a substring,
     *              "pwke" is a subsequence and not a substring.
     */

    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            ans = Math.max(ans, i - j + 1);
            map.put(s.charAt(i), i);
        }
        return ans;
    }

    public static int lengthOfLongestSubstring2(String s) {
        char[] ss = s.toCharArray();
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int max = 0;

        while (right < s.length()) {
            char c1 = ss[right];
            window.put(c1, window.getOrDefault(c1, 0) + 1);
            ++right;
            while (window.get(c1) > 1) {
                char c2 = ss[left];
                window.put(c2, window.get(c2) - 1);
                ++left;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("pwwkew"));
    }
}
