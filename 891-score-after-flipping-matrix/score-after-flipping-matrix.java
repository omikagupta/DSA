class Solution {
    public int matrixScore(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

 
        for (int i = 0; i < rows; i++) {

            if (grid[i][0] == 0) {

            
                for (int j = 0; j < cols; j++) {
                    grid[i][j] = 1 - grid[i][j];
                }
            }
        }


       
        for (int j = 1; j < cols; j++) {

            int ones = 0;

            for (int i = 0; i < rows; i++) {
                if (grid[i][j] == 1) {
                    ones++;
                }
            }

            int zeros = rows - ones;


            if (zeros > ones) {

                for (int i = 0; i < rows; i++) {
                    grid[i][j] = 1 - grid[i][j];
                }
            }
        }


        int score = 0;

        for (int i = 0; i < rows; i++) {

            int value = 0;

            for (int j = 0; j < cols; j++) {

                value = value * 2 + grid[i][j];
            }

            score += value;
        }


        return score;
    }
}