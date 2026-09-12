class Solution {

    public boolean canPartition(int[] nums) {

        int sum = 0;

        for(int i : nums) {
            sum += i;
        }

        if(sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;

        Boolean[][] dp = new Boolean[nums.length][target + 1];

        return helper(nums, dp, target, 0);
    }

    public boolean helper(int[] nums, Boolean[][] dp, int target, int i) {

      
        if(target == 0) {
            return true;
        }

      
        if(i == nums.length) {
            return false;
        }

        if(dp[i][target] != null) {
            return dp[i][target];
        }

        boolean take = false;

        if(nums[i] <= target) {
            take = helper(nums, dp, target - nums[i], i + 1);
        }

        boolean skip = helper(nums, dp, target, i + 1);

        dp[i][target] = take || skip;

        return dp[i][target];
    }
}