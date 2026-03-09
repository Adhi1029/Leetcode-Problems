class Solution {
    private Long[][][] dp;
    private int maxConsecutive;
    private final int MOD = (int) 1e9 + 7;
    public int numberOfStableArrays(int zero, int one, int limit) {
        dp = new Long[zero + 1][one + 1][2];
        this.maxConsecutive = limit;
        long result = (dfs(zero, one, 0) + dfs(zero, one, 1)) % MOD;
        return (int) result;
    }
    private long dfs(int zerosLeft, int onesLeft, int lastDigit) {
        if (zerosLeft < 0 || onesLeft < 0) return 0;
        if (zerosLeft == 0) return (lastDigit == 1 && onesLeft <= maxConsecutive) ? 1 : 0;
        if (onesLeft == 0) return (lastDigit == 0 && zerosLeft <= maxConsecutive) ? 1 : 0;
        if (dp[zerosLeft][onesLeft][lastDigit] != null) return dp[zerosLeft][onesLeft][lastDigit];
        if (lastDigit == 0) {
            long totalArrays = dfs(zerosLeft - 1, onesLeft, 0) + dfs(zerosLeft - 1, onesLeft, 1);
            long invalidArrays = dfs(zerosLeft - maxConsecutive - 1, onesLeft, 1);
            dp[zerosLeft][onesLeft][lastDigit] = (totalArrays - invalidArrays + MOD) % MOD;
        } else {
            long totalArrays = dfs(zerosLeft, onesLeft - 1, 0) + dfs(zerosLeft, onesLeft - 1, 1);
            long invalidArrays = dfs(zerosLeft, onesLeft - maxConsecutive - 1, 0);
            dp[zerosLeft][onesLeft][lastDigit] = (totalArrays - invalidArrays + MOD) % MOD;
        }
        return dp[zerosLeft][onesLeft][lastDigit];
    }
}