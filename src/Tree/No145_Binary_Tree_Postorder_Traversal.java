package Tree;

/**
 * @author HailongZeng
 * @date 1/9/20 10:13 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 Given a binary tree, return the postorder traversal of its nodes' values.

 Example:

 Input: [1,null,2,3]
 1
 \
 2
 /
 3

 Output: [3,2,1]
 Follow up: Recursive solution is trivial, could you do it iteratively?

 */
public class No145_Binary_Tree_Postorder_Traversal {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    //recursive  time:O(n)  space:O(logn)
    public static List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(root, res);
        return res;
    }

    public static void helper(TreeNode root, List<Integer> res){
        if (root == null) return;
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
    }

    //iterative  time:O(n)  space:O(n)
    public static List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> s = new Stack<>();
        s.add(root);
        while (!s.isEmpty()){
            TreeNode node = s.pop();
            if (node == null) continue;
            res.addFirst(node.val);
            s.add(node.left);
            s.add(node.right);
        }
        return res;
    }

    private static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return null;
        Queue<String> q = new LinkedList<>(Arrays.asList(input.split(",")));
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
            List<Integer> res = postorderTraversal1(root);
            System.out.println(res);
        }
    }

}
