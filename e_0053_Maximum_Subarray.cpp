/**
 * 53. Maximum Subarray
    Easy

    Given an integer array nums, find the contiguous subarray (containing at least one number) 
    which has the largest sum and return its sum.

    Example:

    Input: [-2,1,-3,4,-1,2,1,-5,4],
    Output: 6
    Explanation: [4,-1,2,1] has the largest sum = 6.
    Follow up:

    If you have figured out the O(n) solution, try coding another solution using 
    the divide and conquer approach, which is more subtle.
 * */


#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        vector<int> dp(nums.size(), 0);
        dp[0] = nums[0];
        int maximum = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            dp[i] = max(nums[i], dp[i - 1] + nums[i]);
            maximum = max(dp[i], maximum);
        }
        for (int i = 0; i < nums.size(); ++i) {
            cout << dp[i] << " ";
        }
        cout << endl;
        return maximum;
    }
};

int main() {
    Solution s = Solution();
    vector<int> nums {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    cout << s.maxSubArray(nums) << endl;
    return 0;
}
