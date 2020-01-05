package Amazon_OA2;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given an int array nums and an int target, find how many unique pairs in the array such that their sum is equal to target. Return the number of pairs.
 *
 * Example 1:
 *
 * Input: nums = [1, 1, 2, 45, 46, 46], target = 47
 * Output: 2
 * Explanation:
 * 1 + 46 = 47
 * 2 + 45 = 47
 *
 * Example 2:
 *
 * Input: nums = [1, 1], target = 2
 * Output: 1
 * Explanation:
 * 1 + 1 = 2
 *
 * Example 3:
 *
 * Input: nums = [1, 5, 1, 5], target = 6
 * Output: 1
 * Explanation:
 * [1, 5] and [5, 1] are considered the same.
 */
public class Two_Sum_Unique_Pairs {

    public static int twoSum(int[] nums, int target){
        HashSet<Integer> s = new HashSet<>();
        HashSet<Integer> visited = new HashSet<>();
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if (!visited.contains(nums[i]) && s.contains(complement)){
                visited.add(complement);
                visited.add(nums[i]);
                res++;
            }
            s.add(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 2, 45, 46, 46};
        int target1 = 47;
        System.out.println(twoSum(nums1, target1));

        int[] nums2 = {1, 1};
        int target2 = 2;
        System.out.println(twoSum(nums2, target2));

        int[] nums3 = {1, 5, 1, 5};
        int target3 = 6;
        System.out.println(twoSum(nums3, target3));
    }
}
