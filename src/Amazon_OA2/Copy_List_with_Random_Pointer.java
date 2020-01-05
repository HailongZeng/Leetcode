package Amazon_OA2;

import java.util.HashMap;

/**
 * LeetCode 138
 *
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input:
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 *
 * Explanation:
 * Node 1's value is 1, both of its next and random pointer points to Node 2.
 * Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
 *
 *
 * Note:
 *
 * You must return the copy of the given head as a reference to the cloned list.
 */
public class Copy_List_with_Random_Pointer {

    public static class Node{
        public int val;
        public Node next;
        public Node random;
        public Node(){}
        public Node(int _val, Node _next, Node _random){
            val = _val;
            next = _next;
            random = _random;
        }
    }

    public static Node copyRandomList(Node head){
        if (head == null) return null;
        HashMap<Node, Node> m = new HashMap<>();
        //loop1    copy all the nodes value
        Node cur = head;
        while (cur != null){
            m.put(cur, new Node(cur.val, null, null));
            cur = cur.next;
        }

        //loop2   assign next and random pointer
        cur = head;
        while (cur != null){
            m.get(cur).next = m.get(cur.next);
            m.get(cur).random = m.get(cur.random);
            cur = cur.next;
        }
        return m.get(head);
    }

//    public static void main(String[] args) {
//        Node = new Node();
//    }
}
