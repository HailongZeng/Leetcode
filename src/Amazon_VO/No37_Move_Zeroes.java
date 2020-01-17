package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/14/20 7:50 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
//leetcode283
public class No37_Move_Zeroes {

    public static void moveZeroes(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != 0) nums[l++] = nums[i];
            else count++;
        }
        while (count != 0){
            nums[r--] = 0;
            count--;
        }
        return;
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
            moveZeroes(nums);
            String s = arrayToString(nums);
            System.out.println(s);
        }
    }
}
