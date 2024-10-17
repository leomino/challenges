package palomino.leetcode.easy;

import palomino.leetcode.data.TreeNode;

/**
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * @author Leonardo Palomino
 * @see <a href="https://leetcode.com/problems/two-sum/submissions/1425222524/">LeetCode</a>
 * @since 2022-04-01
 */
public class ConvertSortedArrayToBalancedBinaryTree {
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return helper(nums, 0, nums.length -1);
    }

    private static TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid -1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    public static void main(String[] args) {
        System.out.println(sortedArrayToBST(new int[]{-10, -3, 0, 5, 9}));
    }
}
