package Tree;

/**
 * @author HailongZeng
 * @date 1/5/20 5:00 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class No107_Binary_Tree_Level_Order_TraversalI {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    //format of input: [xxx,xxx,xxx]
    public static TreeNode stringToTreeNode(String input){
        input = input.trim();
        input = input.substring(1, input.length() - 1);
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
            item = item.trim();
            if (!item.equals("null")){
                node.left = new TreeNode(Integer.parseInt(item));
                nodeQueue.add(node.left);
            }
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")){
                node.right = new TreeNode(Integer.parseInt(item));
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static List<List<Integer>> levelOrderBottom1(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()){
            List<Integer> l = new ArrayList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = nodeQueue.poll();
                l.add(node.val);
                if (node.left != null) nodeQueue.add(node.left);
                if (node.right != null) nodeQueue.add(node.right);
            }
            res.addFirst(l);
        }
        return res;
    }

    public static List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(root, 0, res);
        return res;
    }

    public static void helper(TreeNode root, int level, List<List<Integer>> res){
        if (root == null) return;
        if (res.size() == level) res.add(0, new ArrayList<Integer>());
        res.get(res.size()-1-level).add(root.val);
        helper(root.left, level+1, res);
        helper(root.right, level+1, res);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            TreeNode root = stringToTreeNode(line);
            System.out.println(levelOrderBottom1(root));
        }
    }

}
