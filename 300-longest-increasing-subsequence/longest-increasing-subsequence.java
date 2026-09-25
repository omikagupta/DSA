class Solution {
    public int lengthOfLIS(int[] nums) {
        int[][]dp=new int[nums.length][nums.length+1];
        for(int [] a:dp){
Arrays.fill(a,-1);
        }
        return helper(nums,dp,0,-1);
    }
    public int helper(int[]nums,int[][]dp,int i,int prev){
        if(i == nums.length) return 0;
        if(dp[i][prev+1]  != -1) return dp[i][prev+1];
        int take=0;
        int skip= helper(nums,dp,i+1,prev);
        if(prev == -1 || nums[i]>nums[prev]){
take=1+helper(nums,dp,i+1,i);
        }
     dp[i][prev+1]= Math.max(take,skip);
           
        
        return dp[i][prev+1];
    }
}