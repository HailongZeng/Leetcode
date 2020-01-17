package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/14/20 8:13 下午
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * Example:
 *
 * Input:
 *
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * Output: 6
 *
 * Explanation: Given three people living at (0,0), (0,4), and (2,2):
 *              The point (0,2) is an ideal meeting point, as the total travel distance
 *              of 2+2+2=6 is minimal. So return 6.
 */
//leetcode296
//对每个点进行bfs求解
public class No39_Best_Meeting_Point {

    //time:O(mn)  space:O(mn)
    public static int minTotalDistance(int[][] grid) {
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        int row = rows.get(rows.size() / 2);
        int col = cols.get(cols.size() / 2);
        return minDistance1D(rows, row) + minDistance1D(cols, col);
    }

    private static int minDistance1D(List<Integer> points, int origin) {
        int distance = 0;
        for (int point : points) {
            distance += Math.abs(point - origin);
        }
        return distance;
    }

    private static List<Integer> collectRows(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                }
            }
        }
        return rows;
    }

    private static List<Integer> collectCols(int[][] grid) {
        List<Integer> cols = new ArrayList<>();
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] == 1) {
                    cols.add(col);
                }
            }
        }
        return cols;
    }

    //bfs   time:O((mn)^2)  space:O(mn)
    public static int minTotalDistance2(int[][] grid) {
        int minDistance = Integer.MAX_VALUE;
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[0].length; col++){
                int distance = search(grid, row, col);
                minDistance = Math.min(distance, minDistance);
            }
        }
        return minDistance;
    }

    public static int search(int[][] grid, int row, int col){
        Queue<Point> q = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        q.add(new Point(row, col, 0));
        int totalDistance = 0;
        while (!q.isEmpty()){
            Point point = q.poll();
            int r = point.row;
            int c = point.col;
            int d = point.distance;
            if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c]){
                continue;
            }
            if (grid[r][c] == 1){
                totalDistance += d;
            }
            visited[r][c] = true;
            q.add(new Point(r+1, c, d+1));
            q.add(new Point(r-1, c, d+1));
            q.add(new Point(r, c+1, d+1));
            q.add(new Point(r, c-1, d+1));
        }
        return totalDistance;
    }

    public static class Point{
        int row;
        int col;
        int distance;
        public Point(int r, int c, int d){
            row = r;
            col = c;
            distance = d;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };
        int res = minTotalDistance(grid);
        System.out.println(res);
    }
}
