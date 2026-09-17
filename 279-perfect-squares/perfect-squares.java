import java.util.Arrays;

class Solution {

    public int numSquares(int n) {

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return helper(n, dp);
    }

    public int helper(int n, int[] dp) {

     
        if (n == 0) {
            return 0;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        int ans = 1000000;

        for (int j = 1; j * j <= n; j++) {

            int sq = j * j;

            int take = 1 + helper(n - sq, dp);

            ans = Math.min(ans, take);
        }

        return dp[n] = ans;
    }
}