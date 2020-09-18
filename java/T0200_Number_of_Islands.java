package leetcode;

public class T0200_Number_of_Islands {

    /**
     * 200. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     * 示例 1:
     * 输入:
     * [
     * ['1','1','1','1','0'],
     * ['1','1','0','1','0'],
     * ['1','1','0','0','0'],
     * ['0','0','0','0','0']
     * ]
     * 输出: 1
     *
     * 示例 2:
     * 输入:
     * [
     * ['1','1','0','0','0'],
     * ['1','1','0','0','0'],
     * ['0','0','1','0','0'],
     * ['0','0','0','1','1']
     * ]
     * 输出: 3
     * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
     */

    public int[] dx = new int[] {0, 1, -1, 0};
    public int[] dy = new int[] {-1, 0, 0, 1};

    public int numIslands(char[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    ++ans;
                    dfs(i, j, grid);
                }
            }
        }
        return ans;
    }

    private void dfs(int x, int y, char[][] grid) {
        int m = grid.length, n = grid[0].length;

        grid[x][y] = '0';
        for (int i = 0; i < 4; ++i) {
                int tx = x + dx[i], ty = y + dy[i];
                if (tx < 0 || tx >= m || ty < 0 || ty >= n) continue;
                if (grid[tx][ty] == '0') continue;

                dfs(tx, ty, grid);
        }
    }


    public static void main(String[] args) {

    }

}
