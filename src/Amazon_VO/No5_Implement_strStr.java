package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/12/20 10:29 上午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
//rolling hash介绍在leetcode答案中
public class No5_Implement_strStr {

    public static int strStr1(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        if (haystack.length() < needle.length()) return -1;
        return haystack.indexOf(needle);
    }

    //time:O(n*(m-n))  space:O(1)
    public static int strStr2(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        int m = haystack.length(), n = needle.length();
        if (m < n) return -1;
        for (int i = 0; i <= m - n; i++){
            if (haystack.substring(i, i+n).equals(needle)) return i;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            String haystack = line;
            String needle = io.readLine();
            int res = strStr2(haystack, needle);
            System.out.println(res);
        }
    }
}
