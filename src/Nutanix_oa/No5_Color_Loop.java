package Nutanix_oa;

/**
 * @author HailongZeng
 * @date 1/10/20 11:01 上午
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D array consisting of elements of different values, how to detect if there is a loop consisting of same value? A loop is defined to have two or more adjacent element with same value and has the same starting and end point.
 *
 * Input:
 *
 * ["B", "B", "B", "B", "B"],
 * ["B", "G", "G", "G", "B"],
 * ["B", "G", "B", "G", "B"],
 * ["B", "G", "G", "G", "B"],
 * ["B", "B", "B", "B", "B"]
 *
 * Output:
 * Yes : Values "B" and "G" form a loop
 *
 * Input:
 * ["A", "A", "A", "A"],
 * ["A", "G", "B", "A"],
 * ["A", "A", "B", "A"]
 *
 * Output:
 * No : None of the elements form a closed loop
 */
public class No5_Color_Loop {

    public static int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static List<Character> findCircle(char[][] grid){
        List<Character> res = new ArrayList<>();
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        boolean[][] visiting = new boolean[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (!visited[i][j]){
                    visited[i][j] = true;
                    dfs(grid, res, visited, visiting, i, j);
                    markVisited(visiting, visited);
                }
            }
        }
        return res;
    }

    private static void dfs(char[][] grid, List<Character> res, boolean[][] visited, boolean[][] visiting, int i, int j) {
        visiting[i][j] = true;
        for (int[] d: dirs){
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length && grid[ni][nj] == grid[i][j]){
                if (!visiting[ni][nj]){
                    dfs(grid, res, visited, visiting, ni, nj);
                }else{
                    if (visited[i][j]){
                        res.add(grid[i][j]);
                        markVisited(visiting, visited);
                    }
                }
            }
        }
    }

    private static void markVisited(boolean[][] visiting, boolean[][] visited) {
        for (int i = 0; i < visiting.length; i++){
            for (int j = 0; j < visiting[0].length; j++){
                if (visiting[i][j]){
                    visited[i][j] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid1 = {{'B', 'B', 'B', 'B', 'B'},
                {'B', 'G', 'G', 'G', 'B'},
                {'B', 'G', 'B', 'G', 'B'},
                {'B', 'G', 'G', 'G', 'B'},
                {'B', 'B', 'B', 'B', 'B'}};
        char[][] grid2 = {{'A', 'A', 'A', 'A'},
                {'A', 'G', 'B', 'A'},
                {'A', 'A', 'B', 'A'}};

        System.out.println(findCircle(grid1));
        System.out.println(findCircle(grid2));
    }
}
