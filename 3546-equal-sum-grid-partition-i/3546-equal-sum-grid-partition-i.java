class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        long totalSum = 0;
        for (int[] row : grid) {
            for (int value : row) {
                totalSum += value;
            }
        }
        if (totalSum % 2 != 0) {
            return false;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        long prefixSum = 0;
        for (int i = 0; i < rows; i++) {
            for (int value : grid[i]) {
                prefixSum += value;
            }
            if (prefixSum * 2 == totalSum && i < rows - 1) {
                return true;
            }
        }
        prefixSum = 0;
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                prefixSum += grid[i][j];
            }
            if (prefixSum * 2 == totalSum && j < cols - 1) {
                return true;
            }
        }
        return false;
    }
}