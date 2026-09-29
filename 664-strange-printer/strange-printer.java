class Solution {
    public int strangePrinter(String s) {
        int n=s.length();
        int[][] dp=new int[n][n];
        for(int [] i:dp){
Arrays.fill(i,-1);
        }
return helper(s,dp,0,n-1);
    }
    public int helper(String s, int[][] dp,int i,int j){
        if(i == j) return 1;
        if(i > j) return 0;
   
        int answer=Integer.MAX_VALUE;
        if(dp[i][j]!=-1) return dp[i][j];
        answer=1+helper(s,dp,i+1,j);
        int left=0;
        int right=0;

        for(int k=i+1;k<=j;k++){
        if(s.charAt(i) == s.charAt(k)) {
           left=helper(s,dp,i+1,k-1);
           right=helper(s,dp,k,j);
             answer=Math.min(answer,left+right);
        }
     
        }
      dp[i][j]=answer;
        return dp[i][j];
    }

}