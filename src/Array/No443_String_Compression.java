package Array;

/**
 * @author HailongZeng
 * @date 1/11/20 9:19 上午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given an array of characters, compress it in-place.
 *
 * The length after compression must always be smaller than or equal to the original array.
 *
 * Every element of the array should be a character (not int) of length 1.
 *
 * After you are done modifying the input array in-place, return the new length of the array.
 *
 *
 * Follow up:
 * Could you solve it using only O(1) extra space?
 *
 *
 * Example 1:
 *
 * Input:
 * ["a","a","b","b","c","c","c"]
 *
 * Output:
 * Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 *
 * Explanation:
 * "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 *
 *
 * Example 2:
 *
 * Input:
 * ["a"]
 *
 * Output:
 * Return 1, and the first 1 characters of the input array should be: ["a"]
 *
 * Explanation:
 * Nothing is replaced.
 *
 *
 * Example 3:
 *
 * Input:
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 *
 * Output:
 * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 *
 * Explanation:
 * Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
 * Notice each digit has it's own entry in the array.
 *
 *
 * Note:
 *
 * All characters have an ASCII value in [35, 126].
 * 1 <= len(chars) <= 1000.
 */
public class No443_String_Compression {

    //time:O(n)  space:O(1)
    //read--mark where we are reading and writing from
    //anchor--the start position of the continuous group of characters we are currently reading
    public static int compress(char[] chars){
        int anchor = 0, write = 0;
        for (int read = 0; read < chars.length; read++){
            if (read + 1 == chars.length || chars[read + 1] != chars[read]){
                chars[write++] = chars[anchor];
                if (read > anchor){
                    for (char c: ("" + (read - anchor + 1)).toCharArray()){
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }
        System.out.print("[");
        for (int i = 0; i < chars.length - 1; i++){
            if (i == chars.length - 2) System.out.print(chars[i]);
            else System.out.print(chars[i] + ",");
        }
        System.out.println("]");
        return write;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            char[] chars = stringToArray(line);
            int res = compress(chars);
            System.out.println(res);
        }
    }

    private static char[] stringToArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new char[0];
        String[] parts = input.split(",");
        char[] chars = new char[parts.length];
        for (int i = 0; i < chars.length; i++){
            chars[i] = parts[i].charAt(0);
        }
        return chars;
    }
}
