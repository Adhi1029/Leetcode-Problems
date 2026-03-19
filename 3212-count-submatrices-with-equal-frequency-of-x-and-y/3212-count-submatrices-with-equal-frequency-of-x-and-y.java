class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][][] prefixSum = new int[rows + 1][cols + 1][2];
        int result = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                prefixSum[i][j][0] = prefixSum[i - 1][j][0] + prefixSum[i][j - 1][0] - prefixSum[i - 1][j - 1][0] + (grid[i - 1][j - 1] == 'X' ? 1 : 0);
                prefixSum[i][j][1] = prefixSum[i - 1][j][1] + prefixSum[i][j - 1][1] - prefixSum[i - 1][j - 1][1] + (grid[i - 1][j - 1] == 'Y' ? 1 : 0);
                if (prefixSum[i][j][0] > 0 && prefixSum[i][j][0] == prefixSum[i][j][1]) result++;
            }
        }
        return result;
    }
}