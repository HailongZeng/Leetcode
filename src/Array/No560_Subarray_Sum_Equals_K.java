package Array;

/**
 * @author HailongZeng
 * @date 1/2/20 8:29 下午
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 *
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 *
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class No560_Subarray_Sum_Equals_K {

    //time:O(n)  space:O(n)
    //sum[i]-sum[j] = k
    public static int subarraySum1(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            if (map.containsKey(sum-k)){
                count += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return count;
    }

    //time:O(n^2)  space:O(1)
    public static int subarraySum2(int[] nums, int k){
        int count = 0;
        for (int start = 0; start < nums.length; start++){
            int sum = 0;
            for (int end = start; end < nums.length; end++){
                sum += nums[end];
                if (sum == k) count++;
            }
        }
        return count;
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
            System.out.println(subarraySum1(nums, k));
        }
    }
}
