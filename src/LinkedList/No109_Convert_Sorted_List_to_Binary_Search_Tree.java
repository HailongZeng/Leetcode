package LinkedList;

/**
 * @author HailongZeng
 * @date 12/26/19 9:50 上午
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted linked list: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class No109_Convert_Sorted_List_to_Binary_Search_Tree {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    public static TreeNode sortedListToBST(ListNode head){
        if (head == null) return null;
        return helper(head, null);
    }

    public static TreeNode helper(ListNode head, ListNode tail){
        if (head == tail) return null;
        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode cur = new TreeNode(slow.val);
        cur.left = helper(head, slow);
        cur.right = helper(slow.next, tail);
        return cur;
    }

    public static void printNode(ListNode l){
        while (l != null){
            if (l.next == null) System.out.println(l.val);
            else System.out.print(l.val + "->");
            l = l.next;
        }
    }

    public static void printTree(TreeNode t){
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(t);
        System.out.print("[");
        while (!q.isEmpty()){
            TreeNode cur = q.poll();
            if (cur == null){
                if (q.peek() != null) {
                    System.out.print(null + ",");
                }else{
                    break;
                }
                continue;
            }
            if (cur.left == null && cur.right == null && q.size() == 0){
                System.out.print(cur.val);
            }else{
                System.out.print(cur.val + ",");
            }
            q.offer(cur.left);
            q.offer(cur.right);
        }
        System.out.println("]");
    }

    public static void main(String[] args){
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        for (int i = 0; i < N; i++) {
            int n = st.nextInt();
            for (int j = 0; j < n; j++) {
                pre.next = new ListNode(st.nextInt());
                pre = pre.next;
            }
            TreeNode res = sortedListToBST(dummy.next);
            printTree(res);
        }
    }
}
