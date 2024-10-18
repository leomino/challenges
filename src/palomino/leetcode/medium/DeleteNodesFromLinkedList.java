package palomino.leetcode.medium;

import palomino.leetcode.data.ListNode;
import java.util.*;
import java.util.stream.Collectors;

public class DeleteNodesFromLinkedList {
    public static ListNode modifiedList(int[] nums, ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode result = null;
        HashSet<Integer> numSet = new HashSet<>();

        for (int num : nums) {
            numSet.add(num);
        }

        while (curr.next != null) {
            if (!numSet.contains(curr.val)) {
                if (prev != null) {
                    prev.next = curr;
                    prev = curr;
                } else {
                    prev = curr;
                    result = curr;
                }
            }
            curr = curr.next;
        }
        if (!numSet.contains(curr.val)) {
            if (prev != null) {
                prev.next = curr;
            } else {
                result = curr;
            }
        } else {
            prev.next = null;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(modifiedList(new int[] {9, 2, 5}, new ListNode(2, new ListNode(10, new ListNode(9)))));
    }
}
