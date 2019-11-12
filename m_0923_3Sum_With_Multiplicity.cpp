/**
 * 923. 3Sum With Multiplicity
    Medium

    Given an integer array A, and an integer target, return the number of tuples i, j, k  
    such that i < j < k and A[i] + A[j] + A[k] == target.
    As the answer can be very large, return it modulo 10^9 + 7.

    Example 1:

    Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
    Output: 20
    Explanation: 
    Enumerating by the values (A[i], A[j], A[k]):
    (1, 2, 5) occurs 8 times;
    (1, 3, 4) occurs 8 times;
    (2, 2, 4) occurs 2 times;
    (2, 3, 3) occurs 2 times.

    Example 2:

    Input: A = [1,1,2,2,2,2], target = 5
    Output: 12
    Explanation: 
    A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
    We choose one 1 from [1,1] in 2 ways,
    and two 2s from [2,2,2,2] in 6 ways.
    
    Note:

    3 <= A.length <= 3000
    0 <= A[i] <= 100
    0 <= target <= 300
 * */

#include <iostream>
#include <cmath>
#include <vector>
using namespace std;

class Solution {
public:
    int threeSumMulti(vector<int>& A, int target) {
        sort(A.begin(), A.end());
        int cnt = 0;
        const int max_value = pow(10, 9) + 7;
        for (int k = 0; k < A.size() - 2; ++k) { // anchor one number
            for (int i = k + 1, j = A.size() - 1; i < j;) { // two pointers to search
                if (A[k] + A[i] + A[j] < target) {
                    ++i;
                } else if (A[k] + A[i] + A[j] > target) {
                    --j;
                } else if (A[k] + A[i] + A[j] == target) {
                    if (A[i] != A[j]) { 
                        int left = 1, right = 1;
                        while (A[i] == A[i + 1]) { // count same elements
                            ++i;
                            ++left;
                        }
                        ++i; // move to next different element
                        while (A[j] == A[j - 1]) { // count same elements
                            --j;
                            ++right;
                        }
                        --j; // move to next different element

                        cnt += left * right;
                        cnt %= max_value;
                    } else if (A[i] == A[j]) {
                        int n = j - i + 1;
                        // compute choose 2 from n, C_n^2
                        // n * (n + 1) / 2
                        cnt += (n * (n - 1)) >> 1;
                        cnt %= max_value;
                        break;
                    }
                }
            }
        }
        return cnt;
    }

    int threeSumMulti_2(vector<int>& A, int target) {
        const int max_value = pow(10, 9) + 7; // 10^9 + 7
        long long cnt[101] = {0};
        long long ans = 0;
        // count number of each element
        for (int a : A) {
            cnt[a] += 1;
        }

        // x != y != z
        for (int x = 0; x <= 100; ++x) {
            for (int y = x + 1; y <= 100; ++y) {
                int z = target - x - y;
                if (z <= 100 && y < z) {
                    ans += cnt[x] * cnt[y] * cnt[z];
                    ans %= max_value;
                }
            }
        }
        // x == y != z
        for (int x = 0; x <= 100; ++x) {
            int z = target - 2 * x;
            if (z <= 100 && x < z) {
                // Note: ```>>``` operator priority is lower than ```* /``` operator.
                ans += (cnt[x] * (cnt[x] - 1) >> 1) * cnt[z];
                ans %= max_value;
            }
        }
        // x != y == z
        for (int x = 0; x <= 100; ++x) {
            if ((target & 1) == (x & 1)) {
                int y = (target - x) >> 1;
                if (x < y && y <= 100) {
                    ans += cnt[x] * cnt[y] * (cnt[y] - 1) >> 1;
                    ans %= max_value;
                }
            }
        }
        // x == y == z
        if (target % 3 == 0) {
            int x = target / 3;
            if (0 <= x && x <= 100) {
                ans += cnt[x] * (cnt[x] - 1) * (cnt[x] - 2) / 6;
                ans %= max_value;
            }
        }

        return (int)ans;
    }
};


int main() {
    Solution s;
    
    // vector<int> A {1,1,2,2,3,3,4,4,5,5};
    // int target = 8;

    vector<int> A {1,1,2,2,2,2};
    int target = 5;

    cout << s.threeSumMulti(A, target) << endl;
    cout << s.threeSumMulti_2(A, target) << endl;
    return 0;
}

