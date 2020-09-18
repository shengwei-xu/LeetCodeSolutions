package leetcode;

public class T0152_Maximum_Product_Subarray {

    /**
     * 152. 乘积最大子数组
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     *
     * 示例 1:
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     *
     * 示例 2:
     * 输入: [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     */

    public int maxProduct2(int[] nums) {
        int curMax = 1, curMin = 1, res = Integer.MIN_VALUE;
        for (int x : nums) {
            if (x < 0) {
                int tmp = curMax;
                curMax = curMin;
                curMin = tmp;
            }
            curMax = Math.max(curMax * x, x);
            curMin = Math.min(curMin * x, x);

            res = Math.max(res, curMax);
        }
        return res;
    }

    public int maxProduct(int[] nums) {
        int pos = 0, neg = 0;
        int res = Integer.MIN_VALUE;
        for (int x : nums) {
            if (x > 0) {
                if (pos > 0) pos *= x;
                else pos = x;
                neg *= x;
            } else if (x < 0) {
                int np = pos, ng = neg;
                // 求新的pos
                np = neg * x;
                // 求新的neg
                if (pos > 0) {
                    ng = pos * x;
                } else {
                    ng = x;
                }
                // 更新 pos neg
                pos = np;
                neg = ng;
            } else {
                // x == 0
                pos = neg = 0;
            }
            res = Math.max(res, x);
            if (pos > 0) res = Math.max(res, pos);
        }
        return res;
    }

}
