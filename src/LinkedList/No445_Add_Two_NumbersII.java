package LinkedList;

/**
 * @author HailongZeng
 * @date 12/23/19 8:02 下午
 */

import java.util.Stack;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class No445_Add_Two_NumbersII {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    //Not reverse, with stack
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2){
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null){
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode res = new ListNode(-1);
        int sum = 0;
        while (!s1.isEmpty() || !s2.isEmpty()){
            if (!s1.isEmpty()){
                sum += s1.pop();
            }
            if (!s2.isEmpty()){
                sum += s2.pop();
            }
            res.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = res;
            res = head;
            sum /= 10;
        }
        return res.val == 0 ? res.next : res;
    }

    //reverse first, and add, and reverse
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2){
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode l1_rev = reverse1(l1);
        ListNode l2_rev = reverse1(l2);
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        int carry = 0;
        while (l1_rev != null || l2_rev != null){
            int d1 = l1_rev == null ? 0 : l1_rev.val;
            int d2 = l2_rev == null ? 0 : l2_rev.val;
            int sum = d1 + d2 + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1_rev != null) l1_rev = l1_rev.next;
            if (l2_rev != null) l2_rev = l2_rev.next;
        }
        if (carry == 1) cur.next = new ListNode(1);
        return reverse1(res.next);
    }

    //iterative,time:O(n) space:O(1)
    public static ListNode reverse1(ListNode l){
        ListNode pre = null;
        ListNode cur = l;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //recursive, time:O(n), space:O(n)
    public static ListNode reverse2(ListNode l){
        if (l == null || l.next == null) return l;
        ListNode p = reverse2(l.next);
        l.next.next = l;
        l.next = null;
        return p;
    }

    public static void printNode(ListNode l){
        while (l != null){
            if (l.next != null) System.out.print(l.val + "-->");
            else System.out.println(l.val);
            l = l.next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

//        printNode(l1);
//        printNode(l2);
        ListNode res = addTwoNumbers2(l1, l2);
        printNode(res);
    }
}
