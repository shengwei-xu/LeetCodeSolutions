package leetcode;

public class T0154_Find_Minimum_in_Rotated_Sorted_Array_II {

    /**
     * 154. Find Minimum in Rotated Sorted Array II
     * Hard
     * <p>
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
     * <p>
     * Find the minimum element.
     * The array may contain duplicates.
     * <p>
     * Example 1:
     * Input: [1,3,5]
     * Output: 1
     * <p>
     * Example 2:
     * Input: [2,2,2,0,1]
     * Output: 0
     * <p>
     * Note:
     * This is a follow up problem to Find Minimum in Rotated Sorted Array.
     * Would allow duplicates affect the run-time complexity? How and why?
     */

    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        int mid = left;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                --right;
            }
        }
        return nums[left];
    }

    public int findMin2(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        int mid = left;
        while (nums[left] >= nums[right]) {
            if (right - left == 1) {
                mid = right;
                break;
            }
            mid = left + (right - left) / 2;
            // 三者相等必须顺序查找
            if (nums[left] == nums[mid] && nums[right] == nums[mid]) {
                int min = nums[left];
                for (int i = left + 1; i <= right; ++i) {
                    if (min > nums[i]) {
                        min = nums[i];
                    }
                }
                return min;
            }
            if (nums[mid] >= nums[left]) {
                left = mid;
            } else if (nums[mid] <= nums[right]) {
                right = mid;
            }
        }
        return nums[mid];
    }

    public static void main(String[] args) {

    }
}
