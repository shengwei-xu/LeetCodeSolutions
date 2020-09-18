package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class  T0113_Path_Sum_II {

    /**
     * 113. 路径总和 II
     * Medium
     *
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     * 返回:
     *
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     */

    private static List<List<Integer>> ans;
    private static List<TreeNode> path;

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        ans = new LinkedList<>();
        if (root == null) return ans;
        path = new LinkedList<>();
        core(root, sum, 0);
        return ans;
    }

    private static void core(TreeNode root, int target, int curSum) {
        path.add(root);
        curSum += root.val;
        if (root.left == null && root.right == null) {
            if (curSum == target) {
                List<Integer> tmp = new LinkedList<>();
                for (TreeNode node : path) {
                    tmp.add(node.val);
                }
                ans.add(tmp);
            }
            path.remove(path.size() - 1);
        } else {
            if (root.left != null) core(root.left, target, curSum);
            if (root.right != null) core(root.right, target, curSum);
            path.remove(path.size() - 1);
        }
    }

    private static void core2(TreeNode root, int target, int curSum) {
        if (root.left == null && root.right == null) {
            if (curSum + root.val == target) {
                List<Integer> tmp = new LinkedList<>();
                for (TreeNode node : path) {
                    tmp.add(node.val);
                }
                tmp.add(root.val);
                ans.add(tmp);
            }
        } else {
            path.add(root);
            if (root.left != null) core(root.left, target, curSum + root.val);
            if (root.right != null) core(root.right, target, curSum + root.val);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> FindPath(TreeNode root, int target) {
        List<List<Integer>> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }

        int curSum = 0;
        List<TreeNode> path = new ArrayList<>();
        return FindPathCore(paths, path, root, curSum, target);
    }

    private List<List<Integer>> FindPathCore(List<List<Integer>> paths,
                                                       List<TreeNode> path,
                                                       TreeNode root,
                                                       int curSum, int target) {
        if (root == null) {
            return paths;
        }

        path.add(root);
        curSum += root.val;

        boolean isLeaf = (root.left == null) && (root.right == null);
        if (target == curSum && isLeaf) {
            List<Integer> onePath = new ArrayList<>();
            for (TreeNode n : path) {
                onePath.add(n.val);
            }
            paths.add(onePath);
        }

        if (root.left != null) {
            paths = FindPathCore(paths, path, root.left, curSum, target);
        }

        if (root.right != null) {
            paths = FindPathCore(paths, path, root.right, curSum, target);
        }

        // remove root node out of path
        if (path.size() > 0) {
            path.remove(path.size() - 1);
        }

        return paths;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(5);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(3);

        pathSum(head, 6);
    }
}
