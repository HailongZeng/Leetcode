package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/16/20 12:46 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 *
 * If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.
 *
 * Example 1:
 *
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 *
 *
 * Note:
 *
 * All the strings in the input will only contain lowercase letters.
 * The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].
 *
 */
//leetcode727
public class No58_Minimum_Window_Subsequence {

    //brute force  time:O((S-T)^S)   space:O(1)
    public static String minWindow1(String S, String T) {
        if (S.length() < T.length()) return "";
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        for (int i = 0; i < S.length() - T.length() + 1; i++){
            if (S.charAt(i) == T.charAt(0)){
                int j = 1;
                int k = i+1;
                for (; k < S.length(); k++){
                    if (j == T.length()) break;
                    if (S.charAt(k) == T.charAt(j)) j++;
                }
                if (j == T.length()) {
                    if (minLen > k - i){
                        minLen = k - i;
                        start = i;
                        end = k;
                    }
                }
            }
        }
        return S.substring(start, end);
    }

    //优化暴力搜索，双指针  time:O(S+T)  space:O(1)
    public static String minWindow2(String S, String T) {
        if (S.length() < T.length()) return "";
        int m = S.length(), n = T.length(), start = 0, end = 0;
        int minLen = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i < m){
            if (S.charAt(i) == T.charAt(j)){
                if (++j == n){
                    int tmpEnd = i + 1;
                    while (--j >= 0){
                        while (S.charAt(i--) != T.charAt(j));
                    }
                    ++i;
                    ++j;
                    if (tmpEnd - i < minLen){
                        minLen = tmpEnd - i;
                        start = i;
                        end = tmpEnd;
                    }
                }
            }
            ++i;
        }
        return S.substring(start, end);
    }

    //dp[i][j]表示S(0,i)包含T(0,j)的起始位置
    //dp[i][j] = dp[i-1][j-1] if S.charAt(i-1) == T.charAt(j-1)
    //dp[i][j] = dp[i-1][j] if S.charAt(i-1) != T.charAt(j-1)
    //time:O(mn)   space:O(mn)
    public static String minWindow3(String S, String T) {
        int m = S.length(), n = T.length();
        int start = -1, minLen = Integer.MAX_VALUE;
        if (m < n) return "";
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < m + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= Math.min(i, n); j++){
                dp[i][j] = (S.charAt(i-1) == T.charAt(j-1)) ? dp[i-1][j-1] : dp[i-1][j];
            }
            if (dp[i][n] != -1){
                int len = i - dp[i][n];
                if (minLen > len){
                    minLen = len;
                    start = dp[i][n];
                }
            }
        }
        return start != -1 ? S.substring(start, start + minLen) : "";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            String S = line;
            String T = io.readLine();
            String res = minWindow2(S, T);
            System.out.println(res);
        }
    }
}
