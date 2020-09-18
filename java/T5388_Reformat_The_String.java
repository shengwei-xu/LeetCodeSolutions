package leetcode;

import java.util.LinkedList;
import java.util.List;

public class T5388_Reformat_The_String {

    /**
     * 5388. 重新格式化字符串
     * Easy
     * 给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
     * 请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，
     * 字母后面应该跟着数字，而数字后面应该跟着字母。
     * 请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
     * <p>
     * 示例 1：
     * 输入：s = "a0b1c2"
     * 输出："0a1b2c"
     * 解释："0a1b2c" 中任意两个相邻字符的类型都不同。 "a0b1c2", "0a1b2c", "0c2a1b" 也是满足题目要求的答案。
     * <p>
     * 示例 2：
     * 输入：s = "leetcode"
     * 输出：""
     * 解释："leetcode" 中只有字母，所以无法满足重新格式化的条件。
     * <p>
     * 示例 3：
     * 输入：s = "1229857369"
     * 输出：""
     * 解释："1229857369" 中只有数字，所以无法满足重新格式化的条件。
     * <p>
     * 示例 4：
     * 输入：s = "covid2019"
     * 输出："c2o0v1i9d"
     * <p>
     * 示例 5：
     * 输入：s = "ab123"
     * 输出："1a2b3"
     * <p>
     * 提示：
     * 1 <= s.length <= 500
     * s 仅由小写英文字母和/或数字组成。
     */

    public static String reformat(String s) {
        if (s == null || s.length() <= 1) return s;
        List<Character> charList = new LinkedList<>();
        List<Character> digitList = new LinkedList<>();
        for (int i = 0; i < s.length(); ++i) {
            if (Character.isDigit(s.charAt(i))) {
                digitList.add(s.charAt(i));
            } else if (Character.isLetter(s.charAt(i))) {
                charList.add(s.charAt(i));
            }
        }
        if (Math.abs(charList.size() - digitList.size()) > 1) return "";
        else {
            StringBuilder sb = new StringBuilder();
            if (charList.size() - digitList.size() == 1) {
                sb.append(charList.get(0));
                charList.remove(0);
            }

            while (charList.size() != 0 || digitList.size() != 0) {
                if (digitList.size() != 0) {
                    sb.append(digitList.get(0));
                    digitList.remove(0);
                }
                if (charList.size() != 0) {
                    sb.append(charList.get(0));
                    charList.remove(0);
                }
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(reformat("covid2019"));
    }
}
