package Math;

/**
 * @author HailongZeng
 * @date 1/11/20 9:31 下午
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.
 *
 * Return true if and only if we can do this in a way such that the resulting number is a power of 2.
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: true
 * Example 2:
 *
 * Input: 10
 * Output: false
 * Example 3:
 *
 * Input: 16
 * Output: true
 * Example 4:
 *
 * Input: 24
 * Output: false
 * Example 5:
 *
 * Input: 46
 * Output: true
 *
 *
 * Note:
 *
 * 1 <= N <= 10^9
 */
public class No869_Reordered_Power_of_2 {

    public static boolean reorderedPowerOf2(int N){
        String n = String.valueOf(N);
        char[] ns = n.toCharArray();
        Arrays.sort(ns);
        n = String.valueOf(ns);
        for (int i = 0; i <= 30; i++){//for Integer(214783647), only 1~2^30  2^31 = 214783648
            int x = 1<<i;
            String tmp = String.valueOf(x);
            char[] chs = tmp.toCharArray();
            Arrays.sort(chs);
            if (String.valueOf(chs).equals(n)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        Scanner st = new Scanner(System.in);
        int n = st.nextInt();
        for (int i = 0; i < n; i++){
            int N = st.nextInt();
            boolean res = reorderedPowerOf2(N);
            System.out.println(res);
        }
    }
}
