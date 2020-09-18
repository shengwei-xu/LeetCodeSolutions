package leetcode;

public class T0416_Partition_Equal_Subset_Sum {

    /**
     * 416. 分割等和子集
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * <p>
     * 注意:
     * 每个数组中的元素不会超过 100
     * 数组的大小不会超过 200
     * <p>
     * 示例 1:
     * 输入: [1, 5, 11, 5]
     * 输出: true
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     * <p>
     * 示例 2:
     * 输入: [1, 2, 3, 5]
     * 输出: false
     * 解释: 数组不能分割成两个元素和相等的子集.
     */

    /**
     * 0-1 背包问题
     */
    public static boolean canPartition(int[] nums) {
        int n = nums.length;
        if (nums.length <= 1) return false;

        int sum = 0;
        for (int x : nums) sum += x;
        if (sum % 2 != 0) return false;

        int target = sum / 2;
        boolean[] f = new boolean[target + 1];

        f[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= nums[i]; j--) {
                f[j] |= f[j - nums[i]];
            }
        }
        return f[target];
    }

    public static void main(String[] args) {
        canPartition(new int[]{1, 5, 11, 5});
    }
}
