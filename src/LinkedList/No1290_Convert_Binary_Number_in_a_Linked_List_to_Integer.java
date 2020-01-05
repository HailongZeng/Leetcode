package LinkedList;

/**
 * @author HailongZeng
 * @date 1/2/20 5:17 下午
 */

import java.util.Scanner;

/**
 * Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.
 *
 * Return the decimal value of the number in the linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,0,1]
 * Output: 5
 * Explanation: (101) in base 2 = (5) in base 10
 * Example 2:
 *
 * Input: head = [0]
 * Output: 0
 * Example 3:
 *
 * Input: head = [1]
 * Output: 1
 * Example 4:
 *
 * Input: head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
 * Output: 18880
 * Example 5:
 *
 * Input: head = [0,0]
 * Output: 0
 *
 *
 * Constraints:
 *
 * The Linked List is not empty.
 * Number of nodes will not exceed 30.
 * Each node's value is either 0 or 1.
 */
public class No1290_Convert_Binary_Number_in_a_Linked_List_to_Integer {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    //time: O(n)   space: O(1)
    public static int getDecimalValue(ListNode head){
        int res = 0;
        while (head != null){
            res = res * 2 + head.val;
            head = head.next;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            for (int j = 0; j < n; j++){
                cur.next = new ListNode(st.nextInt());
                cur = cur.next;
            }
            System.out.println(getDecimalValue(dummy.next));
        }
    }
}
