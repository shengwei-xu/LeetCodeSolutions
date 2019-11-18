/*
63. Unique Paths II
Medium

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    // Dynamic programming
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        int m = obstacleGrid.size();
        int n = obstacleGrid[0].size();
        long long dp[m][n]; // Space: O(m*n)
        memset(dp, 0, sizeof(dp));
        for (int i = m - 1; i >= 0; --i) {
            if (obstacleGrid[i][n - 1] == 1) break;
            dp[i][n - 1] = 1;
        }
        for (int j = n - 1; j >= 0; --j) {
            if (obstacleGrid[m - 1][j] == 1) break;
            dp[m - 1][j] = 1;
        }
        for (int i = m - 2; i >= 0; --i) { // Time: O(n^2)
            for (int j = n - 2; j >= 0; --j) {
                if (obstacleGrid[i][j] == 1) continue;
                if (obstacleGrid[i + 1][j] != 1) dp[i][j] += dp[i + 1][j];
                if (obstacleGrid[i][j + 1] != 1) dp[i][j] += dp[i][j + 1];
            }
        }
        return (int)dp[0][0];
    }
};

int main() {
    Solution* s = new Solution();

    vector<vector<int>> obstacleGrid {{0,0,0}, {0,1,0}, {0,0,0}};

    cout << s->uniquePathsWithObstacles(obstacleGrid) << endl;
    
    return 0;
}
