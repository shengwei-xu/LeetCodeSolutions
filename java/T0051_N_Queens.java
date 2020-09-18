package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class T0051_N_Queens {

    /**
     * 51. N-Queens
     * Hard
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that
     * no two queens attack each other.
     * Given an integer n, return all distinct solutions to the n-queens puzzle.
     * Each solution contains a distinct board configuration of the n-queens' placement,
     * where 'Q' and '.' both indicate a queen and an empty space respectively.
     *
     * Example:
     * Input: 4
     * Output: [
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
     * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
     */

    private static List<List<String>> ans;
    private static int[] queens;

    public static List<List<String>> solveNQueens(int n) {
        ans = new LinkedList<>();
        queens = new int[n];
        Arrays.fill(queens, -1);
        solveNQueensCore(n, 0);
        return ans;
    }

    private static void solveNQueensCore(int n, int row) {
        if (row == n) {
            List<String> oneAns = new LinkedList<>();
            for (int i = 0; i < n; ++i) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; ++j) {
                    sb.append(queens[i] == j ? "Q" : ".");
                }
                oneAns.add(sb.toString());
            }
            ans.add(oneAns);
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (isOK(n, row, i)) {
                queens[row] = i;
                solveNQueensCore(n, row + 1);
                queens[row] = -1;
            }
        }
    }

    private static boolean isOK(int n, int row, int col) {
        for (int i = 0; i < n; ++i) {
            if (queens[i] != -1) {
                if (row == i || col == queens[i] ||
                        (row - i) == (col - queens[i]) ||
                        (row - i) == (queens[i] - col)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(4).size());
    }
}
