class Solution {
    private int[] parent;
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int[] swap : allowedSwaps) {
            int index1 = swap[0];
            int index2 = swap[1];
            parent[find(index1)] = find(index2);
        }
        Map<Integer, Map<Integer, Integer>> componentFrequency = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            componentFrequency .computeIfAbsent(root, k -> new HashMap<>()).merge(source[i], 1, Integer::sum);
        }
        int hammingDistance = 0;
        for (int i = 0; i < n; i++) {
            int root = find(i);
            Map<Integer, Integer> frequencyMap = componentFrequency.get(root);
            if (frequencyMap.merge(target[i], -1, Integer::sum) < 0) {
                hammingDistance++;
            }
        }
        return hammingDistance;
    }
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}