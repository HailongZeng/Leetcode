package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/14/20 7:57 下午
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 *
 * Example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 *
 * Follow up:
 *
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */
//leetcode295
public class No38_Find_Median_in_Data_Stream {

    //insertion sort
    public static class MedianFinder1 {

        List<Integer> list = new ArrayList<>();
        /** initialize your data structure here. */
        public MedianFinder1() {
        }

        public void addNum(int num) {
            if (list.isEmpty()) {
                list.add(num);
                return;
            }
            int size = list.size();
            int i = 0;
            while (i < size && list.get(i) < num) i++;
            if (i == size) {
                list.add(num);
                return;
            }
            list.add(list.get(size-1));
            int j = size - 1;
            while (j > i){
                list.set(j, list.get(--j));
            }
            list.set(i, num);
        }

        public double findMedian() {
            if (list.isEmpty()) return 0;
            int size = list.size();
            return ((double)list.get((size-1)/2) + list.get(size/2)) / 2;
        }
    }

    //priorityQueue, 最小堆和最大堆
    public static class MedianFinder2 {

        PriorityQueue<Integer> lo = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return a - b;
            }
        });
        PriorityQueue<Integer> hi = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return b - a;
            }
        });
        /** initialize your data structure here. */
        public MedianFinder2() {

        }

        public void addNum(int num) {
            lo.add(num);
            hi.add(lo.poll());
            if (lo.size() < hi.size()){
                lo.add(hi.poll());
            }
        }

        public double findMedian() {
            return lo.size() > hi.size() ? (double)lo.peek() : (double)(lo.peek() + hi.peek()) / 2;
        }
    }
}
