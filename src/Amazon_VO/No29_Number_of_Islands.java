package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/13/20 10:36 下午
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 */
//leetcode200
public class No29_Number_of_Islands {

    //dfs,实际上是求连通分量的个数  time:O(mn)  space:O(mn)
    public static int numIslands1(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int res = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == '1'){
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public static void dfs(char[][] grid, int i, int j){
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0'; //mark as visited
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
    }

    //bfs  time:O(mn)  space:O(min(m,n)
    public static int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int res = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1'){
                    res++;
                    grid[i][j] = '0';  //mark as visited
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.offer(i*n + j);
                    while (!neighbors.isEmpty()){
                        int idx = neighbors.remove();
                        int row = idx / n, col = idx % n;
                        for (int k = 0; k < dirs.length; k++){
                            int x = row + dirs[k][0];
                            int y = col + dirs[k][1];
                            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1'){
                                neighbors.offer(x*n+y);
                                grid[x][y] = '0';//mark as visited
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    public static class UnionFind{
        int count; // # of connected components
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid){
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m*n];
            rank = new int[m*n];
            for (int i = 0; i < m; i++){
                for (int j = 0; j < n; j++){
                    if (grid[i][j] == '1'){
                        parent[i*n+j] = i*n+j;
                        count++;
                    }
                    rank[i*n+j] = 0;
                }
            }
        }

        public int find(int i){//path compression
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y){
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty){
                if (rank[rootx] > rank[rooty]){
                    parent[rooty] = rootx;
                }else if(rank[rootx] < rank[rooty]){
                    parent[rootx] = rooty;
                }else{
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                count--;
            }
        }

        public int getCount(){
            return count;
        }
    }

    //union find(disjoint set)并查集  time:O(m*n)  space:O(m*n)
    public static int numIslands3(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int res = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1'){
                    grid[i][j] = '0'; //mark as visited
                    for (int k = 0; k < dirs.length; k++){
                        int x = i + dirs[k][0];
                        int y = j + dirs[k][1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') uf.union(i*n+j, x*n+j);
                    }
                }
            }
        }
        return uf.getCount();
    }

    public static void main(String[] args) {
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        int res1 = numIslands1(grid1);
        int res2 = numIslands1(grid2);
        System.out.println(res1);
        System.out.println(res2);
    }
}
