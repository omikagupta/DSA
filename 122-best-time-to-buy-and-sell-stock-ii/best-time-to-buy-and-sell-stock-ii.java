class Solution {

    public int maxProfit(int[] prices) {

        int n = prices.length;

        int[][] dp = new int[n][2];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helper(prices, dp, 0, 0);
    }

    public int helper(int[] prices, int[][] dp, int i, int state) {

        if (i == prices.length) {
            return 0;
        }

        if (dp[i][state] != -1) {
            return dp[i][state];
        }

        if (state == 0) {
            return canbuy(prices, dp, i);
        }

        if (state == 1) {

            int sell = prices[i] + helper(prices, dp, i + 1, 0);

            int skip = helper(prices, dp, i + 1, 1);

            return dp[i][state] = Math.max(sell, skip);
        }

        return 0;
    }

    public int canbuy(int[] prices, int[][] dp, int i) {

        int buy = -prices[i] + helper(prices, dp, i + 1, 1);

        int skip = helper(prices, dp, i + 1, 0);

        return dp[i][0] = Math.max(buy, skip);
    }
}