package Amazon_OA2;

import java.util.HashMap;

/**
 * Given a list of positive integers nums and an int target, return indices of the two numbers such that they add up to a target - 30.
 *
 * Conditions:
 *
 * You will pick exactly 2 numbers.
 * You cannot pick the same element twice.
 * If you have muliple pairs, select the pair with the largest number.
 * Example 1:
 *
 * Input: nums = [1, 10, 25, 35, 60], target = 90
 * Output: [2, 3]
 * Explanation:
 * nums[2] + nums[3] = 25 + 35 = 60 = 90 - 30
 * Example 2:
 *
 * Input: nums = [20, 50, 40, 25, 30, 10], target = 90
 * Output: [1, 5]
 * Explanation:
 * nums[0] + nums[2] = 20 + 40 = 60 = 90 - 30
 * nums[1] + nums[5] = 50 + 10 = 60 = 90 - 30
 * You should return the pair with the largest number.
 */
public class Find_Pair_With_Given_Sum {

    public static int[] findPair(int[] nums, int target){
        if (nums == null || nums.length < 2) return new int[]{-1, -1};
        int[] res = new int[2];
        int max = 0;
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int complement = target - 30 - nums[i];
            if (m.containsKey(complement)){
                int idx = m.get(complement);
                if (i > max){
                    max = Math.max(max, i);
                    res[0] = idx;
                    res[1] = i;
                }
            }
            m.put(nums[i], i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 10, 25, 35, 60};
        int target1 = 90;
        int[] res1 = findPair(nums1, target1);
        System.out.println("[" + res1[0] + "," + res1[1] + "]");
        int[] nums2 = {20, 50, 40, 25, 30, 10};
        int target2 = 90;
        int[] res2 = findPair(nums2, target2);
        System.out.println("[" + res2[0] + "," + res2[1] + "]");
    }
}
