class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
       int[][][] dp =new int[n][2][3]; 
      for(int[][] i : dp){
    for(int[] j : i){
        Arrays.fill(j,-1);
    }
}
       return helper(prices,0,0,0,dp);
    }
    public int helper(int [] prices,int i,int state,int count, int[][][] dp){
        if(i == prices.length || count == 2) return 0;
        if(dp[i][state][count] != -1) return dp[i][state][count];
        if(state == 0){
          return  canbuy(prices,i,state,count,dp);
        }
        if(state == 1){
            int sell = prices[i]+helper(prices,i+1,0,count+1,dp);
            int skip=helper(prices,i+1,1,count,dp);
          return dp[i][state][count] = Math.max(sell, skip);
        }
      return 0;
    }
    public int canbuy(int[] prices,int i,int state,int count, int[][][] dp){
        int buy=-prices[i]+helper(prices,i+1,1,count,dp);
        int skip=helper(prices,i+1,0,count,dp);
        return dp[i][state][count]=Math.max(buy,skip);
    }
}