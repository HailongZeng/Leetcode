package String;

/**
 * @author HailongZeng
 * @date 12/13/19 12:17 下午
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 *
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: S = "aaab"
 * Output: ""
 * Note:
 *
 * S will consist of lowercase letters and have length in range [1, 500].
 */
public class No767_Reorganize_String {

    //Using only one number to represent the number of character occurred and the character. For example, 201 means 'b' occur 2 times
    public static String reorganizeString(String S){
        int[] counts = new int[26];
        int n = S.length();
        for (char c: S.toCharArray()) counts[c-'a'] += 100;
        for (int i = 0; i < 26; i++) counts[i] += i;
        Arrays.sort(counts);
        int index = 1;
        char[] res = new char[n];
        for (int num: counts){
            char ch = (char)('a' + (num % 100)); //the character
            int t = num / 100; //how many times the character occur
            if (t > (n + 1) / 2) return "";
            for (int i = 0; i < t; i++){ //隔位赋值
                if (index >= n) index = 0;
                res[index] = ch;
                index += 2;
            }
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int n = Integer.parseInt(st.nextLine());
        for (int i = 0; i < n; i++){
            String S = st.nextLine();
            System.out.println(reorganizeString(S));
        }
    }
}
