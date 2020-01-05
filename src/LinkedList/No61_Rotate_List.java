package LinkedList;

/**
 * @author HailongZeng
 * @date 12/25/19 11:02 上午
 */

/**
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * Example 2:
 *
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */
public class No61_Rotate_List {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public static ListNode rotateRight(ListNode head, int k){
        if (head == null || k == 0) return head;
        ListNode fast = head;
        ListNode slow = head;

        //Calculate the length the ListNode head
        int len = 0;
        while (fast != null){
            len++;
            fast = fast.next;
        }
        fast = head;
        k %= len;
        if (k == 0) return head;

        //determine where should be split, let slow direct to the position
        while (k-- > 0){
            fast = fast.next;
        }
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        fast.next = head; //the last element direct to the head, a circle formed
        head = slow.next;
        slow.next = null; // split the connection, uncircle
        return head;
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
        head.next.next.next.next = new ListNode(5);
        int k = 3;
        ListNode res = rotateRight(head, k);
        printNode(res);
    }
}
