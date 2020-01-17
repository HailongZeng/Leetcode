package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/15/20 2:35 下午
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
//leetcode445  similar with leetcode2
public class No45_Add_Two_NumbersII {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            stack2.push(l2.val);
            l2 = l2.next;
        }
        Stack<Integer> s = new Stack<>();
        int carry = 0;
        int sum = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()){
            int d1 = stack1.isEmpty() ? 0 : stack1.pop();
            int d2 = stack2.isEmpty() ? 0 : stack2.pop();
            sum = d1 + d2 + carry;
            s.add(sum%10);
            carry = sum / 10;
        }
        if (carry == 1) s.push(carry);
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (!s.isEmpty()){
            cur.next = new ListNode(s.pop());
            cur = cur.next;
        }
        return dummy.next;
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
        ListNode res = addTwoNumbers(l1, l2);
        printNode(res);
    }
}
