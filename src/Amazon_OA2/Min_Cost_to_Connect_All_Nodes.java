package Amazon_OA2;

import java.util.Arrays;

/**
 * Given an undirected graph with n nodes labeled 1..n. Some of the nodes are already connected. The i-th edge connects nodes edges[i][0] and edges[i][1] together. Your task is to augment this set of edges with additional edges to connect all the nodes. Find the minimum cost to add new edges between the nodes such that all the nodes are accessible from each other.
 *
 * Input:
 *
 * n, an int representing the total number of nodes.
 * edges, a list of integer pair representing the nodes already connected by an edge.
 * newEdges, a list where each element is a triplet representing the pair of nodes between which an edge can be added and the cost of addition, respectively (e.g. [1, 2, 5] means to add an edge between node 1 and 2, the cost would be 5).
 * Example 1:
 *
 * Input: n = 6, edges = [[1, 4], [4, 5], [2, 3]], newEdges = [[1, 2, 5], [1, 3, 10], [1, 6, 2], [5, 6, 5]]
 * Output: 7
 * Explanation:
 * There are 3 connected components [1, 4, 5], [2, 3] and [6].
 * We can connect these components into a single component by connecting node 1 to node 2 and node 1 to node 6 at a minimum cost of 5 + 2 = 7.
 */
public class Min_Cost_to_Connect_All_Nodes {

    public static class DSU{
        int[] root;
        int[] size;   //union by rank
        public DSU(int n){
            root = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++){
                root[i] = i;
                size[i]  = 1;
            }
        }

        public int find(int x){
            if (root[x] != x){
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            if (size[rootX] > size[rootY]){
                root[rootX] = rootY;
                size[rootY]++;
            }else{
                root[rootY] = rootX;
                size[rootX]++;
            }
        }
    }

    public static int kruskal(int n, int[][] edges, int[][] newEdges){
        DSU dsu = new DSU(n);
        int component = n;
        Arrays.sort(newEdges, (int[] a, int[] b)->a[2] - b[2]);
        int res = 0;
        for (int[] edge: edges){
            if (dsu.find(edge[0] - 1) != dsu.find(edge[1] - 1)) {
                dsu.union(edge[0] - 1, edge[1] - 1);
                component--;
            }
        }
        for (int i = 0; i < newEdges.length; i++){
            if (!(dsu.find(newEdges[i][0] - 1) == dsu.find(newEdges[i][1] - 1))){
                dsu.union(newEdges[i][0] - 1, newEdges[i][1] - 1);
                res += newEdges[i][2];
                component--;
                if (component == 1) return res;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{1, 4}, {4, 5}, {2, 3}};
        int[][] newEdges = {{1, 2, 5}, {1, 3, 10}, {1, 6, 2}, {5, 6, 5}};
        System.out.println(kruskal(n, edges, newEdges));
    }
}
