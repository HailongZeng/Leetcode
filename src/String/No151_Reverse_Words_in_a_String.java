package String;

/**
 * @author HailongZeng
 * @date 1/13/20 11:44 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given an input string, reverse the string word by word.
 *
 *
 *
 * Example 1:
 *
 * Input: "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 *
 * Input: "  hello world!  "
 * Output: "world! hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 *
 * Note:
 *
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 *
 * Follow up:
 *
 * For C programmers, try to solve it in-place in O(1) extra space.
 */
public class No151_Reverse_Words_in_a_String {

    //time:O(n)  space:O(n)
    public static String reverseWords(String s){
        StringBuilder res = new StringBuilder();
        String[] words = s.trim().split("\\s+"); //\\s表示空格  +表示一个或多个
        //String[] words = s.trim().split(" +");
        for (int i = words.length - 1; i >= 0; i--) {
            res.append(words[i] + " ");
        }
        return res.toString().substring(0, res.length()-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            String res = reverseWords(line);
            System.out.println(res);
        }
    }
}
