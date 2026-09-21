class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k]; // dp[r] stores count of subarrays ending at current index with remainder r

        for (int num : nums) {
            long[] nextDp = new long[k];
            int currentRem = num % k;
            
            // Single element subarray [num]
            nextDp[currentRem]++;
            
            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (r * currentRem) % k;
                    nextDp[newRem] += dp[r];
                }
            }
            
            // Add current subarrays to total results
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }
            
            dp = nextDp; // Transition to next state
        }

        return result;
    }
}