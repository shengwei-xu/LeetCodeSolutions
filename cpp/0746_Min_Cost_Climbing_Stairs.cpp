/*
746. Min Cost Climbing Stairs
Easy

On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. 
You need to find minimum cost to reach the top of the floor, 
and you can either start from the step with index 0, or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
Note:
1. cost will have a length in the range [2, 1000].
2. Every cost[i] will be an integer in the range [0, 999].
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        vector<int> dp (cost.size());
        // ```cost``` in the range [2, 1000], we donot need to consider costs in index 0 and 1
        dp[0] = cost[0]; // cost starting from index 0
        dp[1] = cost[1]; // cost starting from index 1 with skipping index 0
        for (int i = 2; i < dp.size(); ++i) {
            if (i == dp.size() - 1) { // the last dp element can get from dp[n-1] with skipping cost[n]
                dp[i] = min(dp[i - 1], dp[i - 2] + cost[i]);
            } else {
                dp[i] = min(dp[i - 1] + cost[i], dp[i - 2] + cost[i]);
            }
        }
        return dp[dp.size() - 1];
    }

    int minCostClimbingStairs_2(vector<int>& cost) {
        vector<int> dp (cost.size() + 1);
        dp[0] = 0; // cost reaching at index 0
        dp[1] = 0; // cost reaching at index 1
        // dp[2] = min(cost[0], cost[1]);
        for (int i = 2; i < dp.size(); ++i) {
            // Finally, we reach the last index that is behind of the last index of ```cost```
            dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[dp.size() - 1];
    }

    int minCostClimbingStairs_3(vector<int>& cost) {
        int f1 = 0, f2 = 0;
        for (int i = cost.size() - 1; i >= 0; --i) {
            int f0 = cost[i] + min(f1, f2);
            f2 = f1;
            f1 = f0;
        }
        return min(f1, f2);
    }
};

int main() {
    Solution* s = new Solution();

    // vector<int> cost {10, 15, 20};
    vector<int> cost {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
    cout << s->minCostClimbingStairs(cost) << endl;
    cout << s->minCostClimbingStairs_2(cost) << endl;
    cout << s->minCostClimbingStairs_3(cost) << endl;

    return 0;
}
