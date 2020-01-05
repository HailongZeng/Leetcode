package Math;

/**
 * @author HailongZeng
 * @date 1/4/20 12:09 上午
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * Given an array of 4 digits, return the largest 24 hour time that can be made.
 *
 * The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.
 *
 * Return the answer as a string of length 5.  If no valid time can be made, return an empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,4]
 * Output: "23:41"
 * Example 2:
 *
 * Input: [5,5,5,5]
 * Output: ""
 *
 *
 * Note:
 *
 * A.length == 4
 * 0 <= A[i] <= 9
 */
public class No949_Largest_Time_for_Given_Digits {

    public static String largestTimeFromDigits(int[] A){
        Arrays.sort(A);
        if (A[0] > 2) return "";
        for(int i = 3; i >= 0; i--){
            if (A[i] <= 2){
                for (int j = 3; j >= 0; j--){
                    if ((A[i] != 2 || A[j] <= 3) && i != j){//when the first number is 2, the second number should be less than 4; when the first number is less than 2, the second number can be any number 0~9
                        for (int k = 3; k >= 0; k--){
                            if (A[k] <= 5 && i != k && j != k){
                                return "" + A[i] + A[j] + ":" + A[k] + A[6-i-j-k];
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int[] A = new int[n];
            for (int j = 0; j < n; j++){
                A[j] = st.nextInt();
            }
            System.out.println(largestTimeFromDigits(A));
        }
    }
}
