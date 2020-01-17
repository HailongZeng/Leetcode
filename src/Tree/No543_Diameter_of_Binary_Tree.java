package Tree;

/**
 * @author HailongZeng
 * @date 1/15/20 11:54 上午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
//leetcode543  --->similar with leetcode124
public class No543_Diameter_of_Binary_Tree {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int[] res = {0};
        Map<TreeNode, Integer> m = new HashMap<>();
        helper(root, res, m);
        return res[0];
    }

    public static int helper(TreeNode node, int[] res, Map<TreeNode, Integer> m){
        if (node == null) return 0;
        if (m.containsKey(node)) return m.get(node);
        int l = helper(node.left, res, m);
        int r = helper(node.right, res, m);
        res[0] = Math.max(res[0], l+r);
        int h = Math.max(l, r) + 1;
        m.put(node, h);
        return h;
    }

    private static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return null;
        String[] parts = input.split(",");
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
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

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            TreeNode root = stringToTreeNode(line);
            int res = diameterOfBinaryTree(root);
            System.out.println(res);
        }
    }
}
