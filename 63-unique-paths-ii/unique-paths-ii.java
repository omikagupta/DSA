class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n= obstacleGrid[0].length;
        int[][]dp=new int[m+1][n+1];
for(int []i:dp){
    Arrays.fill(i,-1);
}
return helper(obstacleGrid,m-1,n-1,dp);
    }
    public int helper(int[][]obstacleGrid,int i,int j,int[][]dp){
        int m = obstacleGrid.length;
        int n= obstacleGrid[0].length;
       
        if(i<0 || j<0) return 0;
        if(obstacleGrid[i][j]==1){
            return 0;
        }
         if(i==0 && j ==0) return 1;
        if (dp[i][j] != -1) {
             return dp[i][j];
      }
      dp[i][j]=helper(obstacleGrid,i-1,j,dp)+helper(obstacleGrid,i,j-1,dp);
return dp[i][j];
    }
}