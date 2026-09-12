class Solution {

    public int findTargetSumWays(int[] nums, int target) {

        int sum = 0;

        for (int x : nums) {
            sum += x;
        }

        int[][] dp = new int[nums.length][2 * sum + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helper(nums, target, 0, 0, dp, sum);
    }

    public int helper(int[] nums, int target, int currsum,
                      int i, int[][] dp, int offset) {

        if (i == nums.length) {
            return currsum == target ? 1 : 0;
        }

        if (dp[i][currsum + offset] != -1) {
            return dp[i][currsum + offset];
        }

        int plus = helper(
            nums, target, currsum + nums[i], i + 1, dp, offset
        );

        int minus = helper(
            nums, target, currsum - nums[i], i + 1, dp, offset
        );

        dp[i][currsum + offset] = plus + minus;

        return dp[i][currsum + offset];
    }
}