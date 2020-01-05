package Divide_and_conquer;

/**
 * @author HailongZeng
 * @date 12/17/19 9:49 上午
 */

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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

    public static TreeNode constructMaximumBinaryTree(int[] nums){
        return helper(nums, 0, nums.length);
    }

    //O(nlogn)
    public static TreeNode helper(int[] nums, int l, int r){
        if (l == r) return null;
        int max_i = max(nums, l, r);
        TreeNode root = new TreeNode(nums[max_i]);
        root.left = helper(nums, l, max_i);
        root.right = helper(nums, max_i + 1, r);
        return root;
    }

    //O(n)
    public static int max(int[] nums, int l, int r){
        int max_i = l;
        for (int i = l; i < r; i++){
            if (nums[max_i] < nums[i]){
                max_i = i;
            }
        }
        return max_i;
    }

    public static void printTreeNode(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        System.out.print("[");
        while (!q.isEmpty()){
            TreeNode tmp = q.poll();
            if (tmp == null) {
                System.out.print("null" + ",");
                continue;
            }
            System.out.print(tmp.val + ",");
            q.offer(tmp.left);
            q.offer(tmp.right);
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++){
                nums[j] = st.nextInt();
            }
            TreeNode res = constructMaximumBinaryTree(nums);
            printTreeNode(res);
        }
    }
}
