package palomino.leetcode.medium;

public class MinimumPathSum {
    int min = Integer.MAX_VALUE;
    public static int recursion(int[][] mat, int i, int j, int m, int n, int[][] grid){
        if(mat[i][j] > 0){
            return mat[i][j];
        }

        mat[i][j] = grid[i][j];
        if(i < m-1 && j < n-1){
            mat[i][j] += Math.min(recursion(mat, i+1, j, m, n, grid), recursion(mat, i, j+1, m, n, grid));
        }
        else if(i < m-1){
            mat[i][j] += recursion(mat, i+1, j, m, n, grid);
        }
        else if(j < n-1){
            mat[i][j] += recursion(mat, i, j+1, m, n, grid);
        }

        return mat[i][j];
    }
    public static int minPathSum(int[][] grid) {
        int[][] mat = new int[grid.length][grid[0].length];
        return recursion(mat, 0, 0, grid.length, grid[0].length, grid);
    }

    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        }));
    }

}
