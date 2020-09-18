package leetcode;

public class T0343_Integer_Break {

    /**
     * 343. Integer Break
     * Medium
     * <p>
     * Given a positive integer n, break it into the sum of at least two positive integers
     * and maximize the product of those integers. Return the maximum product you can get.
     * <p>
     * Example 1:
     * Input: 2
     * Output: 1
     * Explanation: 2 = 1 + 1, 1 × 1 = 1.
     * <p>
     * Example 2:
     * Input: 10
     * Output: 36
     * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
     * <p>
     * Note: You may assume that n is not less than 2 and not larger than 58.
     */

    public static int integerBreak(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; ++i) {
            int max = 0;
            for (int j = 2; j <= i / 2; ++j) {
                int val = dp[j] * dp[i - j];
                if (max < val) {
                    max = val;
                }
            }
            dp[i] = max;
        }
        return dp[n];
    }

    public int cuttingRope(int n) {
        if (n == 2 || n == 3) {
            return n == 2 ? 1 : 2;
        }
        final long MOD = 1000000007;
        long ans = 1;
        while (n > 4) {
            ans = (3 * ans) % MOD;
            n -= 3;
        }

        return (int) (ans * n % MOD);
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }
}
