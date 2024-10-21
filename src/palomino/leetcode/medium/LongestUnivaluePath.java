package palomino.leetcode.medium;
import palomino.data.TreeNode;

public class LongestUnivaluePath {
    Integer longestPath = 0;

    public int longestUnivaluePath(TreeNode root) {
        helper(root, null);
        return longestPath;
    }

    private int helper(TreeNode node, Integer parentValue) {
        int path = 0;

        if (node == null) {
            return path;
        }

        if (parentValue != null && node.val == parentValue) {
            path++;
        }

        if (node.left == null && node.right == null) {
            return path;
        }

        int left = helper(node.left, node.val);
        int right = helper(node.right, node.val);

        longestPath = Math.max(longestPath, left + right);

        return parentValue != null && parentValue == node.val ? Math.max(left, right) + 1 : 0;
    }
}
