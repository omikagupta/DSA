class Solution {
    public int numSquares(int n) {

        int INF = 1000000;

        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);

        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j * j <= i; j++) {

                int sq = j * j;

                dp[i] = Math.min(dp[i], 1 + dp[i - sq]);
            }
        }

        return dp[n];
    }
}