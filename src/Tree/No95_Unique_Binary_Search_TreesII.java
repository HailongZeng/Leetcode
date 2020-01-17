package Tree;

/**
 * @author HailongZeng
 * @date 1/6/20 2:11 下午
 */

import java.util.*;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 *
 * Example:
 *
 * Input: 3
 * Output:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class No95_Unique_Binary_Search_TreesII {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    //divide and conquer    time:O(2^n)  space:O(2^n)
    public static List<TreeNode> generateTrees1(int n){
        if (n == 0) return new ArrayList<>();
        return helper1(1, n);
    }

    public static List<TreeNode> helper1(int l, int r) {
        if (l > r) return null;
        List<TreeNode> res = new ArrayList<>();
        for (int i = l; i <= r; i++){
            List<TreeNode> left = helper1(l, i - 1), right = helper1(i+1, r);
            for (TreeNode a: left){
                for (TreeNode b: right){
                    TreeNode node = new TreeNode(i);
                    node.left = a;
                    node.right = b;
                    res.add(node);
                }
            }
        }
        return res;
    }

    //recursive+memo  memo[i][j]表示在区间[i,j]范围内可以生成所有BST的根节点
    public static List<TreeNode> generateTrees2(int n){
        if (n == 0) return new ArrayList<>();
        Map<int[], List<TreeNode>> memo = new HashMap<>();
        return helper2(1, n, memo);
    }

    public static List<TreeNode> helper2(int l, int r, Map<int[], List<TreeNode>> memo) {
        int[] range = new int[]{l-1, r-1};
        if (l > r) return null;
        if (memo.containsKey(range)) return memo.get(range);
        List<TreeNode> res = new ArrayList<>();
        for (int i = l; i <= r; i++){
            List<TreeNode> left = helper2(l, i-1, memo), right = helper2(i+1, r, memo);
            for (TreeNode a: left){
                for (TreeNode b: right){
                    TreeNode node = new TreeNode(i);
                    node.left = a;
                    node.right = b;
                    res.add(node);
                }
            }
        }
        memo.put(range, res);
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            List<TreeNode> res = generateTrees1(n);
        }
    }
}
