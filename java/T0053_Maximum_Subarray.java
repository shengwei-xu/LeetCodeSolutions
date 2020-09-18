package leetcode;

import java.util.Arrays;
import java.util.OptionalInt;

public class T0053_Maximum_Subarray {

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 示例:
     * <p>
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     */

    public static int maxSubArray(int[] nums) {
        int consum = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            if (consum + nums[i] > 0) {
                consum += nums[i];
                max = Math.max(max, consum);
            } else {
                consum = 0;
                max = Math.max(max, nums[i]);
            }
        }
        return max;
    }

    /**
     * dp 解法：dp_i 表示以第i个位置为终点的最长的子序列和为多少
     */
    public static int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * dp 优化版
     */
    public static int maxSubArray3(int[] nums) {
        int maxEndingHere = nums[0], maxSoFar = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
