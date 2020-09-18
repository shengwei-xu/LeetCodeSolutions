package leetcode;

import java.util.Arrays;

public class T0031_Next_Permutation {

    /**
     * 31. 下一个排列
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * <p>
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * <p>
     * 必须原地修改，只允许使用额外常数空间。
     * <p>
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * <p>
     * <p>
     * 以[5,6,11,9,7,5,3,1]举例
     * <p>
     * 1.首先从尾部查找，直到找到当前元素大于前一个元素为止，记录该位置；
     * 对于[5,6,11,9,7,5,3,1]，即查找到11>6，此时位置索引为2；
     * 2.从该位置到数组尾部的子序列中，找到一个比前一位置的大且最接近的数；
     * 对于[5,6,11,9,7,5,3,1]，即在[11,9,7,5,3,1]找到7，7是比6大且最接近6的，从尾部开始遍历，遍历到大于6的索引即可；
     * 3.将找到的数与前一位置的数交换；
     * 对于[5,6,11,9,7,5,3,1]，交换后变为[5,7,11,9,6,5,3,1]；
     * 4.将该位置到数组尾部的子序列进行升序排列，因为已经为降序，故首尾两两交换即可；
     * 对于步骤3中的[5,7,11,9,6,5,3,1],交换后为[5,7,1,3,5,6,9,11]；
     * <p>
     * 注意：
     * 对于最大的排列，从小到大排列即可；
     */

    public static void nextPermutation(int[] nums) {
        if (nums.length <= 1) return;

        // 找到连续下边的 nums[i] < nums[i + 1]
        int idx = nums.length - 1;
        while (idx > 0 && nums[idx - 1] >= nums[idx]) --idx;

        // 反转后半段数组
        for (int i = idx, j = nums.length - 1; i < j; ++i, --j) {
            swap(nums, i, j);
        }

        // 找到第一个比 idx - 1 大的数字，交换即可
        if (idx > 0) {
            for (int i = idx; i < nums.length; ++i) {
                if (nums[i] > nums[idx - 1]) {
                    swap(nums, i, idx - 1);
                    break;
                }
            }
        }

    }

    private static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j] - (nums[j] = nums[i]);
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 3, 1};
        nextPermutation(data);
        for (int i : data) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
