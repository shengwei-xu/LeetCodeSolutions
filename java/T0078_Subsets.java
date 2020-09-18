package leetcode;

import java.util.*;

public class T0078_Subsets {

    /**
     * 78. 子集
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     *
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */


    /**
     * 1. backtrace
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, nums, 0, new ArrayList<>());
        return ans;
    }

    public static void backtrack(List<List<Integer>> ans, int[] nums, int idx, List<Integer> tmp) {
        ans.add(new ArrayList<>(tmp));
        for (int i = idx; i < nums.length; ++i) {
            tmp.add(nums[i]);
            backtrack(ans, nums, i + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * 二进制记录写法
     */
    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < (1 << nums.length); ++i) {
            List<Integer> sub = new ArrayList<>();
            for (int j = 0; j < nums.length; ++j) {
                if (((i >> j) & 1) == 1) {
                    sub.add(nums[j]);
                }
            }
            ans.add(sub);
        }
        return ans;
    }


    public static void main(String[] args) {

    }

}
