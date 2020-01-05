package Amazon_OA2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an N-ary tree, find the subtree with the maximum average. Return the root of the subtree.
 * A subtree of a tree is the node which have at least 1 child plus all its descendants. The average value of a subtree is the sum of its values, divided by the number of nodes.
 *
 * Example 1:
 *
 * Input:
 * 		 20
 * 	   /   \
 * 	 12     18
 *   /  |  \   / \
 * 11   2   3 15  8
 *
 * Output: 18
 * Explanation:
 * There are 3 nodes which have children in this tree:
 * 12 => (11 + 2 + 3 + 12) / 4 = 7
 * 18 => (18 + 15 + 8) / 3 = 13.67
 * 20 => (12 + 11 + 2 + 3 + 18 + 15 + 8 + 20) / 8 = 11.125
 *
 * 18 has the maximum average so output 18.
 */
public class Subtree_with_Maximum_Average {

    public static class Node{
        int val;
        List<Node> children;
        public Node(){}
        public Node(int v, List<Node> c){
            val = v;
            children = c;
        }
    }

    public static double max = 0;
    public static Node res = null;
    public static int[] computeAvg(Node root){
        if (root == null) return new int[]{0, 0};
        int val = root.val, count = 1;
        for (Node child: root.children){
            int[] arr = computeAvg(child);
            val += arr[0];
            count += arr[1];
        }
        if (count > 1 && (res == null || val / (0.0 + count) > max)){
            res = root;
            max = val / (0.0 + count);
        }
        return new int[]{val, count};
    }

    public static Node subtreeWithMaxAverage(Node root){
        double max = 0;
        if (root == null) return res;
        computeAvg(root);
        return res;
    }

    public static void main(String[] args) {

    }
}
