class Solution {
    private int[] parent;
    private int[][] grid;
    private int rows;
    private int cols;
    public boolean hasValidPath(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        parent = new int[rows * cols];
        for (int i = 0; i < parent.length; ++i) {
            parent[i] = i;
        }
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int streetType = grid[i][j];
                if (streetType == 1) {
                    connectLeft(i, j);
                    connectRight(i, j);
                } else if (streetType == 2) {
                    connectUp(i, j);
                    connectDown(i, j);
                } else if (streetType == 3) {
                    connectLeft(i, j);
                    connectDown(i, j);
                } else if (streetType == 4) {
                    connectRight(i, j);
                    connectDown(i, j);
                } else if (streetType == 5) {
                    connectLeft(i, j);
                    connectUp(i, j);
                } else {
                    connectRight(i, j);
                    connectUp(i, j);
                }
            }
        }
        return find(0) == find(rows * cols - 1);
    }
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    private void connectLeft(int i, int j) {
        if (j > 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] == 4 || grid[i][j - 1] == 6)) {
            parent[find(i * cols + j)] = find(i * cols + j - 1);
        }
    }
    private void connectRight(int i, int j) {
        if (j < cols - 1 && (grid[i][j + 1] == 1 || grid[i][j + 1] == 3 || grid[i][j + 1] == 5)) {
            parent[find(i * cols + j)] = find(i * cols + j + 1);
        }
    }
    private void connectUp(int i, int j) {
        if (i > 0 && (grid[i - 1][j] == 2 || grid[i - 1][j] == 3 || grid[i - 1][j] == 4)) {
            parent[find(i * cols + j)] = find((i - 1) * cols + j);
        }
    }
    private void connectDown(int i, int j) {
        if (i < rows - 1 && (grid[i + 1][j] == 2 || grid[i + 1][j] == 5 || grid[i + 1][j] == 6)) {
            parent[find(i * cols + j)] = find((i + 1) * cols + j);
        }
    }
}