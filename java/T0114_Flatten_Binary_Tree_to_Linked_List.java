package leetcode;

import java.util.LinkedList;

public class T0114_Flatten_Binary_Tree_to_Linked_List {

    /**
     * 114. 二叉树展开为链表
     * 给定一个二叉树，原地将它展开为一个单链表。
     *
     * 例如，给定二叉树
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     *
     * 将其展开为：
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     */

    public void flatten(TreeNode root) {
        if (root == null || root.left ==null && root.right == null) return;
        LinkedList<TreeNode> s = new LinkedList<>();
        s.push(root);
        TreeNode dummy = new TreeNode(0);
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            dummy.right = node;
            dummy = node;
            if (node.right != null) {
                s.push(node.right);
            }
            if (node.left != null) {
                s.push(node.left);
            }
            node.left = null;
        }
    }

    public static void main(String[] args) {

    }
}
