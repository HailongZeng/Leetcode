package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/16/20 1:54 下午
 */

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
 *
 * Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example 1:
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * Explanation:
 * The first event can be booked.  The second can't because time 15 is already booked by another event.
 * The third event can be booked, as the first event takes every time less than 20, but not including 20.
 *
 *
 * Note:
 *
 * The number of calls to MyCalendar.book per test case will be at most 1000.
 * In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 */
//leetcode729
public class No59_My_CalendarI {

    /*
    time complexity: O(N^2), where N is the number of events booked. For each new event, we process every previous event to decide whether the new event can be booked. This lead to sum_(from k to N)(O(k)) = O(N^2)
    space: O(N)
     */
    public static class MyCalendar1 {

        List<int[]> calendar;
        public MyCalendar1() {
            calendar = new ArrayList();
        }

        public boolean book(int start, int end) {
            for (int[] iv: calendar){
                if (iv[0] < end && start < iv[1]) return false;
            }
            calendar.add(new int[]{start, end});
            return true;
        }
    }

    /*
    Time Complexity (Java):O(NlogN), where N is the number of events booked. For each new event, we search that the event is legal in O(logN) time, then insert it in O(1) time.
    Space Complexity: O(N), the size of the data structures used.
     */

    public static class MyCalendar2 {
        TreeMap<Integer, Integer> calendar;
        public MyCalendar2(){
           calendar = new TreeMap<>();
        }

        public boolean book(int start, int end){
            Integer prev = calendar.floorKey(start), next = calendar.ceilingKey(start);
            if ((prev == null || calendar.get(prev) <= start) && (next == null || end <= next)){
                calendar.put(start, end);
                return true;
            }
            return false;
        }
    }
}
