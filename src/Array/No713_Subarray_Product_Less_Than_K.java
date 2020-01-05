package Array;

/**
 * @author HailongZeng
 * @date 1/2/20 9:14 下午
 */

import java.util.Scanner;

/**
 * Your are given an array of positive integers nums.
 *
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 *
 * Example 1:
 *
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * Note:
 *
 * 0 < nums.length <= 50000.
 * 0 < nums[i] < 1000.
 * 0 <= k < 10^6.
 */
public class No713_Subarray_Product_Less_Than_K {

    //time:O(n)   space:O(1)
    //sliding window
    public static int numSubarrayProductLessThanK1(int[] nums, int k){
        if (k <= 1) return 0;
        int res = 0;
        long product = 1;
        int l = 0;
        for (int r = 0; r < nums.length; r++){
            product *= nums[r];
            while (product >= k) product /= nums[l++];
            res += r - l + 1;
        }
        return res;
    }

    //Binary search on logarithms
    //because log(fact(x_i)) = sum(log(x_i))   reduce to subarray sums
    //time:O(NlogN)   space:O(N)
    public static int numSubarrayProductLessThanK2(int[] nums, int k){
        if (k <= 1) return 0;
        double logk = Math.log(k);
        double[] prefix = new double[nums.length+1];
        for (int i = 0; i < nums.length; i++){
            prefix[i+1] = prefix[i] + Math.log(nums[i]);
        }
        int res = 0;
        for (int i = 0; i < prefix.length; i++){
            int lo = i + 1, hi = prefix.length;
            while (lo < hi){
                int mi = lo + (hi-lo) / 2;
                if (prefix[mi] < prefix[i] + logk - 1e-9) lo = mi + 1;
                else hi = mi;
            }
            res += lo - i - 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++){
                nums[j] = st.nextInt();
            }
            int k = st.nextInt();
            System.out.println(numSubarrayProductLessThanK1(nums, k));
        }
    }
}
