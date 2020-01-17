package Sliding_Window;

/**
 * @author HailongZeng
 * @date 1/8/20 3:33 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * Examples:
 * [2,3,4] , the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 *
 * Note:
 * You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 */
public class No480_Sliding_Window_Median {

    //time:O(k*logk + (n-k+1)*k)  space:O(k)
    //time:O(k*logk + (n-k+1)*logk) if binary search   space:O(k)
    public static double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new double[0];
        double[] res = new double[nums.length - k + 1];
        int[] window = new int[k];
        for (int i = 0; i < k; i++){
            window[i] = nums[i];
        }
        Arrays.sort(window);
        for (int i = k; i <= nums.length; i++){
            res[i-k] = ((double)window[k/2]+window[(k-1)/2]) / 2;
            if (i == nums.length) break;
            remove(window, nums[i-k]); //remove the left one from the window
            insert(window, nums[i]);   //add the current one to the window
        }
        return res;
    }

    //Insert val into window, window[k-1] is empty before inseration
    public static void insert(int[] window, int val){
        int i = 0;
        while (i < window.length - 1 && val > window[i]) i++;
        int j = window.length - 1;
        while (j > i) window[j] = window[--j];
        window[j] = val;
    }

    //binary search
    public static void insert2(int[] window, int val){
        int i = Arrays.binarySearch(window, val);
        if (i < 0) i = -i - 1;
        int j = window.length - 1;
        while (j > i) window[j] = window[--j];
        window[j] = val;
    }

    //Remove val from window and shrink it
    public static void remove(int[] window, int val){
        int i = 0;
        while (i < window.length && val != window[i]) ++i;
        while (i < window.length - 1) window[i] = window[++i];
    }

    public static void remove2(int[] window, int val){
        int i = Arrays.binarySearch(window, val);
        while (i < window.length - 1) window[i] = window[++i];
    }

    public static String arrayToString(double[] res) {
        if (res.length == 0) return "[]";
        String out = "";
        for (int i = 0; i < res.length; i++){
            if (i == res.length - 1) out += String.valueOf(res[i]);
            else out += String.valueOf(res[i]) + ", ";
        }
        return "[" + out + "]";
    }

    public static int[] stringToArray(String input) {
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

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] nums = stringToArray(line);
            line = io.readLine();
            int k = Integer.parseInt(line);
            double[] res = medianSlidingWindow(nums, k);
            String s = arrayToString(res);
            System.out.println(s);
        }
    }
}
