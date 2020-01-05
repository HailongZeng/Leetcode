package Math;

/**
 * @author HailongZeng
 * @date 12/12/19 12:27 下午
 */

import java.util.Scanner;

/**
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 *
 * Example:
 *
 * Input: 13
 * Output: 6
 * Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
public class No233_Number_of_Digit_One {

    //count position by position
    public static int countDigitOne(int n){
        if (n <= 0) return 0;
        String s = String.valueOf(n);
        int len = s.length();
        int count = 0;
        for (int i = 1; i <= len; i++){
            //count the number of "1" on position i
            int a = n / (int)Math.pow(10, i);
            count += a * (int)Math.pow(10, i-1);
            int num_i = (n % (int)Math.pow(10, i)) / ((int)Math.pow(10, i-1));
            if (num_i > 1){
                count += (int)Math.pow(10, i-1);
            }else if(num_i == 1){
                count += n % (int)Math.pow(10, i-1) + 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int n = st.nextInt();
        System.out.println(countDigitOne(n));
    }
}
