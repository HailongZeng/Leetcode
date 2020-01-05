package LinkedList;

/**
 * @author HailongZeng
 * @date 12/24/19 11:00 下午
 */
public class No25_Reverse_Nodes_in_k_Group {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        for (int i = 1; cur != null; i++){
            if (i % k == 0){
                pre = reverseOneGroup(pre, cur.next);
                cur = cur.next;
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static ListNode reverseOneGroup(ListNode pre, ListNode next){
        ListNode last = pre.next;
        ListNode cur = last.next;
        while (cur != next){
            last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = last.next;
        }
        return last;
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
        ListNode res = reverseKGroup(head, k);
        printNode(res);
    }
}
