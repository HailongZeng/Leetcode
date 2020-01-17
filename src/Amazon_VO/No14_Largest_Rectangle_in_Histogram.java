package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/12/20 9:32 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 *        *
 *      * *
 *      * *
 *      * *   *
 *  *   * * * *
 *  * * * * * *
 *  2 1 5 6 2 3
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 *
 *
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 *
 *
 * Example:
 *
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
public class No14_Largest_Rectangle_in_Histogram {

    //brute force   time:O(N^2)  space:O(1)
    public static int largestRectangleArea1(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++){
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++){
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }

    //divide and conquer   time:O(NlogN) worst:O(N^2) since we need to find the minIndex in O(n) each time   space:O(N)
    /*
    1.The widest possible rectangle with height equal to the height of the shortest bar.
    2.The largest rectangle confined to the left of the shortest bar(subproblem).
    3.The largest rectangle confined to the right of the shortest bar(subproblem).
    Let's take an example:
    [6, 4, 5, 2, 4, 3, 9]
Here, the shortest bar is of height 2. The area of the widest rectangle using this bar as height is 2x7=14. Now, we need to look for cases 2 and 3 mentioned above. Thus, we repeat the same process to the left and right of 2. In the left of 2, 4 is the minimum, forming an area of rectangle 4x3=12. Further, rectangles of area 6x1=6 and 5x1=5 exist in its left and right respectively. Similarly we find an area of 3x3=9, 4x1=4 and 9x1=9 to the left of 2. Thus, we get 14 as the correct maximum area. See the figure below for further clarification:
     */
    public static int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        return calculateArea(heights, 0, heights.length - 1);
    }

    public static int calculateArea(int[] heights, int start, int end){
        if (start > end) return 0;
        int minIndex = start;
        for (int i = start; i <= end; i++){
            if (heights[minIndex] > heights[i]) minIndex = i;
        }
        return Math.max(heights[minIndex] * (end - start + 1), Math.max(calculateArea(heights, start, minIndex - 1), calculateArea(heights, minIndex + 1, end)));
    }

    /*
    In this approach, we maintain a stack. Initially, we push a -1 onto the stack to mark the end. We start with the leftmost bar and keep pushing the current bar's index onto the stack until we get two successive numbers in descending order, i.e. until we get a[i] < a[i−1]. Now, we start popping the numbers from the stack until we hit a number stack[j] on the stack such that a[stack[j]]≤a[i]. Every time we pop, we find out the area of rectangle formed using the current element as the height of the rectangle and the difference between the the current element's index pointed to in the original array and the element stack[top−1]−1 as the width i.e. if we pop an element stack[top] and i is the current index to which we are pointing in the original array, the current area of the rectangle will be considered as:(i−stack[top−1]−1)×a[stack[top]].
    Further, if we reach the end of the array, we pop all the elements of the stack and at every pop, this time we use the following equation to find the area: (stack[top]−stack[top−1])×a[stack[top]], where stack[top] refers to the element just popped. Thus, we can get the area of the of the largest rectangle by comparing the new area found every time.
     */
    //time:O(N)  space:O(N)
    public static int largestRectangleArea3(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++){
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]){
                maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1){
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return maxArea;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] heights = stringToArray(line);
            int res = largestRectangleArea1(heights);
            System.out.println(res);
        }
    }

    //the format of input:[xxx,xxx,xxx]
    private static int[] stringToArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < nums.length; i++){
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }
}
