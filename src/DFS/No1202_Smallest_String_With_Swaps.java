package DFS;

import java.util.*;

/**
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 *
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 *
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * Example 2:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 * Example 3:
 *
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 */
public class No1202_Smallest_String_With_Swaps {

    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        if (pairs.size() == 0 || n == 1) return s;
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> l: pairs){
            int a = l.get(0);
            int b = l.get(1);
            graph[a].add(b);
            graph[b].add(a);
        }

        char[] c = s.toCharArray();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++){
            if (visited[i] == false && graph[i] != null) {
                List<Integer> idx = new ArrayList<>();
                List<Character> ch = new ArrayList<>();
                dfs(c, idx, ch, visited, i, graph);
                Collections.sort(idx);
                Collections.sort(ch);
                for (int j = 0; j < ch.size(); j++) {
                    c[idx.get(j)] = ch.get(j);
                }
            }
        }
        return new String(c);
    }

    public static void dfs(char[] c, List<Integer> idx, List<Character> ch, boolean[] visited, int u, ArrayList<Integer>[] graph){
        if (visited[u] == true) return;
        visited[u] = true;
        idx.add(u);
        ch.add(c[u]);
        for (int v: graph[u]){
            dfs(c, idx, ch, visited, v, graph);
        }
    }

    public static void main(String[] args) {
        String s1 = "dcab";
        List<List<Integer>> pairs1 = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(0);
        temp.add(3);
        pairs1.add(temp);
        temp = new ArrayList<>();
        temp.add(1);
        temp.add(2);
        pairs1.add(temp);
        System.out.println(smallestStringWithSwaps(s1, pairs1));
    }
}
