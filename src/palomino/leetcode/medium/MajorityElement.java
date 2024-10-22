package palomino.leetcode.medium;

import java.util.*;

public class MajorityElement {
    public static List<Integer> majorityElement(int[] nums) {
        int threshold = nums.length / 3;

        Set<Integer> result = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }

            if (map.get(num) > threshold) {
                result.add(num);
            }
        }

        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[] {1, 2}));
    }
}
