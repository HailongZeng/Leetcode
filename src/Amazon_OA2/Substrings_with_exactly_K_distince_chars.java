package Amazon_OA2;

import java.util.HashMap;

/**
 * LeetCode 992
 * Given a string s and an int k, return an int representing the number of substrings (not unique) of s with exactly k distinct characters. If the given string doesn't have k distinct characters, return 0.
 * https://leetcode.com/problems/subarrays-with-k-different-integers
 *
 * Example 1:
 *
 * Input: s = "pqpqs", k = 2
 * Output: 7
 * Explanation: ["pq", "pqp", "pqpq", "qp", "qpq", "pq", "qs"]
 * Example 2:
 *
 * Input: s = "aabab", k = 3
 * Output: 0
 * Constraints:
 *
 * The input string consists of only lowercase English letters [a-z]
 * 0 ≤ k ≤ 26
 */
public class Substrings_with_exactly_K_distince_chars {

    public static class window{
        HashMap<Character, Integer> count;
        int nonzero;

        public window(){
            count = new HashMap<>();
            nonzero = 0;
        }

        public void add(char s){
            count.put(s, count.getOrDefault(s, 0) + 1);
            if (count.get(s) == 1){
                nonzero++;
            }
        }

        public void remove(char s){
            count.put(s, count.get(s) - 1);
            if (count.get(s) == 0){
                nonzero--;
            }
        }

        public int different(){
            return nonzero;
        }
    }

    public static int subarraysWithDistinct(String s, int k){
        if (s.length() < k) return 0;
        int res = 0;
        int left1 = 0;
        int left2 = 0;
        window w1 = new window();
        window w2 = new window();
        for (int i = 0; i < s.length(); i++){
            char tmp = s.charAt(i);
            w1.add(tmp);
            w2.add(tmp);
            while (w1.nonzero > k){
                w1.remove(s.charAt(left1++));
            }
            while (w2.nonzero >= k){
                w2.remove(s.charAt(left2++));
            }
            res += left2 - left1;
        }
        return res;
    }

    public static void main(String[] args) {
        String s1 = "pqpqs";
        int k1 = 2;
        System.out.println(subarraysWithDistinct(s1, k1));
        String s2 = "aabab";
        int k2 = 3;
        System.out.println(subarraysWithDistinct(s2, k2));
    }
}
