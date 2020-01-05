package DP;

/**
 * @author HailongZeng
 * @date 12/18/19 10:29 下午
 */

import java.util.Scanner;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class No132_Palindrome_PartitioningII {

    //DP, cut[i]表示子串[0,i]范围内的最小分割数
    //cut[i] = Math.min(cut[j-1] + 1, cut[i]) if dp[j][i] == true
    //dp[j][i]表示子串[j,i]是否为回文串, dp[j][i] = (s.charAt(j) == s.charAt(i) && (dp[j+1][i-1] || i - j < 2))
    public static int minCut(String s){
        if (s.length() <= 1 || s == null) return 0;
        int n = s.length();
        int[] cut = new int[n];
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++){
            cut[i] = i;
            for (int j = 0; j <= i; j++){
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j+1][i-1])){
                    dp[j][i] = true;
                    cut[i] = (j == 0) ? 0 : Math.min(cut[i], cut[j-1]+1);
                }
            }
        }
        return cut[n-1];
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String s = st.nextLine();
            System.out.println(minCut(s));
        }
    }
}
