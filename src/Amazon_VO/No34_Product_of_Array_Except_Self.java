package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/14/20 7:03 下午
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Example:
 *
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 *
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
//leetcode238
public class No34_Product_of_Array_Except_Self {

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] small = new int[n];
        int[] big = new int[n];
        small[0] = 1;
        big[n-1] = 1;
        //small[i] = nums[0]*..nums[i-1]
        //big[i] = big[i+1]*big[i+1]...*big[n-1]
        for (int i = 1; i < n; i++){
            small[i] = small[i-1] * nums[i-1];
        }
        for (int i = n-2; i >= 0; i--){
            big[i] = big[i+1] * nums[i+1];
        }
        for (int i = 0; i < n; i++){
            nums[i] = small[i] * big[i];
        }
        return nums;
    }

    public static int[] stringToArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length()-1);
        if (input.length() == 0) return new int[]{};
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++){
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }

    public static String arrayToString(int[] res) {
        if (res.length == 0) return "[]";
        String out = "";
        for (int i = 0; i < res.length; i++){
            if (i == res.length - 1) out += String.valueOf(res[i]);
            else out += String.valueOf(res[i]) + ", ";
        }
        return "[" + out + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] nums = stringToArray(line);
            int[] res = productExceptSelf(nums);
            String s = arrayToString(res);
            System.out.println(s);
        }
    }
}
