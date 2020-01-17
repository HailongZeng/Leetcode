package Tree;

/**
 * @author HailongZeng
 * @date 1/9/20 9:44 上午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 *
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 *
 * Example 1:
 *
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 * Note:
 *
 * The size of the given array will be in the range [1,1000].
 */
public class No654_Maximum_Binary_Tree {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    //recursive   time:O(nlogn)   space:O(logn)  if balanced   time:O(n^2)  space:O(n)
    public static TreeNode constructMaximumBinaryTree1(int[] nums){
        if (nums == null || nums.length == 0) return null;
        return helper(nums, 0, nums.length - 1);
    }

    private static TreeNode helper(int[] nums, int l, int r) {
        if (l > r) return null;
        int max = nums[l];
        int maxIndex = l;
        for (int i = l + 1; i <= r; i++){
            if (nums[i] > max){
                max = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = helper(nums, l, maxIndex - 1);
        root.right = helper(nums, maxIndex + 1, r);
        return root;
    }

    //time:O(n)  space:O(n)
    public static TreeNode constructMaximumBinaryTree2(int[] nums){
        if (nums == null || nums.length == 0) return null;
        Deque<TreeNode> nodeQueue = new ArrayDeque<>();
        for (int num: nums){
            TreeNode cur = new TreeNode(num);
            while (!nodeQueue.isEmpty() && nodeQueue.peekLast().val < num){
                cur.left = nodeQueue.pollLast();
            }
            if (!nodeQueue.isEmpty()){
                nodeQueue.peekLast().right = cur;
            }
            nodeQueue.offerLast(cur);
        }
        return nodeQueue.peekFirst();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] nums = stringToArray(line);
            TreeNode root = constructMaximumBinaryTree1(nums);
            String res = treeNodeToString(root);
            System.out.println(res);
        }
    }

    private static String treeNodeToString(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.remove();
            if (node == null){
                sb.append("null, ");
                continue;
            }
            sb.append(node.val + ", ") ;
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + sb.toString().substring(0, sb.length() - 2) + "]";
    }

    //the format of input:[xxx, xxx, xxx]
    private static int[] stringToArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++){
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }


}
