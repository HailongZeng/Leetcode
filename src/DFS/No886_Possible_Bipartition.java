package DFS;

import java.util.ArrayList;

/**
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 *
 * Each person may dislike some other people, and they should not go into the same group.
 *
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 *
 * Return true if and only if it is possible to split everyone into two groups in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 *
 * Example 2:
 *
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 *
 * Example 3:
 *
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 *
 *
 * Note:
 *
 * 1 <= N <= 2000
 * 0 <= dislikes.length <= 10000
 * 1 <= dislikes[i][j] <= N
 * dislikes[i][0] < dislikes[i][1]
 * There does not exist i != j for which dislikes[i] == dislikes[j].
 */
public class No886_Possible_Bipartition {

    public static boolean possibleBipartition(int N, int[][] dislikes){
        if (dislikes.length == 0 || dislikes[0].length == 0 || dislikes == null) return true;
        ArrayList<Integer>[] graph = new ArrayList[N + 1];

        //构建图
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList();
        for (int[] d: dislikes){
            graph[d[0]].add(d[1]);
            graph[d[1]].add(d[0]);
        }

        int[] color = new int[N + 1];
        for (int i = 1; i <= N; i++){  //0--没有染色   1--染成红色  -1--染成蓝色
            if (color[i] == 0 && !dfs(i, 1, graph, color)) return false;
        }
        return true;
    }

    public static boolean dfs(int node, int curColor, ArrayList<Integer>[] graph, int[] color){
        if (color[node] != 0) return color[node] == curColor;
        color[node] = curColor;
        for (int next: graph[node]){
            if (!dfs(next, -curColor, graph, color)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int N1 = 4;
        int[][] dislikes1 = {
                {1, 2},
                {1, 3},
                {2, 4}
        };
        int N2 = 3;
        int[][] dislikes2 = {
                {1, 2},
                {1, 3},
                {2, 3}
        };
        int N3 = 5;
        int[][] dislikes3 = {
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 5},
                {1, 5}
        };
        System.out.println(possibleBipartition(N1, dislikes1));
        System.out.println(possibleBipartition(N2, dislikes2));
        System.out.println(possibleBipartition(N3, dislikes3));
    }
}
