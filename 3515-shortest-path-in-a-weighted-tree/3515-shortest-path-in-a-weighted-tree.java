class Solution {
    static class FenTree {
        int[] tree;
        int n;
        FenTree(int n) {
            this.n = n;
            tree = new int[n + 2];
        }
        void update(int i, int d) {
            while (i <= n) {
                tree[i] += d;
                i  += i & -i;
            }
        }
        int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
        void rangeUpdate(int l, int r, int d) {
            update(l, d);
            update(r + 1, -d);
        }
    }
    public int[] treeQueries(int n, int[][] edges, int[][] queries) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int i = 1; i <= n; i++) adj.put(i, new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }
        int[] t_in = new int[n + 1];
        int[] t_out = new int[n + 1];
        int[] dist = new int[n + 1];
        int[] time = {1};
        Map<String, Integer> mp = new HashMap<>();
        dfs(1, 0, 0, adj, t_in, t_out, dist, time, mp);
        FenTree tree = new FenTree(n);
        List<Integer> ansList = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                int u = q[1], v = q[2], newW = q[3];
                int parent = (t_in[u] < t_in[v]) ? u : v;
                int child = (t_in[u] < t_in[v]) ? v : u;
                String key = parent + "," + child;
                int oldW = mp.get(key);
                int d = newW - oldW;
                tree.rangeUpdate(t_in[child], t_out[child], d);
                mp.put(key, newW);
            } else {
                int x = q[1];
                int total = dist[x] + tree.query(t_in[x]);
                ansList.add(total);
            }
        }
        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) ans[i] = ansList.get(i);
        return ans;
    }
    private void dfs(int u, int parent, int d, Map<Integer, List<int[]>> adj, int[] t_in, int[] t_out, int[] dist, int[] time, Map<String, Integer> mp) {
        dist[u] = d;
        t_in[u] = time[0]++;
        for (int[] nei : adj.get(u)) {
            int v = nei[0], w = nei[1];
            if (v != parent) {
                mp.put(u + "," + v, w);
                dfs(v, u, d + w, adj, t_in, t_out, dist, time, mp);
            }
        }
        t_out[u] = time[0] - 1;
    }
}