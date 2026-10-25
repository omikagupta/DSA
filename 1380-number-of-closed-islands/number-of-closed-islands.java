class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Step 1: Flood-fill (sink) all 0s connected to the boundary
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if ((r == 0 || r == rows - 1 || c == 0 || c == cols - 1) && grid[r][c] == 0) {
                    dfs(grid, r, c);
                }
            }
        }
        
        // Step 2: Count remaining isolated groups of 0s
        int closedIslandsCount = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    closedIslandsCount++;
                    dfs(grid, r, c); // sink this closed island so we don't count it again
                }
            }
        }
        
        return closedIslandsCount;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Check boundaries and if it's already water (1)
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == 1) {
            return;
        }
        
        // Mark current cell as visited by turning it into water (1)
        grid[r][c] = 1;
        
        // Traverse all 4 directions
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}