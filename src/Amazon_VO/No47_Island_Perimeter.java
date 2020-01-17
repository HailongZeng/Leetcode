package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/15/20 12:22 上午
 */

/**
 *
 */
//leetcode 463
public class No47_Island_Perimeter {

    /*
    我们对于每个格子的四条边分别来处理，首先看左边的边，只有当左边的边处于第一个位置或者当前格子的左面没有岛格子的时候，左边的边计入周长。其他三条边的分析情况都跟左边的边相似
     */
    public static int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int res = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1) {
                    if (j == 0 || grid[i][j-1] == 0) res++;
                    if (i == 0 || grid[i-1][j] == 0) res++;
                    if (j == n - 1 || grid[i][j+1] == 0) res++;
                    if (i == m - 1 || grid[i+1][j] == 0) res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        };
        int res = islandPerimeter(grid);
        System.out.println(res);
    }
}
