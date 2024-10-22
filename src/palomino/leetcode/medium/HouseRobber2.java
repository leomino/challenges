package palomino.leetcode.medium;

public class HouseRobber2 {
    public static int rob(int[] nums) {
        byte n = (byte) nums.length;
        return Math.max(robHouses(nums, 0, n - 1), robHouses(nums, 1, n));
    }

    private static int robHouses(int[] arr, int start, int end) {
        if (arr.length == 1) {
            return arr[0];
        }

        if (arr.length == 2) {
            return Math.max(arr[0], arr[1]);
        }

        int prev1 = 0;
        int prev2 = 0;
        for (int i = start; i < end; i++) {
            int curr = Math.max(prev1, prev2 + arr[i]);
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[] {1, 3, 1, 3, 100}));
    }
}
