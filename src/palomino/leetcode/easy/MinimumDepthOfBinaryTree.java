package palomino.leetcode.easy;
import palomino.data.TreeNode;

public class MinimumDepthOfBinaryTree {

    public static int minDepth(TreeNode root) {
        return minHeight(root);
    }

    private static int minHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = minHeight(node.left);
        int right = minHeight(node.right);

        if (left == 0 || right == 0) {
            return Math.max(left, right) + 1;
        }

        return Math.min(left, right) + 1;
    }


    public static void main(String[] args) {
        System.out.println(minDepth(new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)))));
    }
}
