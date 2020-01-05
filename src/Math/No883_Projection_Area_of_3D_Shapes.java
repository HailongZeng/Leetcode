package Math;

/**
 * @author HailongZeng
 * @date 12/23/19 11:40 下午
 */

import java.util.Scanner;

/**
 * On a N * N grid, we place some 1 * 1 * 1 cubes that are axis-aligned with the x, y, and z axes.
 *
 * Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
 *
 * Now we view the projection of these cubes onto the xy, yz, and zx planes.
 *
 * A projection is like a shadow, that maps our 3 dimensional figure to a 2 dimensional plane.
 *
 * Here, we are viewing the "shadow" when looking at the cubes from the top, the front, and the side.
 *
 * Return the total area of all three projections.
 *
 *
 *
 * Example 1:
 *
 * Input: [[2]]
 * Output: 5
 * Example 2:
 *
 * Input: [[1,2],[3,4]]
 * Output: 17
 * Explanation:
 * Here are the three projections ("shadows") of the shape made with each axis-aligned plane.
 *
 * Example 3:
 *
 * Input: [[1,0],[0,2]]
 * Output: 8
 * Example 4:
 *
 * Input: [[1,1,1],[1,0,1],[1,1,1]]
 * Output: 14
 * Example 5:
 *
 * Input: [[2,2,2],[2,1,2],[2,2,2]]
 * Output: 21
 *
 *
 * Note:
 *
 * 1 <= grid.length = grid[0].length <= 50
 * 0 <= grid[i][j] <= 50
 */
public class No883_Projection_Area_of_3D_Shapes {

    //针对不管是长方体还是正方体
    public static int projectionArea1(int[][] grid){
        int xy = 0;
        int yz = 0; //同一个横坐标下的所有纵坐标对应的最大值
        int xz = 0; //同一个纵坐标下的所有纵坐标对应的最大值
        for (int i = 0; i < grid.length; i++){
            int yMax = 0;
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] > 0) xy += 1;
                yMax = Math.max(yMax, grid[i][j]);
            }
            yz += yMax;
        }

        for (int j = 0; j < grid[0].length; j++){
            int xMax = 0;
            for (int i = 0; i < grid.length; i++){
                xMax = Math.max(xMax, grid[i][j]);
            }
            xz += xMax;
        }

        return xy+yz+xz;
    }

    //针对N*N
    public int projectionArea2(int[][] grid) {
        int xy = 0;
        int yz = 0; //同一个横坐标下的所有纵坐标对应的最大值
        int xz = 0; //同一个纵坐标下的所有纵坐标对应的最大值
        for (int i = 0; i < grid.length; i++){
            int yMax = 0;
            int xMax = 0;
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] > 0) xy += 1;
                yMax = Math.max(yMax, grid[i][j]);
                xMax = Math.max(xMax, grid[j][i]);
            }
            yz += yMax;
            xz += xMax;
        }
        return xy+yz+xz;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int[][] grid = new int[n][n];
            for (int j = 0; j < n; j++){
                for (int k = 0; k < n; k++){
                    grid[j][k] = st.nextInt();
                }
            }
            System.out.println(projectionArea1(grid));
        }
    }
}
