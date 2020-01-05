package Backtracking;

/**
 * @author HailongZeng
 * @date 12/17/19 7:13 下午
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class No46_Permutations {

    public static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> cur = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        helper(res, cur, nums, visited);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> cur, int[] nums, boolean[] visited){
        if (cur.size() == nums.length){
            res.add(new ArrayList(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (visited[i] == true) continue;
            cur.add(nums[i]);
            visited[i] = true;
            helper(res, cur, nums, visited);
            cur.remove(cur.size()-1);
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
            System.out.println(permute(nums));
        }
    }
}
