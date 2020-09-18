package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class T0039_Combination_Sum {

    /**
     * 39. 组合总和
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的数字可以无限制重复被选取。
     *
     * 说明：
     *
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1：
     *
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 示例 2：
     *
     * 输入：candidates = [2,3,5], target = 8,
     * 所求解集为：
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     *
     * 提示：
     *
     * 1 <= candidates.length <= 30
     * 1 <= candidates[i] <= 200
     * candidate 中的每个元素都是独一无二的。
     * 1 <= target <= 500
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(ans, candidates, target, 0, new ArrayList<>());
        return ans;
    }

    private void backtrack(List<List<Integer>> ans, int[] candidates, int target, int idx, List<Integer> path) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
        } else {
            for (int i = idx; i < candidates.length; ++i) {
                if (target >= candidates[i]) {
                    path.add(candidates[i]);
                    backtrack(ans, candidates, target - candidates[i], i, path);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}
