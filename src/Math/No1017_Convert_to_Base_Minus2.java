package Math;

/**
 * @author HailongZeng
 * @date 12/25/19 1:51 下午
 */

import java.util.Collections;
import java.util.Scanner;

/**
 * Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).
 *
 * The returned string must have no leading zeroes, unless the string is "0".
 *
 *
 *
 * Example 1:
 *
 * Input: 2
 * Output: "110"
 * Explantion: (-2) ^ 2 + (-2) ^ 1 = 2
 * Example 2:
 *
 * Input: 3
 * Output: "111"
 * Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
 * Example 3:
 *
 * Input: 4
 * Output: "100"
 * Explantion: (-2) ^ 2 = 4
 *
 *
 * Note:
 *
 * 0 <= N <= 10^9
 */
public class No1017_Convert_to_Base_Minus2 {

    public static String baseNeg2(int N){
        if (N == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (N != 0){
            int remainder = N % (-2);
            N /= (-2);
            if (remainder < 0){  //-5%(-3) = 1...-2   should be 2...1
                N += 1;
                remainder += Math.abs(2);
            }
            sb.append(remainder);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int testNum = st.nextInt();
        for (int i = 0; i < testNum; i++){
            int N = st.nextInt();
            System.out.println(baseNeg2(N));
        }
    }
}
