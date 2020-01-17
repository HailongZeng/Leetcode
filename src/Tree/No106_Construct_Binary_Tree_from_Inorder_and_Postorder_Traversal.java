package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author HailongZeng
 * @date 1/6/20 4:04 下午
 */
public class No106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {

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

    public static int index = 0;
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0 || inorder == null || postorder == null || inorder.length != postorder.length) return null;
        index = postorder.length - 1;
        return helper(inorder, postorder, 0, inorder.length - 1);
    }

    public static TreeNode helper(int[] inorder, int[] postorder, int l, int r){
        if (l > r || index < 0) return null;
        int mid = r;
        while (mid >= l && postorder[index] != inorder[mid]) mid--;
        TreeNode root = new TreeNode(postorder[index]);
        index--;
        root.right = helper(inorder, postorder, mid+1, r);
        root.left = helper(inorder, postorder, l, mid-1);
        return root;
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
