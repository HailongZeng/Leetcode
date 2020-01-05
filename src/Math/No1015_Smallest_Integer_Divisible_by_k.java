package Math;

/**
 * @author HailongZeng
 * @date 1/4/20 1:05 上午
 */

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Given a positive integer K, you need find the smallest positive integer N such that N is divisible by K, and N only contains the digit 1.
 *
 * Return the length of N.  If there is no such N, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: 1
 * Explanation: The smallest answer is N = 1, which has length 1.
 * Example 2:
 *
 * Input: 2
 * Output: -1
 * Explanation: There is no such positive integer N divisible by 2.
 * Example 3:
 *
 * Input: 3
 * Output: 3
 * Explanation: The smallest answer is N = 111, which has length 3.
 *
 *
 * Note:
 *
 * 1 <= K <= 10^5
 */
public class No1015_Smallest_Integer_Divisible_by_k {

    //time:O(K)    space:O(K)
    public static int smallestRepunitDivByK1(int K) {
        if (K % 2 == 0 || K % 5 == 0) return -1;
        Set<Integer> remainder = new HashSet<>();
        int N = 1;
        int sum = 1;
        int r = sum % K;
        while (remainder.isEmpty() || !remainder.contains(r)){
            remainder.add(r);
            if (r == 0) return N;
            r = (10 * r + 1) % K;
            N++;
        }
        return -1;
    }

    //time:O(K)  space:O(1)
    public static int smallestRepunitDivByK2(int K) {
        if (K % 2 == 0 || K % 5 == 0) return -1;
        for (int len = 1, r = 1; len <= K; len++, r = ((10 * r) + 1) % K){
            if (r % K == 0) return len;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int K = st.nextInt();
            System.out.println(smallestRepunitDivByK1(K));
        }
    }
}
