/**
 * 16. 3Sum Closest
    Medium

    Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    Example:

    Given array nums = [-1, 2, 1, -4], and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 **/

#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

class Solution {
public:
   // The method is similar to the method solving #15 Three Sum question.
   // Fix one number and use two pointers crash method.
   // If the sum of three numbers equals to target, just break and output the answer.
   // If the sum doesn't equal to target, we compare the sum with smallest distance recoreded.
   int threeSumClosest(vector<int> nums, int target) {
      sort(nums.begin(), nums.end());
      int dist_min = INT_MAX;
      int ans;
      bool jump = false;
      for (int k = 0; k < nums.size() - 2; ++k) {
         if (k > 0 && nums[k] == nums[k - 1]) continue;
         for (int i = k + 1, j  = nums.size() - 1; i < j;) {
            int sum = nums[k] + nums[i] + nums[j];
            if (sum == target) {
               ans = sum;
               jump = true;
               break;
            } else if (sum < target) {
               ++i;
            } else if (sum > target) {
               --j;
            }
            if (fabs(sum - target) < dist_min) {
                  dist_min = fabs(sum - target);
                  ans = sum;
            }
         }
         if (jump) break;
      }
      return ans;
   }
};

int main() {
   Solution s;
   // vector<int> nums {-1, 2, 1, -4};
   // int target = 1;

   // vector<int> nums {0, 1, 2};
   // int target = 3;

   // vector<int> nums {0, 2, 1, -3};
   // int target = 1;

   vector<int> nums {1, 1, -1, -1, 3};
   int target = 1;

   cout << s.threeSumClosest(nums, target) << endl;

   return 0;
}
