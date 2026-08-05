class Solution {
public:
    void dfs(int node, vector<vector<int>>& graph, vector<bool>& vis) {
        if (vis[node]) return;

        vis[node] = true;

        for (int nei : graph[node]) {
            dfs(nei, graph, vis);
        }
    }

    vector<int> remainingMethods(int n, int k, vector<vector<int>>& invocations) {
        vector<vector<int>> graph(n), rev(n);

        for (auto &e : invocations) {
            graph[e[0]].push_back(e[1]);
            rev[e[1]].push_back(e[0]);
        }

        vector<bool> suspicious(n, false);
        dfs(k, graph, suspicious);

        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) continue;

            for (int parent : rev[i]) {
                if (!suspicious[parent]) {
                    vector<int> ans;
                    for (int j = 0; j < n; j++)
                        ans.push_back(j);
                    return ans;
                }
            }
        }

        vector<int> ans;
        for (int i = 0; i < n; i++) {
            if (!suspicious[i])
                ans.push_back(i);
        }

        return ans;
    }
};