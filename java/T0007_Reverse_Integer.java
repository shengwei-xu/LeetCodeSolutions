package leetcode;

public class T0007_Reverse_Integer {

    /**
     * 7. 整数反转
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * <p>
     * 示例 1:
     * 输入: 123
     * 输出: 321
     * <p>
     * 示例 2:
     * 输入: -123
     * 输出: -321
     * <p>
     * 示例 3:
     * 输入: 120
     * 输出: 21
     * <p>
     * 注意:
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。
     * 请根据这个假设，如果反转后整数溢出那么就返回 0。
     */

    public static int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int num = x % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && num > 7)) {
                return 0;
            }
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && num < -8)) {
                return 0;
            }
            ans = ans * 10 + num;
            x /= 10;
        }
        return ans;
    }

    public static int reverse2(int x) {
        long ans = 0;
        int flag = 1;
        if (x < 0) {
            flag = -1;
            x = -x;
        }
        while (x != 0) {
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) return 0;
        return flag * (int) ans;
    }


    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(120));
        System.out.println(reverse(-345));
        System.out.println(reverse(-3));
    }
}
