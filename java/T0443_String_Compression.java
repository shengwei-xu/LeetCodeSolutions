package leetcode;

import java.util.LinkedList;

public class T0443_String_Compression {

    /**
     * 443. 压缩字符串
     * 给定一组字符，使用原地算法将其压缩。
     * <p>
     * 压缩后的长度必须始终小于或等于原数组长度。
     * <p>
     * 数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。
     * <p>
     * 在完成原地修改输入数组后，返回数组的新长度。
     * <p>
     * <p>
     * <p>
     * 进阶：
     * 你能否仅使用O(1) 空间解决问题？
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：
     * ["a","a","b","b","c","c","c"]
     * <p>
     * 输出：
     * 返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
     * <p>
     * 说明：
     * "aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
     * 示例 2：
     * <p>
     * 输入：
     * ["a"]
     * <p>
     * 输出：
     * 返回 1 ，输入数组的前 1 个字符应该是：["a"]
     * <p>
     * 解释：
     * 没有任何字符串被替代。
     * 示例 3：
     * <p>
     * 输入：
     * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
     * <p>
     * 输出：
     * 返回 4 ，输入数组的前4个字符应该是：["a","b","1","2"]。
     * <p>
     * 解释：
     * 由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
     * 注意每个数字在数组中都有它自己的位置。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 所有字符都有一个ASCII值在[35, 126]区间内。
     * 1 <= len(chars) <= 1000。
     */

    public static int compress(char[] chars) {
        int idx = 0;
        for (int i = 0; i < chars.length; ) {
            int cnt = 0;
            char target = chars[i];
            while (i < chars.length && chars[i] == target) {
                ++cnt;
                ++i;
            }
            chars[idx++] = target;
            if (cnt > 1) {
                LinkedList<Integer> stack = new LinkedList<>();
                while (cnt != 0) {
                    stack.push(cnt % 10);
                    cnt /= 10;
                }
                while (!stack.isEmpty()) {
                    chars[idx++] = (char) (stack.pop() + '0');
                }
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        char[] data = new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int len = compress(data);
        for (int i = 0; i < len; ++i) {
            System.out.print(data[i]);
        }
        System.out.println();
    }
}
