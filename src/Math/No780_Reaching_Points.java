package Math;

/**
 * @author HailongZeng
 * @date 12/16/19 9:20 上午
 */

import java.util.Scanner;

/**
 * A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).
 *
 * Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.
 *
 * Examples:
 * Input: sx = 1, sy = 1, tx = 3, ty = 5
 * Output: True
 * Explanation:
 * One series of moves that transforms the starting point to the target is:
 * (1, 1) -> (1, 2)
 * (1, 2) -> (3, 2)
 * (3, 2) -> (3, 5)
 *
 * Input: sx = 1, sy = 1, tx = 2, ty = 2
 * Output: False
 *
 * Input: sx = 1, sy = 1, tx = 1, ty = 1
 * Output: True
 *
 * Note:
 *
 * sx, sy, tx, ty will all be integers in the range [1, 10^9].
 */
public class No780_Reaching_Points {

    public static boolean reachingPoints(int sx, int sy, int tx, int ty){
        if (sx > tx || sy > ty) return false;
        if (sx == tx) return (ty-sy)%tx==0;
        if (sy == ty) return (tx-sx)%ty==0;
        if (tx > ty) return reachingPoints(sx, sy, tx%ty, ty);
        else if(tx < ty) return reachingPoints(sx, sy, tx, ty%tx);
        else return false;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt(); //1 1 3 5; 9 5 12 8
        for (int i = 0; i < N; i++){
            int sx = st.nextInt();
            int sy = st.nextInt();
            int tx = st.nextInt();
            int ty = st.nextInt();
            System.out.println(reachingPoints(sx, sy, tx, ty));
        }
    }
}
