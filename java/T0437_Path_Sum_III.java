package leetcode;

import java.util.HashMap;
import java.util.Map;

public class T0437_Path_Sum_III {

    /**
     * 437. 路径总和 III
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     * 找出路径和等于给定数值的路径总数。
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     *
     * 示例：
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *
     *       10
     *      /  \
     *     5   -3
     *    / \    \
     *   3   2   11
     *  / \   \
     * 3  -2   1
     *
     * 返回 3。和等于 8 的路径有:
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3.  -3 -> 11
     */

    public static int cnt;
    public static Map<Integer, Integer> map;
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        cnt = 0;
        map = new HashMap<>();
        map.put(0, 1);
        traverse(root, root.val, sum);
        return cnt;
    }

    private void traverse(TreeNode root, int curSum, int sum) {
        if (root == null) return;

        if (map.containsKey(curSum - sum)) {
            cnt += map.get(curSum - sum);
        }

        map.put(curSum, map.getOrDefault(curSum, 0) + 1);

        if (root.left != null) traverse(root.left, curSum + root.left.val, sum);
        if (root.right != null) traverse(root.right, curSum + root.right.val, sum);

        // 不要忘记取消
        map.put(curSum, map.get(curSum) - 1);
    }

    public static void main(String[] args) {

    }
}
