package leetcode;

import java.util.Arrays;

public class T0052_N_Queens_II {

    /**
     * 52. N-Queens II
     * Hard
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
     * such that no two queens attack each other.
     * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
     *
     * Example:
     * Input: 4
     * Output: 2
     * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
     * [
     *  [".Q..",  // Solution 1
     *   "...Q",
     *   "Q...",
     *   "..Q."],
     *
     *  ["..Q.",  // Solution 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]
     * ]
     */

    private static int solutions = 0;
    public static int totalNQueens(int n) {
        solutions = 0;
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        totalNQueensCore(queens, 0);
        return solutions;
    }

    private static void totalNQueensCore(int[] queens, int idx) {
        if (idx == queens.length) {
            ++solutions;
            return;
        }
        for (int i = 0; i < queens.length; ++i) {
            if (isOK(queens, idx, i)) {
                queens[idx] = i;
                totalNQueensCore(queens,idx + 1);
                queens[idx] = -1;
            }
        }
    }

    private static boolean isOK(int[] queens, int x, int y) {
        for (int i = 0; i < queens.length; ++i) {
            if (queens[i] != -1) {
                if (x == i || y == queens[i] ||
                        (x - i) == (y - queens[i]) ||
                        (x - i) == (queens[i] - y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(totalNQueens(4));
    }
}
