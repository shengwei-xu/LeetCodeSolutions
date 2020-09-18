package leetcode;

import java.util.Arrays;

public class T0322_Coin_Change {

    /**
     * 322. 零钱兑换
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     * 示例 1:
     * 输入: coins = [1, 2, 5], amount = 11
     * 输出: 3
     * 解释: 11 = 5 + 5 + 1
     *
     * 示例 2:
     * 输入: coins = [2], amount = 3
     * 输出: -1
     *
     * 说明:
     * 你可以认为每种硬币的数量是无限的。
     */


    /**
     * 完全背包问题
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        final int INF = 0xffffff;
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < coins.length; ++i) {
            for (int j = coins[i]; j <= amount; ++j) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        return dp[amount] == INF ? -1 : dp[amount];
    }

    public static void main(String[] args) {

    }
}
