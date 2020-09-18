package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class T0020_Valid_Parentheses {
    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * 示例 1:
     *
     * 输入: "()"
     * 输出: true
     * 示例 2:
     *
     * 输入: "()[]{}"
     * 输出: true
     * 示例 3:
     *
     * 输入: "(]"
     * 输出: false
     * 示例 4:
     *
     * 输入: "([)]"
     * 输出: false
     * 示例 5:
     *
     * 输入: "{[]}"
     * 输出: true
     */

    public boolean isValid(String s) {
        if (s == null || s.length() % 2 == 1) return false;

        Deque<Character> stack = new LinkedList<>();
        int idx = 0;
        while (idx != s.length()) {
            char ch = s.charAt(idx++);
            if (ch == '(' || ch == '[' || ch == '{') stack.push(ch);
            else {
                if (stack.isEmpty()) return false;
                if (ch == ')' && stack.peek() == '(') stack.pop();
                else if (ch == ']' && stack.peek() == '[') stack.pop();
                else if (ch == '}' && stack.peek() == '{') stack.pop();
                else return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

    }
}
