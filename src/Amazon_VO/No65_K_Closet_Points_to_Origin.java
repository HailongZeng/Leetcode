package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/16/20 6:47 下午
 */

import java.util.PriorityQueue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 *
 * Note:
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
//leetcode973
public class No65_K_Closet_Points_to_Origin {

    public static int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->(o2[0]*o2[0]+o2[1]*o2[1])-(o1[0]*o1[0]+o1[1]*o1[1]));
        for (int[] point: points){
            pq.add(point);
            if (pq.size() > K) pq.poll();
        }
        int[][] res = new int[K][2];
        int i = 0;
        for (int[] p: pq){
            res[i++] = p;
        }
        return res;
    }

    public static void print2Array(int[][] res){
        System.out.print("[");
        for (int i = 0; i < res.length; i++){
            System.out.print("[");
            for (int j = 0; j < res[0].length; j++){
                if (j == res[0].length - 1) System.out.print(res[i][j]);
                else System.out.print(res[i][j] + ",");
            }
            if (i == res.length - 1) System.out.print("]");
            else System.out.print("]");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[][] points = {
                {1, 3},
                {-2, 2}
        };
        int K = 1;
        int[][] res = kClosest(points, K);
        print2Array(res);
    }
}
