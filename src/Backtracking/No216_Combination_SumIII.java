package Backtracking;

/**
 * @author HailongZeng
 * @date 12/17/19 11:00 下午
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Note:
 *
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 *
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class No216_Combination_SumIII {

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(res, cur, k, n, 1);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> cur, int k, int n, int pos){
        if (cur.size() == k && n == 0){
            res.add(new ArrayList(cur));
            return;
        }
        for (int i = pos; i <= 9; i++){
            if (i > n) break;
            cur.add(i);
            helper(res, cur, k, n-i, i+1);
            cur.remove(cur.size()-1);
        }
        return;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int k = st.nextInt();
            int n = st.nextInt();
            System.out.println(combinationSum3(k, n));
        }
    }
}
