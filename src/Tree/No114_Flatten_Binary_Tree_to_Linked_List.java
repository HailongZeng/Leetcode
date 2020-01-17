package Tree;

/**
 * @author HailongZeng
 * @date 1/11/20 12:34 上午
 */

import java.util.Stack;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example, given the following tree:
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class No114_Flatten_Binary_Tree_to_Linked_List {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    //iterative   time:O(n)  space:O(n)
    //从根节点开始出发，先检测其左子结点是否存在，如存在则将根节点和其右节点断开，将左子节点以及其后面所有结构一起连到原右子节点的位置，把原右子节点连到原左子节点最后面的右子节点之后
    public static void flatten1(TreeNode root){
        TreeNode cur = root;
        while (cur != null){
            if (cur.left != null){
                TreeNode p = cur.left;
                while (p.right != null) p = p.right;
                p.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    //前序迭代
    //iterative   time:O(n)  space:O(n)
    public static void flatten2(TreeNode root){
        if (root == null) return;
        Stack<TreeNode> s = new Stack<>();
        s.add(root);
        while (!s.isEmpty()){
            TreeNode t = s.pop();
            if (t.left != null){
                TreeNode r = t.left;
                while (r.right != null) r = r.right;
                r.right = t.right;
                t.right = t.left;
                t.left = null;
            }
            if (t.right != null) s.add(t.right);
        }
    }

    //recursive
    //先利用DFS的思路找到最左子节点，然后回到其父节点，把父节点和右子节点断开，将原左子结点连上父节点的右子节点，然后再把原右子节点连到新右子节点的右子节点上，然后再回到上一父节点做相同操作
    public static void flatten3(TreeNode root){
        if (root == null) return;
        if (root.left != null) flatten3(root.left);
        if (root.right != null) flatten3(root.right);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) root = root.right;
        root.right = tmp;
    }
}
