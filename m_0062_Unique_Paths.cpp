/*
62. Unique Paths
Medium

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 7 x 3 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    // Dynamic programming
    int uniquePaths(int m, int n) {
        // if (m <= 0 || n <= 0) return 0;
        int dp[m][n];
        for (int i = 0; i < m; ++i) {
            dp[i][n - 1] = 1;
        }
        for (int i = 0; i < n; ++i) {
            dp[m - 1][i] = 1;
        }
        for (int i = m - 2; i >= 0; --i) {
            for (int j = n - 2; j >= 0; --j) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];
    }
};

int main() {
    Solution* s = new Solution();
    cout << s->uniquePaths(7, 3) << endl;
    return 0;
}
