class Solution {
    public int maxProfit(int[] prices) {
        int n =prices.length;
      int [][] dp = new int[n][2];  
      for(int [] i:dp){
        Arrays.fill(i,-1);
      }
      return helper(prices,0,0,dp);
    }


     public int helper(int [] prices,int i,int state, int[][] dp){
        if(i >= prices.length ) return 0;
        if(dp[i][state] != -1) return dp[i][state];
        if(state == 0){
          return  canbuy(prices,i,state,dp);
        }
        if(state == 1){
            int sell = prices[i]+helper(prices,i+2,0,dp);
            int skip=helper(prices,i+1,1,dp);
          return dp[i][state]= Math.max(sell, skip);
        }
      return 0;
    }
    public int canbuy(int[] prices,int i,int state, int[][] dp){
        int buy=-prices[i]+helper(prices,i+1,1,dp);
        int skip=helper(prices,i+1,0,dp);
        return dp[i][state]=Math.max(buy,skip);
    }
}