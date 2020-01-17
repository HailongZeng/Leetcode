package Tree;

/**
 * @author HailongZeng
 * @date 1/9/20 10:47 上午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input: [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * Example 2:
 *
 * Input: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 */
public class No129_Sum_Root_to_Leaf_Numbers {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    private static TreeNode stringToTreeNode(String input) {
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
            TreeNode node = nodeQueue.remove();
            if (index == parts.length) break;
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")){
                node.left = new TreeNode(Integer.parseInt(item));
                nodeQueue.add(node.left);
            }
            if (index == parts.length) break;
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")){
                node.right = new TreeNode(Integer.parseInt(item));
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static int sum = 0;
    public static int sumNumbers1(TreeNode root) {
        if (root == null) return 0;
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sum;
    }

    public static void helper(TreeNode root, StringBuilder sb){
        if (root == null) return;
        if (root.left == null && root.right == null){
            sb.append(String.valueOf(root.val));
            sum += Integer.parseInt(sb.toString());
            sb.deleteCharAt(sb.length()-1);
            return;
        }
        sb.append(String.valueOf(root.val));
        helper(root.left, sb);
        helper(root.right, sb);
        sb.deleteCharAt(sb.length()-1);
    }

    public static int sumNumbers2(TreeNode root) {
        return sum(root, 0);
    }

    private static int sum(TreeNode root, int n) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return n*10 + root.val;
        return sum(root.left, n*10+root.val) + sum(root.right, n*10+root.val);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            TreeNode root = stringToTreeNode(line);
            int res = sumNumbers1(root);
            sum = 0;//update the sum when each case
            System.out.println(res);
        }
    }
}
