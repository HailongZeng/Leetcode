package Tree;

/**
 * @author HailongZeng
 * @date 1/5/20 11:16 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class No103_Binary_Tree_Zigzag_Level_Order_Traversal {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    public static TreeNode stringToTreeNode(String input){
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
            TreeNode node = nodeQueue.poll();
            if (index == parts.length) break;
            item = parts[index++];
            item.trim();
            if (!item.equals("null")){
                node.left = new TreeNode(Integer.parseInt(item));
                nodeQueue.add(node.left);
            }
            item = parts[index++];
            item.trim();
            if (!item.equals("null")){
                node.right = new TreeNode(Integer.parseInt(item));
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        boolean reverse = false;
        while (!nodeQueue.isEmpty()){
            LinkedList<Integer> l = new LinkedList<>();
            int size = nodeQueue.size();
            if (!reverse){
                for (int i = 0; i < size; i++){
                    TreeNode tmp = nodeQueue.poll();
                    l.add(tmp.val);
                    if (tmp.left != null) nodeQueue.add(tmp.left);
                    if (tmp.right != null) nodeQueue.add(tmp.right);
                }
                reverse = true;
            }else{
                for (int i = 0; i < size; i++){
                    TreeNode tmp = nodeQueue.poll();
                    l.addFirst(tmp.val);
                    if (tmp.left != null) nodeQueue.add(tmp.left);
                    if (tmp.right != null) nodeQueue.add(tmp.right);
                }
                reverse = false;
            }
            res.add(l);
        }
        return res;
    }

    public static List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(root, 0, res);
        return res;
    }

    public static void helper(TreeNode node, int level, List<List<Integer>> res){
        if (node == null) return;
        if (res.size() == level){
            LinkedList<Integer> newLevel = new LinkedList<Integer>();
            newLevel.add(node.val);
            res.add(newLevel);
        }else{
            if (level % 2 == 0) res.get(level).add(node.val);
            else res.get(level).add(0, node.val);
        }
        if (node.left != null) helper(node.left, level+1, res);
        if (node.right != null) helper(node.right, level+1, res);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = io.readLine()) != null){
            TreeNode root = stringToTreeNode(line);
            List<List<Integer>> res = zigzagLevelOrder1(root);
            System.out.println(res);
        }
    }
}
