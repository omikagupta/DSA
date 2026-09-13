#include <vector>
#include <unordered_map>
#include <algorithm>

class Solution {
public:
    int largestOverlap(std::vector<std::vector<int>>& img1, std::vector<std::vector<int>>& img2) {
        int n = img1.size();
        std::vector<std::pair<int, int>> ones1, ones2;

        // Collect coordinates of 1s in both matrices
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < n; ++c) {
                if (img1[r][c] == 1) ones1.push_back({r, c});
                if (img2[r][c] == 1) ones2.push_back({r, c});
            }
        }

        // Map to count frequencies of each displacement vector
        // Key format: (dr + 100) * 100 + (dc + 100) to safely hash pairs of negative/positive offsets
        std::unordered_map<int, int> vector_counts;
        int max_overlap = 0;

        for (const auto& [r1, c1] : ones1) {
            for (const auto& [r2, c2] : ones2) {
                int dr = r2 - r1;
                int dc = c2 - c1;
                int key = (dr + 100) * 100 + (dc + 100);
                
                vector_counts[key]++;
                max_overlap = std::max(max_overlap, vector_counts[key]);
            }
        }

        return max_overlap;
    }
};