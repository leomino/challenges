package palomino.leetcode.medium;

public class MatrixSearch {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int col = matrix[0].length - 1;
        int row = 0;

        while (row < matrix.length && col >= 0) {
            if (target == matrix[row][col]) {
                return true;
            }

            if (target < matrix[row][col]) {
                col--;
            }

            row++;
        }
        return false;
    }
}
