class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[j] represents the minimum cost to reach step j
        int[] dp = new int[n + 1];
        
        // Base case: starting at step 0 with cost 0
        dp[0] = 0;
        
        for (int j = 1; j <= n; j++) {
            long minCost = Long.MAX_VALUE;
            // Check previous valid steps: j - 1, j - 2, j - 3
            for (int i = Math.max(0, j - 3); i < j; i++) {
                long jumpCost = (long)dp[i] + costs[j - 1] + (long)(j - i) * (j - i);
                minCost = Math.min(minCost, jumpCost);
            }
            dp[j] = (int)minCost;
        }
        
        return dp[n];
    }
}