package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/12/20 7:57 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 *
 * You are given a target value to search. If found in the array return true, otherwise return false.
 *
 * Example 1:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * Follow up:
 *
 * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 * Would this affect the run-time complexity? How and why?
 */
//和leetcode33的区别是此题的最差时间复杂度为O(n)，而leetcode33为O(logn)
public class No13_Search_in_Rotated_Sorted_ArrayII {

    //time:O(logn)  worst:O(n)
    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int l = 0, r = nums.length - 1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            if (target == nums[mid]) return true;
            if (nums[mid] == nums[l]) l++;
            else if (nums[mid] > nums[l]){//left part is sorted
                if (nums[l] <= target && nums[mid] > target) r = mid - 1;
                else l = mid + 1;
            }else{
                if (nums[mid] < target && nums[r] >= target) l = mid + 1;
                else r = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] nums = stringToArray(line);
            int target = Integer.parseInt(io.readLine());
            boolean res = search(nums, target);
            System.out.println(res);
        }
    }

    //the format of input:[xxx,xxx,xxx]
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
