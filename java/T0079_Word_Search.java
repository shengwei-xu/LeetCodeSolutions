package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class T0079_Word_Search {
    /**
     * 79. Word Search
     * Medium
     * <p>
     * Given a 2D board and a word, find if the word exists in the grid.
     * The word can be constructed from letters of sequentially adjacent cell,
     * where "adjacent" cells are those horizontally or vertically neighboring.
     * The same letter cell may not be used more than once.
     * <p>
     * Example:
     * <p>
     * board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     * <p>
     * Given word = "ABCCED", return true.
     * Given word = "SEE", return true.
     * Given word = "ABCB", return false.
     * <p>
     * Constraints:
     * board and word consists only of lowercase and uppercase English letters.
     * 1 <= board.length <= 200
     * 1 <= board[i].length <= 200
     * 1 <= word.length <= 10^3
     */


    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        int[][] vis = new int[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (hasPathCore(board, word, vis, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean hasPathCore(char[][] board, String word, int[][] vis, int x, int y, int idx) {
        if (idx == word.length()) {
            return true;
        }

        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }

        boolean hasPath = false;
        if (vis[x][y] == 0 && board[x][y] == word.charAt(idx)) {
            vis[x][y] = 1;
            hasPath = hasPathCore(board, word, vis, x + 1, y, idx + 1)
                    || hasPathCore(board, word, vis, x - 1, y, idx + 1)
                    || hasPathCore(board, word, vis, x, y + 1, idx + 1)
                    || hasPathCore(board, word, vis, x, y - 1, idx + 1);
            if (!hasPath) {
                vis[x][y] = 0;
            }
        }
        return hasPath;
    }

    public static void main(String[] args) {

    }
}
