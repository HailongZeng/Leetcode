package Parentheses;

/**
 * @author HailongZeng
 * @date 12/23/19 11:16 上午
 */

import java.util.Scanner;

/**
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 * An empty string is also valid.
 * Example 1:
 *
 * Input: "()"
 * Output: True
 * Example 2:
 *
 * Input: "(*)"
 * Output: True
 * Example 3:
 *
 * Input: "(*))"
 * Output: True
 * Note:
 *
 * The string size will be in the range [1, 100].
 */
public class No678_Valid_Parenthesis_String {

    //lo, hi respectively be the smallest and largest possible number of open left brackets after processing the current character in the string
    public static boolean checkValidString(String s){
        int lo = 0, hi = 0;
        for (char c: s.toCharArray()){
            lo += c == '(' ? 1 : -1;
            hi += c != ')' ? 1 : -1;
            System.out.println(lo + "," + hi);
            if (hi < 0) break;
            lo = Math.max(lo, 0);
        }
        return lo == 0;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String s = st.nextLine();
            System.out.println(checkValidString(s));
        }
    }
}
