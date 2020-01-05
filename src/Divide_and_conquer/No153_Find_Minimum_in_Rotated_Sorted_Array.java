package Divide_and_conquer;

/**
 * @author HailongZeng
 * @date 12/16/19 10:35 下午
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * You may assume no duplicate exists in the array.
 *
 * Example 1:
 *
 * Input: [3,4,5,1,2]
 * Output: 1
 * Example 2:
 *
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 */
public class No153_Find_Minimum_in_Rotated_Sorted_Array {

    public static void quickSort(int[] nums, int start, int end){
        int pivot = nums[start];
        int i = start;
        int j = end;
        while (i < j){
            while (i < j && nums[j] >= pivot){
                j--;
            }
            while (i < j && nums[i] < pivot){
                i++;
            }
            int tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
        }
        if (i - 1 > start) quickSort(nums, start, i - 1);
        if (i + 1 < end) quickSort(nums, i + 1, end);
        return;
    }

    //O(nlogn)
    public static int findMin1(int[] nums){
        Arrays.sort(nums);
        return nums[0];
    }

    //O(n)
    public static int findMin2(int[] nums){
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++){
            min = Math.min(min, nums[i]);
        }
        return min;
    }

    //O(logn)  T(n) = 1 + T(n/2) = logn  master theory
    public static int findMin3(int[] nums){
        return helper(nums, 0, nums.length - 1);
    }

    public static int helper(int[] nums, int l, int r){
        //Only 1 or 2 elements
        if (l + 1 >= r) return Math.min(nums[l], nums[r]);
        //Sorted
        if (nums[l] < nums[r]) return nums[l];
        int mid = l + (r - l) / 2;
        return Math.min(helper(nums, l, mid - 1), helper(nums, mid, r));
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++){
                nums[j] = st.nextInt();
            }
            System.out.println(findMin3(nums));
        }
    }
}
