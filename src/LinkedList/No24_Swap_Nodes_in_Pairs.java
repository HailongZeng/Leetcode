package LinkedList;

/**
 * @author HailongZeng
 * @date 12/24/19 10:07 上午
 */

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 *
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class No24_Swap_Nodes_in_Pairs {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public static ListNode swapPairs1(ListNode head){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null){
            ListNode t = pre.next.next;
            pre.next.next = t.next;
            t.next = pre.next;
            pre.next = t;
            pre = t.next;
        }
        return dummy.next;
    }

    public static ListNode swapPairs2(ListNode head){
        if (head != null || head.next != null) return head;
        ListNode t = head.next;
        head.next = swapPairs2(head.next.next);
        t.next = head;
        return t;
    }

    public static ListNode swapPairs(ListNode head){
        if (head == null) return null;
        ListNode res = new ListNode(-1);
        res.next = head;
        ListNode pre = res;
        while (pre != null){
            pre = reverse(pre, 2);
        }
        return res.next;
    }

    public static ListNode reverse(ListNode pre, int k){
        ListNode last = pre;
        for (int i = 0; i < k + 1; i++){
            last = last.next;
            if (i != k && last == null) return null;
        }
        ListNode tail = pre.next;
        ListNode cur = pre.next.next;
        while (cur != last){ //similar with No92
            tail.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = tail.next;
        }
        return tail;
    }

    public static void printNode(ListNode l){
        while (l != null){
            if (l.next != null) System.out.print(l.val + "->");
            else System.out.println(l.val);
            l = l.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode res = swapPairs(head);
        printNode(res);
    }
}
