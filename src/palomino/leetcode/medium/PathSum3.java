package palomino.leetcode.medium;

import palomino.leetcode.data.TreeNode;

import java.util.*;

public class PathSum3 {
    static Integer result = 0;

    public static int pathSum(TreeNode root, int targetSum) {
        helper(root, targetSum, new HashMap<>());
        return result;
    }

    private static void helper(TreeNode node, int target, Map<Long, Integer> search) {
        if (node == null) {
            return;
        }

        if (search.containsKey((long) node.val)) {
            result += search.get((long) node.val);
        }

        if (target - node.val == 0) {
            result++;
        }

        Map<Long, Integer> map = new HashMap<>();

        for (Map.Entry<Long, Integer> entry : search.entrySet()) {
            long newKey = entry.getKey() - node.val;
            map.put(newKey, entry.getValue());
        }

        int increment = 1;
        if (map.containsKey((long) target - node.val)) {
            increment += map.get((long) target - node.val);
        }
        map.put((long) (target - node.val), increment);

        helper(node.left, target, new HashMap<>(map));
        helper(node.right, target, new HashMap<>(map));
    }

    public static void main(String[] args) {
        System.out.println(pathSum(new TreeNode(1000000000, new TreeNode(1000000000, new TreeNode(294967296, new TreeNode(1000000000, new TreeNode(1000000000, new TreeNode(1000000000, null, null), null), null), null), null), null), 0));
    }
}

