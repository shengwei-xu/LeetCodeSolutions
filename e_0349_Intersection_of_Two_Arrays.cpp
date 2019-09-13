/**
 * 349. Intersection of Two Arrays
    Easy

    Given two arrays, write a function to compute their intersection.

    Example 1:

    Input: nums1 = [1,2,2,1], nums2 = [2,2]
    Output: [2]
    Example 2:

    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [9,4]
    Note:

    Each element in the result must be unique.
    The result can be in any order.
 * */

#include <iostream>
#include <vector>
#include <unordered_set>
using namespace std;

class Solution {
public:
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        vector<int> ans;
        unordered_set<int> s (nums1.begin(), nums1.end());
        for (auto item : nums2) {
            if (s.find(item) != s.end()) {
            // if (s.count(item) != 0) {
                ans.emplace_back(item);
                s.erase(item);
            }
        }
        return ans;
    }
};

int main() {
    Solution s;

    vector<int> nums1 {4, 9, 5};
    vector<int> nums2 {9, 4, 9, 8, 4};

    // vector<int> nums1 {1, 2, 2, 1};
    // vector<int> nums2 {2, 2};

    vector<int> ans = s.intersection(nums1, nums2);
    
    copy(ans.begin(), ans.end(), ostream_iterator<int>(cout, " "));
    cout << endl;

    return 0;
}
