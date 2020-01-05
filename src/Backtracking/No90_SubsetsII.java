package Backtracking;

/**
 * @author HailongZeng
 * @date 12/17/19 9:17 下午
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class No90_SubsetsII {

    public static List<List<Integer>> subsetsWithDup(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
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
            while (i + 1 < nums.length && nums[i] == nums[i+1]) i++;
        }
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
            System.out.println(subsetsWithDup(nums));
        }
    }
}
