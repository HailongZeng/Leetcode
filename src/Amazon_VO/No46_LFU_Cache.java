package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/15/20 2:45 下午
 */

import java.util.HashMap;
import java.util.Map; /**
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 *
 * Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.
 *
 *
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 *
 *
 * Example:
 *
 * LFUCache cache = new LFUCache( 2 /* capacity */;
//        *cache.put(1,1);
//        *cache.put(2,2);
//        *cache.get(1);       // returns 1
//        *cache.put(3,3);    // evicts key 2
//        *cache.get(2);       // returns -1 (not found)
//        *cache.get(3);       // returns 3.
//        *cache.put(4,4);    // evicts key 1.
//        *cache.get(1);       // returns -1 (not found)
//        *cache.get(3);       // returns 3
//        *cache.get(4);       // returns 4
// */
//leetcode460  leetcode146--LRU Cache
public class No46_LFU_Cache {

    public static class LFUCache {

        public class Node{
            int key;
            int value;
            int freq;
            Node prev;
            Node next;
            public Node(int key, int value){
                this.key = key;
                this.value = value;
                this.freq = 1;
            }
        }

        Map<Integer, Node> map;
        int capacity;
        Node dummyHead;
        Node dummyTail;
        public LFUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            dummyHead = new Node(-1, -1);
            dummyTail = new Node(-2, -2);
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        public void unlink(Node node){
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }

        public void move(Node node){
            Node prev = node.prev;
            if (prev == dummyHead || prev.freq > node.freq){
                return;
            }
            unlink(node);
            while (prev != dummyHead && prev.freq <= node.freq){
                prev = prev.prev;
            }
            Node next = prev.next;
            //insert
            node.next = next;
            next.prev = node;
            prev.next = node;
            node.prev = prev;
        }

        public int get(int key) {
            if (capacity == 0) return -1;
            Node node = map.get(key);
            if (node == null) return -1;
            node.freq++;
            move(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (capacity == 0) return;
            Node node = map.get(key);
            if (node == null){
                if (map.size() == capacity){
                    //remove last one
                    Node last = dummyTail.prev;
                    map.remove(last.key);
                    unlink(last);
                }
                //find last in the tail whose freq != 1
                Node last = dummyTail.prev;
                while (last.freq == 1 && last != dummyHead){
                    last = last.prev;
                }
                //insert to the tail before the freq=1
                node = new Node(key, value);
                map.put(key, node);
                Node next = last.next;
                last.next = node;
                node.prev = last;
                node.next = next;
                next.prev = node;
            }else{
                node.value = value;
                node.freq++;
                move(node);
            }
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
}
