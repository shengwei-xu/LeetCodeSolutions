/**
 * 70. Climbing Stairs
    Easy

    You are climbing a stair case. It takes n steps to reach to the top.

    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

    Note: Given n will be a positive integer.

    Example 1:

    Input: 2
    Output: 2
    Explanation: There are two ways to climb to the top.
    1. 1 step + 1 step
    2. 2 steps
    Example 2:

    Input: 3
    Output: 3
    Explanation: There are three ways to climb to the top.
    1. 1 step + 1 step + 1 step
    2. 1 step + 2 steps
    3. 2 steps + 1 step
 * 
 * 
 * */

#include <iostream>
using namespace std;

class Solution {
public:
    // Dynamic programming
    int climbStairs(int n) {
        long long ans[46] = { 0 };
        ans[0] = 1;
        ans[1] = 1;
        for (int i = 2; i <= n; ++i) {
            ans[i] = ans[i - 1] + ans[i - 2];
        }
        return ans[n];
    }
    // Binets Method
    // https://leetcode.com/articles/climbing-stairs/

};

int main() {
    Solution s = Solution();
    int n = 45;
    cout << s.climbStairs(n) << endl;
    return 0;
}
