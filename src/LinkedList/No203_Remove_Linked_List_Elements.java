package LinkedList;

/**
 * @author HailongZeng
 * @date 1/2/20 9:51 上午
 */

import java.util.Scanner;

/**
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example:
 *
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 */
public class No203_Remove_Linked_List_Elements {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public static ListNode removeElements(ListNode head, int val){
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (head != null){
            if (head.val == val) {
                head = head.next;
                continue;
            }
            cur.next = new ListNode(head.val);
            cur = cur.next;
            head = head.next;
        }
        return dummy.next;
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
            int val = st.nextInt();
            ListNode res = removeElements(dummy.next, val);
            printNode(res);
        }
    }
}
