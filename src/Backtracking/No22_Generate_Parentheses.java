package Backtracking;

/**
 * @author HailongZeng
 * @date 12/19/19 11:55 下午
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class No22_Generate_Parentheses {

    //Catalan number 1/(n+1).C(2n, n)， which is bounded asymptotically by 4^n/(n(n^0.5))
    public static List<String> generateParenthesis(int n){
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        helper(res, "", 0, 0, n);
        return res;
    }

    public static void helper(List<String> res, String cur, int open, int close, int n){
        if (cur.length() == 2*n) {
            res.add(cur);
            return;
        }
        if (open < n){
            helper(res, cur+"(", open+1, close, n);
        }
        if (close < open){
            helper(res, cur+")", open, close+1, n);
        }
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            System.out.println(generateParenthesis(n));
        }
    }
}
