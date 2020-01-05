package Divide_and_conquer;

/**
 * @author HailongZeng
 * @date 12/16/19 11:22 下午
 */

import java.util.Scanner;

/**
 *Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * The array may contain duplicates.
 *
 * Example 1:
 *
 * Input: [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: [2,2,2,0,1]
 * Output: 0
 * Note:
 *
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 */
public class No154_Find_Minimum_in_Rotated_Sorted_ArrayII {

    //T(n) = 2*T(n/2) = O(n)  master theory
    public static int findMin(int[] nums){
        return helper(nums, 0, nums.length - 1);
    }

    public static int helper(int[] nums, int l, int r){
        //One or two elements, solve it directly
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
            System.out.println(findMin(nums));
        }
    }
}
