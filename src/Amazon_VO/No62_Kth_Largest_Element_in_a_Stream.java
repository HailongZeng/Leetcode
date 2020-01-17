package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/16/20 2:41 下午
 */

import java.util.PriorityQueue;

/**
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.
 *
 * Example:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * Note:
 * You may assume that nums' length ≥ k-1 and k ≥ 1.
 */
public class No62_Kth_Largest_Element_in_a_Stream {

    public static class KthLargest {

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)->o1-o2);
        int K;
        public KthLargest(int k, int[] nums) {
            for (int num: nums){
                pq.add(num);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
            K = k;
        }

        public int add(int val) {
            pq.add(val);
            if (pq.size() > K) pq.poll();
            return pq.peek();
        }
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = {4,5,8,2};
        KthLargest kthLargest = new KthLargest(3, arr);
        System.out.println(kthLargest.add(3));   // returns 4
        System.out.println(kthLargest.add(5));   // returns 5
        System.out.println(kthLargest.add(10));  // returns 5
        System.out.println(kthLargest.add(9));   // returns 8
        System.out.println(kthLargest.add(4));   // returns 8
    }
}
