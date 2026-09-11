class Solution {
    public boolean canPartition(int[] nums) {

        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;

        Boolean[][] dp = new Boolean[nums.length][target + 1];

        return helper(nums, 0, target, dp);
    }

    public boolean helper(int[] nums, int i, int target, Boolean[][] dp) {

     
        if (target == 0) {
            return true;
        }


        if (i == nums.length) {
            return false;
        }

        if (dp[i][target] != null) {
            return dp[i][target];
        }

   
        boolean skip = helper(nums, i + 1, target, dp);

     
        boolean take = false;

        if (nums[i] <= target) {
            take = helper(nums, i + 1, target - nums[i], dp);
        }

        dp[i][target] = take || skip;

        return dp[i][target];
    }
}