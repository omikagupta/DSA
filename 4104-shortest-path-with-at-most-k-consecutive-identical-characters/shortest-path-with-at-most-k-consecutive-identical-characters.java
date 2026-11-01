import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of (v, weight)
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        // Min-priority queue to store states: [current_weight, current_node, current_consecutive_count]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        // dist[node][count] stores the minimum weight to reach node with a given consecutive count
        // count ranges from 1 to k. Using 0-indexed array of size (k + 1)
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // Starting state at node 0
        int startCount = 1;
        pq.offer(new int[]{0, 0, startCount});
        dist[0][startCount] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];
            int count = curr[2];

            if (u == n - 1) {
                return d;
            }

            if (d > dist[u][count]) {
                continue;
            }

            for (int[] neighbor : adj[u]) {
                int v = neighbor[0];
                int w = neighbor[1];

                int nextCount;
                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                if (nextCount <= k && d + w < dist[v][nextCount]) {
                    dist[v][nextCount] = d + w;
                    pq.offer(new int[]{d + w, v, nextCount});
                }
            }
        }

        // Check if node n - 1 was reached with any valid count
        int ans = Integer.MAX_VALUE;
        for (int count = 1; count <= k; count++) {
            ans = Math.min(ans, dist[n - 1][count]);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}