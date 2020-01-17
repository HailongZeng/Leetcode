package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/13/20 1:54 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class No19_Binary_Tree_Maximum_Path_Sum {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    public static int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        sum(root, res);
        return res[0];
    }

    public static int sum(TreeNode root, int[] res){
        if (root == null) return 0;
        int l = Math.max(0, sum(root.left, res));
        int r = Math.max(0, sum(root.right, res));
        int s = l + r + root.val;
        res[0] = Math.max(res[0], s);
        return Math.max(l, r) + root.val;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            TreeNode root = stringToTreeNode(line);
            int res = maxPathSum(root);
            System.out.println(res);
        }
    }

    //the format of input: [xxx,xxx,xxx]
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
            TreeNode node = nodeQueue.poll();
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
