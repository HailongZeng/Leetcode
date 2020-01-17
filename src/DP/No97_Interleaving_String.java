package DP;

/**
 * @author HailongZeng
 * @date 1/6/20 7:17 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * Example 1:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
public class No97_Interleaving_String {

    //dp[i][j] means s3.substring(0,i+j-1)由s1.substring(0,i) and s2.substring(0,j)组成时有效
    //dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-2)) || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-2))
    //time:O(m*n)  space:O(m*n)
    //可以优化空间，因为dp[j] only depend on dp[j-1] and previous dp[j]
    //dp[j] = (dp[j] && s1.charAt(i-1) == s3.charAt(i+j-2)) || (dp[j-1] && s2.charAt(j-1) == s3.charAt(i+j-2))
    //if i == 0, dp[j] = (dp[j] && s1.charAt(i-1) == s3.charAt(i+j-2))
    //if j == 0, dp[j] = (dp[j-1] && s2.charAt(j-1) == s3.charAt(i+j-2))
    public static boolean isInterleave(String s1, String s2, String s3){
        if (s1.length() + s2.length() != s3.length()) return false;
        int m = s1.length(), n = s2.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++){
            if (s1.charAt(i-1) == s3.charAt(i-1)) dp[i][0] = dp[i-1][0];
        }
        for (int i = 1; i <= n; i++){
            if (s2.charAt(i-1) == s3.charAt(i-1)) dp[0][i] = dp[0][i-1];
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            String s1 = line;
            line = io.readLine();
            String s2 = line;
            line = io.readLine();
            String s3 = line;
            boolean res = isInterleave(s1, s2, s3);
            System.out.println(res);
        }
    }
}
