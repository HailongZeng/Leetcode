package DP;

/**
 * @author HailongZeng
 * @date 12/16/19 12:27 下午
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Given an array A of strings, find any smallest string that contains each string in A as a substring.
 *
 * We may assume that no string in A is substring of another string in A.
 *
 *
 * Example 1:
 *
 * Input: ["alex","loves","leetcode"]
 * Output: "alexlovesleetcode"
 * Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
 * Example 2:
 *
 * Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
 * Output: "gctaagttcatgcatc"
 *
 *
 * Note:
 *
 * 1 <= A.length <= 12
 * 1 <= A[i].length <= 20
 *
 */
//Hamiltonian Path
public class No943_Find_the_Shortest_Superstring {

    public static String shortestSuperstring(String[] A){
        List<String> list = new ArrayList(Arrays.asList(A));
        //1*2+2*3+3*4+....+(n-1)*n=(n-1)*n*(n+1))/3--->O(n^3)
        while (true){
            int savedLength = -1;
            int index1 = 0, index2 = 0;
            String newStr = "";
            if (list.size() == 1) break;
            int n = list.size();
            for (int i = 0; i < n - 1; i++){
                for (int j = i + 1; j < n; j++){
                    String s1 = list.get(i);
                    String s2 = list.get(j);
                    String merged = merge(s1, s2);
                    int saved = s1.length() + s2.length() - merged.length();
                    if (saved > savedLength){
                        savedLength = saved;
                        index1 = i;
                        index2 = j;
                        newStr = merged;
                    }
                }
            }
            String str1 = list.get(index1);
            String str2 = list.get(index2);
            list.remove(str1);
            list.remove(str2);
            list.add(newStr);
        }
        return list.get(0);
    }

    public static String merge(String s1, String s2){
        int len1 = s1.length(), len2 = s2.length();
        int overlapped1 = 0, overlapped2 = 0;
        for (int len = 1; len1 - len >= 0 && len <= len2; len++){
            //s.substring(begin)   s.substring(begin,end)
            if (s1.substring(len1 - len).equals(s2.substring(0, len))){
                overlapped1 = len;
            }
        }

        for (int len = 1; len2 - len >= 0 && len <= len1; len++){
            //s.substring(begin)   s.substring(begin,end)
            if (s2.substring(len2 - len).equals(s1.substring(0, len))){
                overlapped2 = len;
            }
        }

        if (overlapped1 >= overlapped2){
            return s1.substring(0, len1-overlapped1) + s2;
        }else{
            return s2.substring(0, len2-overlapped2) + s1;
        }
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            int M = Integer.parseInt(st.nextLine());
            String[] A = new String[M];
            for (int j = 0; j < M; j++){
                A[j] = st.nextLine();
            }
            System.out.println(shortestSuperstring(A));
        }
    }
}
