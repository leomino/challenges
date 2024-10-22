package palomino.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        int[] sumCount = new int[target + 1];
        sumCount[0] = 1;
        for(int sum = 1; sum <= target; sum++){
            for (int num : nums) {
                if (num <= sum) {
                    sumCount[sum] = (sumCount[sum] + sumCount[sum - num]);
                }
            }
        }
        return sumCount[target];
    }

    public int majorityElement(int[] nums) {
        int treshold = nums.length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num + 1));
            } else {
                map.put(num, 1);
            }

            if (map.get(num) > treshold) {
                return num;
            }
        }
        return -1;
    }

    public int majorityElement2(int[] nums) {
        Integer el = null;
        int count = 1;

        for (int num : nums) {
            if (el == null) {
                el = num;
            } else if (count == 0) {
                el = num;
                count = 1;
            } else if (num == el) {
                count++;
            } else {
                count--;
            }
        }

        return el;
    }
}
