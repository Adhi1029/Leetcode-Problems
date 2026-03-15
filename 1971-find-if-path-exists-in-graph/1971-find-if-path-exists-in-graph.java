class Solution {
    private int destination;
    private boolean[] visited;
    private List<Integer>[] graph;
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        this.destination = destination;
        visited = new boolean[n];
        graph = new List[n];
        Arrays.setAll(graph, index -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        return dfs(source);
    }
    private boolean dfs(int currentNode) {
        if (currentNode == destination) return true;
        visited[currentNode] = true;
        for (int neighbor : graph[currentNode]) {
            if (!visited[neighbor] && dfs(neighbor)) return true;
        }
        return false;
    }
}