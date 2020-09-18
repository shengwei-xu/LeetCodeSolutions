package leetcode;

public class T0647_Palindromic_Substrings {

    /**
     * 647. 回文子串
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     * <p>
     * 示例 1：
     * 输入："abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     * <p>
     * 示例 2：
     * 输入："aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     * <p>
     * 提示：
     * 输入的字符串长度不会超过 1000 。
     */

    public static int countSubstrings(String s) {
        int n = s.length();
        if (n <= 1) return s.length();

        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            cnt += countSubstringsCore(s, i, i);
            if (i < n - 1)
                cnt += countSubstringsCore(s, i, i + 1);
        }
        return cnt;
    }

    public static int countSubstringsCore(String s, int i, int j) {
        int cnt = 0;
        while (i >= 0 && i < s.length() && j >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            ++cnt;
            --i;
            ++j;
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings("fdsklf"));
    }
}
