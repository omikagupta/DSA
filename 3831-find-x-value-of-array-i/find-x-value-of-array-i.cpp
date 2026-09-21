#include <vector>

class Solution {
public:
    std::vector<long long> resultArray(std::vector<int>& nums, int k) {
        std::vector<long long> result(k, 0);
        std::vector<long long> dp(k, 0); // dp[r] stores count of subarrays ending at current index with remainder r

        for (int num : nums) {
            std::vector<long long> nextDp(k, 0);
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
            
            dp = std::move(nextDp); // Transition to next state
        }

        return result;
    }
};