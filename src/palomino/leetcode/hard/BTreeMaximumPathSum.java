package palomino.leetcode.hard;

import palomino.leetcode.data.TreeNode;

public class BTreeMaximumPathSum {
    static Integer longestPath = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        helper(root, 0);
        return longestPath;
    }

    private static int helper(TreeNode node, int parentValue) {
        if (node == null) {
            return 0;
        }

        int left = helper(node.left, node.val);
        int right = helper(node.right, node.val);

        int maxPathSum = Math.max(node.val, node.val + Math.max(left, right));
        longestPath = Math.max(longestPath, Math.max(maxPathSum, node.val + left + right));

        return maxPathSum;
    }

    public static void main(String[] args) {
        System.out.println(maxPathSum(new TreeNode(2, new TreeNode(-1), new TreeNode(-2))));
    }
}
