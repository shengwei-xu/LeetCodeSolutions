package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class T0084_Largest_Rectangle_in_Histogram {

    /**
     * 84. 柱状图中最大的矩形
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * <p>
     * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
     * <p>
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * <p>
     * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
     * <p>
     * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
     * <p>
     * 示例:
     * <p>
     * 输入: [2,1,5,6,2,3]
     * 输出: 10
     */

    public static int largestRectangleArea(int[] heights) {
        int area = 0, cur = 0;
        // 前后各设置一个0，用来做两边的边界，防止数组本身就是单调的情况
        int[] hs = new int[heights.length + 2];
        Deque<Integer> stack = new LinkedList<>();
        System.arraycopy(heights, 0, hs, 1, heights.length);

        while (cur < hs.length) {
            while (!stack.isEmpty() && hs[cur] < hs[stack.peek()]) {
                // height
                int h = hs[stack.pop()];
                // width, right_idx - left_idx - 1
                int w = cur - stack.peek() - 1;
                area = Math.max(area, h * w);
            }
            stack.push(cur);
            ++cur;
        }
        return area;
    }


    public static void main(String[] args) {
        largestRectangleArea(new int[] {1, 2, 3, 4, 5, 6});
    }
}
