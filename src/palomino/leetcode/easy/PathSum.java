package palomino.leetcode.easy;

import palomino.data.TreeNode;

public class PathSum {
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return helper(root, targetSum);
    }

    private static boolean helper(TreeNode node, int target) {
        if (node == null) {
            return false;
        }

        if (node.left == null && node.right == null) {
            return target - node.val == 0;
        }

        boolean left = helper(node.left, target - node.val);
        boolean right = helper(node.right, target - node.val);

        return left || right;
    }
}
