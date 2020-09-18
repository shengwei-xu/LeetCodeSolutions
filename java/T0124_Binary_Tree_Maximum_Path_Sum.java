package leetcode;

public class T0124_Binary_Tree_Maximum_Path_Sum {

    /**
     * 124. 二叉树中的最大路径和
     * 给定一个非空二叉树，返回其最大路径和。
     * 本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     *
     * 示例 1：
     * 输入：[1,2,3]
     *        1
     *       / \
     *      2   3
     * 输出：6
     *
     * 示例 2：
     * 输入：[-10,9,20,null,null,15,7]
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 输出：42
     */

    public static int ans;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        ans = Integer.MIN_VALUE;
        pathSum(root);
        return ans;
    }

    private int pathSum(TreeNode root) {
        if (root == null) return 0;

        int L = pathSum(root.left);
        if (L < 0) L = 0;
        int R = pathSum(root.right);
        if (R < 0) R = 0;
        ans = Math.max(ans, root.val + L + R);
        return Math.max(L, R) + root.val;
    }
}
