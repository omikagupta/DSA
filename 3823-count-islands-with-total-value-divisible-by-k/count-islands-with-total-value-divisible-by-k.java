class Solution {
    private int rows, cols;
    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int countIslands(int[][] grid, int k) {
        rows = grid.length;
        cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If we find an unvisited land cell (value > 0)
                if (grid[i][j] > 0 && !visited[i][j]) {
                    long totalValue = dfs(grid, visited, i, j);
                    if (totalValue % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private long dfs(int[][] grid, boolean[][] visited, int r, int c) {
        visited[r][c] = true;
        long currentSum = grid[r][c];

        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            // Check boundaries and whether the neighbor is land (value > 0) and unvisited
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] > 0 && !visited[nr][nc]) {
                currentSum += dfs(grid, visited, nr, nc);
            }
        }

        return currentSum;
    }
}