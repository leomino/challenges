package palomino.leetcode.medium;
import palomino.leetcode.data.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PathSum2 {
    List<List<Integer>> paths = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        helper(root, targetSum, new ArrayList<>());
        return paths;
    }

    private void helper(TreeNode node, int target, List<Integer> path) {
        if (node == null) {
            return;
        }

        path.add(node.val);

        if (node.left == null && node.right == null && target - node.val == 0) {
            paths.add(path);
        }

        helper(node.left, target - node.val, new ArrayList<>(path));
        helper(node.right, target - node.val, new ArrayList<>(path));
    }
}
