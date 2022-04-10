package palomino.leetcode.easy.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] num) {
//        Map<Integer, Integer> m = new HashMap<>();
//        Map<Integer, Set<Integer>> added = new HashMap<>();
//
//        for(int i = 0; i<nums.length; i++) {
//            m.put(nums[i], i);
//        }
//
//        List<List<Integer>> res = new ArrayList<>();
//        for(int i = 0; i<nums.length; i++) {
//            for(int j = 0; j<nums.length; j++) {
//                if(i == j) continue;
//                int sought = (nums[i]+nums[j])*-1;
//
//                if(m.containsKey(sought) && m.get(sought) != i && m.get(sought) != j) {
//                    PriorityQueue<Integer> subjects = new PriorityQueue<>();
//                    subjects.offer(nums[i]);
//                    subjects.offer(nums[j]);
//                    subjects.offer(sought);
//
//                    int key = subjects.poll();
//                    if(added.containsKey(key) && added.get(key).contains(subjects.peek())) continue;
//
//                    res.add(Arrays.asList(nums[i], nums[j], sought));
//
//                    if(added.containsKey(key)) {
//                        added.get(key).addAll(Arrays.asList(subjects.poll(), subjects.poll()));
//                    } else {
//                        added.put(key, new HashSet<>(Arrays.asList(subjects.poll(), subjects.poll())));
//                    }
//                }
//            }
//        }
//
//        return res;
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length-2; i++) {
            if (i == 0 || num[i] != num[i-1]) {
                int lo = i+1;
                int hi = num.length-1;
                int sum = -num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo+1]) lo++;
                        while (lo < hi && num[hi] == num[hi-1]) hi--;
                        lo++; hi--;
                    } else if (num[lo] + num[hi] < sum) {
                        // improve: skip duplicates
                        while (lo < hi && num[lo] == num[lo+1]) lo++;
                        lo++;
                    } else {
                        // improve: skip duplicates
                        while (lo < hi && num[hi] == num[hi-1]) hi--;
                        hi--;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
}
