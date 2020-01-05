package LinkedList;

/**
 * @author HailongZeng
 * @date 12/13/19 10:43 上午
 */

import java.util.Scanner;

/**
 * Sort a linked list using insertion sort.
 *
 *
 * A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
 * With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 *
 *
 * Algorithm of Insertion Sort:
 *
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
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
public class No147_Insertion_Sort_List {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public static ListNode insertionSortList1(ListNode head){
        if (head == null || head.next == null) return head;
        ListNode newHead = head;
        ListNode newTail = head;
        ListNode cur = head.next;
        while (cur != null){
            if (cur.val >= newTail.val){
                newTail = cur;
                cur = cur.next;
            }else{
                ListNode dummy = new ListNode(-1);
                dummy.next = newHead;
                ListNode temp = cur;
                ListNode index = newHead;
                newTail.next = cur.next;
                cur = cur.next;
                ListNode prev = dummy;
                while (temp.val > index.val){
                    prev = index;
                    index = index.next;
                }
                prev.next = temp;
                temp.next = index;
                newHead = dummy.next;
            }
        }
        return newHead;
    }

    public static ListNode insertionSortList(ListNode head){
        if (head == null || head.next == null) return head;

        //let us make a pointer to sortedList and initialize it to null
        ListNode sortedListHead = null;

        //now let us do the processing till every element of the input is not done
        while (head != null){
            //identify an element from the input to be processed
            ListNode oldHead = head;
            //assign head the next value for the next iteration
            head = head.next;
            //also remove the reference to next element from the item which is being processed as we want to assign(may be) this element in the sorted list somewhere in the middle
            oldHead.next = null;
            //insert this head(oldHead) onto the relevant place in sortedList and return the sorted list
            sortedListHead = insertInSortedList(oldHead, sortedListHead);
        }
        return sortedListHead;
    }

    public static ListNode insertInSortedList(ListNode element, ListNode sortedListHead){
        if (sortedListHead == null) return element;

        //if the element we are processing is the smallest
        if (element.val < sortedListHead.val){
            //element needs to go in front
            element.next = sortedListHead;
            return element;
        }

        ListNode temp = sortedListHead;
        while (temp.next != null && element.val >= temp.next.val){
            temp = temp.next;
        }

        element.next = temp.next;
        temp.next = element;
        return sortedListHead;
    }

    public static void printNode(ListNode l){
        while (l != null){
            System.out.print(l.val + "-->");
            l = l.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        Scanner st = new Scanner(System.in);
        int n = st.nextInt();
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int i = 0; i < n; i++){
            curr.next = new ListNode(st.nextInt());
            curr = curr.next;
        }
        ListNode res = insertionSortList1(dummy.next);
        printNode(res);
    }
}
