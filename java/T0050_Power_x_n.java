package leetcode;

public class T0050_Power_x_n {

    /**
     * 50. Pow(x, n)
     * Medium
     * Implement pow(x, n), which calculates x raised to the power n (xn).
     * <p>
     * Example 1:
     * Input: 2.00000, 10
     * Output: 1024.00000
     * <p>
     * Example 2:
     * Input: 2.10000, 3
     * Output: 9.26100
     * <p>
     * Example 3:
     * Input: 2.00000, -2
     * Output: 0.25000
     * Explanation: 2-2 = 1/22 = 1/4 = 0.25
     * <p>
     * Note:
     * -100.0 < x < 100.0
     * n is a 32-bit signed integer, within the range [−231, 231 − 1]
     */

    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1.0d;
        } else if (n == 1) {
            return x;
        }

        double half = myPow(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else {
            if (n > 0) {
                return x * half * half;
            } else {
                return half * half / x;
            }
        }
    }

    /**
     * 求 x 的 负无穷大次会 double 溢出
     */
    public static double myPow2(double x, int n) {
        if (n == 0) {
            return 1.0D;
        }
        if (n == 1) {
            return x;
        }
        boolean neg = false;
        if (n < 0) {
            n *= -1;
            neg = true;
        }
        double ans;
        double half = myPow2(x, n / 2);
        if ((n & 1) == 1) {
            ans = x * half * half;
        } else {
            ans = half * half;
        }
        return neg ? 1.0 / ans : ans;
    }

    public static void main(String[] args) {
        System.out.println(myPow(2.000D, -2));
    }
}
