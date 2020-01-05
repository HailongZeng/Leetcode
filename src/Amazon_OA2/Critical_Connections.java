package Amazon_OA2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 1192
 * Given an undirected connected graph with n nodes labeled 1..n. A bridge (cut edge) is defined as an edge which, when removed, makes the graph disconnected (or more precisely, increases the number of connected components in the graph). Equivalently, an edge is a bridge if and only if it is not contained in any cycle. The task is to find all bridges in the given graph. Output an empty list if there are no bridges.
 *
 * Input:
 *
 * n, an int representing the total number of nodes.
 * edges, a list of pairs of integers representing the nodes connected by an edge.
 * Example 1:
 *
 * Input: n = 5, edges = [[1, 2], [1, 3], [3, 4], [1, 4], [4, 5]]
 * Output: [[1, 2], [4, 5]]
 * Explanation:
 * There are 2 bridges:
 * 1. Between node 1 and 2
 * 2. Between node 4 and 5
 * If we remove these edges, then the graph will be disconnected.
 * If we remove any of the remaining edges, the graph will remain connected.
 *
 * Example 2:
 * Input: n = 6, edges = [[1, 2], [1, 3], [2, 3], [2, 4], [2, 5], [4, 6], [5, 6]]
 * Output: []
 * Explanation:
 * We can remove any edge, the graph will remain connected.
 */

//https://nagato1208.github.io/2019/09/15/leetcode-1192-Critical-Connections-in-a-Network-tarjan求割边/
//Tarjan算法
public class Critical_Connections {

    public static List<List<Integer>> criticalConnection(int n, int[][] edges){
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        int[] dfn = new int[n + 1];
        int[] low = new int[n + 1];
        Arrays.fill(dfn, -1);
        List<List<Integer>> res = new ArrayList<>();

        //draw graph
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        for (int i = 1; i <= n; i++){
            if (dfn[i] == -1){
                tarjan(i, low, dfn, graph, res, 0);
            }
        }
        return res;
    }

    public static int time = 0;
    private static void tarjan(int u, int[] low, int[] dfn, ArrayList<Integer>[] graph, List<List<Integer>> res, int node){
        dfn[u] = low[u] = ++time;
        for (int j = 0; j < graph[u].size(); j++){
            int v = graph[u].get(j);
            if (v == node){
                continue;
            }
            if (dfn[v] == -1){
                tarjan(v, low, dfn, graph, res, u);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > dfn[u]){
                    res.add(Arrays.asList(u, v));
                }
            }else{
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
    }


    public static void main(String[] args) {
        int n1 = 5, n2 = 6, n3 = 9;
        int[][] edges1 = {{1, 2}, {1, 3}, {3, 4}, {1, 4}, {4, 5}};
        int[][] edges2 = {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {2, 5}, {4, 6}, {5, 6}};
        int[][] edges3 = {{1, 2}, {1, 3}, {2, 3}, {3, 4}, {3, 6}, {4, 5}, {6, 7}, {6, 9}, {7, 8}, {8, 9}};
        System.out.println(criticalConnection(n1, edges1));
        System.out.println(criticalConnection(n2, edges2));
        System.out.println(criticalConnection(n3, edges3));
    }
}
