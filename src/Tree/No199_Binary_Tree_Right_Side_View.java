package Tree;

/**
 * @author HailongZeng
 * @date 1/9/20 10:31 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * Example:
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
//similar with leetcode102 and leetcode103 and leetcode107  all about level-order traversal
public class No199_Binary_Tree_Right_Side_View {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    //BFS  time:O(n)  space:O(n)  O(0.5n) the level with largest nodes
    public static List<Integer> rightSideView1(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()){
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++){
                TreeNode t = nodeQueue.poll();
                if (i == size - 1) res.add(t.val);
                if (t.left != null) nodeQueue.add(t.left);
                if (t.right != null) nodeQueue.add(t.right);
            }
        }
        return res;
    }

    //dfs
    public static List<Integer> rightSideView2(TreeNode root){
        List<Integer> res = new ArrayList();
        if (root == null) return res;
        rightSideView(root, res, 0);
        return res;
    }

    private static void rightSideView(TreeNode root, List<Integer> res, int k) {
        if (root == null) return;
        if (res.size() == k) res.add(root.val);
        rightSideView(root.right, res, k+1);
        rightSideView(root.left, res, k+1);
    }

    private static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return null;
        String[] parts = input.split(",");
        Queue<String> q = new LinkedList<>(Arrays.asList(parts));
        return build(q);
    }

    private static TreeNode build(Queue<String> q) {
        if (q.isEmpty()) return null;
        String s = q.poll();
        if (s.equals("null")) return null;
        int num = Integer.parseInt(s);
        TreeNode root = new TreeNode(num);
        root.left = build(q);
        root.right = build(q);
        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            TreeNode root = stringToTreeNode(line);
            List<Integer> res = rightSideView1(root);
            System.out.println(res);
        }
    }

}
