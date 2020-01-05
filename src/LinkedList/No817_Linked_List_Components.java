package LinkedList;

/**
 * @author HailongZeng
 * @date 1/2/20 10:05 下午
 */

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * We are given head, the head node of a linked list containing unique integer values.
 *
 * We are also given the list G, a subset of the values in the linked list.
 *
 * Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.
 *
 * Example 1:
 *
 * Input:
 * head: 0->1->2->3
 * G = [0, 1, 3]
 * Output: 2
 * Explanation:
 * 0 and 1 are connected, so [0, 1] and [3] are the two connected components.
 * Example 2:
 *
 * Input:
 * head: 0->1->2->3->4
 * G = [0, 3, 1, 4]
 * Output: 2
 * Explanation:
 * 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.
 * Note:
 *
 * If N is the length of the linked list given by head, 1 <= N <= 10000.
 * The value of each node in the linked list will be in the range [0, N - 1].
 * 1 <= G.length <= 10000.
 * G is a subset of all values in the linked list.
 */
public class No817_Linked_List_Components {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public static int numComponents(ListNode head, int[] G){
        Set<Integer> Gset = new HashSet<>();
        for (int x: G) Gset.add(x);
        ListNode cur = head;
        int res = 0;
        while (cur != null){
            //only care about the end of connected linkedlist node
            if (Gset.contains(cur.val) && (cur.next == null || !Gset.contains(cur.next.val))){
                res++;
            }
            cur = cur.next;
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
            int m = st.nextInt();
            int[] G = new int[m];
            for (int j = 0; j < n; j++){
                G[j] = st.nextInt();
            }
            System.out.println(numComponents(dummy.next, G));
        }
    }
}
