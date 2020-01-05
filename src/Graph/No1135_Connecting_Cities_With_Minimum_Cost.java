package Graph;

import java.util.Arrays;
import java.util.Comparator;

/**
 * There are N cities numbered from 1 to N.
 *
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together. (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
 *
 * Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together. The cost is the sum of the connection costs used. If the task is impossible, return -1.
 *
 * Example 1:
 *
 * Input: N = 3, connections = [[1, 2, 5], [1, 3, 6], [2, 3, 1]]
 * Output: 6
 * Explanation: Choosing any 2 edges will connect all cities so we choose the minimum 2
 *
 * Example 2:
 *
 * Input: N = 4, connections = [[1, 2, 3], [3, 4, 4]]
 * Output: -1
 * Explanation: There is no way to connect all cities even if all edges are used
 */
public class No1135_Connecting_Cities_With_Minimum_Cost {

    public static int minimumCost(int N, int[][] connections){
        if (connections.length < N - 1) return -1;
        Arrays.sort(connections, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[2] - b[2];
            }
        });
        int[] parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;
        int res = 0;
        int part = N;
        for (int[] conn: connections){
            int a = conn[0] - 1;
            int b = conn[1] - 1;
            int cost = conn[2];
            int pa = find_root(a, parent);
            int pb = find_root(b, parent);
            if (pa != pb){
                parent[pa] = pb;
                part--;
                res += cost;
            }
            if (part == 1) return res;
        }
        return -1;
    }

    public static int find_root(int a, int[] parent){
        if (parent[a] == a) return a;
        return find_root(parent[a], parent);
    }

    public static void main(String[] args) {
        int N1 = 3, N2 = 4;
        int[][] connections1 = {
                {1, 2, 5},
                {1, 3, 6},
                {2, 3, 1}
        };
        int[][] connections2 = {
                {1, 2, 3},
                {3, 4, 4}
        };
        System.out.println(minimumCost(N1, connections1));
        System.out.println(minimumCost(N2, connections2));
    }
}
