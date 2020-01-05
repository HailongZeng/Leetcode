package LinkedList;

/**
 * @author HailongZeng
 * @date 1/2/20 6:03 下午
 */

import java.util.*;

/**
 * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
 *
 * After doing so, return the head of the final linked list.  You may return any such answer.
 *
 *
 *
 * (Note that in the examples below, all sequences are serializations of ListNode objects.)
 *
 * Example 1:
 *
 * Input: head = [1,2,-3,3,1]
 * Output: [3,1]
 * Note: The answer [1,2,1] would also be accepted.
 * Example 2:
 *
 * Input: head = [1,2,3,-3,4]
 * Output: [1,2,4]
 * Example 3:
 *
 * Input: head = [1,2,3,-3,-2]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The given linked list will contain between 1 and 1000 nodes.
 * Each node in the linked list has -1000 <= node.val <= 1000.
 */
public class No1171_Remove_Zero_Sum_Consecutive_Nodes_From_Linked_List {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    //use a hashtable to store the first ListNode that has a given prefix sum,skip all the elements between the first occurrence and current one, eg. first_sum_x.next = curr_sum_x.next
    //time:O(n)  space:O(n)
    /*
    1.create a dummy node of value 0 and a hashmap that maps prefixsum to node
    2.start from the dummy node, compute prefix sum that ends at the current node
     */
    public static ListNode removeZeroSumSublists1(ListNode head){
        Map<Integer, ListNode> seen = new HashMap();
        ListNode dummy = new ListNode(0), cur = dummy;
        dummy.next = head;

        int sum = 0;
        while (cur != null) {
            sum += cur.val;
            seen.put(sum, cur);
            cur = cur.next;
        }

        cur = dummy;
        sum = 0;
        while (cur != null) {
            sum += cur.val;
            if (seen.containsKey(sum)) cur.next = seen.get(sum).next;
            cur = cur.next;
        }
        return dummy.next;
    }

    //two pointer, time: O(n^2)  space: O(1)
    /*
    1.Have a left and right pointer l and r, also init a pointer called prevL that is the previous node of l
    2.start l with the first node, move r one node at a time, For each node that r is visiting, there will be the following 2 cases.
        a.if the running sum is 0, delete all nodes from l to r inclusively by setting the next pointer of prevL to r's next node
        b.else, update prevL to l
    3.move l to its next node and repeat step 2 until l reaches the end of the linked list
     */
    public static ListNode removeZeroSumSublists2(ListNode head){
        ListNode dummy = new ListNode(1000*1000 + 1);
        dummy.next = head;
        ListNode prevL = dummy, l = head, r = head;
        while (l != null){
            r = l;
            int sum = 0;
            boolean skip = false;
            while (r != null){
                sum += r.val;
                if (sum == 0){
                    prevL.next = r.next;
                    l = r;
                    skip = true;
                    break;
                }
                r = r.next;
            }
            if (!skip){
                prevL = l;
            }
            l = l.next;
        }
        return dummy.next;
    }

    public static void printNode(ListNode l){
        while (l != null){
            if (l.next == null) System.out.println(l.val);
            else System.out.print(l.val + "->");
            l = l.next;
        }
    }

    public static void main(String[] args){
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
            ListNode res = removeZeroSumSublists1(dummy.next);
            printNode(res);
        }
    }
}
