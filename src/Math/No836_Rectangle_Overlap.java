package Math;

/**
 * @author HailongZeng
 * @date 1/11/20 9:11 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2) are the coordinates of its top-right corner.
 *
 * Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only touch at the corner or edges do not overlap.
 *
 * Given two (axis-aligned) rectangles, return whether they overlap.
 *
 * Example 1:
 *
 * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * Output: true
 * Example 2:
 *
 * Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * Output: false
 * Notes:
 *
 * Both rectangles rec1 and rec2 are lists of 4 integers.
 * All coordinates in rectangles will be between -10^9 and 10^9.
 */
public class No836_Rectangle_Overlap {

    //先判断两个矩形是否不重叠，一共有四种情况。一、矩形1[x11, y11, x12, y12]在矩形2[x21, y21, x22, y22]的左边：1.当x21 >= x12时；2.当y21 >= y12时
    //二、矩形1[x11, y11, x12, y12]在矩形2[x21, y21, x22, y22]的右边：1.当x11 >= x22时；2.当y11 >= y22时
    public static boolean isRectangleOverlap1(int[] rec1, int[] rec2){
        if (rec1[0] >= rec2[2] || rec1[2] <= rec2[0]) return false;
        if (rec1[1] >= rec2[3] || rec1[3] <= rec2[1]) return false;
        return true;
    }

    //假设两矩形重叠。重叠的新矩形的表示为[x1_new, y1_new, x2_new, y2_new]
    //x1_new = Math.max(x11, x21)  y1_new = Math.max(y11, y21)
    //x2_new = Math.min(x12, x22)  y2_new = Math.min(y12, y22)
    public static boolean isRectangleOverlap2(int[] rec1, int[] rec2){
        int x1 = Math.max(rec1[0], rec2[0]);
        int y1 = Math.max(rec1[1], rec2[1]);
        int x2 = Math.min(rec1[2], rec2[2]);
        int y2 = Math.min(rec1[3], rec2[3]);
        if (x2 > x1 && y2 > y1) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] rec1 = stringToArray(line);
            line = io.readLine();
            int[] rec2 = stringToArray(line);
            boolean res = isRectangleOverlap1(rec1, rec2);
        }
    }

    //format:[x1,y1,x2,y2]
    private static int[] stringToArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++){
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }
}
