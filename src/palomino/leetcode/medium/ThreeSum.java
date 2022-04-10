package palomino.leetcode.medium;

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
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length-2; i++) {
            if (i == 0 || num[i] != num[i-1]) {
                int begin = i+1;
                int end = num.length-1;
                int sum = -num[i];
                while (begin < end) {
                    if (num[begin] + num[end] == sum) {
                        res.add(Arrays.asList(num[i], num[begin], num[end]));
                        while (begin < end && num[begin] == num[begin+1]) begin++;
                        while (begin < end && num[end] == num[end-1]) end--;
                        begin++; end--;
                    } else if (num[begin] + num[end] < sum) {
                        while (begin < end && num[begin] == num[begin+1]) begin++;
                        begin++;
                    } else {
                        while (begin < end && num[end] == num[end-1]) end--;
                        end--;
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
