import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = -1, startC = -1;
        int litterCount = 0;
        int[][] litterIdx = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterIdx[i], -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litterIdx[i][j] = litterCount++;
                }
            }
        }

        // If there is no litter to collect
        if (litterCount == 0) {
            return 0;
        }

        int targetMask = (1 << litterCount) - 1;
        
        // bestEnergy[r][c][mask] stores the maximum remaining energy seen so far
        int[][][] bestEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        // Queue stores int[]: {r, c, mask, remaining_energy}
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC, 0, energy});
        bestEnergy[startR][startC][0] = energy;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            moves++;

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int mask = curr[2];
                int e = curr[3];

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    // Check grid boundaries and obstacles
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    char cell = classroom[nr].charAt(nc);
                    if (cell == 'X') continue;

                    // Moving costs 1 unit of energy
                    int nextE = e - 1;
                    if (nextE < 0) continue;

                    // Handle cell types
                    int nextMask = mask;
                    if (cell == 'R') {
                        nextE = energy; // Reset to maximum capacity
                    } else if (cell == 'L') {
                        int id = litterIdx[nr][nc];
                        nextMask |= (1 << id);
                        if (nextMask == targetMask) {
                            return moves;
                        }
                    }

                    // Prune if we reached this state with <= energy than previously seen
                    if (nextE <= bestEnergy[nr][nc][nextMask]) {
                        continue;
                    }

                    bestEnergy[nr][nc][nextMask] = nextE;
                    queue.offer(new int[]{nr, nc, nextMask, nextE});
                }
            }
        }

        return -1;
    }
}