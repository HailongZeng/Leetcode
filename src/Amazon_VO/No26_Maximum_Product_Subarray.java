package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/13/20 7:55 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
//leetcode152
public class No26_Maximum_Product_Subarray {

    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        int min = nums[0], max = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++){
            int mx = max, mn = min;
            max = Math.max(Math.max(mn*nums[i], mx*nums[i]), nums[i]);
            min = Math.min(Math.min(mn*nums[i], mn*nums[i]), nums[i]);
            res = Math.max(max, res);
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] nums = stringToArray(line);
            int res = maxProduct(nums);
            System.out.println(res);
        }
    }

    private static int[] stringToArray(String input) {
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
