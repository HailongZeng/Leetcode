package Math;

/**
 * @author HailongZeng
 * @date 1/8/20 9:49 上午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Let's say a positive integer is a superpalindrome if it is a palindrome, and it is also the square of a palindrome.
 *
 * Now, given two positive integers L and R (represented as strings), return the number of superpalindromes in the inclusive range [L, R].
 *
 *
 *
 * Example 1:
 *
 * Input: L = "4", R = "1000"
 * Output: 4
 * Explanation: 4, 9, 121, and 484 are superpalindromes.
 * Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.
 *
 *
 * Note:
 *
 * 1 <= len(L) <= 18
 * 1 <= len(R) <= 18
 * L and R are strings representing integers in the range [1, 10^18).
 * int(L) <= int(R)
 */
public class No906_Super_Palindromes {

    //Say P = R^2 is a superpalindrome
    //Because R is a palindrome, the first half of the digits in R determine R up to two possibilities. We can iterate through these digits: let k be the first half of the digits in R. For example if k = 1234, then R = 1234321 or R = 12344321. Each possibility has either an odd or an even number of digits in R
    //Notice P < 10^18, R < (10^18)^(0.5) = 10^9, and R = k|k'(concatenation), where k' is k reversed(and also possibly truncated by one digit). so that k < 10^5 = MAGIC, our magic constant
    //time:O(W^(0.25)*logW)  W=10^18 is our upper limit for R  logW term comes from checking whether each candidate is the root of a palindrome
    //space:O(logW)
    public static int superpalindromesInRange(String L, String R){
        long l = Long.valueOf(L);
        long r = Long.valueOf(R);
        int MAGIC = 100000;
        int res = 0;
        //count odd length   123-->12321
        for (int k = 1; k < MAGIC; k++){
            StringBuilder sb = new StringBuilder(Integer.toString(k));
            for (int i = sb.length() - 2; i >= 0; i--){
                sb.append(sb.charAt(i));
            }
            long v = Long.valueOf(sb.toString());
            v *= v;
            if (v > r) break;
            if (v >= l && isPalindrome(v)) res++;
        }

        //count odd length   123-->123321
        for (int k = 1; k < MAGIC; k++){
            StringBuilder sb = new StringBuilder(Integer.toString(k));
            for (int i = sb.length() - 1; i >= 0; i--){
                sb.append(sb.charAt(i));
            }
            long v = Long.valueOf(sb.toString());
            v *= v;
            if (v > r) break;
            if (v >= l && isPalindrome(v)) res++;
        }
        return res;
    }

    public static boolean isPalindrome(long v) {
        return v == reverse(v);
    }

    public static long reverse(long v) {
        long out = 0;
        while (v > 0){
            out = 10 * out + v % 10;
            v /= 10;
        }
        return out;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            String L = line;
            line = io.readLine();
            String R = line;
            int res = superpalindromesInRange(L, R);
            System.out.println(res);
        }
    }
}
