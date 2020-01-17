package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/15/20 2:15 下午
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
 *
 * Example 1:
 *
 * 11000
 * 11000
 * 00011
 * 00011
 * Given the above grid map, return 1.
 * Example 2:
 *
 * 11011
 * 10000
 * 00001
 * 11011
 * Given the above grid map, return 3.
 *
 * Notice that:
 * 11
 * 1
 * and
 *  1
 * 11
 * are considered different island shapes, because we do not consider reflection / rotation.
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
//leetcode694
public class No56_Number_of_Distinct_Islands {

    //每个相同的island的相对位置是一定的，定义一个顶点为基点，以该点做相对变化。相同的岛的结果应该一致
    //dfs  time:O(mn)  space:O(mn)
    public static int numDistinctIslands1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        Set<Set<String>> shapes = new HashSet<>();
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                Set<String> shape = new HashSet<>();
                if (grid[i][j] == 1){
                    dfs(grid, i, j, i, j, shape);
                    shapes.add(shape);
                }
            }
        }
        return shapes.size();
    }

    public static void dfs(int[][] grid, int r, int c, int r0, int c0, Set<String> shape){
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] <= 0) return;
        grid[r][c] = 0;
        shape.add((r-r0) + "_" + (c-c0));
        dfs(grid, r+1, c, r0, c0, shape);
        dfs(grid, r-1, c, r0, c0, shape);
        dfs(grid, r, c+1, r0, c0, shape);
        dfs(grid, r, c-1, r0, c0, shape);
    }

    //bfs  time:O(mn)  space:O(mn)
    public static int numDistinctIslands2(int[][] grid) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        Set<Set<String>> shapes = new HashSet<>();
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    Set<String> shape = new HashSet<>();
                    Queue<Integer> q = new LinkedList<>();
                    q.add(i*n+j); //(i,j)为基点
                    grid[i][j] = 0; //mark as visited
                    while (!q.isEmpty()){
                        int idx = q.poll();
                        int r = idx / n, c = idx % n;
                        shape.add((r-i) + "_" + (c-j));
                        for (int k = 0; k < dirs.length; k++){
                            int x = r + dirs[k][0];
                            int y = c + dirs[k][1];
                            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
                                q.add(x*n+y);
                                grid[x][y] = 0; //mark as visited
                                shape.add((x-i) + "_" + (y-j));
                            }
                        }
                    }
                    shapes.add(shape);
                }
            }
        }
        return shapes.size();
    }

    public static void main(String[] args) {
        int[][] grid1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };
        int[][] grid2 = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };
        int res1 = numDistinctIslands1(grid1);
        int res2 = numDistinctIslands1(grid2);
        System.out.println(res1);
        System.out.println(res2);
    }

}
