package leetcode;

public class T0105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {

    /**
     * 105. Construct Binary Tree from Preorder and Inorder Traversal
     * Medium
     * <p>
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     * <p>
     * Note:
     * You may assume that duplicates do not exist in the tree.
     * <p>
     * For example, given
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * Return the following binary tree:
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     */

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null
                || preorder.length != inorder.length || preorder.length <= 0) {
            return null;
        }
        return buildTreeCore(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildTreeCore(int[] preorder, int preLeft, int preRight,
                                          int[] inorder, int inLeft, int inRight) {
        TreeNode root = new TreeNode(preorder[preLeft]);
        root.left = root.right = null;
        int rootIdx = 0;
        for (int i = inLeft; i <= inRight; ++i) {
            if (inorder[i] == root.val) {
                rootIdx = i;
                break;
            }
        }

        int leftNodes = rootIdx - inLeft, rightNodes = inRight - rootIdx;
        if (leftNodes > 0) {
            root.left = buildTreeCore(preorder, preLeft + 1, preRight,
                    inorder, inLeft, rootIdx - 1);
        }
        if (rightNodes > 0) {
            root.right = buildTreeCore(preorder, preLeft + leftNodes + 1, preRight,
                    inorder, rootIdx + 1, inRight);
        }
        return root;
    }

}
