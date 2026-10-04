class Solution {
    public boolean canPartition(int[] nums) {
        int n=nums.length;
int sum=0;  
for(int i:nums){
   sum+=i;
}
if(sum % 2 != 0) return false;
int target=sum/2;
int[][] dp=new int[n][target+1];
for(int [] i:dp){
    Arrays.fill(i,-1);
}
return helper(nums,dp,0,target);
    }
    public boolean helper(int[]nums,int[][]dp,int i, int remaining){
        int n =nums.length;
        if(remaining == 0){
            return true;
        }
        if(remaining < 0){
            return false;
        }
        if(i == n && remaining != 0){
            return false;
        }
        if(dp[i][remaining] != -1){
            return dp[i][remaining]==1;
        }
        boolean take=false;
        if(nums[i]<=remaining){
        take = helper(nums,dp,i+1,remaining-nums[i]);
        }
        boolean skip=helper(nums,dp,i+1,remaining);
        
       boolean ans=take||skip;
    dp[i][remaining]=ans?1:0;
    return ans;
    }
}