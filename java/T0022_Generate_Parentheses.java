package leetcode;

import java.util.ArrayList;
import java.util.List;

public class T0022_Generate_Parentheses {

    /**
     * 22. 括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * <p>
     * 示例：
     * 输入：n = 3
     * 输出：[
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     */

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n <= 0) return ans;
        generateParenthesisCore("", 0, n, 0, ans);
        return ans;
    }

    private static void generateParenthesisCore(String str, int left, int right, int n, List<String> ans) {
        if (left == n && right == n) ans.add(str);
        else {
            if (right > left) return;
            if (left <= n) generateParenthesisCore(str + "(", left + 1, right, n, ans);
            if (right <= n) generateParenthesisCore(str + ")", left, right + 1, n, ans);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
