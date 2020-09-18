package leetcode;

import java.util.ArrayList;
import java.util.List;

public class T0017_Letter_Combinations_of_A_Phone_Number {
    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 示例:
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * 说明:
     * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
     */

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return new ArrayList<>();
        List<String> map = new ArrayList<>(10);
        map.add(0, ""); map.add(1, "");
        map.add(2, "abc"); map.add(3, "def");
        map.add(4, "ghi"); map.add(5, "jkl");
        map.add(6, "mno"); map.add(7, "pqrs");
        map.add(8, "tuv"); map.add(9, "wxyz");

        List<String> list = new ArrayList<>();
        for (char c : digits.toCharArray()) {
            list.add(map.get(c - '0'));
        }

        List<String> ans = new ArrayList<>();
        backtrack(ans, list, 0, new StringBuilder());
        return ans;
    }

    private static void backtrack(List<String> ans, List<String> list, int idx, StringBuilder path) {
        if (idx == list.size()) {
            ans.add(path.toString());
            return;
        }

        String s = list.get(idx);
        for (int i = 0; i < s.length(); ++i) {
            path.append(s.charAt(i));
            backtrack(ans, list, idx + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
}
