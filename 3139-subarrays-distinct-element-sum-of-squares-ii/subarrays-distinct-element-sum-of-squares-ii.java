import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[] tree;
    private long[] lazy;

    private void push(int node, int left, int right) {
        if (lazy[node] != 0) {
            int mid = (left + right) / 2;
            long val = lazy[node];

            // Left child
            tree[2 * node] = (tree[2 * node] + val * (mid - left + 1)) % MOD;
            lazy[2 * node] = (lazy[2 * node] + val) % MOD;

            // Right child
            tree[2 * node + 1] = (tree[2 * node + 1] + val * (right - mid)) % MOD;
            lazy[2 * node + 1] = (lazy[2 * node + 1] + val) % MOD;

            lazy[node] = 0;
        }
    }

    private void update(int node, int left, int right, int qLeft, int qRight, long val) {
        if (qLeft > right || qRight < left) {
            return;
        }
        if (qLeft <= left && right <= qRight) {
            tree[node] = (tree[node] + val * (right - left + 1)) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
            return;
        }

        push(node, left, right);
        int mid = (left + right) / 2;
        update(2 * node, left, mid, qLeft, qRight, val);
        update(2 * node + 1, mid + 1, right, qLeft, qRight, val);
        tree[node] = (tree[2 * node] + tree[2 * node + 1]) % MOD;
    }

    private long query(int node, int left, int right, int qLeft, int qRight) {
        if (qLeft > right || qRight < left) {
            return 0;
        }
        if (qLeft <= left && right <= qRight) {
            return tree[node];
        }

        push(node, left, right);
        int mid = (left + right) / 2;
        long resLeft = query(2 * node, left, mid, qLeft, qRight);
        long resRight = query(2 * node + 1, mid + 1, right, qLeft, qRight);
        return (resLeft + resRight) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        tree = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> lastSeen = new HashMap<>();
        long currentSum = 0;
        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int x = nums[j];
            int prev = lastSeen.getOrDefault(x, -1);
            lastSeen.put(x, j);

            int l = prev + 1;
            int r = j;

            long rangeSum = query(1, 0, n - 1, l, r);
            long increment = (2 * rangeSum + (r - l + 1)) % MOD;

            currentSum = (currentSum + increment) % MOD;
            update(1, 0, n - 1, l, r, 1);

            totalSum = (totalSum + currentSum) % MOD;
        }

        return (int) totalSum;
    }
}