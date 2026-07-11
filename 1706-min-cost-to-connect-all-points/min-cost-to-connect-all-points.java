import java.util.*;

class Solution {

    class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path Compression
            }
            return parent[x];
        }

        boolean union(int u, int v) {
            int pu = find(u);
            int pv = find(v);

            if (pu == pv) {
                return false;
            }

            if (rank[pu] < rank[pv]) {
                parent[pu] = pv;
            } else if (rank[pv] < rank[pu]) {
                parent[pv] = pu;
            } else {
                parent[pv] = pu;
                rank[pu]++;
            }

            return true;
        }
    }

    public int minCostConnectPoints(int[][] points) {

        int n = points.length;

        List<int[]> edges = new ArrayList<>();

 
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int cost = Math.abs(points[i][0] - points[j][0])
                         + Math.abs(points[i][1] - points[j][1]);

                edges.add(new int[]{i, j, cost});
            }
        }

        // Sort edges by weight
        Collections.sort(edges, (a, b) -> a[2] - b[2]);

        DSU dsu = new DSU(n);

        int totalCost = 0;
        int edgesUsed = 0;

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            if (dsu.union(u, v)) {
                totalCost += wt;
                edgesUsed++;

                if (edgesUsed == n - 1) {
                    break;
                }
            }
        }

        return totalCost;
    }
}