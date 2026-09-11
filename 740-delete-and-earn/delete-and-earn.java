class Solution {
    public int deleteAndEarn(int[] nums) {

        int max = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }

     
        int[] points = new int[max + 1];

        for (int num : nums) {
            points[num] += num;
        }

        int[] dp = new int[max + 1];

        dp[0] = 0;

        if (max >= 1) {
            dp[1] = points[1];
        }

        for (int i = 2; i <= max; i++) {

       
            int skip = dp[i - 1];

            int take = points[i] + dp[i - 2];

            dp[i] = Math.max(skip, take);
        }

        return dp[max];
    }
}