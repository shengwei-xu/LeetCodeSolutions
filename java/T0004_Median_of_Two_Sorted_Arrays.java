package leetcode;

public class T0004_Median_of_Two_Sorted_Arrays {

    /**
     * 4. Median of Two Sorted Arrays
     * Hard
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.
     * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     * You may assume nums1 and nums2 cannot be both empty.
     * <p>
     * Example 1:
     * nums1 = [1, 3]
     * nums2 = [2]
     * The median is 2.0
     * <p>
     * Example 2:
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * The median is (2 + 3)/2 = 2.5
     */

    /**
     * https://www.acwing.com/solution/LeetCode/content/50/
     * O(lg(m+n))
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if (len % 2 == 0) {
            // 偶数情况的时候, 找到第 len/2 和 len/2 + 1 小的数求均值就可以
            int left = findKthNumber(nums1, 0, nums2, 0, len / 2);
            int right = findKthNumber(nums1, 0, nums2, 0, len / 2 + 1);
            return (left + right) / 2.0;
        } else {
            return findKthNumber(nums1, 0, nums2, 0, len / 2 + 1);
        }
    }

    /**
     * @param nums1
     * @param i     : nums1 中开始找第 k 小的数的位置
     * @param nums2
     * @param j     : nums2 中开始找第 k 小的数的位置
     * @param k     : 第 k 小的数, k 不可以为 0
     */
    public static int findKthNumber(int[] nums1, int i, int[] nums2, int j, int k) {
        // 确保 nums1 是较短的数组
        if (nums1.length - i > nums2.length - j) return findKthNumber(nums2, j, nums1, i, k);
        // 如果 nums1 数组的元素都被排除了可以直接从 nums 数组找到结果
        if (nums1.length == i) return nums2[j + k - 1];
        // 如果要找第 1 小的数, 那么在 nums1 和 nums2 的第一个数中找小的值就可以
        if (k == 1) return Math.min(nums1[i], nums2[j]);
        // 因为确保 nums1 是较短的数组, 在取第 k/2 小的数时取长度和 k/2 的较小值
        // 因为 nums2 是较长的数组, 所以这个数组长度肯定大于等于 k/2
        int si = Math.min(i + k / 2, nums1.length), sj = j + k / 2;
        if (nums1[si - 1] > nums2[sj - 1]) {
            // 这里说明 nums2 左边的元素可以被直接排除, 更新 j 到 sj, k 也已经找到了一半
            return findKthNumber(nums1, i, nums2, sj, k - k / 2);
        } else {
            // 这里说明 nums1 左边的元素可以被排除, 更新 i 到 si
            // 因为 nums1 可能没有 k/2 的长度, 所以减掉有效的个数
            return findKthNumber(nums1, si, nums2, j, k - (si - i));
        }
    }

    public static void main(String[] args) {

    }
}
