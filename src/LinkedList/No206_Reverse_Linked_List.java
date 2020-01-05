package LinkedList;

/**
 * @author HailongZeng
 * @date 12/23/19 8:23 下午
 */

/**
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class No206_Reverse_Linked_List {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    //iterative   time:O(n)  space:O(1)
    public static ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    //recursive   time:O(n)  space:O(n)
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList2(head.next);
        printNode(p);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void printNode(ListNode l){
        while (l != null){
            if (l.next != null) System.out.print(l.val + "-->");
            else System.out.println(l.val);
            l = l.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

//        ListNode res = reverseList1(head);
        ListNode res = reverseList2(head);
        printNode(res);
    }
}
