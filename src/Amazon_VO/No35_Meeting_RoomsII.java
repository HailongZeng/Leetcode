package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/14/20 7:12 下午
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class No35_Meeting_RoomsII {

    //time:O(NlogN)   space:O(N)
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        //sort the intervals by start time
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });
        //minHeap
        PriorityQueue<Integer> allocator = new PriorityQueue<Integer>(intervals.length, new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return a - b;
            }
        });

        //add the first meeting
        allocator.add(intervals[0][1]);

        //Iterate over remaining intervals
        for (int i = 1; i < intervals.length; i++){
            //if the room due to free up the earliest is free, assign that room to this meeting
            if (intervals[i][0] >= allocator.peek()){
                allocator.poll();
            }
            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            allocator.add(intervals[i][1]);
        }
        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        int res = minMeetingRooms(intervals);
        System.out.println(res);
    }
}
