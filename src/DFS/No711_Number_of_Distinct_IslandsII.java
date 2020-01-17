package DFS;

/**
 * @author HailongZeng
 * @date 1/14/20 9:55 上午
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).
 *
 * Example 1:
 *
 * 11000
 * 10000
 * 00001
 * 00011
 * Given the above grid map, return 1.
 *
 * Notice that:
 * 11
 * 1
 * and
 *  1
 * 11
 * are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
 * Example 2:
 *
 * 11100
 * 10001
 * 01001
 * 01110
 * Given the above grid map, return 2.
 *
 * Here are the two distinct islands:
 * 111
 * 1
 * and
 * 1
 * 1
 *
 * Notice that:
 * 111
 * 1
 * and
 * 1
 * 111
 * are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
//与leetcode200和leetcode694相似
// The 8 rotations and reflections of each point are (x, y), (-x, y), (x, -y), (-x, -y), (y, x), (-y, x), (y, -x), (-y, -x)
public class No711_Number_of_Distinct_IslandsII {

    //dfs  time:O(m*n*log(m*n))  log comes from sorting the shapes
    //space: O(m*n)
    public static int numDistinctIslands1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        Set<String> shapes = new HashSet<>();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    ArrayList<Integer> shape = new ArrayList<>();
                    dfs(grid, i, j, shape);
                    shapes.add(canonical(grid, shape));
                }
            }
        }
        return shapes.size();
    }

    public static void dfs(int[][] grid, int r, int c, ArrayList<Integer> shape){
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) return;
        grid[r][c] = 0; //mark as visited
        shape.add(r*grid[0].length + c);
        dfs(grid, r+1, c, shape);
        dfs(grid, r-1, c, shape);
        dfs(grid, r, c+1, shape);
        dfs(grid, r, c-1, shape);
    }

    public static String canonical(int[][] grid, ArrayList<Integer> shape){
        String res = "";
        int lift = grid.length + grid[0].length;
        int[] out = new int[shape.size()];
        int[] xs = new int[shape.size()];
        int[] ys = new int[shape.size()];
        for (int c = 0; c < 8; c++){
            int t = 0;
            for (int z: shape){
                int x = z / grid[0].length;
                int y = z % grid[0].length;
                //x y, x -y, -x y, -x -y
                //y x, y -x, -y x, -y -x
                xs[t] = c <= 1 ? x : c <= 3 ? -x : c <= 5 ? y : -y;
                ys[t] = c <= 3 ? (c % 2 == 0 ? y : -y) : (c % 2 == 0 ? x : -x);
                t++;
            }

            int mx = xs[0], my = ys[0];
            for (int x: xs) mx = Math.min(mx, x);
            for (int y: ys) my = Math.min(my, y);
            for (int j = 0; j < shape.size(); j++){
                out[j] = (xs[j] - mx) * lift + (ys[j] - my);
            }
            Arrays.sort(out);
            String candidate = Arrays.toString(out);
            if (res.compareTo(candidate) < 0) res = candidate;
        }
        return res;
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
