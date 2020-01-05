package LinkedList;

/**
 * @author HailongZeng
 * @date 1/2/20 10:42 上午
 */

import java.util.Scanner;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 *
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * Example 2:
 *
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 * Note:
 *
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 */
public class No328_Odd_Even_Linked_List {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    //time: O(n)   space: O(1)
    public static ListNode oddEvenList(ListNode head){
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
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
            ListNode res = oddEvenList(dummy.next);
            printNode(res);
        }
    }
}
