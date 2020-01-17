package Math;

/**
 * @author HailongZeng
 * @date 1/8/20 9:15 上午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * We have a sorted set of digits D, a non-empty subset of {'1','2','3','4','5','6','7','8','9'}.  (Note that '0' is not included.)
 *
 * Now, we write numbers using these digits, using each digit as many times as we want.  For example, if D = {'1','3','5'}, we may write numbers such as '13', '551', '1351315'.
 *
 * Return the number of positive integers that can be written (using the digits of D) that are less than or equal to N.
 *
 *
 *
 * Example 1:
 *
 * Input: D = ["1","3","5","7"], N = 100
 * Output: 20
 * Explanation:
 * The 20 numbers that can be written are:
 * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 * Example 2:
 *
 * Input: D = ["1","4","9"], N = 1000000000
 * Output: 29523
 * Explanation:
 * We can write 3 one digit numbers, 9 two digit numbers, 27 three digit numbers,
 * 81 four digit numbers, 243 five digit numbers, 729 six digit numbers,
 * 2187 seven digit numbers, 6561 eight digit numbers, and 19683 nine digit numbers.
 * In total, this is 29523 integers that can be written using the digits of D.
 *
 *
 * Note:
 *
 * D is a subset of digits '1'-'9' in sorted order.
 * 1 <= N <= 10^9
 */
public class No902_Numbers_At_Most_N_Given_Digit_Set {

    //say N has K digits, if we write a valid number with k digits(k < K), then there are (D.length)^k possible numbers we could write, since all of them will definitely be less than N
    //Now, say we are to write a valid K digit number from left to right.
    //1.if the first digit we write is less than the first digit of N, then we could write any numbers after, for a total of (D.length)^(K-1)
    //2.if the first digit we write is the same, then we require that the next digit we write is equal to or lower than the next digit in N.
    //dp[i] represents the number of ways to write a valid number if N became N[i], N[i+1]... For example, if N = 2345, then dp[0] would be the number of valid numbers at most 2345, dp[1] would be the ones at most 345, dp[2] would be the ones at most 45, and dp[3] would be the ones at most 5
    //S = String.valueOf(N)
    //dp[i] = (number of d in D with d < S[i]) * ((D.length) ** (K-i-1)), plus dp[i+1] if S[i] is in D
    //time:O(logN)  space:O(logN)
    public static int atMostNGivenDigitSet(String[] D, int N){
        String S = String.valueOf(N);
        int K = S.length();
        int[] dp = new int[K+1];
        dp[K] = 1;
        for (int i = K-1; i >= 0; i--){
            //compute dp[i]
            int Si = S.charAt(i) - '0';
            for (String d: D){
                if (Integer.valueOf(d) < Si){
                    dp[i] += Math.pow(D.length, K-i-1);
                }else if (Integer.valueOf(d) == Si){
                    dp[i] += dp[i+1];
                }
            }
        }
        for (int i = 1; i < K; i++){
            dp[0] += Math.pow(D.length, i);
        }
        return dp[0];
    }

    public static String[] stringToArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new String[0];
        String[] out = input.split(",");
        return out;
    }

    //[1,3,5,7]
    //100
    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            String[] D = stringToArray(line);
            line = io.readLine();
            int N = Integer.parseInt(line);
            int res = atMostNGivenDigitSet(D, N);
            System.out.println(res);
        }
    }

}
