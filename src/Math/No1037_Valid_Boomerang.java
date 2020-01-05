package Math;

/**
 * @author HailongZeng
 * @date 12/23/19 11:32 下午
 */

import java.util.Scanner;

/**
 * A boomerang is a set of 3 points that are all distinct and not in a straight line.
 *
 * Given a list of three points in the plane, return whether these points are a boomerang.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,1],[2,3],[3,2]]
 * Output: true
 * Example 2:
 *
 * Input: [[1,1],[2,2],[3,3]]
 * Output: false
 *
 *
 * Note:
 *
 * points.length == 3
 * points[i].length == 2
 * 0 <= points[i][j] <= 100
 */
public class No1037_Valid_Boomerang {

    //(x1, y1) (x2, y2)  (x3, y3)
    //(x1-x2)/(y1-y2) == (x1-x3)/(y1-y3)--->(x1-x2)(y1-y3) == (x1-x3)(y1-y2)
    public static boolean isBoomerang(int[][] points){
        if (points[0] == points[1] || points[0] == points[2] || points[1] == points[2]) return false;
        if ((points[0][0] - points[1][0])*(points[0][1] - points[2][1]) != (points[0][0] - points[2][0])*(points[0][1] - points[1][1])) return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int[][] points = new int[3][2];
            for (int j = 0; j < 3; j++){
                points[j][0] = st.nextInt();
                points[j][1] = st.nextInt();
            }
            System.out.println(isBoomerang(points));
        }
    }
}
