package String;

/**
 * @author HailongZeng
 * @date 1/15/20 4:12 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.
 *
 * Example 1:
 *
 * Input: 12
 * Output: 21
 *
 * Example 2:
 *
 * Input: 21
 * Output: -1
 */
//与leetcode31相似
public class No556_Next_Greater_ElementIII {

    public static int nextGreaterElement(int n) {
        if (n <= 11) return -1;

        char[] nums = ("" + n).toCharArray();//change into char array
        int i = nums.length - 2;
        //find the first index having ascending sort
        for (; i >= 0; i--){
            if (nums[i] < nums[i+1]) break;
        }
        if (i < 0) return -1;  //the char array is descending sort

        int j = nums.length - 1;
        for (; j > i; j--){
            if (nums[i] < nums[j]) break;
        }

        //swap
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;

        Arrays.sort(nums, i+1, nums.length);
        long res = Long.parseLong(new String(nums)); //overflow
        return (res > Integer.MAX_VALUE) ? -1 : ((int)res);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int n = Integer.parseInt(line);
            int res = nextGreaterElement(n);
            System.out.println(res);
        }
    }
}
