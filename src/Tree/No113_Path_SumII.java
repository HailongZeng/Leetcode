package Tree;

/**
 * @author HailongZeng
 * @date 1/8/20 10:55 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public static TreeNode stringToNode(String input){
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return null;
        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        int index = 1;
        while (!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.remove();
            if (index == parts.length) break;
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")){
                node.left = new TreeNode(Integer.parseInt(item));
                nodeQueue.add(node.left);
            }
            if (index == parts.length) break;
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")){
                node.right = new TreeNode(Integer.parseInt(item));
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    //time:O(n)  space:O(logn)
    public static List<List<Integer>> pathSum(TreeNode root, int sum){
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> cur = new ArrayList<>();
        helper(root, res, cur, sum);
        return res;
    }

    private static void helper(TreeNode root, List<List<Integer>> res, List<Integer> cur, int sum) {
        if (root == null) return;
        if (root.left == null && root.right == null){
            if (root.val == sum){
                cur.add(root.val);
                res.add(new ArrayList<>(cur));
                cur.remove(cur.size()-1);
            }
        }
        cur.add(root.val);
        helper(root.left, res, cur, sum-root.val);
        helper(root.right, res, cur, sum-root.val);
        cur.remove(cur.size()-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            TreeNode root = stringToNode(line);
            line = io.readLine();
            int sum = Integer.parseInt(line);
            List<List<Integer>> res = pathSum(root, sum);
            System.out.println(res);
        }
    }
}
