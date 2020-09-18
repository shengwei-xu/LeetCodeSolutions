package leetcode;

public class T0059_Spiral_Matrix_II {

    /**
     * 59. 螺旋矩阵 II
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     * <p>
     * 示例:
     * 输入: 3
     * 输出:
     * [
     *   [ 1, 2, 3 ],
     *   [ 8, 9, 4 ],
     *   [ 7, 6, 5 ]
     * ]
     */

    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];

        int left = 0, right = n - 1, top = 0, bottom = n - 1, num = 1;
        while (true) {
            if (left > right) break;
            for (int i = left; i <= right; ++i) ans[top][i] = num++;
            ++top;
            if (top > bottom) break;
            for (int i = top; i <= bottom; ++i) ans[i][right] = num++;
            --right;
            if (left > right) break;
            for (int i = right; i >= left; --i) ans[bottom][i] = num++;
            --bottom;
            if (top > bottom) break;
            for (int i = bottom; i >= top; --i) ans[i][left] = num++;
            ++left;
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
