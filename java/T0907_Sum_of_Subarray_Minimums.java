package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class T0907_Sum_of_Subarray_Minimums {
    /**
     * 907. 子数组的最小值之和
     * Medium
     * 给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。
     *
     * 由于答案可能很大，因此返回答案模 10^9 + 7。
     *
     * 示例：
     * 输入：[3,1,2,4]
     * 输出：17
     * 解释：
     * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
     * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
     *  
     * 提示：
     * 1 <= A <= 30000
     * 1 <= A[i] <= 30000
     */

    /**
     * 1，利用单调栈记录截止到当前下标之前的所有下边界
     * 2，利用sums[i]记录所有以i为终点的区间最小值之和
     */
    public int sumSubarrayMins(int[] A) {
        final int MOD = 1000_000_000 + 7;
        int n = A.length;
        int[] sums = new int[n];
        // 单调栈
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && A[stack.peek()] >= A[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                sums[i] = A[i] * (i + 1);
            } else {
                sums[i] = sums[stack.peek()] + A[i] * (i - stack.peek());
            }
            sums[i] %= MOD;
            stack.push(i);
        }
        int res = 0;
        for (int sum : sums) {
            res += sum % MOD;
            res %= MOD;
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
