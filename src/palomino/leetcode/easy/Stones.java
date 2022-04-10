package palomino.leetcode.easy;

import java.util.PriorityQueue;

public class Stones {
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b-a);
        for(int i : stones) {
            pq.offer(i);
        }
        while(pq.size() > 1) {
            int rest = Math.abs(pq.poll()-pq.poll());
            if(rest == 0) continue;
            pq.offer(rest);
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }

    public static void main(String[] args) {
        System.out.println(lastStoneWeight(new int[]{2, 2}));
    }
}
