package leetcode;

import java.util.*;

public class T0347_Top_K_Frequent_Elements {

    /**
     * 347. 前 K 个高频元素
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     *
     * 示例 1:
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     *
     * 示例 2:
     * 输入: nums = [1], k = 1
     * 输出: [1]
     *
     * 提示：
     * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
     * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
     * 你可以按任意顺序返回答案。
     */

    /**
     * 桶排序, 用频率作为桶的下标
     */
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) map.put(n, map.getOrDefault(n, 0) + 1);
        
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int cnt = map.get(key);
            if (buckets[cnt] == null) buckets[cnt] = new ArrayList<>();
            buckets[cnt].add(key);
        }

        int[] ans = new int[k];
        int idx = 0;
        for (int i = buckets.length - 1; i >= 0 && idx < k; --i) {
            if (buckets[i] != null) {
                for (int n : buckets[i]) {
                    ans[idx++] = n;
                }
            }
        }
        return ans;
    }

    /**
     * 最小堆
     */
    public static int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> map.get(o1) - map.get(o2));

        for (int key : map.keySet()) {
            if (minHeap.size() < k) minHeap.add(key);
            else if (map.get(key) > map.get(minHeap.peek())) {
                minHeap.remove();
                minHeap.add(key);
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = minHeap.poll();
        }
        return ans;
    }

    public static void main(String[] args) {
        topKFrequent(new int[] {4,1,-1,2,-1,2,3}, 2);
    }
}
