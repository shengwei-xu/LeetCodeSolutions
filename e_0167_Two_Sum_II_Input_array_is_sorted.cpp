/**
 * 167. Two Sum II - Input array is sorted
    Easy

    Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

    The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

    Note:

    Your returned answers (both index1 and index2) are not zero-based.
    You may assume that each input would have exactly one solution and you may not use the same element twice.
    Example:

    Input: numbers = [2,7,11,15], target = 9
    Output: [1,2]
    Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 * */

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    // O(nlogn) dichotomy
    vector<int> twoSum(vector<int>& numbers, int target) {
        vector<int> ans;
        for (int i = 0; i < numbers.size(); ++i) {
            int a = target - numbers[i];
            int l = i + 1, r = numbers.size() - 1;
            while(l <= r) {
                int mid = l + ((r - l) >> 1);
                if (a == numbers[mid]) {
                    ans.push_back(i + 1);
                    ans.push_back(mid + 1);
                    break;
                }
                else if (a < numbers[mid]) r = mid - 1;
                else if (a > numbers[mid]) l = mid + 1;
            }
            if (ans.size() == 2) break;
        }
        return ans;
    }
    // O(n) pointer crash
    vector<int> twoSum_2(vector<int>& numbers, int target) {
        vector<int> ans;
        int l = 0, r = numbers.size() - 1;
        for (int i = 0; i < numbers.size(); ++i) {
            if (numbers[l] + numbers[r] == target) {
                ans.push_back(l + 1);
                ans.push_back(r + 1);
                break;
            }
            else if (numbers[l] + numbers[r] < target) { ++l; }
            else if (numbers[l] + numbers[r] > target) { --r; }
        }
        return ans;
    }

};

int main() {
    Solution * s = new Solution();
    vector<int> numbers {1, 2, 3, 4, 4, 9, 56, 90};
    vector<int> t = s->twoSum(numbers, 8);
    vector<int> t2 = s->twoSum_2(numbers, 8);

    cout << "Method 1\n";
    for (auto index : t) {
        cout << index << " ";
    }
    cout << endl;

    cout << "Method 2\n";
    for (auto index : t2) {
        cout << index << " ";
    }
    cout << endl;
    return 0;
}
