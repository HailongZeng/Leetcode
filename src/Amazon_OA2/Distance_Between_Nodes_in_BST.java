package Amazon_OA2;

/**
 * Given a list of unique integers nums, construct a BST from it (you need to insert nodes one-by-one with the given order to get the BST) and find the distance between two nodes node1 and node2. Distance is the number of edges between two nodes. If any of the given nodes does not appear in the BST, return -1.
 *
 * Example 1:
 *
 * Input: nums = [2, 1, 3], node1 = 1, node2 = 3
 * Output: 2
 * Explanation:
 *      2
 *    /   \
 *   1     3
 */
public class Distance_Between_Nodes_in_BST {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    public static void addToBST(TreeNode root, TreeNode node){
        for (TreeNode curr = root; true;){
            if (curr.val > node.val){
                if (curr.left == null){
                    curr.left = node;
                    break;
                } else{
                    curr = curr.left;
                }
            } else{
                if (curr.right == null){
                    curr.right = node;
                    break;
                }else{
                    curr = curr.right;
                }
            }
        }
    }

    public static TreeNode buildBST(int[] nums, int node1, int node2){
        TreeNode root = null;
        boolean found1 = false;
        boolean found2 = false;
        for (int val: nums){
            if (val == node1) found1 = true;
            if (val == node2) found2 = true;
            TreeNode node = new TreeNode(val);
            if (root == null){
                root = node;
                continue;
            }
            addToBST(root, node);
        }
        if (!found1 || !found2) return null;
        return root;
    }

    public static TreeNode lca(TreeNode root, int node1, int node2){
        while (true){
            if (root.val > node1 && root.val > node2){
                root = root.left;
            } else if (root.val < node1 && root.val < node2){
                root = root.right;
            } else{
                return root;
            }
        }
    }

    public static int getDistance(TreeNode src, int dest){
        if (src.val == dest) return 0;
        TreeNode node = src.left;
        if (src.val < dest){
            node = src.right;
        }
        return 1 + getDistance(node, dest);
    }

    public static int distanceBetweenNodes(int[] nums, int node1, int node2){
        TreeNode root = buildBST(nums, node1, node2);
        if (root == null) return -1;
        TreeNode lca = lca(root, node1, node2);
        return getDistance(lca, node1) + getDistance(lca, node2);
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 3};
        int node1 = 1;
        int node2 = 3;
        System.out.println(distanceBetweenNodes(nums, node1, node2));
    }
}
