package leetcode;

import java.util.Arrays;

public class T0494_Target_Sum {

    /**
     * 494. 目标和
     * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
     * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     *
     * 示例：
     * 输入：nums: [1, 1, 1, 1, 1], S: 3
     * 输出：5
     *
     * 解释：
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     * 一共有5种方法让最终目标和为3。
     *
     * 提示：
     * 数组非空，且长度不会超过 20 。
     * 初始的数组的和不会超过 1000 。
     * 保证返回的最终结果能被 32 位整数存下。
     */


    /**
     * dp
     */
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) sum += nums[i];
        if (S > sum || S < -sum) return 0;

        int[][] dp = new int[n + 1][sum * 2 + 1];
        dp[0][0 + sum] = 1;

        for (int i = 1; i <= n; ++i) {
            for (int j = -sum; j <= sum; ++j) {
                if (-sum <= j - nums[i - 1]) {
                    dp[i][j + sum] += dp[i - 1][j - nums[i - 1] + sum];
                }
                if (j + nums[i - 1] <= sum) {
                    dp[i][j + sum] += dp[i - 1][j + nums[i - 1] + sum];
                }
            }
        }
        return dp[n][sum + S];
    }

    /**
     * dfs
     */
    public int findTargetSumWays2(int[] nums, int S) {
        return dfs(nums, S, 0);
    }

    private int dfs(int[] nums, int target, int idx) {
        if (idx == nums.length) {
            return target == 0 ? 1 : 0;
        }
        int ans = 0;
        ans += dfs(nums, target - nums[idx], idx + 1);
        ans += dfs(nums, target + nums[idx], idx + 1);
        return ans;
    }


    public static void main(String[] args) {

    }
}
