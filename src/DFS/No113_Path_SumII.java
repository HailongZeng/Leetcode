package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class No113_Path_SumII {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        helper(root, sum, path, res);
        return res;
    }

    public static void helper(TreeNode root, int sum, List<Integer> cur, List<List<Integer>> res){
        if (root == null) return;
        if (root.left == null && root.right == null){
            if (root.val == sum){
                cur.add(root.val);
                res.add(new ArrayList<>(cur));
                cur.remove(cur.size()-1);
            }
        }
        cur.add(root.val);
        helper(root.left,sum-root.val, cur, res);
        helper(root.right, sum-root.val, cur, res);
        cur.remove(cur.size()-1);
    }

    public static void main(String[] args) {
        int sum = 22;
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        System.out.println(pathSum(root, sum));
    }
}
