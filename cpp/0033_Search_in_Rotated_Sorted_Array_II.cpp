/**
 * 33. Search in Rotated Sorted Array
    Medium

    Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

    (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

    You are given a target value to search. If found in the array return its index, otherwise return -1.

    You may assume no duplicate exists in the array.

    Your algorithm's runtime complexity must be in the order of O(log n).

    Example 1:

    Input: nums = [4,5,6,7,0,1,2], target = 0
    Output: 4
    Example 2:

    Input: nums = [4,5,6,7,0,1,2], target = 3
    Output: -1
 * */

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int search(vector<int>& nums, int target) {
        if (nums.size() == 0) return -1;

        int left = 0;
        int right = nums.size() - 1;
        int mid = (left + right) >> 1;

        while (left <= right) {
            if (target == nums[left]) return left;
            if (target == nums[right]) return right;
            if (target == nums[mid]) return mid;

            if (target <= nums[mid] && target >= nums[0]) {
                ++left;
                right = mid - 1;
            } else {
                --right;
                left = mid + 1;
            }
            mid = (left + right) >> 1;
        }
        return -1;
    }
};

int main() {
    /** Test case
     * {}
     * {4,5,6,7,0,1,2}
     * {1,1,1,1,1,1,1}
     * {7,1,2,3,4,5,6}
     * {2,3,4,5,6,7,1}
     * {1,0,1,1,1,1}
     * {1,1,1,1,0,1}
     * */
    vector<int> nums {7,1,2,3,4,5,6};
    int target = 1;

    Solution* s = new Solution();

    cout << s -> search(nums, target) << endl;

    return 0;
}