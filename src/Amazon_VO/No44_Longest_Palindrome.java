package Amazon_VO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HailongZeng
 * @date 1/15/20 12:14 上午
 */

/**
 *Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 *
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 *
 * Note:
 * Assume the length of given string will not exceed 1,010.
 *
 * Example:
 *
 * Input:
 * "abccccdd"
 *
 * Output:
 * 7
 *
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
//leetcode409
public class No44_Longest_Palindrome {

    //time:O(n)  space:O(n)
    public static int longestPalindrome1(String s) {
        if (s.length() == 0) return 0;
        Map<Character, Integer> m = new HashMap<>();
        for (char c: s.toCharArray()){
            m.put(c, m.getOrDefault(c, 0) + 1);
        }
        int res = 0;
        boolean odd = false;
        for (char key: m.keySet()){
            if (m.get(key) % 2 == 0) res += m.get(key);
            else{
                if (odd == false) res += m.get(key);
                else {
                    res += m.get(key) - 1;
                }
                odd = true;
            }
        }
        return res;
    }

    //time:O(n)  space:O(1)
    public static int longestPalindrome2(String s) {
        int[] count = new int[128];
        for (char c: s.toCharArray())
            count[c]++;

        int ans = 0;
        for (int v: count) {
            ans += v / 2 * 2;
            if (ans % 2 == 0 && v % 2 == 1) //only add once if there exists odd freq
                ans++;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int res = longestPalindrome1(line);
            System.out.println(res);
        }
    }
}
