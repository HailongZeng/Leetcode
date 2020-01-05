package String;

/**
 * @author HailongZeng
 * @date 12/18/19 6:02 下午
 */

import java.util.Scanner;

/**
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 *
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence. You can do so recursively, in other words from the previous member read off the digits, counting the number of digits in groups of the same digit.
 *
 * Note: Each term of the sequence of integers will be represented as a string.
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: "1"
 * Explanation: This is the base case.
 * Example 2:
 *
 * Input: 4
 * Output: "1211"
 * Explanation: For n = 3 the term was "21" in which we have two groups "2" and "1", "2" can be read as "12" which means frequency = 1 and value = 2, the same way "1" is read as "11", so the answer is the concatenation of "12" and "11" which is "1211".
 */
public class No38_Count_and_Say {

    public static String countAndSay(int n){
        if (n == 1) return "1";
        StringBuilder sb = new StringBuilder();
        String res = countAndSay(n-1);
        int count = 1;
        char[] ch = res.toCharArray();
        for (int i = 0; i < ch.length - 1; i++){
            if (ch[i] != ch[i+1]){
                sb.append(count).append(ch[i]);
                count = 1;
            }else{
                count++;
            }
        }
        sb.append(count).append(ch[ch.length - 1]);
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            System.out.println(countAndSay(n));
        }
    }
}
