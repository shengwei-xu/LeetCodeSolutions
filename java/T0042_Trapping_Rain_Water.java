package leetcode;

import javax.swing.*;
import java.util.Deque;
import java.util.LinkedList;

public class T0042_Trapping_Rain_Water {

    /**
     *42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * https://leetcode-cn.com/problems/trapping-rain-water/
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     *
     * 示例:
     *
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     *
     */


    /**
     * 解法一：动态规划
     * O(n), O(n)
     * 思路：用dp方法缓存每一列的左右两侧最高的高度
     */
    public int trap(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; ++i) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }

        for (int i = height.length - 2; i >= 0; --i) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }

        for (int i = 1; i < height.length - 1; ++i) {
            int min = Math.min(max_left[i], max_right[i]);
            if (height[i] < min) {
                sum += min - height[i];
            }
        }
        return sum;
    }

    /**
     * 解法二：双指针
     * O(n) O(1)
     */
    public int trap2(int[] height) {
        int sum = 0;
        int max_left = 0, max_right = 0;
        int left = 1, right = height.length - 2;
        for (int i = 1; i < height.length - 1; ++i) {
            // 能装多少取决于短板
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(height[left - 1], max_left);
                if (max_left > height[left]) {
                    sum += max_left - height[left];
                }
                ++left;
            } else {
                max_right = Math.max(height[right + 1], max_right);
                if (max_right > height[right]) {
                    sum += max_right - height[right];
                }
                --right;
            }
        }
        return sum;
    }

    /**
     * 解法三：单调栈
     * 当后面的柱子高度比前面的低时，是无法接雨水的
     * 当找到一根比前面高的柱子，就可以计算接到的雨水
     * O(n), O(n)
     */
    public int trap3(int[] height) {
        int sum = 0, cur = 0;
        Deque<Integer> stack = new LinkedList<>();
        while (cur < height.length) {
            while (!stack.isEmpty() && height[cur] > height[stack.peek()]) {
                int h = stack.pop();
                if (stack.isEmpty()) break;
                // 两墙之间的距离
                int distance = cur - stack.peek() - 1;
                int min = Math.min(height[stack.peek()], height[cur]);
                sum += distance * (min - h);
            }
            stack.push(cur);
            ++ cur;
        }
        return sum;
    }

    public static void main(String[] args) {

    }
}
