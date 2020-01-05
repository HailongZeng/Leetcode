package Amazon_OA2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * LeetCode 56
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class Merge_Intervals {

    public static int[][] merge(int[][] intervals){
        if (intervals.length <= 1) return intervals;
        Arrays.sort(intervals, (int[] a, int[] b)->{
            return a[0]-b[0];
        });
        ArrayList<int[]> res = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++){
            if (intervals[i - 1][1] >= intervals[i][0]){
                intervals[i][0] = intervals[i - 1][0];
                intervals[i][1] = Math.max(intervals[i][1], intervals[i - 1][1]);
            } else{
                res.add(intervals[i - 1]);
            }
        }
        res.add(intervals[intervals.length - 1]);
        return res.toArray(new int[res.size()][2]);
    }

    public static void printArray(int[][] res){
        System.out.println("[");
        for (int i = 0; i < res.length; i++){
            System.out.print("[");
            for (int j = 0; j < res[0].length; j++){
                if (j == res[0].length - 1) {
                    System.out.print(res[i][j]);
                }
                else{
                    System.out.print(res[i][j] + ",");
                }
            }
            System.out.println("]");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals2 = {{1, 4}, {4, 5}};
        int[][] res1 = merge(intervals1);
        int[][] res2 = merge(intervals2);
        printArray(res1);
        printArray(res2);
    }
}