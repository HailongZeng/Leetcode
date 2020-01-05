package Math;

/**
 * @author HailongZeng
 * @date 12/25/19 2:03 下午
 */

import java.util.Scanner;
import java.util.Stack;

/**
 * Given two numbers arr1 and arr2 in base -2, return the result of adding them together.
 *
 * Each number is given in array format:  as an array of 0s and 1s, from most significant bit to least significant bit.  For example, arr = [1,1,0,1] represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3.  A number arr in array format is also guaranteed to have no leading zeros: either arr == [0] or arr[0] == 1.
 *
 * Return the result of adding arr1 and arr2 in the same format: as an array of 0s and 1s with no leading zeros.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [1,1,1,1,1], arr2 = [1,0,1]
 * Output: [1,0,0,0,0]
 * Explanation: arr1 represents 11, arr2 represents 5, the output represents 16.
 *
 *
 * Note:
 *
 * 1 <= arr1.length <= 1000
 * 1 <= arr2.length <= 1000
 * arr1 and arr2 have no leading zeros
 * arr1[i] is 0 or 1
 * arr2[i] is 0 or 1
 */
public class No1073_Adding_Two_Negabinary_Numbers {

    public static int[] addNegabinary(int[] arr1, int[] arr2){
        int i = arr1.length - 1, j = arr2.length - 1, carry = 0;
        Stack<Integer> stack = new Stack<>();
        while (i >= 0 || j >= 0 || carry != 0){
            int d1 = i >= 0 ? arr1[i--] : 0;
            int d2 = j >= 0 ? arr2[j--] : 0;
            carry += d1 + d2;
            /*
            stack.push(carry & 1);
            carry = -(carry>>2);
             */
            int r = carry % (-2);
            carry /= -2;
            if (r < 0){
                carry += 1;
                r += Math.abs(2);
            }
            stack.push(r);
        }
        while (!stack.isEmpty() && stack.peek() == 0) stack.pop(); //pop the leading zero
        int[] res = new int[stack.size()];
        int index = 0;
        while (!stack.isEmpty()){
            res[index++] = stack.pop();
        }
        return res.length == 0 ? new int[1] : res;
    }

    public static void printArray(int[] arr){
        System.out.print("[");
        for (int i = 0; i < arr.length; i++){
            if (i == arr.length - 1) System.out.println(arr[i] + "]");
            else System.out.print(arr[i] + ",");
        }
    }
    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int m = st.nextInt();
            int[] arr1 = new int[m];
            for (int j = 0; j < m; j++){
                arr1[j] = st.nextInt();
            }
            int n = st.nextInt();
            int[] arr2 = new int[n];
            for (int j = 0; j < n; j++){
                arr2[j] = st.nextInt();
            }
            int[] res = addNegabinary(arr1, arr2);
            printArray(res);
        }
    }
}
