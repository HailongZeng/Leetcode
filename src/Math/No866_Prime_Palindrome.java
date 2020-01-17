package Math;

/**
 * @author HailongZeng
 * @date 1/7/20 11:44 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Find the smallest prime palindrome greater than or equal to N.
 *
 * Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1.
 *
 * For example, 2,3,5,7,11 and 13 are primes.
 *
 * Recall that a number is a palindrome if it reads the same from left to right as it does from right to left.
 *
 * For example, 12321 is a palindrome.
 *
 *
 *
 * Example 1:
 *
 * Input: 6
 * Output: 7
 * Example 2:
 *
 * Input: 8
 * Output: 11
 * Example 3:
 *
 * Input: 13
 * Output: 101
 *
 *
 * Note:
 *
 * 1 <= N <= 10^8
 * The answer is guaranteed to exist and be less than 2 * 10^8.
 */

/**
 * we can check whether an integer N is a palindrome in O(logN) time, and check whether it is prime in O(sqrt(N)) time. So we would probably like to do the palindrome check first
 */
public class No866_Prime_Palindrome {

    //brute force   time:O(N)  space:O(1)
    //all 8 digit palindromes are not prime, so we can skip all 8 digit numbers
    //一个8位的回文数可以写成  sum(a_i(10^(7-i)+10^(i)))  where i=0,1,2,3   sum%11 == 0, not prime
    public static int primePalindrome(int N){
        if (N == 1) return 2;
        while (true){
            if (isPalindrome(N)){ //reverse(N) == N
                if (isPrime(N)){
                    break;
                }
            }
            N++;
            if (10_000_000 < N && N < 100_000_000) N = 100_000_000;
        }
        return N;
    }

    public static boolean isPalindrome(int N) {
        String s = String.valueOf(N);
        int l = 0;
        int r = s.length() - 1;
        while (l < r){
            if (s.charAt(l) == s.charAt(r)){
                l++;
                r--;
            }else{
                return false;
            }
        }
        return true;
    }

    public static int reverse(int N){
        int res = 0;
        while (N > 0){
            res = 10 * res + (N % 10);
            N /= 10;
        }
        return res;
    }

    public static boolean isPrime(int N) {
        for (int i = 2; i * i <= N; i++){
            if (N % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int N = Integer.parseInt(line);
            int res = primePalindrome(N);
            System.out.println(res);
        }
    }
}
