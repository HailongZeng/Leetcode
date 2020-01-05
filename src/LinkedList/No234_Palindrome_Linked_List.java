package LinkedList;

/**
 * @author HailongZeng
 * @date 1/2/20 10:02 上午
 */

import java.util.Scanner;
import java.util.Stack;

/**
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 *
 * Input: 1->2
 * Output: false
 * Example 2:
 *
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */
public class No234_Palindrome_Linked_List {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    //Stack   time:O(n)   space:O(n)
    public static boolean isPalindrome1(ListNode head){
        if (head == null || head.next == null) return true;
        Stack<Integer> st = new Stack<>();
        ListNode cur = head;
        while (cur != null){
            st.push(cur.val);
            cur = cur.next;
        }
        while (head != null){
            int t = st.pop();
            if (head.val != t) return false;
            head = head.next;
        }
        return true;
    }

    //two pointer
    public static boolean isPalindrome2(ListNode head){
        if (head == null || head.next == null) return true;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode last = slow.next, pre = head;
        while (last.next != null){
            ListNode tmp = last.next;
            last.next = tmp.next;
            tmp.next = slow.next;
            slow.next = tmp;
        }
        while (slow.next != null){
            slow = slow.next;
            if (pre.val != slow.val) return false;
            pre = pre.next;
        }
        return true;
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
            System.out.println(isPalindrome2(dummy.next));
        }
    }
}
