package leetcode;

import java.util.HashMap;
import java.util.Map;

public class T0560_Subarray_Sum_Equals_K {

    /**
     * 560. 和为K的子数组
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     *
     * 示例 1 :
     * 输入:nums = [1,1,1], k = 2
     * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
     *
     * 说明 :
     * 数组的长度为 [1, 20,000]。
     * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
     */


    /**
     * 前缀和+哈希表, 对原数组求前缀和后，一个和为 k 的子数组即为一对前缀和的差值为 k 的位置
     * t_j - t_i = k, 每次缓存 t_j, 即当前j下标位置, 看 t_j - k 后的下标处有没有解决方案.
     * 看 t_i = t_j - k 有没有数字.
     */
    public int subarraySum(int[] nums, int k) {
        int preSum = 0, ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int n : nums) {
            preSum += n;
            ans += map.getOrDefault(preSum - k, 0);
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return ans;
    }

    /**
     * 前缀和数组
     */
    public int subarraySum2(int[] nums, int k) {
        // 计算前缀和数组
        int[] preSum = new int[nums.length + 1];
        preSum[0] = 0;
        for (int i = 1; i <= nums.length; ++i) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int cnt = 0;
        for (int i = 1; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                int sum = preSum[i + 1] - preSum[j];
                if (sum == k) ++cnt;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {

    }
}
