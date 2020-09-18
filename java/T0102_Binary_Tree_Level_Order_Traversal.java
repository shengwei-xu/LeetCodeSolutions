package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class T0102_Binary_Tree_Level_Order_Traversal {

    /**
     * 102. 二叉树的层序遍历
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * <p>
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * 返回其层次遍历结果：
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     */


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) return ans;
        LinkedList<TreeNode> q = new LinkedList<>();
        List<Integer> one = new ArrayList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int nodes = q.size();
            while (nodes -- > 0) {
                TreeNode node = q.poll();
                one.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            ans.add(one);
            one = new ArrayList<>();
        }
        return ans;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) return ans;
        LinkedList<TreeNode> q = new LinkedList<>();
        List<Integer> one = new ArrayList<>();
        q.offer(root);
        int nodes = 1;
        int nextNodes = 0;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            one.add(node.val);
            --nodes;

            if (node.left != null) {
                q.offer(node.left);
                ++nextNodes;
            }

            if (node.right != null) {
                q.offer(node.right);
                ++nextNodes;
            }

            if (nodes == 0) {
                ans.add(one);
                one = new ArrayList<>();
                nodes = nextNodes;
                nextNodes = 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
