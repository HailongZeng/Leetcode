package Backtracking;

/**
 * @author HailongZeng
 * @date 12/17/19 10:32 下午
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * Example:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class No77_Combinations {

    public static List<List<Integer>> combine(int n, int k){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(res, cur, k, n, 1);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> cur, int k, int n, int pos){
        if (cur.size() == k){
            res.add(new ArrayList(cur));
            return;
        }
        for (int i = pos; i <= n; i++){
            if (cur.size() + n - i + 1 < k) break; //pruning
            cur.add(i);
            helper(res, cur, k, n, i+1);
            cur.remove(cur.size()-1);
        }
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int k = st.nextInt();
            System.out.println(combine(n, k));
        }
    }

}
