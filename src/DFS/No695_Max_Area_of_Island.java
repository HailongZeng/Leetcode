package DFS;

/**
 * @author HailongZeng
 * @date 1/14/20 10:29 上午
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 *
 * Example 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
//island系列，与Leetcode200, leetcode694, leetcode711相似
public class No695_Max_Area_of_Island {

    //dfs  time:O(mn)  space:O(mn)
    public static int maxAreaOfIsland1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int res = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    int[] a = new int[1];
                    dfs(grid, i, j, a);
                    res = Math.max(res, a[0]);
                }
            }
        }
        return res;
    }

    public static void dfs(int[][] grid, int r, int c, int[] a){
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) return;
        grid[r][c] = 0;  //mark as visited
        a[0] += 1;
        dfs(grid, r+1, c, a);
        dfs(grid, r-1, c, a);
        dfs(grid, r, c+1, a);
        dfs(grid, r, c-1, a);
    }

    public static int maxAreaOfIsland2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int res = 0;
        int m = grid.length, n = grid[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    Queue<Integer> q = new LinkedList<>();
                    q.add(i*n+j);
                    grid[i][j] = 0;//mark as visited
                    int tmp = 1;
                    while (!q.isEmpty()){
                        int idx = q.remove();
                        int r = idx / n, c = idx % n;
                        for (int k = 0; k < dirs.length; k++){
                            int x = r + dirs[k][0];
                            int y = c + dirs[k][1];
                            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
                                tmp += 1;
                                grid[x][y] = 0; //marked as visited;
                                q.add(x*n+y);
                            }
                        }
                    }
                    res = Math.max(res, tmp);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        int res = maxAreaOfIsland1(grid);
        System.out.println(res);
    }
}
