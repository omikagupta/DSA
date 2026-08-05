class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] graph = new ArrayList[n];
        List<Integer>[] rev = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            rev[i] = new ArrayList<>();
        }

        for (int[] e : invocations) {
            graph[e[0]].add(e[1]);
            rev[e[1]].add(e[0]);
        }

        boolean[] suspicious = new boolean[n];
        dfs(k, graph, suspicious);

        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) continue;

            for (int parent : rev[i]) {
                if (!suspicious[parent]) {
                    List<Integer> ans = new ArrayList<>();
                    for (int j = 0; j < n; j++) ans.add(j);
                    return ans;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) ans.add(i);
        }
        return ans;
    }

    private void dfs(int node, List<Integer>[] graph, boolean[] vis) {
        if (vis[node]) return;
        vis[node] = true;

        for (int nei : graph[node]) {
            dfs(nei, graph, vis);
        }
    }
}