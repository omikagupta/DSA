class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] dp = new int[m][n];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helper(dungeon, 0, 0, dp);
    }

    public int helper(int[][] dungeon, int i, int j, int[][] dp) {

        int INF = 1_000_000_000;

      
        if (i >= dungeon.length || j >= dungeon[0].length) {
            return INF;
        }

        if (i == dungeon.length - 1 && j == dungeon[0].length - 1) {
            return Math.max(1, 1 - dungeon[i][j]);
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int down = helper(dungeon, i + 1, j, dp);
        int right = helper(dungeon, i, j + 1, dp);

       
        int next = Math.min(down, right);

      
        dp[i][j] = Math.max(1, next - dungeon[i][j]);

        return dp[i][j];
    }
}