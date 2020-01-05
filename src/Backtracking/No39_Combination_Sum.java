package Backtracking;

/**
 * @author HailongZeng
 * @date 12/17/19 10:22 下午
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class No39_Combination_Sum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        List<Integer> cur = new ArrayList<>();
        helper(res, cur, candidates, target, 0);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> cur, int[] candidates, int target, int pos){
        if (target == 0){
            res.add(new ArrayList(cur));
            return;
        }
        for (int i = pos; i < candidates.length; i++){
            if (candidates[i] > target) break;
            cur.add(candidates[i]);
            helper(res, cur, candidates, target-candidates[i], i);
            cur.remove(cur.size()-1);
        }
        return;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int[] candidates = new int[n];
            for (int j = 0; j < n; j++){
                candidates[j] = st.nextInt();
            }
            int target = st.nextInt();
            System.out.println(combinationSum(candidates, target));
        }
    }
}
