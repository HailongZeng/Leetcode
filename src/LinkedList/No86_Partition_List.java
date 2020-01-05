package LinkedList;

/**
 * @author HailongZeng
 * @date 12/26/19 9:32 上午
 */

import java.util.Scanner;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example:
 *
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */
public class No86_Partition_List {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    //use two listnode, one for the nodes larger than x, the other for nodes not larger than x
    public static ListNode partition(ListNode head, int x){
        ListNode before_head = new ListNode(-1);
        ListNode before = before_head;
        ListNode after_head = new ListNode(-1);
        ListNode after = after_head;

        while (head != null){
            if (head.val < x){
                before.next = head;
                before = before.next;
            }else{
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        after.next = null;
        before.next = after_head.next;
        return before_head.next;
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
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            for (int j = 0; j < n; j++) {
                pre.next = new ListNode(st.nextInt());
                pre = pre.next;
            }
            int x = st.nextInt();
            ListNode res = partition(dummy.next, x);
            printNode(res);
        }
    }
}
