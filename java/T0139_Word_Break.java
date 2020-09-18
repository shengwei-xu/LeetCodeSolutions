package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T0139_Word_Break {

    /**
     * 139. 单词拆分
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * <p>
     * 说明：
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     * <p>
     * 示例 1：
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     * <p>
     * 示例 2：
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     * 注意你可以重复使用字典中的单词。
     * <p>
     * 示例 3：
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     */

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i < dp.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    public boolean wordBreak2(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return true;
        Map<String, Boolean> memo = new HashMap<>();
        return backtrack(s, wordDict, memo);
    }

    private boolean backtrack(String s, List<String> wordDict, Map<String, Boolean> memo) {
        if (s == null || s.length() == 0) return true;

        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        boolean ans = false;
        for (int i = 0; i <= s.length(); ++i) {
            for (String p : wordDict) {
                if (p.equals(s.substring(0, i))) {
                    boolean res = backtrack(s.substring(i), wordDict, memo);
                    memo.put(s.substring(i), res);
                    ans |= res;
                }
            }
        }
        return ans;
    }

}
