package Tree;

/**
 * @author HailongZeng
 * @date 1/6/20 3:34 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class No105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    public static int index = 0;
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) return null;
        return helper(preorder, inorder, 0, inorder.length - 1);
    }

    public static TreeNode helper(int[] preorder, int[] inorder, int l, int r) {
        if (l > r || index >= preorder.length) return null;
        int mid = r;
        while (mid >= l && preorder[index] != inorder[mid]) mid--; //find the root element in inorder
        TreeNode root = new TreeNode(preorder[index]);
        index++;

        root.left = helper(preorder, inorder, l, mid-1);
        root.right = helper(preorder, inorder, mid+1, r);
        return root;
    }

    public static int[] stringToArray(String input){
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[]{};
        String[] parts = input.split(",");
        int[] out = new int[parts.length];
        for (int i = 0; i < parts.length; i++){
            out[i] = Integer.parseInt(parts[i]);
        }
        return out;
    }

    public static String treeNodeToString(TreeNode root){
        if (root == null) return "[]";
        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.remove();
            if (node == null) {
                output += "null, ";
                continue;
            }
            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] preorder = stringToArray(line);
            line = io.readLine();
            int[] inorder = stringToArray(line);
            TreeNode root = buildTree(preorder, inorder);
            String res = treeNodeToString(root);
            System.out.println(res);
        }
    }
}
