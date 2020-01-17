package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/16/20 6:55 下午
 */

import java.util.ArrayList;
import java.util.List;

/**
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.
 *
 * Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 *
 * Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: [1,0]
 * Explanation:
 * Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
 * Example 2:
 *
 *
 *
 * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * Output: [0,2,1]
 * Explanation:
 * Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
 *
 *
 * Note:
 *
 * 0 <= workers[i][j], bikes[i][j] < 1000
 * All worker and bike locations are distinct.
 * 1 <= workers.length <= bikes.length <= 1000
 */
//leetcode1057
public class No66_Campus_Bikes {

    //time:O(M+N)  space:O(M+N)
    public static int[] assignBikes(int[][] workers, int[][] bikes) {
        int N = workers.length, M = bikes.length;
        int[] res = new int[N];
        boolean[] assigned = new boolean[N]; //represent whether the worker has been assigned or not
        boolean[] occupied = new boolean[M]; //represent whether the bike is occupied or not
        List<int[]>[] list = new List[2001];//since the maximum distance is 2000 by the size given 0 <= workers[i][j], bikes[i][j] < 1000
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                int d = dist(workers[i], bikes[j]);
                if (list[d] == null){
                    list[d] = new ArrayList<>();
                }
                list[d].add(new int[]{i, j});
            }
        }

        for (int i = 0; i < 2001; i++){
            if (list[i] == null) continue;
            int size = list[i].size();
            for (int j = 0; j < size; j++){
                int w = list[i].get(j)[0];
                int b = list[i].get(j)[1];
                if (!assigned[w] && !occupied[b]){
                    res[w] = b;
                    assigned[w] = true;
                    occupied[b] = true;
                }
            }
        }
        return res;
    }

    public static int dist(int[] p1, int[] p2){
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    public static void main(String[] args) {
        int[][] workers = {{0, 0}, {1, 1}, {2, 0}};
        int[][] bikes = {{1, 0}, {2, 2}, {2, 1}};
        int[] res = assignBikes(workers, bikes);
        printArray(res);
    }

    private static void printArray(int[] res) {
        System.out.print("[");
        for (int i = 0; i < res.length; i++){
            if (i == res.length - 1) System.out.print(res[i]);
            else System.out.print(res[i] + ",");
        }
        System.out.println("]");
    }
}
