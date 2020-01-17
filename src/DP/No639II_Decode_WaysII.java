package DP;

/**
 * @author HailongZeng
 * @date 1/6/20 10:07 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
 *
 * Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
 *
 * Also, since the answer may be very large, you should return the output mod 109 + 7.
 *
 * Example 1:
 *
 * Input: "*"
 * Output: 9
 * Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 * Example 2:
 *
 * Input: "1*"
 * Output: 9 + 9 = 18
 * Note:
 *
 * The length of the input string will fit in range [1, 105].
 * The input string will only contain the character '*' and digits '0' - '9'.
 */
public class No639II_Decode_WaysII {

    public static final int M = 1000000007;
    //time:O(n)  space:O(n)
    public static int numDecodings1(String s){
        Integer[] memo = new Integer[s.length()];
        return ways(s, s.length()-1, memo);
    }

    public static int ways(String s, int i, Integer[] memo) {
        if (i < 0) return 1;
        if (memo[i] != null) return memo[i];
        if (s.charAt(i) == '*'){
            long res = 9 * ways(s, i-1, memo);
            if (i > 0 && s.charAt(i-1) == '1') res = (res + 9 * ways(s, i-2, memo)) % M;
            else if (i > 0 && s.charAt(i-1) == '2') res = (res + 6 * ways(s, i-2, memo)) % M;
            else if (i > 0 && s.charAt(i-1) == '*') res = (res + 15 * ways(s, i-2, memo)) % M;
            memo[i] = (int)res;
            return memo[i];
        }
        long res = s.charAt(i) != '0' ? ways(s, i-1, memo) : 0;
        if (i > 0 && s.charAt(i-1) == '1') res = (res + ways(s, i-2, memo)) % M;
        else if (i > 0 && s.charAt(i-1) == '2' && s.charAt(i) <= '6') res = (res + ways(s, i-2, memo)) % M;
        else if (i > 0 && s.charAt(i-1) == '*') res = (res + (s.charAt(i)<='6' ? 2 : 1) * ways(s, i-2, memo)) % M;
        memo[i] = (int)res;
        return memo[i];
    }

    //dp[i]表示前i个字符的decoding数目  dp是一个n+1长度的数组
    //time:O(n)  space:O(n)
    public static int numDecodings2(String s){
        if (s.charAt(0) == '0') return 0;
        long[] dp = new long[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '*' ? 9 : 1;
        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i) == '*'){
                dp[i+1] = 9*dp[i];
                if (s.charAt(i-1) == '1'){
                    dp[i+1] = (dp[i+1] + 9*dp[i-1]) % M;
                }else if(s.charAt(i-1) == '2'){
                    dp[i+1] = (dp[i+1] + 6*dp[i-1]) % M;
                }else if(s.charAt(i-1) == '*'){
                    dp[i+1] = (dp[i+1] + 15*dp[i-1]) % M;
                }
            }else{
                dp[i+1] = s.charAt(i) != '0' ? dp[i] : 0;
                if (s.charAt(i-1) == '1'){
                    dp[i+1] = (dp[i+1] + dp[i-1]) % M;
                }else if (s.charAt(i-1) == '2' && s.charAt(i) <= '6'){
                    dp[i+1] = (dp[i+1] + dp[i-1]) % M;
                }else if (s.charAt(i-1) == '*'){
                    dp[i+1] = (dp[i+1] + (s.charAt(i) <= '6' ? 2 : 1) * dp[i-1]) % M;
                }
            }
        }
        return (int)dp[s.length()];
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
