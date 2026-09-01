#include <vector>
#include <string>
#include <queue>
#include <tuple>
#include <algorithm>

using namespace std;

class Solution {
public:
    int minMoves(vector<string>& classroom, int energy) {
        int m = classroom.size();
        int n = classroom[0].size();

        int startR = -1, startC = -1;
        int litterCount = 0;
        vector<vector<int>> litterIdx(m, vector<int>(n, -1));

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char ch = classroom[i][j];
                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litterIdx[i][j] = litterCount++;
                }
            }
        }

        // If there are no litter cells to clean
        if (litterCount == 0) {
            return 0;
        }

        int targetMask = (1 << litterCount) - 1;

        // bestEnergy[r][c][mask] tracks max remaining energy
        vector<vector<vector<int>>> bestEnergy(
            m, vector<vector<int>>(n, vector<int>(1 << litterCount, -1))
        );

        // Queue stores tuple: (r, c, mask, remaining_energy)
        queue<tuple<int, int, int, int>> q;
        q.push({startR, startC, 0, energy});
        bestEnergy[startR][startC][0] = energy;

        int dirs[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int moves = 0;

        while (!q.empty()) {
            int sz = q.size();
            moves++;

            for (int i = 0; i < sz; ++i) {
                auto [r, c, mask, e] = q.front();
                q.pop();

                for (auto& d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    // Check boundaries and obstacles
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    char cell = classroom[nr][nc];
                    if (cell == 'X') continue;

                    // Moving costs 1 energy unit
                    int nextE = e - 1;
                    if (nextE < 0) continue;

                    int nextMask = mask;
                    if (cell == 'R') {
                        nextE = energy; // Reset energy capacity
                    } else if (cell == 'L') {
                        int id = litterIdx[nr][nc];
                        nextMask |= (1 << id);
                        if (nextMask == targetMask) {
                            return moves;
                        }
                    }

                    // Prune visited states with lesser or equal energy
                    if (nextE <= bestEnergy[nr][nc][nextMask]) {
                        continue;
                    }

                    bestEnergy[nr][nc][nextMask] = nextE;
                    q.push({nr, nc, nextMask, nextE});
                }
            }
        }

        return -1;
    }
};