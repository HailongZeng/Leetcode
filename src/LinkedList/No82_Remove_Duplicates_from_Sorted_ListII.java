package LinkedList;

import java.util.Scanner;

/**
 * @author HailongZeng
 * @date 12/25/19 2:43 下午
 */
public class No82_Remove_Duplicates_from_Sorted_ListII {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public static ListNode deleteDuplicates1(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null){
            ListNode cur = pre.next;
            while (cur.next != null && cur.next.val == cur.val){
                cur = cur.next;
            }
            if (cur != pre.next) pre.next = cur.next; //duplicate, do not move pre, but let pre.next-->cur.next
            else pre = pre.next; //no duplicate, move pre to pre.next
        }
        return dummy.next;
    }

    public static ListNode deleteDuplicates2(ListNode head){
        if (head == null) return head;
        if (head.next != null && head.val == head.next.val){
            while (head.next != null && head.val == head.next.val){
                head = head.next;
            }
            return deleteDuplicates2(head.next);
        }
        head.next = deleteDuplicates2(head.next);
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
