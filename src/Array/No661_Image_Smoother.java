package Array;

/**
 * @author HailongZeng
 * @date 1/11/20 1:19 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.
 *
 * Example 1:
 *
 * Input:
 * [[1,1,1],
 *  [1,0,1],
 *  [1,1,1]]
 * Output:
 * [[0, 0, 0],
 *  [0, 0, 0],
 *  [0, 0, 0]]
 * Explanation:
 * For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
 * For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
 * For the point (1,1): floor(8/9) = floor(0.88888889) = 0
 * Note:
 *
 * The value in the given matrix is in the range of [0, 255].
 * The length and width of the given matrix are in the range of [1, 150].
 */
public class No661_Image_Smoother {

    public static int[][] imageSmoother(int[][] M) {
        int R = M.length, C = M[0].length;
        int[][] res = new int[R][C];
        for (int r = 0; r < R; r++){
            for (int c = 0; c < C; c++){
                int count = 0;
                for (int nr = r-1; nr <= r+1; nr++){
                    for (int nc = c-1; nc <= c+1; nc++){
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C){
                            res[r][c] += M[nr][nc];
                            count++;
                        }
                    }
                }
                res[r][c] /= count;
            }
        }
        return res;
    }

    public static String int2dArrayToString(int[][] array) {
        if (array == null) {
            return "null";
        }
        if (array.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int[] item : array) {
            sb.append(String.valueOf(item));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] M = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] res = imageSmoother(M);
        String s = int2dArrayToString(res);
        System.out.println(s);
    }

}
