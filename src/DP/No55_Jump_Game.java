package DP;

/**
 * @author HailongZeng
 * @date 12/19/19 11:08 上午
 */

import java.util.Scanner;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 *
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 */
public class No55_Jump_Game {

    //DP, top-down   O(n^2)  space: O(2n)-->O(n)
    /*
    1.Initially, all elements of the memo table are UNKNOWN, except for the last one, which is (trivially) GOOD (it can reach itself)
    2.Modify the backtracking algorithm such that the recursive step first checks if the index is known (GOOD / BAD)
        * If it is known then return True / False
        * Otherwise perform the backtracking steps as before
    3.Once we determine the value of the current index, we store it in the memo table
     */
    static enum Index{GOOD, BAD, UNKNOWN};
    static Index[] memo;
    public static boolean canJump1(int[] nums){
        memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++){
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length-1] = Index.GOOD;
        return canJumpFromPosition(0, nums);
    }

    public static boolean canJumpFromPosition(int position, int[] nums){
        if (memo[position] != Index.UNKNOWN){
            return memo[position] == Index.GOOD ? true : false;
        }
        int furthestJump = Math.min(position+nums[position], nums.length-1);
        for (int nextPosition = position+1; nextPosition <= furthestJump; nextPosition++){
            if (canJumpFromPosition(nextPosition, nums)){
                memo[position] = Index.GOOD;
                return true;
            }
        }
        memo[position] = Index.BAD;
        return false;
    }

    //DP, bottom-up  O(n^2)   space: O(n)
    public static boolean canJump2(int[] nums){
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++){
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length-1] = Index.GOOD;
        for (int i = nums.length - 2; i >= 0; i--){
            int furthestJump = Math.min(i+nums[i], nums.length-1);
            for (int j = i+1; j <= furthestJump; j++){
                if (memo[j] == Index.GOOD){
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }
        return memo[0] == Index.GOOD;
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
            System.out.println(canJump1(nums));
        }
    }
}
