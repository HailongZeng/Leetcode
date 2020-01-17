package Tree;

/**
 * @author HailongZeng
 * @date 1/9/20 9:17 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,2,3]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class No144_Binary_Tree_Preorder_Traversal {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    //recursive, time: O(N)  space: O(logN)
    public static List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(root, res);
        return res;
    }

    public static void helper(TreeNode root, List<Integer> res){
        if (root == null) return;
        res.add(root.val);
        helper(root.left, res);
        helper(root.right, res);
    }

    //iterative, time:O(n) space:O(n)
    public static List<Integer> preorderTraversal2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> s = new Stack<>();
        s.add(root);
        while (!s.isEmpty()){
            TreeNode node = s.pop();
            if (node == null) continue;
            res.add(node.val);
            s.add(node.right);
            s.add(node.left);
        }
        return res;
    }

    private static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return null;
        String[] d = input.split(",");
        Queue<String> q = new LinkedList<>(Arrays.asList(d));
        return build(q);
    }

    private static TreeNode build(Queue<String> q) {
        if (q.isEmpty()) return null;
        String s = q.poll();
        if (s.equals("null")) return null;
        int num = Integer.parseInt(s);
        TreeNode node = new TreeNode(num);
        node.left = build(q);
        node.right = build(q);
        return node;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            TreeNode root = stringToTreeNode(line);
            List<Integer> res = preorderTraversal1(root);
            System.out.println(res);
        }
    }
}
