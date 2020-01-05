package Array;

/**
 * @author HailongZeng
 * @date 12/25/19 11:29 上午
 */

import java.util.Scanner;

/**
 * Given an array nums of positive integers, return the longest possible length of an array prefix of nums, such that it is possible to remove exactly one element from this prefix so that every number that has appeared in it will have the same number of occurrences.
 *
 * If after removing one element there are no remaining elements, it's still considered that every appeared number has the same number of ocurrences (0).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,1,1,5,3,3,5]
 * Output: 7
 * Explanation: For the subarray [2,2,1,1,5,3,3] of length 7, if we remove nums[4]=5, we will get [2,2,1,1,3,3], so that each number will appear exactly twice.
 * Example 2:
 *
 * Input: nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
 * Output: 13
 * Example 3:
 *
 * Input: nums = [1,1,1,2,2,2]
 * Output: 5
 * Example 4:
 *
 * Input: nums = [10,2,8,9,3,8,1,5,2,3,7,6]
 * Output: 8
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class No1224_Maximum_Equal_Frequency {

    public static int maxEqualFreq(int[] nums) {
        int[] count = new int[100001];     //count how many times a number occur
        int[] frequency = new int[100001]; //count how many number occur with same frequency
        for (int i = 0; i < nums.length; i++){
            count[nums[i]]++;
            frequency[count[nums[i]]]++;
        }

        for (int i = nums.length - 1; i > 0; i--){
            if (count[nums[i]] * frequency[count[nums[i]]] == i) return i+1;
            frequency[count[nums[i]]]--; //remove nums[i]
            count[nums[i]]--;
            if (count[nums[i-1]] * frequency[count[nums[i-1]]] == i) return i+1;
        }
        return 1;
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
            System.out.println(maxEqualFreq(nums));
        }
    }

}
