package Parentheses;

/**
 * @author HailongZeng
 * @date 12/20/19 6:58 下午
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

    public static List<String> generateParenthesis(int n){
        List<String> res = new ArrayList<>();
        helper(res, "", 0, 0, n);
        return res;
    }

    public static void helper(List<String> res, String cur, int left, int right, int n){
        if (right == n){
            System.out.println(cur);
            res.add(cur);
            return;
        }
        if (left < n){
            System.out.println("left:"+left+","+right+","+cur);
            helper(res, cur+'(', left+1, right, n);
        }
        if (right < left){
            System.out.println("right:"+left+","+right+","+cur);
            helper(res, cur+')', left, right+1, n);
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
