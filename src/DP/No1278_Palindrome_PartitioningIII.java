package DP;

/**
 * You are given a string s containing lowercase letters and an integer k. You need to :
 *
 * First, change some characters of s to other lowercase English letters.
 * Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
 * Return the minimal number of characters that you need to change to divide the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", k = 2
 * Output: 1
 * Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
 * Example 2:
 *
 * Input: s = "aabbc", k = 3
 * Output: 0
 * Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
 * Example 3:
 *
 * Input: s = "leetcode", k = 8
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= k <= s.length <= 100.
 * s only contains lowercase English letters.
 */
import java.util.*;
public class No1278_Palindrome_PartitioningIII {

    //dp[i][k]表示把字符串s[0, i]分成k组所需要的最小字符改变, i >= k
    //dp[i][k] = min(dp[i][k], dp[j][k-1] + cost(j+1,i))  j=0~i
    //cost(j+1,i)表示把s[j+1,i]变成回文字符串所需要的字符改变数
    //cost(j+1,i) = cost(j+2,i-1) + (s[j+1] != s[i])
    public static int palindromePartition(String s, int k){
        int n = s.length();
        int[][] cost = new int[n][n];
        for (int l = 2; l <= n; l++){
            for (int i = 0, j = l - 1; j < n; i++, j++){
                if(s.charAt(i) == s.charAt(j)){
                    cost[i][j] = cost[i+1][j-1];
                }else{
                    cost[i][j] = cost[i+1][j-1] + 1;
                }
            }
        }


        int[][] dp = new int[n][k+1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }

        for (int i = 0; i < n; i++){
            dp[i][1] = cost[0][i];
            for (int K = 2; K <= k; K++){
                for (int j = 0; j < i; j++){
                    dp[i][K] = Math.min(dp[i][K], dp[j][K-1] + cost[j+1][i]);
                }
            }
        }
        return dp[n-1][k];
    }

    public static void main(String[] args) {
        String s1 = "abc";
        int k1 = 2;
        String s2 = "aabbc";
        int k2 = 3;
        String s3 = "leetcode";
        int k3 = 8;
        System.out.println(palindromePartition(s1, k1));
        System.out.println(palindromePartition(s2, k2));
        System.out.println(palindromePartition(s3, k3));
    }
}
