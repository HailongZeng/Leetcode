package Divide_and_conquer;

/**
 * @author HailongZeng
 * @date 12/24/19 9:35 上午
 */

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class No23_Merge_k_Sorted_Lists {

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int x) { val = x; }
    }

    //O(Nlogk)  time: O(k)
    public static ListNode mergeKLists2(ListNode[] lists){
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>(){
            public int compare(ListNode a, ListNode b){
                return a.val - b.val;
            }
        });
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        for (ListNode node: lists){
            if (node != null){
                pq.add(node);
            }
        }

        while (!pq.isEmpty()){
            ListNode node = pq.poll();
            cur.next = new ListNode(node.val);
            cur = cur.next;
            if (node.next != null) pq.add(node.next);
        }
        return res.next;
    }

    //O(Nlogk)  space: O(1) divide and conquer
    public static ListNode mergeKLists(ListNode[] lists){
        if (lists.length == 0) return null;
        int n = lists.length;
        while (n > 1){
            int k = (1 + n) / 2;
            for (int i = 0; i < n; i++){
                lists[i] = merge(lists[i], lists[i+k]);
            }
            n = k;
        }
        return lists[0];
    }

    public static ListNode merge(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            }else{
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) cur.next = l1;
        if (l2 != null) cur.next = l2;
        return dummy.next;
    }

    public static void printNode(ListNode l){
        while (l != null){
            if (l.next != null) System.out.print(l.val + "->");
            else System.out.println(l.val);
            l = l.next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};
        ListNode res = mergeKLists2(lists);
        printNode(res);
    }
}
