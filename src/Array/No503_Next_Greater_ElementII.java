package Array;

/**
 * @author HailongZeng
 * @date 1/15/20 3:52 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 *
 * Example 1:
 *
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 * Note: The length of given array won't exceed 10000.
 */
public class No503_Next_Greater_ElementII {

    //brute force   time:O(n^2)  space:O(n)
    public static int[] nextGreaterElements1(int[] nums) {
        if (nums.length == 1) return new int[]{-1};
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++){
            res[i] = -1;
            for (int j = 1; j < n; j++){
                if (nums[(i+j) % n] > nums[i]){
                    res[i] = nums[(i+j) % n];
                    break;
                }
            }
        }
        return res;
    }

    //stack  time:O(2n)  space:O(n)
    /*
    我们遍历两倍的数组，然后还是坐标i对n取余，取出数字，如果此时栈不为空，且栈顶元素小于当前数字，说明当前数字就是栈顶元素的右边第一个较大数，那么建立二者的映射，并且去除当前栈顶元素，最后如果i小于n，则把i压入栈。因为res的长度必须是n，超过n的部分我们只是为了给之前栈中的数字找较大值，所以不能压入栈
     */
    public static int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2*n; i++){
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num){
                res[stack.pop()] = num;
            }
            if (i < n) stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null) {
            int[] nums = stringToArray(line);
            int[] res = nextGreaterElements2(nums);
            String s = arrayToString(res);
            System.out.println(s);
        }
    }

    private static String arrayToString(int[] res) {
        if (res.length == 0) return "[]";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++){
            sb.append(res[i] + ",");
        }
        return "[" + sb.toString().substring(0, sb.length()-1) + "]";
    }

    private static int[] stringToArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < nums.length; i++){
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }

}
