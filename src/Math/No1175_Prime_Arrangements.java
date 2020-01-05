package Math;

/**
 * Return the number of permutations of 1 to n so that prime numbers are at prime indices (1-indexed.)
 *
 * (Recall that an integer is prime if and only if it is greater than 1, and cannot be written as a product of two positive integers both smaller than it.)
 *
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5
 * Output: 12
 * Explanation: For example [1,2,5,4,3] is a valid permutation, but [5,2,3,4,1] is not because the prime number 5 is at index 1.
 * Example 2:
 *
 * Input: n = 100
 * Output: 682289015
 */
import java.util.Scanner;
public class No1175_Prime_Arrangements {

    private static long M = (long)Math.pow(10, 9) + 7;
    public static int numPrimeArrangements(int n){
        long count = 0;
        for (long i = 2; i <= n; i++){
            boolean flag = true;
            for (long j = 2; j*j <= i; j++){
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) count++;
        }
        if (count == 0 || count == n) return (int)(factorial(n)%M);
        else return (int)((factorial(count) * factorial(n - count)) % M);
    }

    public static long factorial(long n){
        long res = 1;
        for (long i = 1; i <= n; i++){
            res = res * i % M;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int n = st.nextInt();
        System.out.println(numPrimeArrangements(n));
    }
}
