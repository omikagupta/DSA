import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of (v, weight)
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[] { edge[1], edge[2] });
        }

        // Min-priority queue storing [weight, node, count]
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        // Distances table: dist[node][count]
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        // Starting state at node 0
        char startChar = labels.charAt(0);
        // If starting character count exceeds k, no valid path can begin
        if (1 > k) return -1;

        pq.offer(new long[] { 0, 0, 1 });
        dist[0][1] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            if (d > dist[u][count]) {
                continue;
            }

            // If we reached destination node n - 1
            if (u == n - 1) {
                return (int) d;
            }

            for (int[] neighbor : adj[u]) {
                int v = neighbor[0];
                int w = neighbor[1];

                char nextChar = labels.charAt(v);
                char currChar = labels.charAt(u); // Note: count tracks consecutive runs of labels ending at u, 
                                                // but when transitioning to v, the character added is labels.charAt(v).
                                                // Wait, let's look closer at how consecutive characters are defined.
                // The problem says: "the concatenation of the labels of the nodes along the path contains at most k consecutive identical characters."
                // When we move from u to v:
                // If labels[v] == labels[u], does it continue the run? 
                // Actually, the consecutive run length applies to the last node's character. 
                // Let's check Hint 3: "When moving from node u to node v, the next count becomes count + 1 if labels[u] == labels[v], otherwise it becomes 1."
                // Wait, if we are at u with a run of labels[u], moving to v means the new tail character is labels[v].
                // If labels[u] == labels[v], the run length of labels[v] extends by 1 (i.e., count + 1). 
                // If labels[u] != labels[v], a new run of labels[v] starts with length 1.
                
                int nextCount = (labels.charAt(u) == labels.charAt(v)) ? count + 1 : 1;

                if (nextCount <= k) {
                    if (d + w < dist[v][nextCount]) {
                        dist[v][nextCount] = d + w;
                        pq.offer(new long[] { dist[v][nextCount], v, nextCount });
                    }
                }
            }
        }

        // Find the minimum distance to node n - 1 across all valid counts
        long ans = Long.MAX_VALUE;
        for (int c = 1; c <= k; c++) {
            ans = Math.min(ans, dist[n - 1][c]);
        }

        return ans == Long.MAX_VALUE ? -1 : (int) ans;
    }
}