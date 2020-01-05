package Math;

/**
 * @author HailongZeng
 * @date 12/16/19 9:37 上午
 */

import java.util.Scanner;

/**
 * You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available. You need to determine whether it is possible to measure exactly z litres using these two jugs.
 *
 * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
 *
 * Operations allowed:
 *
 * Fill any of the jugs completely with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
 * Example 1: (From the famous "Die Hard" example)
 *
 * Input: x = 3, y = 5, z = 4
 * Output: True
 * Example 2:
 *
 * Input: x = 2, y = 6, z = 5
 * Output: False
 */
public class No365_Water_and_Jug_Problem {

    //1.如果x和y的容量相等，则只需要找到z=px即可
    //2.如果x>y，则需要z=t+px即可
    //3.如果x<y，则需要z=t+py即可
    //总之，z = ax + by，a, b为整数，如果u是x和y的最大公约数，x=mu, y=nu, z=(am+bn)u
    public static boolean canMeasureWater(int x, int y, int z){
        return z >= 0 && z <= (x + y) && z%(gcd(x, y))==0;
    }

    public static int gcd(int x, int y){
        if (y==0) return x;
        return gcd(y, x%y);
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int x = st.nextInt();
            int y = st.nextInt();
            int z = st.nextInt();
            System.out.println(canMeasureWater(x, y, z));
        }
    }
}
