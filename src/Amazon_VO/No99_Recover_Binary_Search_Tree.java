package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/5/20 10:04 上午
 */

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Example 1:
 *
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * Example 2:
 *
 * Input: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * Output: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * Follow up:
 *
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Example 1:
 *
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * Example 2:
 *
 * Input: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * Output: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * Follow up:
 *
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 */
public class No99_Recover_Binary_Search_Tree {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    //1.root == null or there are just one node, return
    //2.there are only two node
    //3.# of node > 3. inorder traversal
    //  3.1 partial descending nodes are neighbor of each other like [3,6,4], only one descending point(4<6)
    //  3.2 partial descending nodes are not neighbor like [6,4,5,3], two descending points(4<6, 3<5), 6 and 3 are the mistaken element
    //time:O(n)   space:O(n)
    public static void recoverTree1(TreeNode root){
        List<TreeNode> nodes = new ArrayList<>();
        inorderTraversal1(root, nodes);
        int size = nodes.size();
        TreeNode first = null, second = null;
        for (int i = 0; i < size - 1; i++){
            TreeNode prev = nodes.get(i);
            TreeNode next = nodes.get(i+1);
            if (prev.val >= next.val){
                if (first == null){
                    first = prev;
                }
                second = next;
            }
        }
        int val = first.val;
        first.val = second.val;
        second.val = val;
    }

    public static void inorderTraversal1(TreeNode root, List<TreeNode> nodes){
        if (root == null) return;
        inorderTraversal1(root.left, nodes);
        nodes.add(root);
        inorderTraversal1(root.right, nodes);
    }

    private static TreeNode firstNode;
    private static TreeNode secondNode;
    private static TreeNode prev;
    public static void recoverTree2(TreeNode root){
        inorderTraversal2(root);
        int firstVal = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = firstVal;
        return;
    }

    public static void inorderTraversal2(TreeNode root){
        if (root == null) return;
        inorderTraversal2(root.left);
        if (prev != null && prev.val > root.val){
            if (firstNode == null) firstNode = prev;
            secondNode = root;
        }
        prev = root;
        inorderTraversal2(root.right);
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
            TreeNode node = nodeQueue.remove();
            if (index == parts.length) break;
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")){
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }
            if (index == parts.length) break;
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")){
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String treeNodeToString(TreeNode root) {
        if (root == null) return "[]";
        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.remove();
            if (node == null){
                output += "null, ";
                continue;
            }
            output += String.valueOf(node.val) + ",";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null){
            TreeNode root = stringToTreeNode(line);
            recoverTree2(root);
            String res = treeNodeToString(root);
            System.out.println(res);
        }
    }

}
