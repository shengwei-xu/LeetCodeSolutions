package leetcode;

import java.util.ArrayList;
import java.util.List;

public class T0054_Spiral_Matrix {

    /**
     * 54. 螺旋矩阵
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * 示例 2:
     * <p>
     * 输入:
     * [
     * [1, 2, 3, 4],
     * [5, 6, 7, 8],
     * [9,10,11,12]
     * ]
     * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     */

    public int[] spiralOrder2(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) return new int[]{};
        int m = matrix.length, n = matrix[0].length;
        int[] ans = new int[m * n];
        int left = 0, right = n - 1, top = 0, bottom = m - 1;
        int idx = 0;
        while (true) {
            if (left > right) break;
            for (int j = left; j <= right; ++j) ans[idx++] = matrix[top][j];
            ++ top;
            if (top > bottom) break;
            for (int i = top; i <= bottom; ++i) ans[idx++] = matrix[i][right];
            -- right;
            if (left > right) break;
            for (int j = right; j >= left; --j) ans[idx++] = matrix[bottom][j];
            -- bottom;
            if (top > bottom) break;
            for (int i = bottom; i >= top; --i) ans[idx++] = matrix[i][left];
            ++ left;
        }
        return ans;
    }


    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) return new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        List<Integer> ans = new ArrayList<>();
        int left = 0, right = n - 1, top = 0, bottom = m - 1;
        while (true) {
            if (left > right) break;
            for (int j = left; j <= right; ++j) ans.add(matrix[top][j]);
            ++ top;
            if (top > bottom) break;
            for (int i = top; i <= bottom; ++i) ans.add(matrix[i][right]);
            -- right;
            if (left > right) break;
            for (int j = right; j >= left; --j) ans.add(matrix[bottom][j]);
            -- bottom;
            if (top > bottom) break;
            for (int i = bottom; i >= top; --i) ans.add(matrix[i][left]);
            ++ left;
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
