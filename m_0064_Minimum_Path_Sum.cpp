/*
64. Minimum Path Sum
Medium

Given a m x n grid filled with non-negative numbers, 
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int minPathSum(vector<vector<int>>& grid) {
        if (grid.size() <= 0 || grid[0].size() <= 0) return 0;

        int m = grid.size();
        int n = grid[0].size();

        int dp[m][n];
        memset(dp, 0, sizeof(dp));

        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (i == m - 1 && j == n - 1) { // last element
                    dp[m - 1][n - 1] = grid[m - 1][n - 1];
                } else if (i == m - 1) { // last row
                    dp[i][j] = dp[i][j + 1] + grid[i][j];
                } else if (j == n - 1) { // last column
                    dp[i][j] = dp[i + 1][j] + grid[i][j];
                } else {
                    dp[i][j] = min(dp[i + 1][j] + grid[i][j], dp[i][j + 1] + grid[i][j]);
                }
            }
        }
        return dp[0][0];
    }
};

int main() {
    Solution* s = new Solution();

    vector<vector<int>> grid {{1,3,1}, {1,5,1}, {4,2,1}};
    // vector<vector<int>> grid;
    cout << s->minPathSum(grid) << endl;

    return 0;
}

