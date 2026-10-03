class Solution {

    int[][] dp;
    int[][] matrix;

    public int longestIncreasingPath(int[][] matrix) {

        this.matrix = matrix;

        int n = matrix.length;
        int m = matrix[0].length;

        dp = new int[n][m];

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                answer = Math.max(answer, dfs(i, j));
            }
        }

        return answer;
    }

    int dfs(int i, int j) {


        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int best = 1;


        if (i > 0 && matrix[i - 1][j] > matrix[i][j]) {
            best = Math.max(best, 1 + dfs(i - 1, j));
        }

        if (i + 1 < matrix.length &&
            matrix[i + 1][j] > matrix[i][j]) {

            best = Math.max(best, 1 + dfs(i + 1, j));
        }

        
        if (j > 0 && matrix[i][j - 1] > matrix[i][j]) {
            best = Math.max(best, 1 + dfs(i, j - 1));
        }

       
        if (j + 1 < matrix[0].length &&
            matrix[i][j + 1] > matrix[i][j]) {

            best = Math.max(best, 1 + dfs(i, j + 1));
        }

        dp[i][j] = best;

        return dp[i][j];
    }
}