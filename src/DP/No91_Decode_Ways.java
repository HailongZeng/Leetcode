package DP;

/**
 * @author HailongZeng
 * @date 1/6/20 7:46 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class No91_Decode_Ways {

    //dp[i]表示s.substring(0,i)有多少种decoding方法  前i个字符  the length of dp is n+1
    //"x" cannot be zero; "xx" cannot be greater than 26; "ab"--'a' cannot be zero
    //initialization:dp[0] = 1
    //note: if s.charAt(0) == '0'  (leading zero)  return 0 directly.
    //if (s.charAt(i-1) == '0') dp[i] = 0, else dp[i] = dp[i-1];
    //if (i > 1 && s.charAt(i-2) == '1' || (s.charAt(i-2) == '2' && s.charAt(i-1) <= '6')) dp[i] += dp[i-2]
    //time:O(n)  space:O(n)
    public static int numDecodings1(String s){
        if (s.isEmpty() || s.charAt(0) == '0') return 0;
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++){
            dp[i] = s.charAt(i-1) == '0' ? 0 : dp[i-1];
            if (i > 1 && s.charAt(i-2) == '1' || (s.charAt(i-2) == '2' && s.charAt(i-1) <= '6')){
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }

    //time:O(n)  space:O(1)
    //a表示s[i-1]的解码方法个数，b表示s[i-2]的解码方法个数
    public static int numDecodings2(String s){
        if (s.isEmpty() || s.charAt(0) == '0') return 0;
        int a = 1, b = 1, n = s.length();
        for (int i = 1; i < n; i++){
            if (s.charAt(i) == '0') a = 0; //x0
            if (s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && s.charAt(i) <= '6')){//1x or 2x when x<='6'
                int tmp = a;
                a = a + b;
                b = tmp;
            } else{
                b = a;
            }
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = io.readLine()) != null){
            int res = numDecodings1(s);
            System.out.println(res);
        }
    }
}
