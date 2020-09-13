/**
 * 1. Two Sum
    Easy

    Given an array of integers, return indices of the two numbers such that they add up to a specific target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    Example:

    Given nums = [2, 7, 11, 15], target = 9,

    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
 * */


#include <iostream>
#include <vector>
#include <map>
#include <unordered_map>
using namespace std;

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        // The input sequence is not sorted
        unordered_map<int, int> mymap;
        for (int i = 0; i < nums.size(); ++i) {
            unordered_map<int, int>::iterator it = mymap.find(target - nums[i]);
            if (it != mymap.end()) {
                return vector<int> {it->second, i};
            }
            mymap.emplace(nums[i], i);
        }
        return vector<int> {};
    }

};

int main() {
    vector<int> nums {2, 7, 11, 15};
    int target = 9;
    Solution s;
    vector<int> ans = s.twoSum(nums, target);
    copy(ans.begin(), ans.end(), ostream_iterator<int>(cout, " "));
    cout << endl;
    return 0;
}