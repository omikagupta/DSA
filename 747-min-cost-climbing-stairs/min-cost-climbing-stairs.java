class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

    //     return Math.min(
    //         helper(cost, n - 1),
    //         helper(cost, n - 2)
    //     );
    // }

    // public int helper(int[] cost, int i) {
    //     if (i == 0) return cost[0];
    //     if (i == 1) return cost[1];

    //     return cost[i] + Math.min(
    //         helper(cost, i - 1),
    //         helper(cost, i - 2)
    //     );
   

        int[] dp = new int[n];
        Arrays.fill(dp, -1);

    //     return Math.min(
    //         helper(cost, n - 1, dp),
    //         helper(cost, n - 2, dp)
    //     );
    // }

    // int helper(int[] cost, int i, int[] dp) {
    //     if (i == 0) return cost[0];
    //     if (i == 1) return cost[1];

    //     if (dp[i] != -1) {
    //         return dp[i];
    //     }

    //     dp[i] = cost[i] + Math.min(
    //         helper(cost, i - 1, dp),
    //         helper(cost, i - 2, dp)
    //     );

    //     return dp[i];
    dp[0]=cost[0];
    dp[1]=cost[1];
    for(int i=2;i<n;i++){
dp[i]=cost[i]+Math.min(
    dp[i-1],dp[i-2]
);

    }
    return Math.min(dp[n - 1], dp[n - 2]);
    

    }
}