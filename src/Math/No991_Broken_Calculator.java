package Math;

/**
 * @author HailongZeng
 * @date 1/1/20 1:24 下午
 */

/**
 * On a broken calculator that has a number showing on its display, we can perform two operations:
 *
 * Double: Multiply the number on the display by 2, or;
 * Decrement: Subtract 1 from the number on the display.
 * Initially, the calculator is displaying the number X.
 *
 * Return the minimum number of operations needed to display the number Y.
 *
 *
 *
 * Example 1:
 *
 * Input: X = 2, Y = 3
 * Output: 2
 * Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.
 * Example 2:
 *
 * Input: X = 5, Y = 8
 * Output: 2
 * Explanation: Use decrement and then double {5 -> 4 -> 8}.
 * Example 3:
 *
 * Input: X = 3, Y = 10
 * Output: 3
 * Explanation:  Use double, decrement and double {3 -> 6 -> 5 -> 10}.
 * Example 4:
 *
 * Input: X = 1024, Y = 1
 * Output: 1023
 * Explanation: Use decrement operations 1023 times.
 *
 *
 * Note:
 *
 * 1 <= X <= 10^9
 * 1 <= Y <= 10^9
 */

import java.util.Scanner;

/**
 * If say Y is even, then if we perform 2 additions and one division, we could instead perform one division and one addition for less operations [(Y+2) / 2 vs Y/2 + 1].
 *
 * If say Y is odd, then if we perform 3 additions and one division, we could instead perform 1 addition, 1 division, and 1 addition for less operations [(Y+3) / 2 vs (Y+1) / 2 + 1].
 */
public class No991_Broken_Calculator {

    //recursive
    public static int brokenCalc1(int X, int Y){
        if (X >= Y) return X-Y;
        if (Y % 2 != 0) return 1+brokenCalc1(X, Y+1);
        else return 1+brokenCalc1(X, Y/2);
    }

    //iteration
    public static int brokenCalc2(int X, int Y){
        int res = 0;
        while (Y > X){
            res++;
            if (Y % 2 == 1) Y++;
            else Y /= 2;
        }
        return res+X-Y;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int X = st.nextInt();
            int Y = st.nextInt();
            System.out.println(brokenCalc2(X, Y));
        }
    }
}
