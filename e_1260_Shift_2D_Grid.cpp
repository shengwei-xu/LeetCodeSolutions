/*
1260. Shift 2D Grid

Given a 2D grid of size n * m and an integer k. You need to shift the grid k times.

In one shift operation:

Element at grid[i][j] becomes at grid[i][j + 1].
Element at grid[i][m - 1] becomes at grid[i + 1][0].
Element at grid[n - 1][m - 1] becomes at grid[0][0].
Return the 2D grid after applying shift operation k times.

Example 1:

Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[9,1,2],[3,4,5],[6,7,8]]

Example 2:

Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
Example 3:

Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
Output: [[1,2,3],[4,5,6],[7,8,9]]
 
Constraints:

1 <= grid.length <= 50
1 <= grid[i].length <= 50
-1000 <= grid[i][j] <= 1000
0 <= k <= 100
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    vector<vector<int>> shiftGrid(vector<vector<int>>& grid, int k) {
        int row = grid.size();
        int column = grid[0].size();
        k %= row * column;
        for (int p = 0; p < k; ++p) {
            for (int i = 0; i < row; ++i) {
                int tmp = grid[i][column - 1];
                grid[i].erase(grid[i].end() - 1);
                grid[i].insert(grid[i].begin(), tmp);
            }
            int tmp = grid[row - 1][0];
            for (int i = row - 1; i > 0; --i) {
                grid[i][0] = grid[i - 1][0];
            }
            grid[0][0] = tmp;
        }
        return grid;
    }
};

int main() {

    Solution s;
    vector<vector<int>> grid ={{1,2,3},{4,5,6},{7,8,9}};
    int k = 5;

    grid = s.shiftGrid(grid, k);

    for (int i = 0; i < grid.size(); ++i) {
        for (int j = 0; j < grid[i].size(); ++j) {
            cout << grid[i][j] << " ";
        }
        cout << endl;
    }

    return 0;
}
