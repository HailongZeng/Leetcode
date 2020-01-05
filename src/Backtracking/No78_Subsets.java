package Backtracking;

/**
 * @author HailongZeng
 * @date 12/17/19 7:58 下午
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class No78_Subsets {

    public static List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(res, cur, nums, 0);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> cur, int[] nums, int pos){
        if (pos > nums.length) return;
        res.add(new ArrayList(cur));
        for (int i = pos; i < nums.length; i++){
            cur.add(nums[i]);
            helper(res, cur, nums, i+1);
            cur.remove(cur.size()-1);
        }
    }

    //位运算，一共有2^n个状态
    public static List<List<Integer>> subsets2(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        int all = 1<<nums.length;
        for (int i = 0; i < all; i++){
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < nums.length; j++){
                if ((i & (1<<j))==1){ //判断第i位掩码是否为1
                    cur.add(nums[i]);
                }
            }
            res.add(cur);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int numOfCases = st.nextInt();
        for (int i = 0; i < numOfCases; i++){
            int n = st.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++){
                nums[j] = st.nextInt();
            }
            System.out.println(subsets(nums));
        }
    }
}
