package leetcode;

public class T0581_Shortest_Unsorted_Continuous_Subarray {

    /**
     *581. 最短无序连续子数组
     * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     *
     * 你找到的子数组应是最短的，请输出它的长度。
     *
     * 示例 1:
     *
     * 输入: [2, 6, 4, 8, 10, 9, 15]
     * 输出: 5
     * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     * 说明 :
     *
     * 输入的数组长度范围在 [1, 10,000]。
     * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
     */

    public int findUnsortedSubarray(int[] nums) {
        int end = -2, begin = -1;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            max = Math.max(max, nums[i]);
            if (nums[i] < max) {
                end = i;
            }
        }
        for (int i = nums.length - 1; i >= 0; --i) {
            min = Math.min(min, nums[i]);
            if (nums[i] > min) {
                begin = i;
            }
        }
        return end - begin + 1;
    }

}
