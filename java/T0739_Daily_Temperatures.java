package leetcode;

import java.util.*;

public class T0739_Daily_Temperatures {

    /**
     * 739. 每日温度
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
     * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * <p>
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
     * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     * <p>
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     */

    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] ans = new int[n];

        LinkedList<Integer> s = new LinkedList<>();

        int idx = 0;
        while (idx < n) {
            if (s.isEmpty() || T[idx] <= T[s.peek()]) {
                s.push(idx);
            } else {
                while (!s.isEmpty() && T[idx] > T[s.peek()]) {
                    ans[s.peek()] = idx - s.peek();
                    s.pop();
                }
                s.push(idx);
            }
            ++idx;
        }
        return ans;
    }

}
