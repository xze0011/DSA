# 1020. Number of Enclaves

```java
class Solution {
    int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];

        // 先从边界开始
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1 && !visited[i][0])
                dfs(grid, visited, i, 0);
            if (grid[i][m - 1] == 1&&!visited[i][m-1])
                dfs(grid, visited, i, m - 1);
        }
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1)
                dfs(grid, visited, 0, j);
            if (grid[n - 1][j] == 1)
                dfs(grid, visited, n - 1, j);
        }


        int res = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int sum = dfs(grid, visited, i, j);
                    res += sum;
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        int area = 1;
        for (int i = 0; i < dirs.length; i++) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];

            // 边界判断
            if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length)
                continue;

            if (grid[nx][ny] == 1 && !visited[nx][ny]) {

                area += dfs(grid, visited, nx, ny);
            }
        }
        return area;
    }
}
```
