package Amazon_OA2;

/**
 * LeetCode 572
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 *
 * Example 1:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 * Example 2:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return false.
 */
public class Subtree_of_Another_Tree {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    public static boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s, t);
    }

    public static boolean isIdentical(TreeNode x, TreeNode y){
        if (x == null && y == null) return true;
        if (x == null || y == null) return false;
        return x.val == y.val && isIdentical(x.left, y.left) && isIdentical(x.right, y.right);
    }

    public static boolean traverse(TreeNode s, TreeNode t){
        return s != null && (isIdentical(s, t) || traverse(s.left, t) || traverse(s.right, t));
    }

    public static void main(String[] args) {
        TreeNode s = new TreeNode(3);
        s.left= new TreeNode(4);
        s.right = new TreeNode(5);
        s.left.left = new TreeNode(1);
        s.left.right = new TreeNode(2);

        TreeNode t = new TreeNode(4);
        t.left = new TreeNode(1);
        t.right = new TreeNode(2);

        System.out.println(isSubtree(s, t));
    }
}
