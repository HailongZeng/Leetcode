package Tree;

/**
 * @author HailongZeng
 * @date 1/5/20 1:27 下午
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.io.IOException;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class No102_Binary_Tree_Level_Order_Traversal {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    //bfs
    public static List<List<Integer>> levelOrder1(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res; //corner case
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            List<Integer> l = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++){
                TreeNode tmp = q.poll();
                l.add(tmp.val);
                if (tmp.left != null) q.add(tmp.left);
                if (tmp.right != null) q.add(tmp.right);
            }
            res.add(l);
        }
        return res;
    }

    //dfs
    public static List<List<Integer>> levelOrder2(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(root, 0, res);
        return res;
    }

    private static void helper(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) return;
        if (res.size() == level) res.add(new ArrayList<>());
        res.get(level).add(root.val);
        if (root.left != null) helper(root.left, level+1, res);
        if (root.right != null) helper(root.right, level+1, res);
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

    public static void main(String[] args) throws IOException{
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            TreeNode root = stringToTreeNode(line);
            System.out.println(levelOrder1(root));
        }
    }
}
