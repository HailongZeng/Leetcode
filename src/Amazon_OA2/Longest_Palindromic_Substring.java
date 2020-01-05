package Amazon_OA2;

/**
 * LeetCode 5
 *
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class Longest_Palindromic_Substring {

    //dp[i][j]表示s[i, j]是否为palindromic, O(n^2)
    public static String longestPalindrome(String s){
        if (s.length() == 0) return "";
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int max = 0;
        String res = "";
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= i; j++){
                dp[j][i] = (s.charAt(j) == s.charAt(i) && (i - j <= 2 || dp[j + 1][i - 1]));
                if (dp[j][i]){
                    if (i - j + 1 > max){
                        max = i - j + 1;
                        res = s.substring(j, i + 1);
                    }
                }
            }
        }
        return res;
    }

    public static String longestPalindrome1(String s){
        if (s == null || s.length() < 1) return "";
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++){
            int l1 = helper(s, i, i);
            int l2 = helper(s, i, i + 1);
            int len = Math.max(l1, l2);
            if (len > end - start){
                start = i - (len - 1) / 2;
                end = i + len / 2;
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

    public static void main(String[] args) {
        String s1 = "babad";
        String s2 = "cbbd";
        System.out.println(longestPalindrome(s1));
        System.out.println(longestPalindrome(s2));
    }
}
