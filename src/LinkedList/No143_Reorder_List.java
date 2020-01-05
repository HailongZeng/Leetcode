package LinkedList;

/**
 * @author HailongZeng
 * @date 12/26/19 2:08 下午
 */

import java.util.Scanner;
import java.util.Stack;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example 1:
 *
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 *
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class No143_Reorder_List {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    /*
    思路：
        1.使用快慢指针找到链表的中点，并将链表从中点处断开，形成两个独立的链表
        2.将第二个链变翻转
        3.将第二个链表的元素间隔地插入到第一个链表中
     */
    public static void reorderList(ListNode head){
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        ListNode last = mid, pre = null;
        //2.翻转第二段链表
        while (last != null){
            ListNode next = last.next;
            last.next = pre;
            pre = last;
            last = next;
        }
        //3.将第二个链表的元素间隔地插入到第一个链表中
        while (head != null && pre != null){
            ListNode next = head.next;
            head.next = pre;
            pre = pre.next;
            head.next.next = next;
            head = next;
        }
    }

    public static void reorderList2(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        Stack<ListNode> st = new Stack<>();
        ListNode cur = head;
        while (cur != null){
            st.push(cur);
            cur = cur.next;
        }
        int cnt = (st.size() - 1) / 2;
        cur = head;
        while (cnt-- > 0){
            ListNode t = st.pop();
            ListNode next = cur.next;
            cur.next = t;
            t.next = next;
            cur = next;
        }
        st.pop().next = null;
    }

    public static void printNode(ListNode l){
        while(l != null){
            if (l.next == null) System.out.println(l.val);
            else System.out.print(l.val + "->");
            l = l.next;
        }
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            ListNode dummy = new ListNode(-1);
            ListNode pre = dummy;
            for (int j = 0; j < n; j++){
                pre.next = new ListNode(st.nextInt());
                pre = pre.next;
            }
            ListNode head = dummy.next;
            printNode(head);
            reorderList(head);
            printNode(head);
        }
    }
}
