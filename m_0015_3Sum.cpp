/**
 * 15. 3Sum
    Medium

    Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

    Note:

    The solution set must not contain duplicate triplets.

    Example:

    Given array nums = [-1, 0, 1, 2, -1, -4],

    A solution set is:
    [
    [-1, 0, 1],
    [-1, -1, 2]
    ]
 * */

#include <iostream>
#include <vector>
#include <set>
using namespace std;

class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        // Reference to TwoSum question 
        // fix one number and use method in TwoSum question (two pointers crash)
        vector<vector<int>> ans;
        sort(nums.begin(), nums.end());
        // HERE using `nums.size() - 2` as condition maybe cause RunTimeError
        for (int k = 0; k < nums.size() - 2; ++k) { 
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            for (int i = k + 1, j = nums.size() - 1; i < j;) {
                if (nums[k] + nums[i] + nums[j] == 0) {
                    ans.emplace_back(vector<int> {nums[k], nums[i], nums[j]});
                    do {    // to avoid same solution
                        ++i;
                    } while (nums[i] == nums[i - 1] && i < j);
                    do {
                        --j;
                    } while (nums[j] == nums[j + 1] && i < j);
                } else if (nums[k] + nums[i] + nums[j] > 0) {
                    --j;
                } else if (nums[k] + nums[i] + nums[j] < 0) {
                    ++i;
                }
            }
        }
        return ans;
    }
};


int main() {
    Solution s;
    // vector<int> nums {-2, 0, 0, 2, 2};
    // vector<int> nums {-1, 0, 1, 2, -1, -4};
    vector<int> nums (300, 0);

    vector<vector<int>> ans = s.threeSum(nums);

    for (vector<int> item : ans) {
        copy(item.begin(), item.end(), ostream_iterator<int>(cout, " "));
        cout << endl;
    }
    
    return 0;
}

