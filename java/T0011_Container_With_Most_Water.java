package leetcode;

import java.util.Arrays;

public class T0011_Container_With_Most_Water {
    /**
     * 11. 盛最多水的容器
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * <p>
     * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * <p>
     * 示例：
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     */

    public int maxArea(int[] height) {
        if (height.length <= 1) return 0;
        int ans = 0;
        // 双指针, 每次移动两条边中较短的边
        for (int i = 0, j = height.length - 1; i < j; ) {
            int area = (j - i) * Math.min(height[i], height[j]);
            if (area > ans) ans = area;
            if (height[i] < height[j]) ++i;
            else --j;
        }
        return ans;
    }

    public static int[] getDuplicatedAndMissingNumber(int[] s) {
        int[] ans = new int[2];
        if (s == null || s.length <= 1) return ans;
        for (int i = 0; i < s.length; ) {
            if ((i + 1) != s[i]) {
                if (s[i] != s[s[i] - 1]) swap(s, i, s[i]);
                else {
                    ans[0] = s[i];
                    ans[1] = i + 1;
                    break;
                }
            } else {
                ++i;
            }
        }
        return ans;
    }

    public static void swap(int[] s, int i, int j) {
        int tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    public static void main(String[] args) {
//        int[] data = new int[] {2, 3, 4, 4};
        int[] data = new int[]{1, 2, 2, 4};
        int[] ans = getDuplicatedAndMissingNumber(data);
        for (int n : ans) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
