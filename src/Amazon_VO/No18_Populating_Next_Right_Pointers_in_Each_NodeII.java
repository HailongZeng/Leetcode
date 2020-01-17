package Amazon_VO;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author HailongZeng
 * @date 1/13/20 12:39 下午
 */
public class No18_Populating_Next_Right_Pointers_in_Each_NodeII {

    public static class Node{
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public static Node connect1(Node root) {
        if (root == null) return null;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++){
                Node node = q.poll();
                if (i < size - 1) node.next = q.peek();
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }
        return root;
    }

    public static Node connect2(Node root) {
        if (root == null) return null;
        Node p = root.next;
        while (p != null){
            if (p.left != null){
                p = p.left;
                break;
            }
            if (p.right != null){
                p = p.right;
                break;
            }
            p = p.next;
        }
        if (root.right != null) root.right.next = p;
        if (root.left != null) root.left.next = root.right != null ? root.right : p;
        connect2(root.right);
        connect2(root.left);
        return root;
    }

    public static Node connect3(Node root){
        if (root == null) return null;
        Node dummy = new Node(0, null, null, null);
        Node cur = dummy;
        Node head = root;
        while (root != null){
            if (root.left != null){
                cur.next = root.left;
                cur = cur.next;
            }
            if (root.right != null){
                cur.next = root.right;
                cur = cur.next;
            }
            root = root.next;
            if (root == null){
                cur = dummy;
                root = dummy.next;
                dummy.next = null;
            }
        }
        return head;
    }
}
