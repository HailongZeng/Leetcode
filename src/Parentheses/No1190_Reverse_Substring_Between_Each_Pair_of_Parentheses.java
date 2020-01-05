package Parentheses;

/**
 * @author HailongZeng
 * @date 12/22/19 1:32 下午
 */

import java.util.Scanner;
import java.util.Stack;

/**
 * You are given a string s that consists of lower case English letters and brackets.
 *
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 *
 * Your result should not contain any brackets.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(abcd)"
 * Output: "dcba"
 * Example 2:
 *
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Explanation: The substring "love" is reversed first, then the whole string is reversed.
 * Example 3:
 *
 * Input: s = "(ed(et(oc))el)"
 * Output: "leetcode"
 * Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
 * Example 4:
 *
 * Input: s = "a(bcdefghijkl(mno)p)q"
 * Output: "apmnolkjihgfedcbq"
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 2000
 * s only contains lower case English characters and parentheses.
 * It's guaranteed that all parentheses are balanced.
 */
public class No1190_Reverse_Substring_Between_Each_Pair_of_Parentheses {

    public static String reverseParentheses(String s){
        Stack<StringBuilder> stack = new Stack<>();
        stack.push(new StringBuilder());
        for (char c: s.toCharArray()){
            if (c == '('){
                stack.push(new StringBuilder());//when we occur with left bracket, we push a new StringBuilder
            }else if(c == ')'){
                String inside = stack.pop().reverse().toString();//when occur with right bracket, reverse current StringBuilder
                stack.peek().append(inside);
            }else{
                stack.peek().append(c);
            }
        }
        return stack.peek().toString();
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String s = st.nextLine();
            System.out.println(reverseParentheses(s));
        }
    }
}
