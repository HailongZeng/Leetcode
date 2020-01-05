package Array;

/**
 * @author HailongZeng
 * @date 12/19/19 11:28 上午
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 *
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 *
 * Note:
 *
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 * Example 1:
 *
 * Input: n = 3, k = 3
 * Output: "213"
 * Example 2:
 *
 * Input: n = 4, k = 9
 * Output: "2314"
 */
public class No60_Permutation_Sequence {

    public static String getPermutation(int n, int k){
        String res = "";
        String num = "123456789";
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 1; i < n; i++){//f[i]代表了i个数的组合数个数
            f[i] = f[i-1] * i;  //calculate f[0] to f[n-1]
        }
        k--;
        for (int i = n; i >= 1; i--){
            int j = k / f[i-1];
            k %= f[i-1]; //update k
            res += num.charAt(j);
            num = num.substring(0, j) + num.substring(j+1); //update num string, because we only use each number once
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int k = st.nextInt();
            System.out.println(getPermutation(n, k));
        }
    }
}
