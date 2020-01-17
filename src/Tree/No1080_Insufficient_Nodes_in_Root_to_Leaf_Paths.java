package Tree;

/**
 * @author HailongZeng
 * @date 1/14/20 11:02 上午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, consider all root to leaf paths: paths from the root to any leaf.  (A leaf is a node with no children.)
 *
 * A node is insufficient if every such root to leaf path intersecting this node has sum strictly less than limit.
 *
 * Delete all insufficient nodes simultaneously, and return the root of the resulting binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
 *
 * Output: [1,2,3,4,null,null,7,8,9,null,14]
 * Example 2:
 *
 *
 * Input: root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
 *
 * Output: [5,4,8,11,null,17,4,7,null,null,null,5]
 *
 *
 * Example 3:
 *
 *
 * Input: root = [1,2,-3,-5,null,4,null], limit = -1
 *
 * Output: [1,null,-3,4]
 *
 *
 * Note:
 *
 * The given tree will have between 1 and 5000 nodes.
 * -10^5 <= node.val <= 10^5
 * -10^9 <= limit <= 10^9
 */
public class No1080_Insufficient_Nodes_in_Root_to_Leaf_Paths {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    public static TreeNode sufficientSubset(TreeNode root, int limit) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root.val < limit ? null : root;
        root.left = sufficientSubset(root.left, limit-root.val);
        root.right = sufficientSubset(root.right, limit-root.val);
        return root.left == null && root.right == null ? null : root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            TreeNode root = stringToTreeNode(line);
            line = io.readLine();
            int limit = Integer.parseInt(line);
            TreeNode node = sufficientSubset(root, limit);
            String res = treeNodeToString(node);
            System.out.println(res);
        }
    }

    private static String treeNodeToString(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.remove();
            if (node == null){
                sb.append("null, ");
                continue;
            }
            sb.append(node.val + ", ") ;
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + sb.toString().substring(0, sb.length() - 2) + "]";
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

}
