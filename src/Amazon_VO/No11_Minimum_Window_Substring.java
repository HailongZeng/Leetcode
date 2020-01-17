package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/12/20 6:57 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class No11_Minimum_Window_Substring {

    public static String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) return "";
        if (t.length() > s.length()) return "";
        int[] map = new int[128];
        for (char c: t.toCharArray()){
            map[c]++;
        }
        int count = t.length();
        int start = 0;
        int minStart = 0;
        int end = 0;
        int minLength = Integer.MAX_VALUE;
        while (end < s.length()){
            if (map[s.charAt(end)] > 0) {
                count--;
            }
            map[s.charAt(end)]--;
            end++;
            while (count == 0){
                if (end - start < minLength){
                    minStart = start;
                    minLength = end - start;
                }
                map[s.charAt(start)]++;
                if (map[s.charAt(start)] > 0) count++;
                start++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart+minLength);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            String s = line;
            String t = io.readLine();
            String res = minWindow(s, t);
            System.out.println(res);
        }
    }
}
