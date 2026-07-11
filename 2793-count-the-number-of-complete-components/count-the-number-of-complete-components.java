class Solution {

    int nodes;
    int edges;

    public int countCompleteComponents(int n, int[][] edgesArr) {

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edgesArr) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                nodes = 0;
                edges = 0;

                dfs(i, adj, visited);

                int actualEdges = edges / 2;

                if (actualEdges == nodes * (nodes - 1) / 2) {
                    ans++;
                }
            }
        }

        return ans;
    }

    void dfs(int node, List<List<Integer>> adj, boolean[] visited) {

        visited[node] = true;
        nodes++;

        edges += adj.get(node).size();

        for (int nei : adj.get(node)) {

            if (!visited[nei]) {
                dfs(nei, adj, visited);
            }
        }
    }
}