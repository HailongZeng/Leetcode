package Divide_and_conquer;

/**
 * @author HailongZeng
 * @date 12/13/19 9:26 上午
 */

import java.util.Scanner;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
/**
    Solution 1: Merge Sort(Top-Down)
    Split the list into two parts using fast/slow pointers
    l1, l2 = split(l)
    l1', l2' = sortList(l1), sortList(l2)
    merge(l1', l2')
    Time Complexity: O(nlogn)
    Space Complexity: O(logn)
 */

/**
 Solution 2: Merge Sort(Bottom-Up)
 log(n) rounds, in the i-th round, we split the list into n/(2^i) lists (n, n/2, n/4,...), each group has 2^i elements
 merge every pair of sorted lists
 Time Complexity: O(nlogn)
 Space Complexity: O(1)
 */
public class No148_Sorted_List {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public static ListNode sortList1(ListNode head){
        //0 or 1 element
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = slow;
        while (fast != null && fast.next != null){
            prev = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        prev.next = null;
        ListNode mid = slow;
        return merge1(sortList1(head), sortList1(mid));
    }

    public static ListNode merge1(ListNode l1, ListNode l2){
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (l1 != null && l2 != null){
            if (l1.val > l2.val){
                tail.next = l2;
                l2 = l2.next;
            }else{
                tail.next= l1;
                l1 = l1.next;
            }
            tail = tail.next;
        }
        if (l1 != null) tail.next = l1;
        if (l2 != null) tail.next = l2;
        return dummy.next;
    }

    public static ListNode sortList2(ListNode head){
        if (head == null || head.next == null) return head;
        int len = 1;
        ListNode curr = head;
        while (curr.next != null){
            len++;
            curr = curr.next;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode l;
        ListNode r;
        ListNode tail;
        for (int n = 1; n < len; n <<= 1){
            curr = dummy.next; //partial sorted head
            tail = dummy;
            while (curr != null){
                l = curr;
                r = split(l, n);
                curr = split(r, n);
                ListNode[] tmp = merge2(l, r);
                tail.next = tmp[0];
                tail = tmp[1];
            }
        }
        return dummy.next;
    }

    //split the list into two parts, first n element and the rest
    public static ListNode split(ListNode head, int n){
        while (n != 0 && head != null){
            head = head.next;
            n--;
        }
        ListNode rest = head != null ? head.next : null;
        if (head != null) head.next = null;
        return rest;
    }

    public static ListNode[] merge2(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (l1 != null && l2 != null){
            if (l1.val > l2.val){
                tail.next = l2;
                l2 = l2.next;
            }else{
                tail.next= l1;
                l1 = l1.next;
            }
            tail = tail.next;
        }
        if (l1 != null) tail.next = l1;
        if (l2 != null) tail.next = l2;
        while (tail.next != null) tail = tail.next;
        return new ListNode[]{dummy.next, tail};
    }

    public static void printNode(ListNode l){
        while (l != null){
            System.out.print(l.val + "-->");
            l = l.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int n = st.nextInt();
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int i = 0; i < n; i++){
            curr.next = new ListNode(st.nextInt());
            curr = curr.next;
        }
//        ListNode res = sortList1(dummy.next);
        ListNode res = sortList2(dummy.next);
        printNode(res);
    }
}
