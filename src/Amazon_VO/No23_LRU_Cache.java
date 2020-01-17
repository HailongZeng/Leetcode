package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/13/20 9:43 下午
 */

import java.util.HashMap;
import java.util.Map; /**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
//  LRUCache cache = new LRUCache( 2 /* capacity */;
//        *
//        *cache.put(1,1);
//        *cache.put(2,2);
//        *cache.get(1);       // returns 1
//        *cache.put(3,3);    // evicts key 2
//        *cache.get(2);       // returns -1 (not found)
//        *cache.put(4,4);    // evicts key 1
//        *cache.get(1);       // returns -1 (not found)
//        *cache.get(3);       // returns 3
//        *cache.get(4);       // returns 4

public class No23_LRU_Cache {

    public static class LRUCache {

        public class Node{
            int key;
            int val;
            Node prev;
            Node next;
            public Node(int k, int v){
                key = k;
                val = v;
            }
        }

        public Node head;
        public Node tail;
        public Map<Integer, Node> m;
        public int size;
        public int capacity;
        public LRUCache(int capacity) {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.next = head;
            m = new HashMap<>();
            size = 0;
            this.capacity = capacity;
        }

        public void remove(int key){//help remove a node from double linked list
            Node node = m.get(key);
            Node next = node.next;
            Node prev = node.prev;
            next.prev = prev;
            prev.next = next;
            size--;
            m.remove(key);
        }

        public void addHead(int key, int value){//add a node to head.next, activate it
            Node node = new Node(key, value);
            Node next = head.next;
            head.next = node;
            node.prev = head;
            node.next = next;
            next.prev = node;
            m.put(key, node);
            size++;
            if (size > capacity){
                Node pretail = tail.prev;
                remove(pretail.key);
            }
        }

        public int get(int key) {
            if (!m.containsKey(key)) return -1;
            else{
                Node node = m.get(key);
                remove(key);
                addHead(key, node.val);
                return node.val;
            }
        }

        public void put(int key, int value) {
            if (m.containsKey(key)){
                remove(key);
                addHead(key, value);
            }else{
                addHead(key, value);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
}
