package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/15/20 1:29 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
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
//leetcode560  similar with leetcode523
public class No53_Subarray_Sum_Equals_K {

    //brute force  time:O(n^2)  space:O(1)
    public static int subarraySum1(int[] nums, int k){
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++){
            int sum = nums[i];
            if (sum == k) res++;
            for (int j = i + 1; j < n; j++){
                sum += nums[j];
                if (sum == k) res++;
            }
        }
        return res;
    }

    //map records how many number can be equal to a sum
    //sum[i]--current number  sum[j]--stored number(key) in map  if sum[i] - sum[j] = k, then the sum of elements lying between indices i and j is k
    public static int subarraySum2(int[] nums, int k){
        //the key is the sum value, the value is the number of this sum value
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            if (map.containsKey(sum - k)) count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] nums = stringToArray(line);
            int k = Integer.parseInt(io.readLine());
            int res = subarraySum2(nums, k);
            System.out.println(res);
        }
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
}
