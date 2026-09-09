class Solution {
    public int climbStairs(int n) {
        // if(n==1) return 1;
        // if(n==2) return 2;
        // return climbStairs(n-1)+climbStairs(n-2); TLE
//         int[]dp=new int[n+1];
//         return helper(n,dp);

//     }
//     public int helper(int n,int[]dp){
//         if(n==1) return 1;
//         if(n==2) return 2;
// if(dp[n]!=0){
//     return dp[n];
// }
// dp[n]=helper(n-1,dp)+helper(n-2,dp);
// return dp[n];
//  if(n==1) return 1;
//         if(n==2) return 2;
//               int[]dp=new int[n+1];
//         dp[1]=1;
//         dp[2]=2;
  
//     for(int i=3;i<=n;i++){
//         dp[i]=dp[i-1]+dp[i-2];
//     }
//     return dp[n];
if(n==1) return 1;
if(n==2) return 2;
int prev1=2;
int prev2=1;
for(int i=3;i<=n;i++){
    int current=prev2+prev1;
    prev2=prev1;
    prev1=current;

}
return prev1;
    }
}