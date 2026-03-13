class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }
        int n = grid.length;
        grid[0][0] = 1;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0});
        int pathLength = 1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                int[] currentCell = queue.poll();
                int row = currentCell[0];
                int col = currentCell[1];
                if (row == n - 1 && col == n - 1) return pathLength;
                for (int nextRow = row - 1; nextRow <= row + 1; nextRow++) {
                    for (int nextCol = col - 1; nextCol <= col + 1; nextCol++) {
                        if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n && grid[nextRow][nextCol] == 0) {
                            grid[nextRow][nextCol] = 1;
                            queue.offer(new int[] {nextRow, nextCol});
                        }
                    }
                }
            }
            pathLength++;
        }
        return -1;
    }
}