package DP;

/**
 * @author HailongZeng
 * @date 1/1/20 1:35 下午
 */

import java.util.Scanner;

/**
 * Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:
 *
 * Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
 * Paste: You can paste the characters which are copied last time.
 *
 *
 * Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.
 *
 * Example 1:
 *
 * Input: 3
 * Output: 3
 * Explanation:
 * Intitally, we have one character 'A'.
 * In step 1, we use Copy All operation.
 * In step 2, we use Paste operation to get 'AA'.
 * In step 3, we use Paste operation to get 'AAA'.
 *
 *
 * Note:
 *
 * The n will be in the range [1, 1000].
 */
public class No650_2_Keys_Keyboard {

    public static int minSteps1(int n){
        if (n == 1) return 0;
        int res = n; // the maximum step to have n 'A'
        for (int i = n - 1; i > 1; i--){
            if (n % i == 0){
                res = Math.min(res, minSteps1(n / i) + i);
            }
        }
        return res;
    }

    public static int minSteps2(int n){
        int[] dp = new int[n+1];
        for (int i = 2; i <= n; i++){
            dp[i] = i; //initialization
            for (int j = i - 1; j > 1; j--){
                if (i % j == 0){
                    dp[i] = Math.min(dp[i], dp[j] + i/j);
                }
            }
        }
        return dp[n];
    }

    public static int minSteps3(int n){
        int res = 0;
        for (int i = 2; i <= n; i++){
            while (n % i == 0){
                res += i;
                n /= i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            System.out.println(minSteps1(n));
        }
    }
}
