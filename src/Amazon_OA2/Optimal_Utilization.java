package Amazon_OA2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and the second integer represents a value. Your task is to find an element from a and an element form b such that the sum of their values is less or equal to target and as close to target as possible. Return a list of ids of selected elements. If no pair is possible, return an empty list.
 *
 * Example 1:
 *
 * Input:
 * a = [[1, 2], [2, 4], [3, 6]]
 * b = [[1, 2]]
 * target = 7
 *
 * Output: [[2, 1]]
 *
 * Explanation:
 * There are only three combinations [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8, respectively.
 * Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
 * Example 2:
 *
 * Input:
 * a = [[1, 3], [2, 5], [3, 7], [4, 10]]
 * b = [[1, 2], [2, 3], [3, 4], [4, 5]]
 * target = 10
 *
 * Output: [[2, 4], [3, 2]]
 *
 * Explanation:
 * There are two pairs possible. Element with id = 2 from the list `a` has a value 5, and element with id = 4 from the list `b` also has a value 5.
 * Combined, they add up to 10. Similarily, element with id = 3 from `a` has a value 7, and element with id = 2 from `b` has a value 3.
 * These also add up to 10. Therefore, the optimal pairs are [2, 4] and [3, 2].
 *
 * Example 3:
 *
 * Input:
 * a = [[1, 8], [2, 7], [3, 14]]
 * b = [[1, 5], [2, 10], [3, 14]]
 * target = 20
 *
 * Output: [[3, 1]]
 *
 * Example 4:
 *
 * Input:
 * a = [[1, 8], [2, 15], [3, 9]]
 * b = [[1, 8], [2, 11], [3, 12]]
 * target = 20
 *
 * Output: [[1, 3], [3, 2]]
 */
public class Optimal_Utilization {

    public static List<List<Integer>> optimalUtilization(List<List<Integer>> a, List<List<Integer>> b, int target){
        List<List<Integer>> res = new ArrayList<>();
        Collections.sort(a, (i, j)->i.get(1) - j.get(1));
        Collections.sort(b, (i, j)->i.get(1) - j.get(1));
        int max = Integer.MIN_VALUE;
        int m = a.size();
        int n = b.size();
        int i = 0;
        int j = n - 1;
        while (i < m && j >= 0){
            int sum = a.get(i).get(1) + b.get(j).get(1);
            if (sum > target){
                j--;
            }else{
                if (max <= sum){
                    if (max < sum){
                        max = sum;
                        res.clear();
                    }
                    res.add(Arrays.asList(a.get(i).get(0), b.get(j).get(0)));
                    int idx = j - 1;
                    while (idx >= 0 && b.get(idx).get(1) == b.get(idx + 1).get(1)){
                        res.add(Arrays.asList(a.get(i).get(0), b.get(idx--).get(0)));
                    }
                }
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> a1 = new ArrayList<>();
        a1.add(Arrays.asList(1, 2));
        a1.add(Arrays.asList(2, 4));
        a1.add(Arrays.asList(3, 6));
        List<List<Integer>> b1 = new ArrayList<>();
        b1.add(Arrays.asList(1, 2));
        int target1 = 7;
        System.out.println(optimalUtilization(a1, b1, target1));

        List<List<Integer>> a2 = new ArrayList<>();
        a2.add(Arrays.asList(1, 3));
        a2.add(Arrays.asList(2, 5));
        a2.add(Arrays.asList(3, 7));
        a2.add(Arrays.asList(4, 10));
        List<List<Integer>> b2 = new ArrayList<>();
        b2.add(Arrays.asList(1, 2));
        b2.add(Arrays.asList(2, 3));
        b2.add(Arrays.asList(3, 4));
        b2.add(Arrays.asList(4, 5));
        int target2 = 10;
        System.out.println(optimalUtilization(a2, b2, target2));

        List<List<Integer>> a3 = new ArrayList<>();
        a3.add(Arrays.asList(1, 8));
        a3.add(Arrays.asList(2, 7));
        a3.add(Arrays.asList(3, 14));
        List<List<Integer>> b3 = new ArrayList<>();
        b3.add(Arrays.asList(1, 5));
        b3.add(Arrays.asList(2, 10));
        b3.add(Arrays.asList(3, 14));
        int target3 = 20;
        System.out.println(optimalUtilization(a3, b3, target3));

        List<List<Integer>> a4 = new ArrayList<>();
        a4.add(Arrays.asList(1, 8));
        a4.add(Arrays.asList(2, 15));
        a4.add(Arrays.asList(3, 9));
        List<List<Integer>> b4 = new ArrayList<>();
        b4.add(Arrays.asList(1, 8));
        b4.add(Arrays.asList(2, 11));
        b4.add(Arrays.asList(3, 12));
        int target4 = 20;
        System.out.println(optimalUtilization(a4, b4, target4));
    }
}
