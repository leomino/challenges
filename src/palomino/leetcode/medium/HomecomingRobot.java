package palomino.leetcode.medium;

public class HomecomingRobot {
    public static int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int startRow = startPos[0];
        int startCol = startPos[1];

        int targetRow = homePos[0];
        int targetCol = homePos[1];

        return getDif(rowCosts, startRow, targetRow) + getDif(colCosts, startCol, targetCol);
    }

    private static int getDif(int[] costs, int start, int target) {
        if(start == target) return 0;
        int result = 0;
        int dif = Math.max(start, target)-Math.min(start, target);

        if (start > target) {
            for(int i = 1; i <= dif; i++) {
                result+=costs[start-i];
            }
        } else {
            for(int i = 1; i <= dif; i++) {
                result+=costs[start+i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(minCost(
                new int []{6,3},
                new int []{3,3},
                new int []{6,3,4,4,10,2,14,21},
                new int []{7,2,3,3,15}));
    }
}
