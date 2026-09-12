import java.util.*;

class Solution {
    static class Interval {
        int l, r, weight, id;

        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    static class State {
        long weight;
        List<Integer> ids;

        State(long weight, List<Integer> ids) {
            this.weight = weight;
            this.ids = ids;
        }

        // Returns true if 'this' is strictly better than 'other'
        boolean isBetterThan(State other) {
            if (other == null) return true;
            if (this.weight != other.weight) {
                return this.weight > other.weight;
            }
            // Tie-break: lexicographically smaller
            int len1 = this.ids.size();
            int len2 = other.ids.size();
            int minLen = Math.min(len1, len2);
            for (int i = 0; i < minLen; i++) {
                int cmp = Integer.compare(this.ids.get(i), other.ids.get(i));
                if (cmp != 0) return cmp < 0;
            }
            return len1 < len2;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> it = intervalsList.get(i);
            intervals[i] = new Interval(it.get(0), it.get(1), it.get(2), i);
        }

        // Sort primarily by right endpoint 'r'
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.r));

        // dp[i][k]: best state using a subset of first i intervals with at most k intervals chosen
        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0, new ArrayList<>());
            }
        }

        for (int i = 1; i <= n; i++) {
            Interval curr = intervals[i - 1];

            // Find largest index p (1-based) where intervals[p - 1].r < curr.l
            int low = 0, high = i - 1, p = 0;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (mid == 0 || intervals[mid - 1].r < curr.l) {
                    p = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            for (int k = 1; k <= 4; k++) {
                // Option 1: Do not pick current interval
                State best = dp[i - 1][k];

                // Option 2: Pick current interval
                State prev = dp[p][k - 1];
                List<Integer> newIds = new ArrayList<>(prev.ids);
                // Insert curr.id keeping newIds sorted
                int insertPos = Collections.binarySearch(newIds, curr.id);
                if (insertPos < 0) insertPos = -insertPos - 1;
                newIds.add(insertPos, curr.id);

                State cand = new State(prev.weight + curr.weight, newIds);

                if (cand.isBetterThan(best)) {
                    dp[i][k] = cand;
                } else {
                    dp[i][k] = best;
                }
            }
        }

        List<Integer> resultList = dp[n][4].ids;
        int[] ans = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            ans[i] = resultList.get(i);
        }
        return ans;
    }
}