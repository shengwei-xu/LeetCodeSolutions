package leetcode;

public class T0309_Best_Time_To_Buy_And_Sell_Stock_With_Cooldown {

    /**
     * 309. 最佳买卖股票时机含冷冻期
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * <p>
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * <p>
     * 示例:
     * 输入: [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     */


    /**
     * f[i] = max( 第i天什么都不做: f[i-1]; 或者第i天卖出股票: x[i] + g[i-1] )
     * g[i] = max( 第i天什么都不做, 不买、不卖: g[i-1];
     * 或者第i天买入股票, 当第i天是buy，而题目要求sell的下一天要cooldown，所以i-1不能是sell；
     * 也不可能是buy，因为不能连续buy；
     * 所以第i-1天只能是cooldown, max(f[i-2], g[i-2])，
     * 而 f[i-2] > g[i-2]，所以综上，是 f[i-2] - x[i])
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        int[] f = new int[n]; // 当天不持有
        int[] g = new int[n]; // 当天持有

        f[0] = 0;
        g[0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i] = Math.max(f[i - 1], g[i - 1] + prices[i]);
            if (i >= 2) g[i] = Math.max(g[i - 1], f[i - 2] - prices[i]);
            else g[i] = Math.max(g[i - 1], -prices[i]);
        }
        return f[n - 1];
    }

    public static void main(String[] args) {

    }
}
