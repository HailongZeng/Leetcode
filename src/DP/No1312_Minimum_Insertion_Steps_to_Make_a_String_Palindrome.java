package DP;

/**
 * @author HailongZeng
 * @date 1/5/20 5:30 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a string s. In one step you can insert any character at any index of the string.
 *
 * Return the minimum number of steps to make s palindrome.
 *
 * A Palindrome String is one that reads the same backward as well as forward.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "zzazz"
 * Output: 0
 * Explanation: The string "zzazz" is already palindrome we don't need any insertions.
 * Example 2:
 *
 * Input: s = "mbadm"
 * Output: 2
 * Explanation: String can be "mbdadbm" or "mdbabdm".
 * Example 3:
 *
 * Input: s = "leetcode"
 * Output: 5
 * Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 * Example 4:
 *
 * Input: s = "g"
 * Output: 0
 * Example 5:
 *
 * Input: s = "no"
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * All characters of s are lower case English letters.
 */
public class No1312_Minimum_Insertion_Steps_to_Make_a_String_Palindrome {

    //dp[i][j] represents the minimum insertion step to make s(i,j) to be palindrome
    //if (s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i+1][j-1]
    //else dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1
    public static int minInsertions(String s){
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int l = 2; l <= n; l++){
            for (int i = 0, j = l-1; j < n; i++, j++){
                dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i+1][j-1] : Math.min(dp[i+1][j], dp[i][j-1]) + 1;
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = io.readLine()) != null){
            int res = minInsertions(s);
            System.out.println(res);
        }
    }
}
