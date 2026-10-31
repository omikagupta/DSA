class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // dp[i][j][0]: paths entering (i,j) from the left
        // dp[i][j][1]: paths entering (i,j) from above
        long[][][] dp = new long[m][n][2];

        // First moves from the starting cell
        dp[0][1][0] = 1;
        dp[1][0][1] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Process paths entering from the left
                long fromLeft = dp[i][j][0];

                if (fromLeft > 0) {
                    if (grid[i][j] == 0) {
                        // Empty cell: can move right or down
                        if (j + 1 < n) {
                            dp[i][j + 1][0] =
                                (dp[i][j + 1][0] + fromLeft) % MOD;
                        }

                        if (i + 1 < m) {
                            dp[i + 1][j][1] =
                                (dp[i + 1][j][1] + fromLeft) % MOD;
                        }
                    } else {
                        // Mirror: right -> down
                        if (i + 1 < m) {
                            dp[i + 1][j][1] =
                                (dp[i + 1][j][1] + fromLeft) % MOD;
                        }
                    }
                }

                // Process paths entering from above
                long fromAbove = dp[i][j][1];

                if (fromAbove > 0) {
                    if (grid[i][j] == 0) {
                        // Empty cell: can move right or down
                        if (j + 1 < n) {
                            dp[i][j + 1][0] =
                                (dp[i][j + 1][0] + fromAbove) % MOD;
                        }

                        if (i + 1 < m) {
                            dp[i + 1][j][1] =
                                (dp[i + 1][j][1] + fromAbove) % MOD;
                        }
                    } else {
                        // Mirror: down -> right
                        if (j + 1 < n) {
                            dp[i][j + 1][0] =
                                (dp[i][j + 1][0] + fromAbove) % MOD;
                        }
                    }
                }
            }
        }

        return (int) (
            (dp[m - 1][n - 1][0] +
             dp[m - 1][n - 1][1]) % MOD
        );
    }
}