package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/15/20 11:22 上午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 *
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
 *
 * Example 1:
 *
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * Output: [-1,3,-1]
 * Explanation:
 *     For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 *     For number 1 in the first array, the next greater number for it in the second array is 3.
 *     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 * Example 2:
 *
 * Input: nums1 = [2,4], nums2 = [1,2,3,4].
 * Output: [3,-1]
 * Explanation:
 *     For number 2 in the first array, the next greater number for it in the second array is 3.
 *     For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 * Note:
 *
 * All elements in nums1 and nums2 are unique.
 * The length of both nums1 and nums2 would not exceed 1000.
 */
//leetcode496
public class No49_Next_Greater_ElementI {

    //brute force   time:O(m*n)  space:O(m)   m is the length of nums1, n is the length of nums2
    public static int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return new int[0];
        int n = nums1.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++){
            res[i] = find(nums1[i], nums2);
        }
        return res;
    }

    public static int find(int num, int[] nums2){
        int next = -1;
        int i = 0;
        while (i < nums2.length){
            if (nums2[i++] == num) break;
        }
        while (i < nums2.length){
            if (nums2[i] > num) return nums2[i];
            i++;
        }
        return next;
    }

    //stack and hashmap
    //time:O(m+n)   space:O(m+n)
    public static int[] nextGreaterElement2(int[] nums1, int[] nums2){
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>(); //(element, next_greater_element)
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums2.length; i++){
            while (!stack.isEmpty() && nums2[i] > stack.peek()){
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        while (!stack.isEmpty()){
            map.put(stack.pop(), -1);
        }
        for (int i = 0; i < nums1.length; i++){
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] nums1 = stringToArray(line);
            line = io.readLine();
            int[] nums2 = stringToArray(line);
            int[] res = nextGreaterElement1(nums1, nums2);
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
