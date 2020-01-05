package Parentheses;

/**
 * @author HailongZeng
 * @date 12/22/19 1:04 下午
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 *
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 *
 * Input: ")("
 * Output: [""]
 */
public class No301_Remove_Invalid_Parentheses {

    public static List<String> removeInvalidParentheses(String s){
        List<String> res = new ArrayList<>();
        int l = 0;  //The # of left parentheses to remove
        int r = 0;  //The # of right parentheses to remove
        for (int i = 0; i < s.length(); i++){
            l += (s.charAt(i) == '(') ? 1 : 0;
            if (l == 0){
                r += (s.charAt(i) == ')') ? 1 : 0;
            } else{
                l -= (s.charAt(i) == ')') ? 1 : 0;
            }
        }
        helper(s, 0, l, r, res);
        return res;
    }

    public static void helper(String s, int start, int l, int r, List<String> res){
        //Nothing to remove
        if (l == 0 && r == 0 && isValid(s)){
            res.add(s);
            return;
        }
        for (int i = start; i < s.length(); i++){
            //we only remove the first parentheses if there are consecutive ones to avoid duplications
            if (i != start && s.charAt(i) == s.charAt(i-1)) continue;
            if (s.charAt(i) == '(' || s.charAt(i) == ')'){
                String cur = s.substring(0, i) + s.substring(i+1);
                if (r > 0 && s.charAt(i) == ')') helper(cur, i, l, r - 1, res);
                else if(l > 0 && s.charAt(i) == '(') helper(cur, i, l-1, r, res);
            }
        }
    }

    public static boolean isValid(String s){
        int count = 0;
        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if (ch == '(') count++;
            if (ch == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String s = st.nextLine();
            System.out.println(removeInvalidParentheses(s));
        }
    }
}
