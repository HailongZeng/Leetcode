package Amazon_OA2;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * There's an undirected connected graph with n nodes labeled 1..n. But some of the edges has been broken disconnecting the graph. Find the minimum cost to repair the edges so that all the nodes are once again accessible from each other.
 *
 * Input:
 *
 * n, an int representing the total number of nodes.
 * edges, a list of integer pair representing the nodes connected by an edge.
 * edgesToRepair, a list where each element is a triplet representing the pair of nodes between which an edge is currently broken and the cost of repairing that edge, respectively (e.g. [1, 2, 12] means to repair an edge between nodes 1 and 2, the cost would be 12).
 *
 * Example 1:
 *
 * Input: n = 5, edges = [[1, 2], [2, 3], [3, 4], [4, 5], [1, 5]], edgesToRepair = [[1, 2, 12], [3, 4, 30], [1, 5, 8]]
 * Output: 20
 * Explanation:
 * There are 3 connected components due to broken edges: [1], [2, 3] and [4, 5].
 * We can connect these components into a single component by repearing the edges between nodes 1 and 2, and nodes 1 and 5 at a minimum cost 12 + 8 = 20.
 *
 * Example 2:
 *
 * Input: n = 6, edges = [[1, 2], [2, 3], [4, 5], [3, 5], [1, 6], [2, 4]], edgesToRepair = [[1, 6, 410], [2, 4, 800]]
 * Output: 410
 *
 * Example 3:
 *
 * Input: n = 6, edges = [[1, 2], [2, 3], [4, 5], [5, 6], [1, 5], [2, 4], [3, 4]], edgesToRepair = [[1, 5, 110], [2, 4, 84], [3, 4, 79]]
 * Output: 79
 *
 */
public class Min_Cost_to_Repair_Edges {

    public static int kruskal(int n, int[][] edges, int[][] edgesToRepair){
        if (n == 0) return -1;
        int[] roots = new int[n + 1];// id -> index + 1
        Arrays.fill(roots, -1);

        HashSet<String> broken = new HashSet<>();//record broken edges
        for (int[] edge : edgesToRepair) {
            broken.add(Arrays.toString(Arrays.copyOfRange(edge, 0, 2)));
        }

        for (int[] edge : edges) {
            if (broken.contains(Arrays.toString(edge))) continue;
            int left = find(roots, edge[0]);
            int right = find(roots, edge[1]);
            if (left != right) {
                n--;
                roots[left] = right;
            }
        }
        int res = 0;
        Arrays.sort(edgesToRepair, (a, b) -> a[2] - b[2]);
        for (int[] edge : edgesToRepair) {
            if (n == 1) break;
            int left = find(roots, edge[0]);
            int right = find(roots, edge[1]);
            if (left == right) continue;
            roots[left] = right;
            n--;
            res += edge[2];
        }
        return n == 1 ? res : -1; //return -1 if the graph is not connected, (not possible in the problem)
    }

    public static int find(int[] roots, int i) {
        while (roots[i] != -1) {
            i = roots[i];
        }
        return i;
    }


    public static void main(String[] args) {
        int n1 = 5;
        int[][] edges1 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
        int[][] edgesToRepair1 = {{1, 2, 12}, {3, 4, 30}, {1, 5, 8}};
        System.out.println(kruskal(n1, edges1, edgesToRepair1));

        int n2 = 6;
        int[][] edges2 = {{1, 2}, {2, 3}, {4, 5}, {3, 5}, {1, 6}, {2, 4}};
        int[][] edgesToRepair2 = {{1, 6, 410}, {2, 4, 800}};
        System.out.println(kruskal(n2, edges2, edgesToRepair2));

        int n3 = 6;
        int[][] edges3 = {{1, 2}, {2, 3}, {4, 5}, {5, 6}, {1, 5}, {2, 4}, {3, 4}};
        int[][] edgesToRepair3 = {{1, 5, 110}, {2, 4, 84}, {3, 4, 79}};
        System.out.println(kruskal(n3, edges3, edgesToRepair3));
    }
}
