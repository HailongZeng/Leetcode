package Parentheses;

/**
 * @author HailongZeng
 * @date 12/20/19 10:45 上午
 */

import java.util.Scanner;
import java.util.Stack;

/**
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:
 *
 * () has score 1
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 *
 *
 * Example 1:
 *
 * Input: "()"
 * Output: 1
 *
 * Example 2:
 *
 * Input: "(())"
 * Output: 2
 *
 * Example 3:
 *
 * Input: "()()"
 * Output: 2
 *
 * Example 4:
 *
 * Input: "(()(()))"
 * Output: 6
 *
 *
 * Note:
 *
 * S is a balanced parentheses string, containing only ( and ).
 * 2 <= S.length <= 50
 */
public class No856_Score_of_Parentheses {

    public static int scoreOfParentheses(String S){
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (char c: S.toCharArray()){
            if (c == '('){
                stack.push(0);
            }else{
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w + Math.max(2*v, 1));//when counting (), we have score of 1
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String S = st.nextLine();
            System.out.println(scoreOfParentheses(S));
        }
    }
}
