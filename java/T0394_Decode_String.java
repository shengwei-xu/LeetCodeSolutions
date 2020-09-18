package leetcode;

import java.util.LinkedList;

public class T0394_Decode_String {

    /**
     * 394. 字符串解码
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     *
     * 示例 1：
     * 输入：s = "3[a]2[bc]"
     * 输出："aaabcbc"
     *
     * 示例 2：
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     *
     * 示例 3：
     * 输入：s = "2[abc]3[cd]ef"
     * 输出："abcabccdcdcdef"
     *
     * 示例 4：
     * 输入：s = "abc3[cd]xyz"
     * 输出："abccdcdcdxyz"
     */

    public static String decodeString(String s) {
        if (s.length() < 4) return s;
        char[] chs = s.toCharArray();
        LinkedList<Character> stack = new LinkedList<>();
        int right = 0;
        while (right < chs.length) {
            if (chs[right] == ']') {
                StringBuilder sb = new StringBuilder();
                while (stack.peek() != '[') {
                    sb.append(stack.pop());
                }
                stack.pop(); // pop '['
                sb.reverse();
                String str = sb.toString();

                // 数字
                sb = new StringBuilder();
                int num = 0, base = 1;
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    num += (stack.pop() - '0') * base;
                    base *= 10;
                }
                // 组装结果
                for (int i = 0; i < num; ++i) {
                    sb.append(str);
                }

                // 结果push到stack
                char[] chars = sb.toString().toCharArray();
                for (char c : chars) {
                    stack.push(c);
                }
                ++right;
            } else {
                stack.push(chs[right++]);
            }
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString("2[abc]3[cd]ef"));
    }
}
