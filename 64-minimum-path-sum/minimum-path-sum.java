class Solution {
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int[][]dp=new int[m][n];
        for(int []i:dp){
            Arrays.fill(i,-1);
        }
        return helper(grid,dp,m-1,n-1);
    }
    public int helper(int[][]grid,int[][]dp,int i,int j){
int INF = 1_000_000_000;
if(i==0 && j == 0) return grid[0][0];
if(i<0 || j<0 ) return INF;
if(dp[i][j]!= -1)
{
return dp[i][j];
}
dp[i][j]=grid[i][j]+Math.min(helper(grid,dp,i-1,j),helper(grid,dp,i,j-1));
return dp[i][j];
    }
    
}