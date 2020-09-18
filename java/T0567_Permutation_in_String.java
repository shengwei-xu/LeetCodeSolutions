package leetcode;

import java.util.HashMap;
import java.util.Map;

public class T0567_Permutation_in_String {

    /**
     * 567. 字符串的排列
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
     *
     * 示例1:
     * 输入: s1 = "ab" s2 = "eidbaooo"
     * 输出: True
     * 解释: s2 包含 s1 的排列之一 ("ba").
     *
     * 示例2:
     * 输入: s1= "ab" s2 = "eidboaoo"
     * 输出: False
     *
     * 注意：
     * 输入的字符串只包含小写字母
     * 两个字符串的长度都在 [1, 10,000] 之间
     */

    public static boolean checkInclusion(String s1, String s2) {
        char[] p = s1.toCharArray();
        char[] s = s2.toCharArray();

        int left = 0, right = 0;
        int match = 0;

        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();
        for (char c : p) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }

        while (right < s.length) {
            char c1 = s[right];
            if (needs.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                if (window.get(c1).equals(needs.get(c1))){
                    ++match;
                }
            }
            ++right;

            while (right - left >= p.length) {
                if (match == needs.size()) {
                    return true;
                }
                char c2 = s[left];
                if (needs.containsKey(c2)) {
                    // 使用equals来判断Integer, 此外这种方式可以避免match多减的情况
                    if (window.get(c2).equals(needs.get(c2))) {
                        -- match;
                    }
                    window.put(c2, window.get(c2) - 1);
                }
                ++ left;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        String p = "trinitrophenylmethylnitramine";
        String s = "dinitrophenylhydrazinetrinitrophenylmethylnitramine";
        System.out.println(checkInclusion(p, s));
        System.out.println(checkInclusion("ab", "dboaooo"));
        System.out.println(checkInclusion("ab", "dbaoooo"));
    }
}
