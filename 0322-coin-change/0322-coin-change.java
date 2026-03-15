class Solution {
    public int coinChange(int[] coins, int amount) {
        final int INF = 1 << 30;
        int numCoins = coins.length;
        int targetAmount = amount;
        int[][] dp = new int[numCoins + 1][targetAmount + 1];
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[0][0] = 0;
        for (int i = 1; i <= numCoins; i++) {
            for (int j = 0; j <= targetAmount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= coins[i - 1]) dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i - 1]] + 1);
            }
        }
        return dp[numCoins][targetAmount] >= INF ? -1 : dp[numCoins][targetAmount];
    }
}