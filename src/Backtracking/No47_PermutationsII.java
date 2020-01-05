package Backtracking;

/**
 * @author HailongZeng
 * @date 12/17/19 7:28 下午
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Example:
 *
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class No47_PermutationsII {

    public static List<List<Integer>> permuteUnique(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0 || nums == null) return res;
        Arrays.sort(nums);
        List<Integer> cur = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        helper(res, cur, nums, visited);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> cur, int[] nums, boolean[] visited){
        if (cur.size() == nums.length) {
            res.add(new ArrayList(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (visited[i] == true) continue;
            if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            cur.add(nums[i]);
            visited[i] = true;
            helper(res, cur, nums, visited);
            cur.remove(cur.size() - 1);
            visited[i] = false;
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
            System.out.println(permuteUnique(nums));
            System.out.println(1<<0);
        }
    }
}
