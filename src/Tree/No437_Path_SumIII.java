package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 *
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * Example:
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * Return 3. The paths that sum to 8 are:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 */
public class No437_Path_SumIII {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    //Recursive  time:O(n^2)  space:O(n)
    public static int pathSum1(TreeNode root, int sum){
        if (root == null) return 0;
        return numberOfPaths(root, sum) + pathSum1(root.left, sum) + pathSum1(root.right, sum);
    }

    public static int numberOfPaths(TreeNode root, int left){
        if (root == null) return 0;
        left -= root.val;
        return (left == 0 ? 1 : 0) + numberOfPaths(root.left, left) + numberOfPaths(root.right, left);
    }

    //Running prefix sum  time:O(n)  space:O(n)
    public static int pathSum2(TreeNode root, int sum){
        if (root == null) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return countPath(root, sum, 0, map);
    }

    public static int countPath(TreeNode root, int target, int curSum, HashMap<Integer, Integer> map){
        if (root == null) {
            return 0;
        }
        curSum += root.val;
        // see if there is a subarray sum equals to target
        int count = map.getOrDefault(curSum - target, 0);
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        // Extend to left and right child
        count += countPath(root.left, target, curSum, map) + countPath(root.right, target, curSum, map);
        //Remove the current node so it won't affect other path(Backtracking)
        map.put(curSum, map.get(curSum) - 1);
        return count;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        int sum = 8;

        System.out.println(pathSum1(root, sum));
        System.out.println(pathSum2(root, sum));
    }
}
