package Sliding_Window;

/**
 * @author HailongZeng
 * @date 1/7/20 2:57 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
 *
 * Follow up:
 * Could you solve it in linear time?
 */
public class No239_Sliding_Window_Maximum {

    //brute force   O(n^2)
    public static int[] maxSlidingWindow1(int[] nums, int k){
        if (nums.length == 0 || nums == null) return new int[]{};
        int n = nums.length;
        int[] res = new int[n-k+1];
        for(int l = 0; l <= n-k; l++){
            res[l] = nums[l];
            for (int r = l+1; r < l+k; r++){
                res[l] = Math.max(res[l], nums[r]);
            }
        }
        return res;
    }

    public static int[] maxSlidingWindow2(int[] nums, int k){
        if (nums.length == 0) return new int[0];
        int n = nums.length - k + 1;
        int[] res = new int[n];
        int index = getMaxIndex(nums, 0, k);
        res[0] = nums[index];
        for (int i = 1; i < n; i++){
            if (i <= index){//means res[i-1] belong to [i, i+k), only need to compare nums[i+k-1] with res[i-1]
                if (nums[i+k-1] >= res[i-1]){
                    res[i] = nums[i+k-1];
                    index = i+k-1;
                }else{
                    res[i] = res[i-1];
                }
            }else{//means res[i-1] not belong to [i, i+k), so we need to compare all the elements between [i, i+k)
                index = getMaxIndex(nums, i, k);
                res[i] = nums[index];
            }
        }
        return res;
    }

    public static int getMaxIndex(int[] nums, int start, int k) {
        int max = nums[start];
        int index = start;
        for (int i = start + 1; i < start + k; i++){
            if (nums[i] > max){
                index = i;
                max = nums[i];
            }
        }
        return index;
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
        while ((line = io.readLine()) != null) {
            int[] nums = stringToArray(line);
            line = io.readLine();
            int k = Integer.parseInt(line);
            int[] res = maxSlidingWindow1(nums, k);
            String out = arrayToString(res);
            System.out.println(out);
        }
    }

}
