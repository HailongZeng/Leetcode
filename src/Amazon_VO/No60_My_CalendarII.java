package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/16/20 2:14 下午
 */

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.
 *
 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example 1:
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * Explanation:
 * The first two events can be booked.  The third event can be double booked.
 * The fourth event (5, 15) can't be booked, because it would result in a triple booking.
 * The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
 * The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
 * the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 *
 *
 * Note:
 *
 * The number of calls to MyCalendar.book per test case will be at most 1000.
 * In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 */
//leetcode731
public class No60_My_CalendarII {

    /*
    time complexity: O(N^2), where N is the number of events booked. For each new event, we process every previous event to decide whether the new event can be booked. This lead to sum_(from k to N)(O(k)) = O(N^2)
    space: O(N)
     */
    public static class MyCalendarTwo1 {

        List<int[]> calendar;
        List<int[]> overlaps;
        public MyCalendarTwo1() {
            calendar = new ArrayList();
            overlaps = new ArrayList();
        }

        public boolean book(int start, int end) {
            for (int[] iv: overlaps){
                if (iv[0] < end && iv[1] > start) return false;
            }
            for (int[] iv: calendar){
                if (iv[0] < end && start < iv[1]){
                    overlaps.add(new int[]{Math.max(start, iv[0]), Math.min(end, iv[1])});
                }
            }
            calendar.add(new int[]{start, end});
            return true;
        }
    }

    /*
    time:O(N^2)
    space:O(N)
     */
    public static class MyCalendarTwo2 {
        TreeMap<Integer, Integer> delta;

        public MyCalendarTwo2() {
            delta = new TreeMap();
        }

        public boolean book(int start, int end) {
            delta.put(start, delta.getOrDefault(start, 0) + 1);
            delta.put(end, delta.getOrDefault(end, 0) - 1);

            int active = 0;
            for (int d: delta.values()) {
                active += d;
                if (active >= 3) {
                    delta.put(start, delta.get(start) - 1);
                    delta.put(end, delta.get(end) + 1);
                    if (delta.get(start) == 0)
                        delta.remove(start);
                    return false;
                }
            }
            return true;
        }
    }
}
