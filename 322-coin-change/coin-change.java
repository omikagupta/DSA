class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int ans = helper(coins, amount, dp, 0);

        return ans >= 1000000 ? -1 : ans;
    }

    public int helper(int[] coins, int amount, int[][] dp, int i) {

        if (amount == 0) return 0;

        if (i == coins.length) return 1000000;

        if (dp[i][amount] != -1) {
            return dp[i][amount];
        }

        int take = 1000000;

        if (coins[i] <= amount) {
            take = 1 + helper(coins, amount - coins[i], dp, i);
        }

        int skip = helper(coins, amount, dp, i + 1);

        return dp[i][amount] = Math.min(take, skip);
    }
}