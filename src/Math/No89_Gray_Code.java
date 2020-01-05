package Math;

/**
 * @author HailongZeng
 * @date 1/1/20 1:15 下午
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 *
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 *
 * Example 1:
 *
 * Input: 2
 * Output: [0,1,3,2]
 * Explanation:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 *
 * For a given n, a gray code sequence may not be uniquely defined.
 * For example, [0,2,3,1] is also a valid gray code sequence.
 *
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * Example 2:
 *
 * Input: 0
 * Output: [0]
 * Explanation: We define the gray code sequence to begin with 0.
 *              A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
 *              Therefore, for n = 0 the gray code sequence is [0].
 */
public class No89_Gray_Code {

    public static List<Integer> grayCode1(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; i++){
            int m = res.size();
            int c = 1<<i;
            for (int j = m - 1; j >= 0; j--){
                res.add(res.get(j) + c);
            }
        }
        return res;
    }

    public static List<Integer> grayCode2(int n){
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1<<n; i++){
            res.add(i ^ (i>>1));
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            System.out.println(grayCode1(n));
        }
    }
}
