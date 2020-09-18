package leetcode;

public class T0034_Find_First_And_Last_Position_of_Element_in_Sorted_Array {

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * <p>
     * 如果数组中不存在目标值，返回 [-1, -1]。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     * 示例 2:
     * <p>
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
     */

    public static int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        if (nums.length == 0) return ans;

        int left = 0, right = nums.length, idx = -1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (target == nums[mid]) {
                idx = mid;
                break;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (idx != -1) {
            // find starting position
            int i = idx - 1;
            for (; i >= 0; --i) {
                if (nums[i] != nums[idx]) break;
            }
            ans[0] = i + 1;
            // find ending position
            i = idx + 1;
            for (; i < nums.length; ++i) {
                if (nums[i] != nums[idx]) break;
            }
            ans[1] = i - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(searchRange(new int[]{1}, 1));
    }
}
