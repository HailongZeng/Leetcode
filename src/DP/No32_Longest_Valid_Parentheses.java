package DP;

/**
 * @author HailongZeng
 * @date 12/21/19 12:54 下午
 */

import java.util.Scanner;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 *
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class No32_Longest_Valid_Parentheses {

    //DP, time: O(n)  space: O(n)
    //dp[i] represents the longest valid parentheses with s.charAt(i) as the end one
    //if s.charAt(i) == ')' and s.charAt(i-1) == '('  dp[i] = dp[i-2]+2
    //if s.charAt(i) == ')' and s.charAt(i-1) == ')' and s.charAt(i - dp[i-1] - 1) == '('  dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2
    public static int longestValidParentheses(String s){
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i) == ')'){
                if (s.charAt(i-1) == '(') dp[i] = (i-2 >= 0 ? dp[i-2] : 0) + 2;
                else if (i - dp[i-1] > 0 && s.charAt(i-dp[i-1]-1) == '('){
                    dp[i] = dp[i-1] + (i - dp[i-1] >= 2 ? dp[i-dp[i-1]-2] : 0) + 2;
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String s = st.nextLine();
            System.out.println(longestValidParentheses(s));
        }
    }
}
