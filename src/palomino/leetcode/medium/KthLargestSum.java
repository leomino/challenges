package palomino.leetcode.medium;

import palomino.data.TreeNode;

import java.util.*;

public class KthLargestSum {
    public static long kthLargestLevelSum(TreeNode root, int k) {
        return bfs(root, k);
    }

    public static long bfs(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }

        PriorityQueue<Long> levelSums = new PriorityQueue<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            long levelSum = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            levelSums.offer(levelSum);
        }

        if (k > levelSums.size()) {
            return -1;
        }

        return levelSums.peek() != null ? levelSums.poll() : -1;
    }

    public static void main(String[] args) {
        System.out.println(kthLargestLevelSum(
                new TreeNode(5, new TreeNode(8, new TreeNode(2), new TreeNode(1)), new TreeNode(9, new TreeNode(3), new TreeNode(7))), 4
        ));
    }

}
