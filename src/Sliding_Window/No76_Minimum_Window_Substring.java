package Sliding_Window;

/**
 * @author HailongZeng
 * @date 1/7/20 11:09 上午
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
public class No76_Minimum_Window_Substring {

    public static String minWindow(String s, String t){
        String res = "";
        if (s == "" || t.length() > s.length()) return res;
        int[] map = new int[128]; //we have 128 characters. so just use this to record the number of one character occurred in t. for example: t="AABC"  map[A]=2, map[B]=1,map[C]=1,....
        int start = 0;
        int minStart = 0;
        int end = 0;
        int count = t.length(); //the number of characters in t
        int minLength = Integer.MAX_VALUE;
        for (char temp: t.toCharArray()){
            map[temp]++;
        }
        while (end < s.length()){
            if (map[s.charAt(end)] > 0) count--;  //if the character occur in map, count=count-1
            map[s.charAt(end)]--;//update array map
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
        return (minLength == Integer.MAX_VALUE) ? "" : s.substring(minStart, minStart+minLength);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            String s = line;
            line = io.readLine();
            String t = line;
            String res = minWindow(s, t);
            System.out.println(res);
        }
    }
}
