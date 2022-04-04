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
package palomino.codejam.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Traverses a tree while merging some nodes and adding all max values of each part together forming the max value overall.
 * @since 2022-04-01
 * @see <a href="https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a45ef7">Chain Reactions</a>
 * @author Leonardo Palomino
 */
public class D {
    private static class Node {
        int val;
        List<Node> children = new ArrayList<>();
        public Node(int val) {
            this.val = val;
        }
    }

    private static int resolveNode(Node node, long[] sum) {
        PriorityQueue<Integer> children = new PriorityQueue<>();
        for (Node n : node.children) {
            children.offer(resolveNode(n, sum));
        }
        int mergeWith = children.peek() != null ? children.poll() : 0;
        while(!children.isEmpty()) {
            sum[0]+=children.poll();
        }
        return Math.max(node.val, mergeWith);
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();

            HashMap<Integer, Node> nodes = new HashMap<>();
            for (int j = 1; j <= N; j++) {
                nodes.put(j, new Node(in.nextInt()));
            }

            Queue<Node> toResolve = new LinkedList<>();
            for (int j = 1; j <= N; j++) {
                int next = in.nextInt();
                if(next == 0) {
                    toResolve.offer(nodes.get(j));
                    continue;
                }
                nodes.get(next).children.add(nodes.get(j));
            }

            long[] sum = new long[]{0};
            while(!toResolve.isEmpty()) {
                int head = resolveNode(toResolve.poll(), sum);
                sum[0]+=head;
            }

            System.out.println("Case #" + i + ": " + sum[0]);
        }
    }
}
