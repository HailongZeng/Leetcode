package LinkedList;

/**
 * @author HailongZeng
 * @date 12/23/19 10:25 下午
 */

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 */
public class No19_Remove_Nth_Node_From_End_of_List {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null) return null;
        ListNode temp = head;
        int count = 0;
        while (temp != null){
            count++;
            temp = temp.next;
        }
        if (count < n) return head;
        if (count == n) return head.next;
        int pos = count - n;
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        int i = 0;
        while (head != null){
            cur.next = new ListNode(head.val);
            cur = cur.next;
            if (i == pos - 1){
                head = head.next;
            }
            head = head.next;
            i++;
        }
        return res.next;
    }

    //先用一个指针cur，遍历n个元素，那么还剩总长度len-n个元素
    //对新的指针target遍历len-n个元素(cur此时指向null)，即到倒数第n个元素
    public static ListNode removeNthFromEnd2(ListNode head, int n){
        ListNode cur = head;
        ListNode target = head;
        int size = 0;
        while (cur != null){
            cur = cur.next;
            size++;
            if (size > (n+1)){
                target = target.next;
            }
        }
        if (size == n){
            return target.next;
        }else{
            printNode(target);
            target.next = target.next.next;
            printNode(target);
            return head;
        }
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
        int n = 1;
        ListNode res = removeNthFromEnd2(head, n);
        printNode(res);
    }
}
