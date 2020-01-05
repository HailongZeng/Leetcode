package LinkedList;

/**
 * @author HailongZeng
 * @date 12/26/19 1:17 下午
 */

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 * Note: Do not modify the linked list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 *
 * Example 2:
 *
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 *
 * Example 3:
 *
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 *
 * Follow-up:
 * Can you solve it without using extra space?
 */
public class No142_Linked_List_CycleII {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
            next = null;
        }
    }

    //time:O(n)  space:O(n)
    public static ListNode detectCycle1(ListNode head){
        Set<ListNode> nodeSeen = new HashSet<>();
        while (head != null){
            if (nodeSeen.contains(head)){
                return head;
            }else{
                nodeSeen.add(head);
            }
            head = head.next;
        }
        return null;
    }

    //time:O(n)  space:O(1)
    public static ListNode detectCycle2(ListNode head){
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                ListNode slow2 = head;
                while (slow2 != slow){
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow2;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++) {
            int n = st.nextInt();
            ListNode dummy = new ListNode(-1);
            ListNode pre = dummy;
            for (int j = 0; j < n; j++) {
                pre.next = new ListNode(st.nextInt());
                pre = pre.next;
            }
            ListNode cur = dummy.next;
            int pos = st.nextInt();
            for (int j = 0; j < pos; j++) {
                cur = cur.next;
            }
            if (pos < 0) {
                ListNode res = detectCycle1(dummy.next);
            } else {
                pre.next = cur;
                ListNode res = detectCycle1(dummy.next);
            }
        }
    }
}
