class Solution {
    private static final int MOD = 1_000_000_007;
    private Long[][][] dp;
    private int maxConsecutive;
    public int numberOfStableArrays(int zero, int one, int limit) {
        dp = new Long[zero + 1][one + 1][2];
        this.maxConsecutive = limit;
        long totalArrays = (dfs(zero, one, 0) + dfs(zero, one, 1)) % MOD;
        return (int) totalArrays;
    }
    private long dfs(int zerosRemaining, int onesRemaining, int lastElementType) {
        if (zerosRemaining < 0 || onesRemaining < 0) return 0;
        if (zerosRemaining == 0) return (lastElementType == 1 && onesRemaining <= maxConsecutive) ? 1 : 0;
        if (onesRemaining == 0) return (lastElementType == 0 && zerosRemaining <= maxConsecutive) ? 1 : 0;
        if (dp[zerosRemaining][onesRemaining][lastElementType] != null) return dp[zerosRemaining][onesRemaining][lastElementType];
        long result;
        if (lastElementType == 0) {
            long totalWays = dfs(zerosRemaining - 1, onesRemaining, 0) + dfs(zerosRemaining - 1, onesRemaining, 1);
            long invalidWays = dfs(zerosRemaining - maxConsecutive - 1, onesRemaining, 1);
            result = (totalWays - invalidWays + MOD) % MOD;
        } else {
            long totalWays = dfs(zerosRemaining, onesRemaining - 1, 0) + dfs(zerosRemaining, onesRemaining - 1, 1);
            long invalidWays = dfs(zerosRemaining, onesRemaining - maxConsecutive - 1, 0);
            result = (totalWays - invalidWays + MOD) % MOD;
        }
        dp[zerosRemaining][onesRemaining][lastElementType] = result;
        return result;
    }
}