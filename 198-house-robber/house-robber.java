class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        // int[] dp = new int[n];
        // Arrays.fill(dp, -1);

    //     return helper(nums, dp, n - 1);
    // }

    // public int helper(int[] nums, int[] dp, int i) {
//    if (i == 0) return nums[0];
//    if (i == 1) return Math.max(nums[0], nums[1]);

    //     if (dp[i] != -1) {
    //         return dp[i];
    //     }

    //     dp[i] = Math.max(
    //         helper(nums, dp, i - 1),
    //         helper(nums, dp, i - 2) + nums[i]
    //     );

    //     return dp[i];
//     if (nums.length == 1) return nums[0];
//     if (nums.length == 2) return Math.max(nums[0],nums[1]);
// dp[0]=nums[0];
// dp[1]=Math.max(nums[0],nums[1]);

//     for( int i=2;i<n;i++){
// dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
//     }
//     return dp[n-1];
//     }
 if (nums.length == 1) return nums[0];
  if (nums.length == 2) return Math.max(nums[0],nums[1]);
int prev2 = nums[0];
int prev1 = Math.max(nums[0], nums[1]);
for(int i=2;i<n;i++){
    int current=Math.max(prev1,nums[i]+prev2);
    prev2=prev1;
    prev1=current;
}
return prev1;
    }

}