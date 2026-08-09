class Solution {

    int n;
    int[] suffix;
    int[][] dp;

    int solve(int i, int M) {

        if (i >= n)
            return 0;

        // Can take all remaining piles
        if (i + 2 * M >= n)
            return suffix[i];

        if (dp[i][M] != -1)
            return dp[i][M];

        int ans = 0;

        // Try taking X piles
        for (int X = 1; X <= 2 * M; X++) {

            // Opponent's best result
            int opponent = solve(i + X, Math.max(M, X));

            // Current player gets everything
            // except what opponent can get
            int current = suffix[i] - opponent;

            ans = Math.max(ans, current);
        }

        return dp[i][M] = ans;
    }

    public int stoneGameII(int[] piles) {

        n = piles.length;

        suffix = new int[n + 1];

        // suffix[i] = sum of piles from i to end
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        dp = new int[n][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(0, 1);
    }
}