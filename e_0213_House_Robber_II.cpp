/*
213. House Robber II
Medium

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. 
All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, 
adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    // dynamic programming
    int rob(vector<int>& nums) {
        if (nums.size() == 0) return 0;
        if (nums.size() == 1) return nums[0];
        if (nums.size() == 2) return max(nums[0], nums[1]);

        vector<int> dp(nums.size() - 1);

        // rob nums[0]~nums[n-1], drop nums[n]
        dp[0] = nums[0];
        dp[1] = max(dp[0], nums[1]);
        for (int i = 2; i < dp.size(); ++i) {
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        int v1 = dp[dp.size() - 1];

        // rob nums[1]~nums[n], drop nums[0]
        dp[0] = nums[1];
        dp[1] = max(dp[0], nums[2]);
        for (int i = 2; i < dp.size(); ++i) {
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i + 1]);
        }
        int v2 = dp[dp.size() - 1];

        return max(v1, v2);
    }
};

int main() {
    Solution s;

    vector<int> nums {2,1,1,2};
    // vector<int> nums {1,1};
    // vector<int> nums {0};
    // vector<int> nums;
    cout << s.rob(nums) << endl;
    return 0;
}
