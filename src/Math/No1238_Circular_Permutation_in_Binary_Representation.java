package Math;

/**
 * @author HailongZeng
 * @date 1/1/20 11:30 上午
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Given 2 integers n and start. Your task is return any permutation p of (0,1,2.....,2^n -1) such that :
 *
 * p[0] = start
 * p[i] and p[i+1] differ by only one bit in their binary representation.
 * p[0] and p[2^n -1] must also differ by only one bit in their binary representation.
 *
 *
 * Example 1:
 *
 * Input: n = 2, start = 3
 * Output: [3,2,0,1]
 * Explanation: The binary representation of the permutation is (11,10,00,01).
 * All the adjacent element differ by one bit. Another valid permutation is [3,1,0,2]
 * Example 2:
 *
 * Input: n = 3, start = 2
 * Output: [2,6,7,5,4,0,1,3]
 * Explanation: The binary representation of the permutation is (010,110,111,101,100,000,001,011).
 *
 *
 * Constraints:
 *
 * 1 <= n <= 16
 * 0 <= start < 2 ^ n
 */
public class No1238_Circular_Permutation_in_Binary_Representation {

    //time: O(2^n)  space: O(2^n)
    public static List<Integer> circularPermutation1(int n, int start){
        List<Integer> cur = new ArrayList<>();
        cur.add(0);
        for (int i = 0; i < n; i++){
            int m = cur.size();
            for (int j = m - 1; j >= 0; j--){
                int c = 1<<i;
                cur.add(cur.get(j)+c);
            }
        }
        //circulate
        int i = 0;
        for (; i < cur.size(); i++){
            if (cur.get(i) == start) break;
        }
        List<Integer> res = cur.subList(i, cur.size());
        for (int j = 0; j < i; j++){
            res.add(cur.get(j));
        }
        return res;
    }

    //time: O(2^n)  space: O(1)
    public static List<Integer> circularPermutation2(int n, int start){
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            int a = start ^ i ^ i >> 1;
            int b = start ^ i;
            System.out.println(b + "," + a);
            res.add(start ^ i ^ i >> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int start = st.nextInt();
            List<Integer> res = circularPermutation2(n, start);
            System.out.println(res);
        }
    }
}
