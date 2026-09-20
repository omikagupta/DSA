class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();

        int[][] dp = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        return helper(triangle, dp, visited, 0, 0);
    }

    public int helper(List<List<Integer>> triangle,
                      int[][] dp,
                      boolean[][] visited,
                      int i, int j) {

        if (i == triangle.size() - 1) {
            return triangle.get(i).get(j);
        }

        if (visited[i][j]) {
            return dp[i][j];
        }

        dp[i][j] = triangle.get(i).get(j)
                 + Math.min(
                     helper(triangle, dp, visited, i + 1, j),
                     helper(triangle, dp, visited, i + 1, j + 1)
                   );

        visited[i][j] = true;

        return dp[i][j];
    }
}