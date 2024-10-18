package palomino.leetcode.medium;

public class NumberOfIslands {
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }

        return res;
    }

    private static void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        if (i < 0 || i > m - 1 || j > n - 1 || j < 0) {
            return;
        }

        if (grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '0';
        dfs(grid,i - 1, j); // up
        dfs(grid,i + 1, j); // down
        dfs(grid,i, j + 1); // right
        dfs(grid,i, j - 1); // left
    }

    public static void main(String[] args) {
        System.out.println(numIslands(new char[][] {
                {'0','1','1'},
                {'0','1','0'},
                {'1','1','1'}
        }));
    }
}
