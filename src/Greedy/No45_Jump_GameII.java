package Greedy;

/**
 * @author HailongZeng
 * @date 12/19/19 1:31 下午
 */

import java.util.Scanner;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Example:
 *
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Note:
 *
 * You can assume that you can always reach the last index.
 */
public class No45_Jump_GameII {

    public static int jump(int[] nums){
        int res = 0;
        if (nums == null || nums.length <= 1) return res;
        int step = 0; //record the step
        int reach = 0;
        int target = 0; //record the next target
        for (int i = 0; i < nums.length - 1; i++){
            reach = Math.max(reach, i+nums[i]);
            if (i == target){
                step++;
                target = reach; //update the target
                if (target > nums.length - 1) break;
            }
        }
        return step;
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
            System.out.println(jump(nums));
        }
    }
}
