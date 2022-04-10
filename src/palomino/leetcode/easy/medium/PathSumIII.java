package palomino.leetcode.easy.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PathSumIII {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static void trav(TreeNode n, int target, int current, List<Integer> q, int[] res) {
        if(n == null) return;
        q.add(n.val);
        current+=n.val;

        int curr = current;
        int i = 0;
        while(q.size() > 1 && curr != target && i < q.size()) {
            curr-=q.get(i++);
            if(curr == target) {
                break;
            }
        }

        if(curr == target) {
            res[0]++;
        }

        trav(n.left, target, current, new ArrayList<>(q), res);
        trav(n.right, target, current, new ArrayList<>(q), res);
    }

    public static int pathSum(TreeNode root, int targetSum) {
        int[] res = new int[1];

        trav(root, targetSum, 0, new LinkedList<>(), res);

        return res[0];
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(-2), new TreeNode(-3));

        System.out.println(pathSum(root, -2));
    }
}
