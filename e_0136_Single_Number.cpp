/*
136. Single Number
Easy

Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4
*/


#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int v = 0;
        for (vector<int>::iterator it = nums.begin(); it != nums.end(); ++it) {
            v ^= *it;
        }
        return v;
    }

    string test() {
        bool judge = true;
        test_case({1}, 1, judge);
        test_case({1, 2, 1}, 2, judge);
        test_case({2, 2, 4, 4, 1}, 1, judge);
        test_case({2, 2, 2, 2, 3}, 3, judge);
        return judge ? "Passed all test." : "Wrong Answer.";
    }

    bool test_case(vector<int> nums, int expected, bool& judge) {
        int v = singleNumber(nums);
        if (v != expected) {
            judge = false;
            cout << "Output: " << v << ", Expected: " << expected << endl;
        }
        return judge;
    }
};

int main() {
    Solution* s = new Solution();
    cout << s -> test() << endl;
    return 0;
}
