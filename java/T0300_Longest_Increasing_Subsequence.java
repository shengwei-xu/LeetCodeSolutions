package leetcode;

import java.util.Arrays;

public class T0300_Longest_Increasing_Subsequence {

    /**
     * 300. 最长上升子序列
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * <p>
     * 示例:
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * <p>
     * 说明:
     * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
     * 你算法的时间复杂度应该为 O(n2) 。
     * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
     */

    /**
     * DP + binary serach
     * O(nlgn)
     */
    public static int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int idx = 0;
        for (int num : nums) {
            int i = 0, j = idx;
            while (i < j) {
                int m = i + (j - i) / 2;
                if (tails[m] < num) i = m + 1;
                else j = m;
            }
            // 找到第一个比 num 大的数，这个数如果存在，这里 i 或 j 都可以，不存在的话 i 或 j 指向新的位置
            tails[i] = num;
            if (idx == j) ++idx;
        }
        return idx;
    }


    /**
     * O(n^2)
     * dp[i] = max(dp[i], dp[j] + 1) for j in [0, i)
     */
    public static int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        int res = 0;
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
}
