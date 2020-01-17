package Math;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author HailongZeng
 * @date 1/6/20 6:43 下午
 */

/**
 * A string S of lowercase letters is given.  Then, we may make any number of moves.
 *
 * In each move, we choose one of the first K letters (starting from the left), remove it, and place it at the end of the string.
 *
 * Return the lexicographically smallest string we could have after any number of moves.
 *
 *
 *
 * Example 1:
 *
 * Input: S = "cba", K = 1
 * Output: "acb"
 * Explanation:
 * In the first move, we move the 1st character ("c") to the end, obtaining the string "bac".
 * In the second move, we move the 1st character ("b") to the end, obtaining the final result "acb".
 * Example 2:
 *
 * Input: S = "baaca", K = 3
 * Output: "aaabc"
 * Explanation:
 * In the first move, we move the 1st character ("b") to the end, obtaining the string "aacab".
 * In the second move, we move the 3rd character ("c") to the end, obtaining the final result "aaabc".
 *
 *
 * Note:
 *
 * 1 <= K <= S.length <= 1000
 * S consists of lowercase letters only.
 */
public class No899_Orderly_Queue {

    //if K = 1, it can only rotate
    //if K > 1, for example K = 2, it can swap any two adjacent elements
    //K = 2, "cba"->"cab"->"abc"
    public static String orderlyQueue(String S, int K){
        if (K == 1){
            String res = S;
            for (int i = 0; i < S.length(); i++){
                String T = S.substring(i) + S.substring(0, i);
                if (T.compareTo(res) < 0) res = T;
            }
            return res;
        }else{
            char[] ca = S.toCharArray();
            Arrays.sort(ca);
            return new String(ca);
        }
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String S = st.nextLine();
            int K = Integer.parseInt(st.nextLine());
            String res = orderlyQueue(S, K);
            System.out.println(res);
        }
    }
}
