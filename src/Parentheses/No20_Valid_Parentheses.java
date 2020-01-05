package Parentheses;

/**
 * @author HailongZeng
 * @date 12/20/19 1:25 上午
 */

import java.util.Scanner;
import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 * Example 2:
 *
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: "(]"
 * Output: false
 * Example 4:
 *
 * Input: "([)]"
 * Output: false
 * Example 5:
 *
 * Input: "{[]}"
 * Output: true
 */
public class No20_Valid_Parentheses {

    public static boolean isValid(String s){
        if (s.length() == 0 || s == null) return true;
        Stack<Character> stack = new Stack<>();//only save '(','[','{'
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
                stack.push(s.charAt(i));
            }else{
                if (s.isEmpty()) return false;
                else if(s.charAt(i) == ')'){
                    if (stack.peek() == '(') stack.pop();
                    else return false;
                }else if(s.charAt(i) == ']'){
                    if (stack.peek() == '[') stack.pop();
                    else return false;
                }else if(s.charAt(i) == '}'){
                    if (stack.peek() == '{') stack.pop();
                    else return false;
                }
            }
        }
        return stack.isEmpty(); //if stack is not empty, return false; otherwise, return true
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String s = st.nextLine();
            System.out.println(isValid(s));
        }
    }
}
