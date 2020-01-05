package LinkedList;

/**
 * @author HailongZeng
 * @date 12/26/19 12:48 下午
 */

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Given a linked list, determine if it has a cycle in it.
 *
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 *
 * Example 2:
 *
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 *
 * Example 3:
 *
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 *
 * Follow up:
 *
 * Can you solve it using O(1) (i.e. constant) memory?
 */
public class No141_Linked_List_Cycle {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
            next = null;
        }
    }

    //Time: O(n)   space: O(n)
    public static boolean hasCycle1(ListNode head){
        Set<ListNode> nodeSeen = new HashSet<>();
        while (head != null){
            if (nodeSeen.contains(head)){
                return true;
            }else{
                nodeSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }

    //Time: O(n)  space: O(1) two pointer
    public static boolean hasCycle2(ListNode head){
        ListNode slow = head, fast = head;
        //此链表的元素个数可能是奇数或偶数个
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
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
            int n = st.nextInt();
            ListNode dummy = new ListNode(-1);
            ListNode pre = dummy;
            for (int j = 0; j < n; j++){
                pre.next = new ListNode(st.nextInt());
                pre = pre.next;
            }
            ListNode cur = dummy.next;
            int pos = st.nextInt();
            for (int j = 0; j < pos; j++){
                cur = cur.next;
            }
            if (pos < 0) System.out.println(hasCycle2(dummy.next));
            else{
                pre.next = cur;
                System.out.println(hasCycle2(dummy.next));
            }
        }
    }
}
