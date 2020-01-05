package Backtracking;

/**
 * @author HailongZeng
 * @date 12/17/19 10:42 下午
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class No40_Combination_SumII {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            if (candidates[i] > target) break;//剪枝
            cur.add(candidates[i]);
            helper(res, cur, candidates, target-candidates[i],i+1);
            cur.remove(cur.size()-1);
            while (i+1<candidates.length && candidates[i] == candidates[i+1]) i++;  //去重
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
            System.out.println(combinationSum2(candidates, target));
        }
    }
}
