package Parentheses;

/**
 * @author HailongZeng
 * @date 12/21/19 6:18 下午
 */

import java.util.Scanner;
import java.util.Stack;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * Example 4:
 *
 * Input: s = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s[i] is one of  '(' , ')' and lowercase English letters.
 */
public class No1249_Minimum_Remove_to_Make_Valid_Parentheses {

    public static String minRemoveToMakeValid(String s){
        Stack<Integer> left = new Stack<>();//save the index of left bracket
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '('){
                sb.append(c);
                left.push(sb.length() - 1);
            }else if (c == ')'){
                if (!left.isEmpty()){
                    left.pop(); //remove the index of left bracket which help to make valid bracket with current ')'
                    sb.append(c);
                }else{
                    continue; //if no left bracket existed in left stack, just not add right bracket
                }
            }else{
                sb.append(c);
            }
        }
        while (!left.isEmpty()){
            sb.deleteCharAt(left.pop()); //delete all the left bracket existed in left stack
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String s = st.nextLine();
            System.out.println(minRemoveToMakeValid(s));
        }
    }
}
