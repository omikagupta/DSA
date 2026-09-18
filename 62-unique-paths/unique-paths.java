class Solution {
    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];

        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = 1;
                }
                else if (j == 0) {
                    dp[i][j] = 1;
                }
                else {
                    dp[i][j] =
                        dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }








    //     for (int[] i : dp) {
    //         Arrays.fill(i, -1);
    //     }

    //     return helper(m, n, dp, m - 1, n - 1);
    // }

    // public int helper(int m, int n, int[][] dp, int i, int j) {

    //     if (i == 0 && j == 0) return 1;

    //     if (i < 0 || j < 0) return 0;

    //     if (dp[i][j] != -1) {
    //         return dp[i][j];
    //     }

    //     dp[i][j] =
    //         helper(m, n, dp, i - 1, j) +
    //         helper(m, n, dp, i, j - 1);

    //     return dp[i][j];
    }
