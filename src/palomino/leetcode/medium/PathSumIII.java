/*
 * MIT License
 *
 * Copyright (c) 2022 Leonardo Palomino
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package palomino.leetcode.medium;

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

    private static int evaluate(TreeNode n, int target, List<Integer> path, int sum) {
        if(n == null) return 0;
        int res = 0;
        path.add(n.val);
        sum+=n.val;

        int curr = sum;
        int p_size = path.size();
        for (int i = 0; i < path.size(); i++) {
            if(curr == target || p_size-- == 1) break;
            curr-=path.get(i);
        }

        if(curr == target) ++res;

        return res+evaluate(n.left, target, new ArrayList<>(path), sum)+evaluate(n.right, target, new ArrayList<>(path), sum);
    }

    public static int pathSum(TreeNode root, int targetSum) {
        return evaluate(root, targetSum, new LinkedList<>(), 0);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0, new TreeNode(1), new TreeNode(1));

        System.out.println(pathSum(root, 1));
    }

}
