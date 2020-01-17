package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/16/20 6:26 下午
 */

/**
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 *
 * Your implementation should support following operations:
 *
 * MyCircularQueue(k): Constructor, set the size of the queue to be k.
 * Front: Get the front item from the queue. If the queue is empty, return -1.
 * Rear: Get the last item from the queue. If the queue is empty, return -1.
 * enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
 * deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
 * isEmpty(): Checks whether the circular queue is empty or not.
 * isFull(): Checks whether the circular queue is full or not.
 *
 *
 * Example:
 *
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
 * circularQueue.enQueue(1);  // return true
 * circularQueue.enQueue(2);  // return true
 * circularQueue.enQueue(3);  // return true
 * circularQueue.enQueue(4);  // return false, the queue is full
 * circularQueue.Rear();  // return 3
 * circularQueue.isFull();  // return true
 * circularQueue.deQueue();  // return true
 * circularQueue.enQueue(4);  // return true
 * circularQueue.Rear();  // return 4
 *
 * Note:
 *
 * All values will be in the range of [0, 1000].
 * The number of operations will be in the range of [1, 1000].
 * Please do not use the built-in Queue library.
 */
//leetcode622
public class No64_Design_Circular_Queue {

    public static class MyCircularQueue {

        public class Node{
            int value;
            Node nextNode;
            public Node(int val){
                value = val;
            }
        }

        Node head, tail;
        int count;    //record the current length of the queue
        int capacity; //record the maximum capacity of the queue
        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            capacity = k;
        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if (count == capacity){//full
                return false;
            }
            Node newNode = new Node(value);
            if (count == 0){
                head = tail = newNode;
            }else{
                tail.nextNode = newNode;
                tail = newNode;
            }
            count += 1;
            return true;
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if (count == 0) {//empty
                return false;
            }
            head = head.nextNode; //delete the head element
            count -= 1;
            return true;
        }

        /** Get the front item from the queue. */
        public int Front() {
            if (count == 0){//empty
                return -1;
            }
            return head.value;
        }

        /** Get the last item from the queue. */
        public int Rear() {
            if (count == 0){//empty
                return -1;
            }
            return tail.value;
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            if (count == 0) return true;
            return false;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            if (count == capacity) return true;
            return false;
        }
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
        System.out.println(circularQueue.enQueue(1));// return true
        System.out.println(circularQueue.enQueue(2));  // return true
        System.out.println(circularQueue.enQueue(3));  // return true
        System.out.println(circularQueue.enQueue(4));  // return false, the queue is full
        System.out.println(circularQueue.Rear());  // return 3
        System.out.println(circularQueue.isFull());  // return true
        System.out.println(circularQueue.deQueue());  // return true
        System.out.println(circularQueue.enQueue(4));  // return true
        System.out.println(circularQueue.Rear());  // return 4
    }
}
