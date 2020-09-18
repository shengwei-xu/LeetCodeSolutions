package leetcode;

public class T0064_Minimum_Path_Sum {

    /**
     * 64. 最小路径和
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * <p>
     * 示例:
     * 输入:
     * [
     * [1,3,1],
     * [1,5,1],
     * [4,2,1]
     * ]
     * 输出: 7
     * 解释: 因为路径 1→3→1→1→1 的总和最小。
     */

    /*
    1 2 5
    3 2 1
     */

    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; ++i) {
            if (i == 0) {
                dp[0] = grid[0][0];
                for (int j = 1; j < n; ++j) dp[j] = dp[j - 1] + grid[0][j];
            } else {
                for (int j = 0; j < n; ++j) {
                    if (j == 0) dp[j] = dp[j]+ grid[i][0];
                    else dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{{1,2,5},{3,2,1}}));
    }
}
