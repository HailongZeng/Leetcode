package Tree;

/**
 * @author HailongZeng
 * @date 1/11/20 4:51 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)
 *
 * If there are no nodes with an even-valued grandparent, return 0.
 *
 *
 *
 * Example 1:
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 18
 * Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is between 1 and 10^4.
 * The value of nodes is between 1 and 100.
 */
public class No1315_Sum_of_Nodes_With_Even_Valued_Grandparent {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    //DFS
    public static int sumEvenGrandparent1(TreeNode root){
        if (root == null) return 0;
        int[] res = new int[1];
        solve(root, res, null, null);
        return res[0];
    }

    public static void solve(TreeNode r, int[] res, TreeNode p, TreeNode gp){
        if (r == null) return;
        if (gp != null && gp.val % 2 == 0) res[0] += r.val;
        solve(r.left, res, r, p);
        solve(r.right, res, r, p);
    }

    //BFS
    public static int sumEvenGrandparent2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> p = new LinkedList<>();
        Queue<TreeNode> gp = new LinkedList<>();
        Queue<TreeNode> s = new LinkedList<>();
        int res = 0;
        s.add(root);
        p.add(null);
        gp.add(null);
        while (!s.isEmpty()){
            int size = s.size();
            for (int i = 0; i < size; i++){
                TreeNode node = s.poll();
                TreeNode parent = p.poll();
                TreeNode grandparent = gp.poll();
                if (grandparent != null && grandparent.val % 2 == 0){
                    res += node.val;
                }
                if (node.left != null){
                    s.add(node.left);
                    p.add(node);
                    gp.add(parent);
                }
                if (node.right != null){
                    s.add(node.right);
                    p.add(node);
                    gp.add(parent);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            TreeNode root = stringToTreeNode1(line);
            int res = sumEvenGrandparent1(root);
            System.out.println(res);
        }
    }

    private static String treeNodeToString(TreeNode root) {
        if (root == null) return null;
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private static void helper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return;
        }
        sb.append(root.val);
        sb.append(",");
        helper(root.left, sb);
        helper(root.right, sb);
    }

    private static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return null;
        String[] parts = input.split(",");
        Queue<String> q = new LinkedList<>(Arrays.asList(parts));
        return build(q);
    }

    private static TreeNode build(Queue<String> q) {
        if (q.isEmpty()) return null;
        String s = q.poll();
        if (s.equals("null")) return null;
        else {
            int num = Integer.parseInt(s);
            TreeNode node = new TreeNode(num);
            node.left = build(q);
            node.right = build(q);
            return node;
        }
    }

    public static TreeNode stringToTreeNode1(String data){
        if (data == null) return null;
        data = data.trim();
        data = data.substring(1, data.length() - 1);
        if (data.length() == 0) return null;
        String[] parts = data.split(",");
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

}
