package Tree;

/**
 * @author HailongZeng
 * @date 1/5/20 9:46 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given two binary trees, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 *
 * Example 1:
 *
 * Input:     1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * Output: true
 * Example 2:
 *
 * Input:     1         1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 *
 * Output: false
 * Example 3:
 *
 * Input:     1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 *
 * Output: false
 */
public class No100_Same_Tree {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    //recursive   time:O(n)   space:O(logn) if balanced tree  O(n) if unbalanced
    public static boolean isSameTree1(TreeNode p, TreeNode q){
        if (p == null) return q == null;
        if (q == null) return p == null;
        return p.val == q.val && isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right);
    }

    //iteration   time:O(n)   space:O(logn) if balanced tree  O(n) if unbalanced
    public static boolean isSameTree2(TreeNode p, TreeNode q){
        if (p == null && q == null) return true;
        if (!check(p,q)) return false;
        //init deques
        ArrayDeque<TreeNode> deqP = new ArrayDeque<TreeNode>();
        ArrayDeque<TreeNode> deqQ = new ArrayDeque<TreeNode>();
        deqP.addLast(p);
        deqQ.addLast(q);
        while (!deqP.isEmpty()){
            p = deqP.removeFirst();
            q = deqQ.removeFirst();
            if (!check(p, q)) return false;
            if (p != null){
                //in Java nulls are not allowed in Deque
                if (!check(p.left, q.left)) return false;
                if (p.left != null){
                    deqP.addLast(p.left);
                    deqQ.addLast(q.left);
                }
                if (!check(p.right, q.right)) return false;
                if (p.left != null){
                    deqP.addLast(p.right);
                    deqQ.addLast(q.right);
                }
            }
        }
        return true;
    }

    public static boolean check(TreeNode p, TreeNode q){
        if (p == null) return q == null;
        if (q == null) return p == null;
        return p.val == q.val;
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

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = io.readLine()) != null){
            TreeNode p = stringToTreeNode(line);
            line = io.readLine();
            TreeNode q = stringToTreeNode(line);
            boolean res = isSameTree1(p, q);
            System.out.println(res);
        }
    }
}
