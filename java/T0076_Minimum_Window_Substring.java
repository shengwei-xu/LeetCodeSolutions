package leetcode;

import java.util.HashMap;
import java.util.Map;

public class T0076_Minimum_Window_Substring {

    /**
     * 76. 最小覆盖子串
     * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
     * <p>
     * 示例：
     * 输入：S = "ADOBECODEBANC", T = "ABC"
     * 输出："BANC"
     * <p>
     * 提示：
     * 如果 S 中不存这样的子串，则返回空字符串 ""。
     * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
     */

    public static String minWindow(String s, String t) {
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();

        Map<Character, Integer> needs = new HashMap<>();
        for (char c : ts) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0, match = 0;
        int start = 0, minLen = Integer.MAX_VALUE;
        while (right < ss.length) {
            char c = ss[right];
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(needs.get(c))) {
                    ++match;
                }
            }
            ++right;
            while (match == needs.size()) {
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }
                c = ss[left];
                if (needs.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) - 1);
                    if (window.get(c) < needs.get(c)) {
                        --match;
                    }
                }
                ++left;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(minWindow(s, t));
    }
}
