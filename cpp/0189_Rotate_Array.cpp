/**
 * 189. Rotate Array
    Easy

    Given an array, rotate the array to the right by k steps, where k is non-negative.

    Example 1:

    Input: [1,2,3,4,5,6,7] and k = 3
    Output: [5,6,7,1,2,3,4]
    Explanation:
    rotate 1 steps to the right: [7,1,2,3,4,5,6]
    rotate 2 steps to the right: [6,7,1,2,3,4,5]
    rotate 3 steps to the right: [5,6,7,1,2,3,4]
    Example 2:

    Input: [-1,-100,3,99] and k = 2
    Output: [3,99,-1,-100]
    Explanation: 
    rotate 1 steps to the right: [99,-1,-100,3]
    rotate 2 steps to the right: [3,99,-1,-100]
    Note:

    Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
    Could you do it in-place with O(1) extra space?
 * 
 **/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    // O(n*k) Time Limit Exceeded
    void rotate(vector<int>& nums, int k) {
        k %= nums.size();
        for (int i = 0; i < k; ++i) {
            int pre = nums[nums.size() - 1];
            for (int j = 0; j < nums.size(); ++j) {
                int tmp = nums[j];
                nums[j] = pre;
                pre = tmp;
            }
        }

        show_result(nums);
    }

    // Using extra space, O(n), O(n) space
    void rotate_2(vector<int>& nums, int k) {
        k %= nums.size();
        vector<int> a (nums.size());
        for (int i = 0; i < nums.size(); ++i) {
            a[(i + k) % nums.size()] = nums[i];
        }
        for (int i = 0; i < nums.size(); ++i) {
            nums[i] = a[i];
        }

        show_result(nums);
    }

    // O(n), O(1) space
    void rotate_3(vector<int>& nums, int k) {
        k %= nums.size();
        int cnt = 0;
        for (int start = 0; cnt < nums.size(); ++start) {
            int cur = start;
            int prev = nums[start];
            do {
                int next = (cur + k) % nums.size();
                int tmp = nums[next];
                nums[next] = prev;
                prev = tmp;
                cur = next;
                cnt++;
            } while (start != cur);
        }
        
        show_result(nums);
    }

    // Using reverse, O(n), O(1)
    void rotate_4(vector<int>& nums, int k) {
        k %= nums.size();
        reverse(nums, 0, nums.size() - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.size() - 1);

        show_result(nums);
    }

    void reverse(vector<int>& nums, int l, int r) {
        while (l < r) {
            swap(nums[l], nums[r]);
            ++l;
            --r;
        }
    }

    void show_result(vector<int>& numbers) {
        for (auto item : numbers) {
            cout << item << " ";
        }
        cout << endl;
    }
};

int main() {
    vector<int> numbers {1, 2, 3, 4, 5, 6, 7};
    int k = 3;
    Solution s;
    // s.rotate(numbers, k);
    // s.rotate_2(numbers, k);
    // s.rotate_3(numbers, k);
    s.rotate_4(numbers, k);
    return 0;
}
