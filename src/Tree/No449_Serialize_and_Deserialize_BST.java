package Tree;

/**
 * @author HailongZeng
 * @date 1/8/20 10:01 下午
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
//obviously we can do the same operation like serialize and deserialize binary tree(leetcode 297)
public class No449_Serialize_and_Deserialize_BST {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    public static String serialize(TreeNode root){
        if (root == null) return null;
        StringBuilder sb = new StringBuilder();
        build(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private static void build(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val);
        sb.append(",");
        build(root.left, sb);
        build(root.right, sb);
    }

    public static TreeNode deserialize(String data){
        if (data == null || data.length() == 0) return null;
        String[] d = data.split(",");
        Queue<String> q = new LinkedList<>(Arrays.asList(d));
        return helper(q);
    }

    private static TreeNode helper(Queue<String> q) {
        if (q.isEmpty()) return null;
        String s = q.poll();
        if (s.equals("null")) return null;
        int num = Integer.parseInt(s);
        TreeNode node = new TreeNode(num);
        node.left = helper(q);
        node.right = helper(q);
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        String s = serialize(root);
        System.out.println(s);
    }
}
