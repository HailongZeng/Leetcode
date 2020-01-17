package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/12/20 8:41 上午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class No2_Longest_Substring_Without_Repeating_Characters {

    //time: O(2n)   space: O(min(n, 26))
    public static int lengthOfLongestSubstring1(String s){
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int res = 0, i = 0, j = 0;
        while (i < n && j < n){
            //[i,j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                res = Math.max(res, j-i);
            }else{
                set.remove(s.charAt(i++));
            }
        }
        return res;
    }

    //time:O(n)  space:O(min(n, 26))
    //if s[j] have a duplicate in the range[i,j) with index j', we don't need to increae i little by little. we can skip all the elements in the range[i,j'] and let i to be j'+1 directly
    public static int lengthOfLongestSubstring2(String s){
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int res = 0;
        //[i, j]
        for (int j = 0, i = 0; j < n; j++){
            if (map.containsKey(s.charAt(j))){
                i = Math.max(map.get(s.charAt(j)), i);
            }
            res = Math.max(res, j-i+1);
            map.put(s.charAt(j), j+1);
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int res = lengthOfLongestSubstring2(line);
            System.out.println(res);
        }
    }
}
