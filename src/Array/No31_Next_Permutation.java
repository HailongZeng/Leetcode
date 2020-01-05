package Array;

/**
 * @author HailongZeng
 * @date 12/19/19 1:01 下午
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place and use only constant extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */

//2 3 6 5 4 -->targetIndex = 2, and target = 3
//minIndex = 6, min = 4 - 3 = 1
//the nums become: 2 4 6 5 3
//sort: 2 4 3 5 6
public class No31_Next_Permutation {

    public static void nextPermutation(int[] nums){
        //find the target and targetIndex
        int targetIndex = nums.length - 1;
        while (targetIndex >= 1 && nums[targetIndex] <= nums[targetIndex-1]) targetIndex--;
        if (targetIndex == 0) {
            Arrays.sort(nums);
            return;
        }

        //find the one at the right of targetIndex which is greater than target and closet to it
        int target = nums[targetIndex-1];
        int min = nums[targetIndex] - nums[targetIndex-1];
        int minIndex = targetIndex;
        for (int i = targetIndex+1; i < nums.length; i++){
            if (nums[i]-target > 0 && nums[i]-target < min){
                min = nums[i] - target; //update the min
                minIndex = i;
            }
        }

        //swap the two num at minIndex and targetIndex-1
        nums[targetIndex-1] = nums[minIndex];
        nums[minIndex] = target;

        //sort the number between targetIndex and nums.length
        Arrays.sort(nums, target, nums.length);
        return;
    }

    public static void printArray(int[] nums){
        System.out.print("[");
        for (int i = 0; i < nums.length; i++){
            if (i == nums.length - 1) System.out.print(nums[i]);
            else System.out.print(nums[i] + ",");
        }
        System.out.println("]");
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
            nextPermutation(nums);
            printArray(nums);
        }
    }
}

