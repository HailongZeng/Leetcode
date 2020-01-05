package Sampling;

/**
 * @author HailongZeng
 * @date 12/16/19 10:09 上午
 */

import java.util.Random;
import java.util.Scanner;

/**
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 *
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 *
 * Example:
 *
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 *
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 *
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 */
public class No398_Random_Pick_Index {

    public static class Solution{
        private static Random random;
        private static int[] a;
        public Solution(int[] nums){
            a = nums;
            random = new Random();
        }

        public int pick(int target){
            int count = 0;
            int res = -1;
            for (int i = 0; i < a.length; i++){
                if (a[i] == target){
                    count++;
                    int tmp = random.nextInt(count);
                    if (tmp == 0){  //1/count * (1-1/(count+1)) * (1-1/(count+2))...
                        res = i;
                    }
                }
            }
            return res;
        }
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
            int target = st.nextInt();
            Solution solution = new Solution(nums);
            System.out.println(solution.pick(target));
        }
    }

}
