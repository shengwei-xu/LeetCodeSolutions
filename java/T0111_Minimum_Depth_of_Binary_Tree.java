package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class T0111_Minimum_Depth_of_Binary_Tree {

    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例:
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最小深度  2.
     *
     * 样例2
     * [1,2] ==> 2
     */

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int curLayerNodes = 1, minLayer = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            -- curLayerNodes;

            if (node.left == null && node.right == null) break;
            if (node.left != null) { queue.offer(node.left); }
            if (node.right != null) { queue.offer(node.right); }

            if (curLayerNodes == 0) {
                curLayerNodes = queue.size();
                ++ minLayer;
            }
        }
        return minLayer;
    }

    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left != 0 && right != 0) ? 1 + Math.min(left, right) : 1 + left + right;
    }

}
