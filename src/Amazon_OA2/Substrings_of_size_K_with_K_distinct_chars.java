package Amazon_OA2;

import java.util.HashSet;

/**
 * Given a string s and an int k, return all unique substrings of s of size k with k distinct characters.
 *
 * Example 1:
 *
 * Input: s = "abcabc", k = 3
 * Output: ["abc", "bca", "cab"]
 * Example 2:
 *
 * Input: s = "abacab", k = 3
 * Output: ["bac", "cab"]
 * Example 3:
 *
 * Input: s = "awaglknagawunagwkwagl", k = 4
 * Output: ["wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag"]
 * Explanation:
 * Substrings in order are: "wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag", "wagl"
 * "wagl" is repeated twice, but is included in the output once.
 * Constraints:
 *
 * The input string consists of only lowercase English letters [a-z]
 * 0 ≤ k ≤ 26
 */
public class Substrings_of_size_K_with_K_distinct_chars {

    public static HashSet<String> kSizeKDistinctChars(String s, int k){
        HashSet<String> res = new HashSet<>();
        HashSet<Character> window = new HashSet<>();
        if (s.length() < k) return res;
        for (int start = 0, end = 0; end < s.length(); end++){
            for (; window.contains(s.charAt(end)); start++){
                window.remove(s.charAt(start));
            }
            window.add(s.charAt(end));
            if (window.size() == k){
                res.add(s.substring(start, end + 1));
                window.remove(s.charAt(start++));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s1 = "abcabc";
        String s2 = "abacab";
        String s3 = "awaglknagawunagwkwagl";
        int k1 = 3;
        int k2 = 3;
        int k3 = 4;
        System.out.println(kSizeKDistinctChars(s1, k1));
        System.out.println(kSizeKDistinctChars(s2, k2));
        System.out.println(kSizeKDistinctChars(s3, k3));
    }
}
