package Parentheses;

/**
 * @author HailongZeng
 * @date 12/21/19 10:41 上午
 */

import java.util.Scanner;
import java.util.Stack;

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

    //Brute force O(n^3)   space: O(n)
    public static int longestValidParentheses1(String s){
        int res = 0;
        for (int i = 0; i < s.length(); i++){
            for (int j = i+2; j <= s.length(); j += 2){
                if (isValid(s.substring(i, j))){
                    res = Math.max(res, j-i);
                }
            }
        }
        return res;
    }

    public static boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray()){
            if (c == '('){
                stack.push(c);
            }else{
                if (!stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    //DP, dp[i]表示[0,i]以s.charAt(i)结尾的最长有效括号的个数。
    //dp[i] = dp[i-2]+2 if s.charAt(i) == ')' and s.charAt(i-1) == '('
    //dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2 if s.charAt(i-dp[i-1]-1) == '(' if s.charAt(i) == ')' and s.charAt(i-1) == ')'
    //time: O(n) space: O(n)
    public static int longestValidParentheses2(String s){
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i) == ')'){
                if (s.charAt(i-1) == '(') dp[i] = (i -2 >= 0 ? dp[i-2] : 0) + 2;
                else if (i - dp[i-1] > 0 && s.charAt(i-dp[i-1]-1) == '('){
                    dp[i] = dp[i-1] + (i-dp[i-1]-2 >= 0 ? dp[i-dp[i-1]-2] : 0) + 2;
                }
                res = Math.max(dp[i], res);
            }
        }
        return res;
    }

    //Stack, time: O(n)  space: O(n)
    public static int longestValidParentheses3(String s){
        int res = 0;
        Stack<Integer> stack = new Stack<>();  //push the index into stack
        stack.push(-1);
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                stack.push(i);
            }else{
                stack.pop();
                if (stack.isEmpty()){
                    stack.push(i);
                }else{
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    //two pointers   time: O(n) space: O(1)
    public static int longestValidParentheses4(String s){
        int left = 0, right = 0, res = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(') {
                left++;
            }else{
                right++;
            }
            if (left == right){
                res = Math.max(res, 2*right);
            }else if (right > left){
                left = right = 0;
            }
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--){
            if (s.charAt(i) == '('){
                left++;
            }else{
                right++;
            }
            if (left == right) {
                res = Math.max(res, 2 * left);
            }else if(left > right){
                left = right = 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String s = st.nextLine();
            System.out.println(longestValidParentheses1(s));
        }
    }
}
