#include <vector>
#include <array>
#include <algorithm>

using namespace std;

class Solution {
    struct Interval {
        int l, r, weight, id;
    };

    struct State {
        long long weight = 0;
        vector<int> ids;

        bool operator<(const State& other) const {
            if (weight != other.weight) {
                return weight < other.weight;
            }
            // Tie-break: lexicographically smaller indices vector is considered "greater" (better)
            return ids > other.ids;
        }
    };

public:
    vector<int> maximumWeight(vector<vector<int>>& intervalsList) {
        int n = intervalsList.size();
        vector<Interval> intervals(n);
        for (int i = 0; i < n; ++i) {
            intervals[i] = {intervalsList[i][0], intervalsList[i][1], intervalsList[i][2], i};
        }

        // Sort by right endpoint 'r'
        sort(intervals.begin(), intervals.end(), [](const Interval& a, const Interval& b) {
            return a.r < b.r;
        });

        // dp[i][k]: best state using a subset of first i intervals with at most k intervals chosen
        vector<array<State, 5>> dp(n + 1);

        for (int i = 1; i <= n; ++i) {
            const auto& curr = intervals[i - 1];

            // Binary search to find largest index p (1-based) where intervals[p - 1].r < curr.l
            int low = 0, high = i - 1, p = 0;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (mid == 0 || intervals[mid - 1].r < curr.l) {
                    p = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            for (int k = 1; k <= 4; ++k) {
                // Option 1: Do not include the current interval
                State best = dp[i - 1][k];

                // Option 2: Include the current interval
                const State& prev = dp[p][k - 1];
                vector<int> newIds = prev.ids;
                newIds.insert(lower_bound(newIds.begin(), newIds.end(), curr.id), curr.id);

                State cand = {prev.weight + curr.weight, std::move(newIds)};

                if (best < cand) {
                    dp[i][k] = std::move(cand);
                } else {
                    dp[i][k] = std::move(best);
                }
            }
        }

        return dp[n][4].ids;
    }
};