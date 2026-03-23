class Solution {
    private static final int MOD = (int) 1e9 + 7;
    public int maxProductPath(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        long[][][] dp = new long[rows][cols][2];
        dp[0][0][0] = grid[0][0];
        dp[0][0][1] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0][0] = dp[i - 1][0][0] * grid[i][0];
            dp[i][0][1] = dp[i - 1][0][1] * grid[i][0];
        }
        for (int j = 1; j < cols; j++) {
            dp[0][j][0] = dp[0][j - 1][0] * grid[0][j];
            dp[0][j][1] = dp[0][j - 1][1] * grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                int currentValue = grid[i][j];
                if (currentValue >= 0) {
                    dp[i][j][0] = Math.min(dp[i - 1][j][0], dp[i][j - 1][0]) * currentValue;
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i][j - 1][1]) * currentValue;
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][1], dp[i][j - 1][1]) * currentValue;
                    dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i][j - 1][0]) * currentValue;
                }
            }
        }
        long maxProduct = dp[rows - 1][cols - 1][1];
        return maxProduct < 0 ? -1 : (int) (maxProduct % MOD);
    }
}