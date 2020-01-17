package Tree;

/**
 * @author HailongZeng
 * @date 1/9/20 8:44 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.
 *
 * For each integer in this list:
 *
 * The hundreds digit represents the depth D of this node, 1 <= D <= 4.
 * The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
 * The units digit represents the value V of this node, 0 <= V <= 9.
 *
 *
 * Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.
 *
 * Example 1:
 *
 * Input: [113, 215, 221]
 * Output: 12
 * Explanation:
 * The tree that the list represents is:
 *     3
 *    / \
 *   5   1
 *
 * The path sum is (3 + 5) + (3 + 1) = 12.
 *
 *
 * Example 2:
 *
 * Input: [113, 221]
 * Output: 4
 * Explanation:
 * The tree that the list represents is:
 *     3
 *      \
 *       1
 *
 * The path sum is (3 + 1) = 4.
 */
public class No666_Path_SumIV {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    public static int[] stringToArray(String input){
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++){
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] nums = stringToArray(line);
            int res = pathSum1(nums);
            System.out.println(res);
        }
    }

    //time:O(n)  space:O(n)
    //recursive
    public static int pathSum1(int[] nums) {
        if (nums.length == 0 || nums == null) return 0;
        int[] res = new int[1];
        res[0] = 0;
        Map<Integer, Integer> m = new HashMap<>();//key is the first two digits, value is the last digit
        for (int num: nums){
            m.put(num/10, num%10);
        }
        helper(nums[0]/10, m, 0, res);
        return res[0];
    }

    private static void helper(int num, Map<Integer, Integer> m, int tmpSum, int[] res) {
        int level = num / 10, pos = num % 10;
        int left = (level + 1) * 10 + 2 * pos - 1, right = left + 1;
        tmpSum += m.get(num);
        if (!m.containsKey(left) && !m.containsKey(right)){
            res[0] += tmpSum;
            return;
        }
        if (m.containsKey(left)) helper(left, m, tmpSum, res);
        if (m.containsKey(right)) helper(right, m, tmpSum, res);
    }

    //iterative   level-order traversal
    public static int pathSum2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        Map<Integer, Integer> m = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(nums[0] / 10);
        for (int num: nums){
            m.put(num/10, num%10);
        }
        while (!q.isEmpty()){
            int t = q.poll();
            int level = t/10, pos = t%10;
            int left = (level+1) * 10 + 2 * pos - 1, right = left + 1;
            if (!m.containsKey(left) && !m.containsKey(right)){
                res += m.get(t);
            }
            if (m.containsKey(left)){
                m.put(left, m.get(left)+m.get(t));
                q.add(left);
            }
            if (m.containsKey(right)){
                m.put(right, m.get(right)+m.get(t));
                q.add(right);
            }
        }
        return res;
    }
}
