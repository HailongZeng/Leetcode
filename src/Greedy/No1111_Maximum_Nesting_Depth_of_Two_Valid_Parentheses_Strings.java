package Greedy;

/**
 * @author HailongZeng
 * @date 12/22/19 8:23 下午
 */

import java.util.Scanner;

/**
 * A string is a valid parentheses string (denoted VPS) if and only if it consists of "(" and ")" characters only, and:
 *
 * It is the empty string, or
 * It can be written as AB (A concatenated with B), where A and B are VPS's, or
 * It can be written as (A), where A is a VPS.
 * We can similarly define the nesting depth depth(S) of any VPS S as follows:
 *
 * depth("") = 0
 * depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's
 * depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
 * For example,  "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.
 *
 *
 *
 * Given a VPS seq, split it into two disjoint subsequences A and B, such that A and B are VPS's (and A.length + B.length = seq.length).
 *
 * Now choose any such A and B such that max(depth(A), depth(B)) is the minimum possible value.
 *
 * Return an answer array (of length seq.length) that encodes such a choice of A and B:  answer[i] = 0 if seq[i] is part of A, else answer[i] = 1.  Note that even though multiple answers may exist, you may return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: seq = "(()())"
 * Output: [0,1,1,1,1,0]
 * Example 2:
 *
 * Input: seq = "()(())()"
 * Output: [0,0,0,1,1,0,1,1]
 *
 *
 * Constraints:
 *
 * 1 <= seq.size <= 10000
 */
public class No1111_Maximum_Nesting_Depth_of_Two_Valid_Parentheses_Strings {

    //Step1: solve depth(seq), and then split it into two parts with closet depth, depth(A) = depth(seq) / 2; depth(B) = depth(seq) - depth(B)
    public static int[] maxDepthAfterSplit(String seq){
        int[] res = new int[seq.length()];
        int level = 0;

        for(int i = 0; i < res.length; i++){
            if(seq.charAt(i) == '('){
                res[i] = level%2;
                level++;
            }
            else{
                level--;
                res[i] = level%2;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String seq = st.nextLine();
            System.out.println(maxDepthAfterSplit(seq));
        }
    }
}
