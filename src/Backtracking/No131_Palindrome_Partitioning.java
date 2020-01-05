package Backtracking;

/**
 * @author HailongZeng
 * @date 12/18/19 9:50 下午
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */
public class No131_Palindrome_Partitioning {

    public static List<List<String>> partition(String s){
        List<List<String>> res = new ArrayList<>();
        if (s.length() == 0 || s == null) return res;
        List<String> cur = new ArrayList<>();
        helper(res, cur, s, 0);
        return res;
    }

    public static void helper(List<List<String>> res, List<String> cur, String s, int start){
        if (start == s.length()){
            res.add(new ArrayList(cur));
            return;
        }
        for (int i = start; i < s.length(); i++){
            if (!isPalindrome(s, start, i)) continue;
            cur.add(s.substring(start, i+1));
            helper(res, cur, s, i+1);
            cur.remove(cur.size()-1);
        }
    }

    public static boolean isPalindrome(String s, int start, int end){
        while (start < end){
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String s = st.nextLine();
            System.out.println(partition(s));
        }
    }
}
