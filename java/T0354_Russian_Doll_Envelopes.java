package leetcode;

import java.net.ServerSocket;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class T0354_Russian_Doll_Envelopes {
    /**
     * 354. 俄罗斯套娃信封问题
     * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     *
     * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     *
     * 说明:
     * 不允许旋转信封。
     *
     * 示例:
     *
     * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
     * 输出: 3
     * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     */

    /**
     * 根据 w 或者 h 排序, 然后转为最长上升子序列问题 (LeetCode 300)
     */
    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length < 1) return 0;
        int n = envelopes.length;

        Arrays.sort(envelopes, (int[] a, int[] b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) nums[i] = envelopes[i][1];
        return lengthOfLIS(nums);
    }

    public static int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int idx = 0;
        for (int num : nums) {
            int left = 0, right = idx;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) left = mid + 1;
                else right = mid;
            }
            tails[right] = num;
            if (idx == right) ++idx;
        }
        return idx;
    }


    public static class Envelope {
        private int w;
        private int h;

        public Envelope(int w, int h) {
            this.w = w;
            this.h = h;
        }
    }

    public static int maxEnvelopes2(int[][] envelopes) {
        if (envelopes == null || envelopes.length < 1) return 0;
        int n = envelopes.length;
        List<Envelope> envelopeList = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            envelopeList.add(new Envelope(envelopes[i][0], envelopes[i][1]));
        }
        envelopeList.sort((Envelope a, Envelope b) -> (a.w == b.w ? b.h - a.h : a.w - b.w));
        int idx = 0;
        int[] tails = new int[n];
        for (Envelope envelope : envelopeList) {
            int left = 0, right = idx;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < envelope.h) left = mid + 1;
                else right = mid;
            }
            tails[left] = envelope.h;
            if (idx == right) ++idx;
        }
        return idx;
    }

    public static void main(String[] args) {
        System.out.println(maxEnvelopes(new int[][] {{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}, {1, 1}}));
        System.out.println(maxEnvelopes2(new int[][] {{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}, {1, 1}}));

    }
}
