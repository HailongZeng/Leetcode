package LinkedList;

/**
 * @author HailongZeng
 * @date 12/25/19 2:26 下午
 */

import java.util.Scanner;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class No83_Remove_Duplicates_From_Sorted_ListII {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public static ListNode deleteDuplicates1(ListNode head) {
        ListNode current = head;
        while(current != null && current.next != null){
            if (current.next.val == current.val){
                current.next = current.next.next; //jump the duplicated element
            }else{
                current = current.next;
            }
        }
        return head;
    }

    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;
        head.next = deleteDuplicates2(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    public static void printNode(ListNode l){
        while (l != null){
            if (l.next != null) System.out.print(l.val + "->");
            else System.out.println(l.val);
            l = l.next;
        }
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            int n = st.nextInt();  // the length of ListNode
            for (int j = 0; j < n; j++){
                cur.next = new ListNode(st.nextInt());
                cur = cur.next;
            }
            ListNode res = deleteDuplicates1(dummy.next);
            printNode(res);
        }
    }
}
