package Greedy;

/**
 * @author HailongZeng
 * @date 1/1/20 10:31 上午
 */

import java.util.Scanner;

/**
 * Given two integers A and B, return any string S such that:
 *
 * S has length A + B and contains exactly A 'a' letters, and exactly B 'b' letters;
 * The substring 'aaa' does not occur in S;
 * The substring 'bbb' does not occur in S.
 *
 *
 * Example 1:
 *
 * Input: A = 1, B = 2
 * Output: "abb"
 * Explanation: "abb", "bab" and "bba" are all correct answers.
 * Example 2:
 *
 * Input: A = 4, B = 1
 * Output: "aabaa"
 *
 *
 * Note:
 *
 * 0 <= A <= 100
 * 0 <= B <= 100
 * It is guaranteed such an S exists for the given A and B.
 */
public class No984_String_Without_AAA_or_BBB {

    public static String strWithout3a3b(int A, int B){
        StringBuilder sb = new StringBuilder();
        if (A > B){
            if (A > 2*B + 2) return "";
            int k = A - B;
            for (int i = 0; i < B; i++){
                sb.append('a');
                if (k > 0) {
                    sb.append('a');
                    k--;
                }
                sb.append('b');
            }
            while (k > 0){
                sb.append('a');
                k--;
            }
        }else{
            if (B > 2*A + 2) return "";
            int k = B - A;
            for (int i = 0; i < A; i++){
                sb.append('b');
                if (k > 0) {
                    sb.append('b');
                    k--;
                }
                sb.append('a');
            }
            while (k > 0){
                sb.append('b');
                k--;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int A = st.nextInt();
            int B = st.nextInt();
            System.out.println(strWithout3a3b(A, B));
        }
    }
}
