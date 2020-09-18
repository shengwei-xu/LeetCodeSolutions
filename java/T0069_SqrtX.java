package leetcode;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;

public class T0069_SqrtX {

    /**
     * 69. x 的平方根
     * 实现 int sqrt(int x) 函数。
     *
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 示例 1:
     *
     * 输入: 4
     * 输出: 2
     * 示例 2:
     *
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     */

    public static int mySqrt(int x) {
        if (x == 0) return 0;
        int left = 1, right = x / 2;
        while (left < right) {
            // 取中位数的上界，否则会死循环
            int mid = left + (right - left + 1) / 2;
            if (mid <= x / mid) left = mid;
            else right = mid - 1;
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(7));
    }

}
