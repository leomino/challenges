package palomino.leetcode.easy.medium;

public class UniquePaths {
    public static int uniquePaths(int m, int n) {
        int[][] board = new int[m][n];
        board[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int top = i - 1 < 0 ? 0 : board[i - 1][j];
                int left = j - 1 < 0 ? 0 : board[i][j - 1];
                board[i][j] = top + left;
            }
        }

        return board[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
    }
}

