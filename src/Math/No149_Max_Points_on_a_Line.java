package Math;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * Example 1:
 *
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 * Example 2:
 *
 * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * Explanation:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */

public class No149_Max_Points_on_a_Line {

    public static int maxPoints(int[][] points){
        int l = points.length;
        if (l == 0) return 0;
        if (l <= 2) return l;
        int res = 0;
        for (int i = 0; i < l - 1; i++){
            HashMap<String, Integer> map = new HashMap<>();
            int overlap = 0;
            int lineMax = 0;
            for (int j = i + 1; j < l; j++){
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                if (dx == 0 && dy == 0){
                    overlap++;
                    continue;
                }
                int c = gcd(dx, dy);
                dx /= c;
                dy /= c;
                String slope = String.valueOf(dx) + String.valueOf(dy);
                int count = map.getOrDefault(slope, 0);
                count++;
                map.put(slope, count);
                lineMax = Math.max(lineMax, count);
            }
            res = Math.max(res, lineMax + overlap + 1);
        }
        return res;
    }

    public static int gcd(int a, int b){
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int M = st.nextInt();
        int[][] points = new int[M][2];
        for (int i = 0; i < M; i++){
            for (int j = 0; j < 2; j++){
                points[i][j] = st.nextInt();
            }
        }

        System.out.println(maxPoints(points));
    }
}
