package DP;

/**
 * @author HailongZeng
 * @date 12/15/19 10:30 上午
 */

import java.util.Scanner;
import java.util.Stack;

/**
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits.
 *
 * Note: You should try to optimize your time and space complexity.
 *
 * Example 1:
 *
 * Input:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * Output:
 * [9, 8, 6, 5, 3]
 * Example 2:
 *
 * Input:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * Output:
 * [6, 7, 6, 0, 4]
 * Example 3:
 *
 * Input:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * Output:
 * [9, 8, 9]
 */
//Greedy+DP
public class No321_Create_Maximum_Number {

    public static int[] maxNumber(int[] nums1, int[] nums2, int k){
        int[] res = null;
        for (int i = 0; i <= k; i++){
            if (nums2.length < k - i || i > nums1.length){
                continue;
            }
            int[] res1 = getF(nums1, i);
            int[] res2 = getF(nums2, k - i);
            int[] result = getFF(res1, res2);
            if (res == null || larger(result, res)){
                res = result;
            }
        }
        return res;
    }

    //concat two arrays
    public static int[] getFF(int[] res1, int[] res2){
        int[] res = new int[res1.length + res2.length];
        int key = 0;
        for (int i = 0, j = 0; i < res1.length || j < res2.length;){
            if (j == res2.length){
                res[key++] = res1[i++];
            }else if((i == res1.length)){
                res[key++] = res2[j++];
            }else{
                if (larger(res1, res2, i, j)){
                    res[key++] = res1[i++];
                }else{
                    res[key++] = res2[j++];
                }
            }
        }
        return res;
    }

    //num1数组中k个元素组成的最大数字
    public static int[] getF(int[] nums, int k){
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int max_pop_num = n - k;//the maximum number the stack can pop, in order to guarantee that the stack can have at least k elements
        int pop = 0;//record the number of elements pop from stack
        for (int i = 0; i < n; i++){
            int temp = nums[i];
            while (!stack.isEmpty() && stack.peek() < temp && pop < max_pop_num){//if the pop number is satisfied to less than max_pop_number, pop all the elements in stack which is less than current number
                stack.pop();
                pop++;
            }
            stack.push(temp);
        }
        while (stack.size() > k){ //if the current size of stack is larger than k, just pop elements to keep the size to be exact k
            stack.pop();
        }
        int[] res = new int[k];
        int j = k-1;
        while(!stack.isEmpty()){
            res[j] = stack.pop();
            j--;
        }
        return res;
    }

    public static boolean larger(int[] res1, int[] res2){
        for (int i = 0; i < res1.length; i++){
            if (res1[i] > res2[i]){
                return true;
            }else if (res1[i] < res2[i]){
                return false;
            }
        }
        return false;
    }

    public static boolean larger(int[] res1, int[] res2, int stat1, int stat2){
        int i = 0;
        for(; i + stat1 < res1.length && i + stat2 < res2.length; i++){
            if (res1[i + stat1] > res2[i + stat2]){
                return true;
            }else if (res1[i + stat1] < res2[i + stat2]){
                return false;
            }
        }
        if (i + stat2 == res2.length){
            return true;
        }
        return false;
    }

    public static void printArray(int[] arr){
        System.out.print("[");
        for (int i = 0; i < arr.length; i++){
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            }else{
                System.out.print(arr[i] + ",");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int numberOfCases = st.nextInt();
        for (int i = 0; i < numberOfCases; i++){
            int m = st.nextInt();
            int[] nums1 = new int[m];
            for (int j = 0; j < m; j++){
                nums1[j] = st.nextInt();
            }
            printArray(nums1);
            int n = st.nextInt();
            int[] nums2 = new int[n];
            for (int j = 0; j < n; j++){
                nums2[j] = st.nextInt();
            }
            printArray(nums2);
            int k = st.nextInt();
            int[] res = maxNumber(nums1, nums2, k);
            printArray(res);
        }
    }
}
