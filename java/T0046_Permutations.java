package leetcode;

import java.util.LinkedList;
import java.util.List;

public class T0046_Permutations {

    /**
     * 46. 全排列
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     *
     * 示例:
     *
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     */

    private List<List<Integer>> ans;
    public List<List<Integer>> permute(int[] nums) {
        ans = new LinkedList<>();
        if (nums.length == 0) return ans;
        permute(nums, 0, ans);
        return ans;
    }

    public void permute(int[] nums, int idx, List<List<Integer>> ans) {
        if (idx == nums.length - 1) {
            List<Integer> result = new LinkedList<>();
            for (int i = 0; i < nums.length; ++i) {
                result.add(nums[i]);
            }
            ans.add(result);
        } else {
            for (int i = idx; i < nums.length; ++i) {
                swap(nums, i, idx);
                permute(nums, idx + 1, ans);
                swap(nums, idx, i);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        nums[i] = (nums[i] + nums[j]) - (nums[j] = nums[i]);
    }

    public static void main(String[] args) {

    }
}
