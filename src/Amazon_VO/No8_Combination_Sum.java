package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/12/20 12:49 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class No8_Combination_Sum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        helper(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> cur, int[] candidates, int target, int pos){
        if (target == 0){
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = pos; i < candidates.length; i++){
            if (candidates[i] > target) break;
            cur.add(candidates[i]);
            helper(res, cur, candidates, target - candidates[i], i);
            cur.remove(cur.size()-1);
        }
        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] candidates = stringToArray(line);
            int target = Integer.parseInt(io.readLine());
            List<List<Integer>> res = combinationSum(candidates, target);
            System.out.println(res);
        }
    }

    //the format of input:[xxx,xxx,xxx]
    private static int[] stringToArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < nums.length; i++){
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }

}
