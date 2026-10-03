class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        int INF = amount + 1;

        dp[0] = 0;

        for (int x = 1; x <= amount; x++) {
            dp[x] = INF;

            for (int coin : coins) {
                if (coin <= x) {
                    dp[x] = Math.min(dp[x], 1 + dp[x - coin]);
                }
            }
        }

        if (dp[amount] == INF) {
            return -1;
        }

        return dp[amount];
    }
}