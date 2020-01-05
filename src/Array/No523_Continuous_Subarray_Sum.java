package Array;

/**
 * @author HailongZeng
 * @date 1/2/20 8:43 下午
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
 *
 *
 *
 * Example 1:
 *
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * Example 2:
 *
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 *
 *
 * Note:
 *
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
public class No523_Continuous_Subarray_Sum {

    //time:O(n^2)  space:O(1)
    public static boolean checkSubarraySum1(int[] nums, int k) {
        if (nums == null || nums.length < 2) return false;
        int n = nums.length;
        for (int i = 0; i < n; i++){
            int sum = 0;
            for (int j = i; j < n; j++){
                sum += nums[j];
                if (k != 0){
                    if (sum % k == 0 && j - i >= 1) return true;

                }else{
                    if (sum == 0 && j - i >= 1) return true;
                }
            }
        }
        return false;
    }

    //time:O(n)    space:O(n)
    public static boolean checkSubarraySum2(int[] nums, int k) {
        if (nums == null || nums.length < 2) return false;
        if (k == 1) return true;
        int sum = 0;
        Map<Integer, Integer> modToIndex = new HashMap<>();
        modToIndex.put(0, -1);
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            int mod = getMod(sum, k);
            if (modToIndex.containsKey(mod)){
                if (i - modToIndex.get(mod) >= 2){
                    return true;
                }
            }else{
                modToIndex.put(mod, i);
            }
        }
        return false;
    }

    private static int getMod(int num, int k){
        return k == 0 ? num : num % k;
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
            System.out.println(checkSubarraySum1(nums, k));
        }
    }
}
