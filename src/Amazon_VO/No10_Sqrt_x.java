package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/12/20 1:09 下午
 */

import java.util.Scanner;

/**
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 *
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 *
 * Example 1:
 *
 * Input: 4
 * Output: 2
 * Example 2:
 *
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 *              the decimal part is truncated, 2 is returned.
 */
public class No10_Sqrt_x {

    //time:O(1)  space:O(1)  x^0.5 = e^(0.5logx)
    public static int mySqrt1(int x) {
        if (x < 2) return x;
        int l = (int)Math.pow(Math.E, 0.5*Math.log(x));
        int r = l + 1;
        return (long)r*r > x ? l : r;
    }

    //the root of x will be not larger than x/2
    //time:O(logN)  T(N) = aT(N/b) + (N^d)  space:O(1)
    public static int mySqrt2(int x) {
        if (x < 2) return x;

        long num;
        int pivot, left = 2, right = x / 2;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            num = (long)pivot * pivot;
            if (num > x) right = pivot - 1;
            else if (num < x) left = pivot + 1;
            else return pivot;
        }

        return right;
    }

    //mySqrt(x) = mySqrt(x >> 2) << 1   x^0.5 = 2*(x/4)^0.5
    //x >> y  means x/2^y   x << y  means x*2^y
    //time:O(logN)  space:O(logN)
    public static int mySqrt3(int x) {
        if (x < 2) return x;

        int left = mySqrt3(x >> 2) << 1;
        int right = left + 1;
        return (long)right * right > x ? left : right;
    }


    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int x = st.nextInt();
            int res = mySqrt1(x);
            System.out.println(res);
        }
    }
}
