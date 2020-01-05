package LinkedList;

/**
 * @author HailongZeng
 * @date 1/2/20 5:22 下午
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.
 *
 * Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.  If such a j does not exist, the next larger value is 0.
 *
 * Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
 *
 * Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.
 *
 *
 *
 * Example 1:
 *
 * Input: [2,1,5]
 * Output: [5,5,0]
 * Example 2:
 *
 * Input: [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 * Example 3:
 *
 * Input: [1,7,5,1,9,2,5,1]
 * Output: [7,9,9,9,0,5,0,0]
 *
 *
 * Note:
 *
 * 1 <= node.val <= 10^9 for each node in the linked list.
 * The given list has length in the range [0, 10000].
 */
public class No1019_Next_Greater_Node_in_Linked_List {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    //monotonic stack(单调栈)  time: O(n)  space: O(n)
    public static int[] nextLargerNodes(ListNode head){
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        int size = list.size();
        int[] res = new int[size];
        Stack<Integer> s = new Stack<>();//record the index right now
        for (int i = 0; i < size; i++){
            while (!s.isEmpty() && list.get(i) > list.get(s.peek())){//if there exist element larger than list.get(s.peek())，just get res[s.pop()]
                res[s.pop()] = list.get(i);
            }
            s.push(i);
        }
        return res;
    }

    public static void printArray(int[] a){
        System.out.print("[");
        for (int i = 0; i < a.length; i++){
            if (i == a.length - 1) System.out.println(a[i] + "]");
            else System.out.print(a[i] + ",");
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
            int[] res = nextLargerNodes(dummy.next);
            printArray(res);
        }
    }
}
