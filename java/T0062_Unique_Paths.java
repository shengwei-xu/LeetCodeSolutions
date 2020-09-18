package leetcode;

public class T0062_Unique_Paths {

    /**
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * 问总共有多少条不同的路径？
     *
     * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
     *
     * 示例 1:
     *
     * 输入: m = 3, n = 2
     * 输出: 3
     * 解释:
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向右 -> 向下
     * 2. 向右 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向右
     * 示例 2:
     *
     * 输入: m = 7, n = 3
     * 输出: 28
     *
     *
     * 提示：
     *
     * 1 <= m, n <= 100
     * 题目数据保证答案小于等于 2 * 10 ^ 9
     */

    public static int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        final int MOD = 2 * 1000_000_000;
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; ++i) dp[0][i] = 1;
        for (int i = 0; i < m; ++i) dp[i][0] = 1;
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % MOD;
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 排列组合 ans = C_(m+n-2)^(m-1)
     */
    public static int uniquePaths2(int m, int n) {
        if(m == 1 || n == 1) return 1;
        m--;
        n--;
        if(m < n) {
            // Swap, so that m is the bigger number
            m = m + n;
            n = m - n;
            m = m - n;
        }
        long res = 1;
        int j = 1;
        // Instead of taking factorial, keep on multiply & divide
        for(int i = m+1; i <= m+n; i++, j++){
            res *= i;
            res /= j;
        }

        return (int)res;
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 2));
        System.out.println(uniquePaths(7, 3));
        System.out.println(uniquePaths(100, 100));
    }
}
