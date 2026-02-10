class Solution {
public:
    long long minimumCost(string source, string target, vector<char>& original, vector<char>& changed, vector<int>& cost) {
        long long graph[26][26];
        const long long INF = 1e15;
        for (int i = 0; i < 26; ++i) {
            fill(begin(graph[i]), end(graph[i]), INF);
            graph[i][i] = 0;
        }
        for (int i = 0; i < original.size(); ++i) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            graph[u][v] = min(graph[u][v], (long long)cost[i]);
        }
        for (int k = 0; k < 26; ++k) {
            for (int i = 0; i < 26; ++i) {
                for (int j = 0; j < 26; ++j) {
                    graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        long long totalCost = 0;
        for (int i = 0; i < source.length(); ++i) {
            int u = source[i] - 'a';
            int v = target[i] - 'a';
            if (graph[u][v] >= INF) {
                return -1;
            }
            totalCost += graph[u][v];
        }
        return totalCost;
    }
};