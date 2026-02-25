class Solution {
    public int[] minCosts(int[] cost) {
        int n = cost.length;
        int[] result = new int[n];
        int minSoFar = cost[0];
        for (int i = 0; i < n; i++) {
            minSoFar = Math.min(minSoFar, cost[i]);
            result[i] = minSoFar;
        }
        return result;
    }
}