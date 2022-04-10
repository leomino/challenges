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

package palomino.leetcode.hard;

import java.util.Arrays;

public class MergeKSortedLists {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /*private static ListNode merge(ListNode a, ListNode b) {
        if(a == null || b == null) return a != null ? a : b;
        ListNode result = null;
        ListNode curr = new ListNode();
        while(a != null && b != null) {
            curr.next = new ListNode();
            curr = curr.next;
            if(a.val < b.val) {
                curr.val = a.val;
                a = a.next;
            } else {
                curr.val = b.val;
                b = b.next;
            }
            if(result == null) result = curr;
        }

        curr.next = a != null ? a : b;

        return result;
    }*/

    public static ListNode mergeKLists(ListNode[] lists) {
        /*Queue<ListNode> q = new LinkedList<>();
        for (ListNode list : lists) {
            q.offer(list);
        }

        ListNode result = null;
        while(!q.isEmpty()) {
            result = merge(result, q.poll());
        }

        return result;*/
        /*PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(ListNode curr : lists) {
            while(curr != null) {
                pq.offer(curr.val);
                curr = curr.next;
            }
        }

        ListNode curr = new ListNode();
        ListNode result = null;
        while(!pq.isEmpty()) {
            curr.next = new ListNode();
            curr = curr.next;
            curr.val = pq.poll();
            if(result == null) result = curr;
        }
        return result;*/
        int n = Arrays.stream(lists).mapToInt(listNode -> {
            int dep = 0;
            ListNode curr = listNode;
            while(curr != null) {
                ++dep;
                curr = curr.next;
            }
            return dep;
        }).sum();

        ListNode curr = new ListNode();
        ListNode result = null;
        for (int j = 0; j < n; j++) {
            curr.next = new ListNode();
            curr = curr.next;
            int next = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i<lists.length; i++) {
                if(lists[i] == null) continue;
                if(lists[i].val < next) {
                    next = lists[i].val;
                    index = i;
                }
            }
            curr.val = next;
            lists[index] = lists[index].next;
            if(result == null) result = curr;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[]{
                new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6))
        };

        System.out.println(mergeKLists(lists));
    }

}
