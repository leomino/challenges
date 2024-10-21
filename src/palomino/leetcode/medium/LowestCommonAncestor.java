package palomino.leetcode.medium;

import palomino.data.TreeNode;

import java.util.*;

public class LowestCommonAncestor {
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        map.put(root.val, new LinkedList<>(List.of(root)));

        dfs(root.left, root);
        dfs(root.right, root);

        Queue<TreeNode> psAncestors = map.get(p.val);
        Queue<TreeNode> qsAncestors = map.get(q.val);

        TreeNode result = null;
        while (!psAncestors.isEmpty() || !qsAncestors.isEmpty()) {
            TreeNode pAncestor = psAncestors.poll();
            TreeNode qAncestor = qsAncestors.poll();
            if (pAncestor != null && qAncestor != null && pAncestor.val == qAncestor.val) {
                result = pAncestor;
            } else {
                break;
            }
        }

        return result;
    }

    static Map<Integer, Queue<TreeNode>> map = new HashMap<>();

    private static void dfs(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }

        Queue<TreeNode> ancestors = new LinkedList<>(map.get(parent.val));
        ancestors.add(node);
        map.put(node.val, ancestors);

        dfs(node.left, node);
        dfs(node.right, node);
    }

    public static void main(String[] args) {
        System.out.println(lowestCommonAncestor(new TreeNode(2, new TreeNode(1), null), new TreeNode(2), new TreeNode(1)));
    }
}
