package Math;

/**
 * @author HailongZeng
 * @date 12/12/19 2:15 下午
 */

import java.util.Scanner;

/**
 * You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move your direction changes counter-clockwise.
 *
 * Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.
 *
 *
 *
 * Example 1:
 *
 * ┌───┐
 * │   │
 * └───┼──>
 *     │
 *
 * Input: [2,1,1,2]
 * Output: true
 * Example 2:
 *
 * ┌──────┐
 * │      │
 * │
 * │
 * └────────────>
 *
 * Input: [1,2,3,4]
 * Output: false
 * Example 3:
 *
 * ┌───┐
 * │   │
 * └───┼>
 *
 * Input: [1,1,1,1]
 * Output: true
 */
public class No335_Self_Crossing {

    public static boolean isSelfCrossing(int[] x){
        if (x == null || x.length < 4) return false;
        int n = x.length;
        int i = 2;
        while (i < n && x[i] > x[i-2]){
            i++;
        }
        if (i == n) return false;
        if (i >= 4 && x[i] >= x[i-2] - x[i-4]){
            x[i-1] = x[i-1] - x[i-3];
        }
        if (i == 3 && x[i] == x[i-2]){
            x[i-1] = x[i-1] - x[i-3];
        }
        i++;
        while (i < n && x[i] < x[i-2]){
            i++;
        }
        if (i == n) return false;
        else return true;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int numOfTest = st.nextInt();
        for (int t = 0; t < numOfTest; t++) {
            int n = st.nextInt();
            int[] x = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = st.nextInt();
            }
            System.out.println(isSelfCrossing(x));
        }
    }
}
