package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class T0145_Binary_Tree_Postorder_Traversal {

    /**
     * 给定一个二叉树，返回它的 后序 遍历。
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     */

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) return ans;

        TreeNode pre = null, node = null;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            node = stack.peek();
            if (node.left == null && node.right == null
                    || pre != null && (pre == node.right ||
                    pre == node.left && node.right == null)) {
                stack.pop();
                ans.add(node.val);
                pre = node;
            } else {
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
        }

        return ans;
    }

}
