package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class T0094_Binary_Tree_Inorder_Traversal {

    /**
     * 给定一个二叉树，返回它的中序 遍历。
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     */

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) return ans;

        TreeNode node = root;
        Deque<TreeNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode popped = stack.pop();
                ans.add(popped.val);
                if (popped.right != null) {
                    node = popped.right;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
