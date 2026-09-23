class Solution {
    public int numDistinct(String s, String t) {
       int[][]dp=new int[s.length()][t.length()];
       for(int [] r:dp){
        Arrays.fill(r,-1);
       }
       return helper(s,t,0,0,dp);

    }
    public int helper(String s, String t,int i , int j,int [][]dp){
        if(j == t.length()){
    return 1;
}
if( i == s.length()){
    return 0;
}

if(dp[i][j]!= -1){
    return dp[i][j];
}
if(s.charAt(i)==t.charAt(j)){
dp[i][j]=helper(s,t,i+1,j+1,dp)+helper(s,t,i+1,j,dp);;
} else{
dp[i][j]=helper(s,t,i+1,j,dp);
}
    return dp[i][j];
    }

}