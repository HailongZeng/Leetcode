package DP;

import java.util.Scanner;

/**
 * We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?
 *
 * Note that our partition must use every number in A, and that scores are not necessarily integers.
 *
 * Example:
 * Input:
 * A = [9,1,2,3,9]
 * K = 3
 * Output: 20
 * Explanation:
 * The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * We could have also partitioned A into [9, 1], [2], [3, 9], for example.
 * That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 100.
 * 1 <= A[i] <= 10000.
 * 1 <= K <= A.length.
 * Answers within 10^-6 of the correct answer will be accepted as correct.
 */
public class No813_Largest_Sum_of_Averages {

    public static double largestSumOfAverages(int[] A, int K){
        int n = A.length;
        double[] sum = new double[n + 1];
        double[][] dp = new double[K+1][n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + A[i-1];
            dp[1][i] = sum[i] / i;
        }
        for (int k = 2; k <= K; k++){
            for (int i = k; i <= n; i++){
                for (int j = k-1; j < i; j++){
                    dp[k][i] = Math.max(dp[k][i], dp[k-1][j] + (sum[i]-sum[j]) / (i-j));
                }
            }
        }
        return dp[K][n];
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++){
            A[i] = st.nextInt();
        }
        int K = st.nextInt();
        System.out.println(largestSumOfAverages(A, K));
    }
}
