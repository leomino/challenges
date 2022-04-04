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
