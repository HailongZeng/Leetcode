package Amazon_OA2;

import java.util.*;

/**
 * LeetCode 763
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 *
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * Note:
 *
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 */
public class Partition_Labels {

    public static List<Integer> partitionLabels(String s){
        List<Integer> res = new ArrayList<>();
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++){
            last[s.charAt(i) - 'a'] = i;
        }
        int j = 0, anchor = 0;
        for (int i = 0; i < s.length(); i++){
            j = Math.max(j, last[s.charAt(i) - 'a']);
            if (i == j){
                res.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s1 = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(s1));

        String s2 = "dccccbaabe";
        System.out.println(partitionLabels(s2));
    }

}
