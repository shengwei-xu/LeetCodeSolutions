package leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class T0128_Longest_Consecutive_Sequence {

    /**
     * 128. 最长连续序列
     * 给定一个未排序的整数数组，找出最长连续序列的长度。
     * <p>
     * 要求算法的时间复杂度为 O(n)。
     * <p>
     * 示例:
     * <p>
     * 输入: [100, 4, 200, 1, 3, 2]
     * 输出: 4
     * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
     */

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length <= 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            set.add(nums[i]);
        }

        int longestStream = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curStream = 1;
                while (set.contains(num + 1)) {
                    ++curStream;
                    ++num;
                }
                longestStream = Math.max(curStream, longestStream);
            }
        }
        return longestStream;
    }

    public static void main(String[] args) {

    }
}
