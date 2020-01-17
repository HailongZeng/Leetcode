package Math;

/**
 * @author HailongZeng
 * @date 1/9/20 9:00 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Given an array of integers A, consider all non-empty subsequences of A.
 *
 * For any sequence S, let the width of S be the difference between the maximum and minimum element of S.
 *
 * Return the sum of the widths of all subsequences of A.
 *
 * As the answer may be very large, return the answer modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: [2,1,3]
 * Output: 6
 * Explanation:
 * Subsequences are [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3].
 * The corresponding widths are 0, 0, 0, 1, 1, 2, 2.
 * The sum of these widths is 6.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= 20000
 */

/**
 * 1.get all the subset of A, and calculate the width one by one, should be 2^n. time:O(2^n)
 * 2.Sort A first, A[i] ... A[j]   there are j-i-1 positions between i and j, we can put element or not put element, so it should be 2^(j-i-1). sum += (A[j]-A[i]) * 2^(j-i-1), time: O(n^2)
 * 3.Sort A first, A[i]....  there are n-1-i position after i can be put, so we will have sum -= A[i] * 2^(n-1-i)  and ...A[i], there are i positions before i can be put, so we will have sum += A[i] * 2^(i)
 */
public class No891_Sum_of_Subsequence_Widths {

    //time: O(NlogN)   space:O(N)
    public static int sumSubseqWidths(int[] A){
        int M = 1000000007;
        Arrays.sort(A);
        int n = A.length;
        long[] pow2 = new long[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++){
            pow2[i] = (2 * pow2[i-1]) % M;
        }
        long sum = 0;
        for (int i = 0; i < n; i++){
            sum = (sum + (pow2[i] - pow2[n-1-i]) * A[i]) % M;
        }
        return (int)sum;
    }

    public static int[] stringToArray(String input){
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++){
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] nums = stringToArray(line);
            int res = sumSubseqWidths(nums);
            System.out.println(res);
        }
    }
}
