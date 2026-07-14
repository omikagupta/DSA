import java.util.*;

class Solution {

    class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int maxDistance(int[][] grid) {

        int[][] dir = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> q = new LinkedList<>();

    
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    q.offer(new Pair(i, j));
                }
            }
        }

        if (q.isEmpty() || q.size() == n * m) {
            return -1;
        }

        int distance = -1;

        while (!q.isEmpty()) {

            int size = q.size();
            distance++;

            for (int i = 0; i < size; i++) {

                Pair curr = q.poll();

                int row = curr.row;
                int col = curr.col;

                for (int[] d : dir) {

                    int newRow = row + d[0];
                    int newCol = col + d[1];

                    if (newRow >= 0 && newRow < n &&
                        newCol >= 0 && newCol < m &&
                        grid[newRow][newCol] == 0) {

                        grid[newRow][newCol] = 1;   
                        q.offer(new Pair(newRow, newCol));
                    }
                }
            }
        }

        return distance;
    }
}