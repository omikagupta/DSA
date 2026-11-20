import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        
        // Since nums[i] <= 10^6, max modified value is 10^6 + 1.
        // dp[v] stores the max length of a consecutive sequence ending at value v.
        int maxVal = nums[nums.length - 1] + 2;
        int[] dp = new int[maxVal];
        int maxLen = 0;

        for (int num : nums) {
            // Update num + 1 first so we don't accidentally use the newly updated dp[num] 
            // to extend dp[num + 1] from the same element.
            dp[num + 1] = dp[num] + 1;
            dp[num] = dp[num - 1] + 1;
            
            maxLen = Math.max(maxLen, Math.max(dp[num], dp[num + 1]));
        }

        return maxLen;
    }
}