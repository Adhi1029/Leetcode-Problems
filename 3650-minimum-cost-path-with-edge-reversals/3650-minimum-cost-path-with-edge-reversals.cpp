class Solution {
public:
    int minCost(int n, vector<vector<int>>& edges) {
        vector<vector<pair<int, pair<int, bool>>>> adj(n);
        for (const auto& edge : edges){
            int u = edge[0],v=edge[1],w = edge[2];
            adj[u].push_back({v, {w, false}});
            adj[v].push_back({u, {w, true}});
        }
        vector< long long> dist(n, -1);
        priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq;

        dist[0]=0;
        pq.push({0, 0});

        while (!pq.empty()) {
            auto [d, u] = pq.top();
            pq.pop();

            if (dist[u] != -1 && d > dist[u]) continue;
            if (u == n - 1) return (int)d;

            for (auto& edge : adj[u]) { 
                int v=edge.first;
                int weight=edge.second.first;
                bool isReversed = edge.second.second;

                long long moveCost = isReversed ? 2LL * weight : (long long)weight;
                if (dist[v]==-1|| d+ moveCost < dist[v]){
                    dist[v] = d + moveCost;
                    pq.push({dist[v],v});
                }
            }
        }

        return (dist[n - 1] == -1) ? -1 : (int)dist[n - 1];
    }
};