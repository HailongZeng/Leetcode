package DP;

/**
 * @author HailongZeng
 * @date 1/11/20 5:50 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Return the number of distinct non-empty substrings of text that can be written as the concatenation of some string with itself.
 *
 *
 *
 * Example 1:
 *
 * Input: text = "abcabcabc"
 * Output: 3
 * Explanation: The 3 substrings are "abcabc", "bcabca" and "cabcab".
 * Example 2:
 *
 * Input: text = "leetcodeleetcode"
 * Output: 2
 * Explanation: The 2 substrings are "ee" and "leetcodeleetcode".
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 2000
 * text has only lowercase English letters.
 */
public class No1316_Distinct_Echo_Substrings {

    public static int distinctEchoSubstrings(String text){
        int res = 0;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < text.length(); i++){
            for (int j = 0; j <= i; j++){
                String tmp = text.substring(j, i+1);
                if (set.contains(tmp)) res++;
                set.add(tmp);
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int res = distinctEchoSubstrings(line);
            System.out.println(res);
        }
    }
}
