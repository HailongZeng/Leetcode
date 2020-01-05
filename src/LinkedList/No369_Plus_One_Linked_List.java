package LinkedList;

/**
 * @author HailongZeng
 * @date 1/2/20 10:54 上午
 */

import java.util.Scanner;

/**
 * Given a non-negative number represented as a singly linked list of digits, plus one to the number.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example:
 *
 * Input:
 * 1->2->3
 *
 * Output:
 * 1->2->4
 */
public class No369_Plus_One_Linked_List {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    //recursive
    public static ListNode plusOneLinkedList1(ListNode head){
        if (head == null) return head;
        int carry = helper(head);
        if (carry == 1){
            ListNode res = new ListNode(1);
            res.next = head;
            return res;
        }
        return head;
    }

    public static int helper(ListNode node){
        if (node == null) return 1;
        int carry = helper(node.next);
        int sum = node.val + carry;
        node.val = sum % 10;
        return sum / 10;
    }

    //iterative
    public static ListNode plusOneLinkedList2(ListNode head){
        if (head == null) return head;
        ListNode rev_head = reverse(head), cur = rev_head, pre = cur;
        int carry = 1;
        while (cur != null){
            pre = cur;
            int t = cur.val + carry;
            cur.val = t % 10;
            carry = t / 10;
            if (carry == 0) break;
            cur = cur.next;
        }
        if (carry == 1) pre.next = new ListNode(1);
        return reverse(rev_head);
    }

    public static ListNode reverse(ListNode head){
        ListNode pre = null;
        while (head != null){
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }

    public static void printNode(ListNode l){
        while (l != null){
            if (l.next == null) System.out.println(l.val);
            else System.out.print(l.val + "->");
            l = l.next;
        }
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            int n = st.nextInt();  // the length of list
            for (int j = 0; j < n; j++){
                cur.next = new ListNode(st.nextInt());
                cur = cur.next;
            }
            ListNode res = plusOneLinkedList1(dummy.next);
            printNode(res);
        }
    }


}
