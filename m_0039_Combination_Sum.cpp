/**
 * 39. Combination Sum
    Medium

    Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), 
    find all unique combinations in candidates where the candidate numbers sums to target.

    The same repeated number may be chosen from candidates unlimited number of times.

    Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.
    Example 1:

    Input: candidates = [2,3,6,7], target = 7,
    A solution set is:
    [
      [7],
      [2,2,3]
    ]
    Example 2:

    Input: candidates = [2,3,5], target = 8,
    A solution set is:
    [
      [2,2,2,2],
      [2,3,3],
      [3,5]
    ]

 * */

#include <iostream>
#include <vector>
#include <stack>
using namespace std;

class Solution {
private:
    void dfs(vector<vector<int>> &ans, vector<int> &candidates, 
            int target, int start, int curSum, vector<int> &path) {
        if (curSum == target) {
            ans.push_back(path);
            return;
        }
        for (int i = start; i < candidates.size(); i++) {
            if (curSum + candidates[i] <= target) {
            path.push_back(candidates[i]);
            dfs(ans, candidates, target, i, curSum + candidates[i], path);
            path.pop_back();
            }
        }
    }

public:
   // backtracking
   // recursive solution
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        vector<vector<int>> ans;
        if (candidates.empty()) {
            return ans;
        }
        vector<int> path;
        dfs(ans, candidates, target, 0, 0, path);
        return ans;
    }

};

int main() {
    Solution s;

    vector<int> candidates = {2,3,6,7};
    int target = 7;

    vector<vector<int>> ans = s.combinationSum(candidates, target);

    for (int i = 0; i < ans.size(); ++i) {
        for (int j = 0; j < ans[i].size(); ++j) {
            cout << ans[i][j] << " ";
        }
        cout << endl;
    }

    return 0;
}
