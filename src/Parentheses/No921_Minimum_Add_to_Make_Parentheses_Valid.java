package Parentheses;

/**
 * @author HailongZeng
 * @date 12/21/19 12:58 下午
 */

import java.util.Scanner;

/**
 * Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.
 *
 *
 *
 * Example 1:
 *
 * Input: "())"
 * Output: 1
 * Example 2:
 *
 * Input: "((("
 * Output: 3
 * Example 3:
 *
 * Input: "()"
 * Output: 0
 * Example 4:
 *
 * Input: "()))(("
 * Output: 4
 *
 *
 * Note:
 *
 * S.length <= 1000
 * S only consists of '(' and ')' characters.
 */
public class No921_Minimum_Add_to_Make_Parentheses_Valid {

    public static int minAddToMakeValid(String s){
        int res = 0, bal = 0;
        for (int i = 0; i < s.length(); i++){
            bal += s.charAt(i) == '(' ? 1 : -1;
            //it is guaranteed that bal >= -1
            if (bal == -1){
                res++;
                bal++;
            }
        }
        return res + bal;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String s = st.nextLine();
            System.out.println(minAddToMakeValid(s));
        }
    }
}
