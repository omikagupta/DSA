class Solution {
    public int maxCoins(int[] nums) {
        int n=nums.length;
        int[]arr=new int[n+2];
        arr[0]=1;
        arr[n+1]=1;
        int[][]dp=new int[n+2][n+2];
        for(int i = 0; i < n; i++){
    arr[i+1] = nums[i];
}
        for(int [] i:dp){
            Arrays.fill(i,-1);
        }
return helper(dp,1,n,arr);
    }
    public int helper(int [][]dp,int i,int j,int[]arr){
        if(i > j) return 0;
        int ans=Integer.MIN_VALUE;
        if(dp[i][j]!=-1) return dp[i][j];
        for(int k=i;k<=j;k++){
            int left=helper(dp,i,k-1,arr);
            int right=helper(dp,k+1,j,arr);
            int cost=arr[i-1]*arr[k]*arr[j+1];
            ans=Math.max(ans,left+right+cost);
   
        }
                 dp[i][j]=ans;
        return dp[i][j];
    }
}