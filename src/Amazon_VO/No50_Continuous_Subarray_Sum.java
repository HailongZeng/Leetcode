package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/15/20 1:17 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
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
//leetcode523   similar with leetcode560
public class No50_Continuous_Subarray_Sum {

    //brute force   time:O(n^2)  space:O(1)
    public static boolean checkSubarraySum1(int[] nums, int k) {
        if(nums.length < 2) return false;
        if (k == 1) return true;
        int n = nums.length;
        for (int i = 0; i < n; i++){
            int sum = nums[i];
            for (int j = i+1; j < n; j++){
                sum += nums[j];
                if (k == 0) {
                    if (sum == 0) return true;
                }else{
                    if (sum % k == 0) return true;
                }
            }
        }
        return false;
    }

    //use hashmap
    //time: O(n)   space:O(n)
    public static boolean checkSubarraySum2(int[] nums, int k) {
        if (nums.length < 2) return false;
        if (k == 1) return true;
        int sum = 0;
        Map<Integer, Integer> modToIndex = new HashMap<>();//key is the mod, value is the index first occurrence of a mod
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

    private static int getMod(int sum, int k) {
        return k == 0 ? sum : sum % k;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] nums = stringToArray(line);
            int k = Integer.parseInt(io.readLine());
            boolean res = checkSubarraySum2(nums, k);
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
