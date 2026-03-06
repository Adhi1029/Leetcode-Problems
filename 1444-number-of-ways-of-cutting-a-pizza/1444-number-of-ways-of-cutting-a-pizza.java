class Solution {
    private int rows;
    private int cols;
    private int[][] prefixSum;
    private Integer[][][] memo;
    private final int MOD = (int) 1e9 + 7;
    public int ways(String[] pizza, int k) {
        rows = pizza.length;
        cols = pizza[0].length();
        prefixSum = new int[rows + 1][cols + 1];
        memo = new Integer[rows][cols][k];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int currentApple = pizza[i - 1].charAt(j - 1) == 'A' ? 1 : 0;
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1]  - prefixSum[i - 1][j - 1] + currentApple;
            }
        }
        return dfs(0, 0, k - 1);
    }
    private int dfs(int startRow, int startCol, int cutsRemaining) {
        if (cutsRemaining == 0) {
            int applesInRemainingPiece = prefixSum[rows][cols] - prefixSum[startRow][cols]   - prefixSum[rows][startCol] + prefixSum[startRow][startCol];
            return applesInRemainingPiece > 0 ? 1 : 0;
        }
        if (memo[startRow][startCol][cutsRemaining] != null) return memo[startRow][startCol][cutsRemaining];
        int totalWays = 0;
        for (int cutRow = startRow + 1; cutRow < rows; cutRow++) {
            int applesInUpperPiece = prefixSum[cutRow][cols] - prefixSum[startRow][cols]  - prefixSum[cutRow][startCol] + prefixSum[startRow][startCol];
            if (applesInUpperPiece > 0) totalWays = (totalWays + dfs(cutRow, startCol, cutsRemaining - 1)) % MOD;
        }
        for (int cutCol = startCol + 1; cutCol < cols; cutCol++) {
            int applesInLeftPiece = prefixSum[rows][cutCol] - prefixSum[startRow][cutCol] - prefixSum[rows][startCol] + prefixSum[startRow][startCol];
            if (applesInLeftPiece > 0) totalWays = (totalWays + dfs(startRow, cutCol, cutsRemaining - 1)) % MOD;
        }
        memo[startRow][startCol][cutsRemaining] = totalWays;
        return totalWays;
    }
}