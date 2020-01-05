package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class No5_Longest_Palindromic_Substring {

    public static String longestPalindrome1(String s){
        if (s.length() == 0 || s == null) return "";
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++){
            int l1 = helper(s, i, i);
            int l2 = helper(s, i, i + 1);
            int l = Math.max(l1, l2);
            if (l > end - start){
                start = i - (l - 1) / 2;
                end = i + l / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public static int helper(String s, int left, int right){
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)){
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static String longestPalindrome2(String s){
        String Q = "@";
        for (int i = 0; i < s.length(); i++){
            Q += "#" + s.charAt(i);
        }
        Q += "#$";

        int n = Q.length();
        int[] P = new int[n];
        int c = 0, r = 0;
        for (int i = 1; i < n - 1; i++){
            int iMirror = c - (i - c);
            if (i < r) P[i] = Math.min(P[iMirror], r - i);
            while (Q.charAt(i + P[i] + 1) == Q.charAt(i - P[i] - 1)) P[i]++;
            if (i + P[i] > r){
                c = i;
                r = i + P[i];
            }
        }

        int maxLength = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++){
            if (P[i] > maxLength){
                maxLength = P[i];
                centerIndex = i;
            }
        }
        return s.substring((centerIndex  - maxLength - 1) / 2 , (centerIndex  + maxLength - 1) / 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(longestPalindrome1(s));
        System.out.println(longestPalindrome2(s));
    }
}
