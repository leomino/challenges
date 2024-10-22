package palomino.leetcode.easy;

import java.util.Map;

public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int prev2 = 1;
        int prev1 = 2;

        int result = 0;
        for (int i = 2; i < n; i++) {
            result = prev1 + prev2;
            prev2 = prev1;
            prev1 = result;
        }
        return result;
    }

    private int dynamicProgramming(int n, Map<Integer, Integer> ways) {
        if (n <= 2) {
            return Math.max(0, n);
        }

        if (ways.containsKey(n)) {
            return ways.get(n);
        }

        int result = dynamicProgramming(n - 1, ways) + dynamicProgramming(n - 2, ways);
        ways.put(n, result);

        return ways.get(n);
    }
}
