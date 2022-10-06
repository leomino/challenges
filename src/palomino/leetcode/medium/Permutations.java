package palomino.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class Permutations {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); //<needed, i>
        for(int i = 0; i<nums.length; i++) {
            if(map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target-nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        ListNode n = new ListNode(1, new ListNode(2, new ListNode(3)));

        while(n.next != null) {
            System.out.println(n);
            n = n.next;
        }
        System.out.println(n);

    }
}
