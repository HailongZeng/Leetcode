package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/14/20 1:39 下午
 */

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *              course 0. So the correct course order is [0,1] .
 * Example 2:
 *
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 *              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
//leetcode210
public class No32_Course_ScheduleII {

    //bfs  time:O(n)  space:O(n)
    public static int[] findOrder1(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) throw new IllegalArgumentException("illegal prerequisites array");
        int len = prerequisites.length;
        int[] in = new int[numCourses];
        ArrayList<Integer>[] m = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++){
            //initialization
            m[i] = new ArrayList<>();
        }
        for (int[] a: prerequisites){
            in[a[0]]++;
            m[a[1]].add(a[0]);
        }
        int[] visit = new int[numCourses]; //mark whether it is visited or not
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++){
            if (in[i] == 0) {
                q.offer(i);
                visit[i] = 1;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()){
            int t = q.remove();
            res.add(t);
            for (int tmp: m[t]){
                if (visit[tmp] != 1) {
                    in[tmp]--;
                    if (in[tmp] == 0) {
                        q.offer(tmp);
                        visit[tmp] = 1;
                    }
                }
            }
        }
        if (res.size() < numCourses) return new int[0];
        int[] a = new int[numCourses];
        for (int i = 0; i < numCourses; i++){
            a[i] = res.get(i);
        }
        return a;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        int[] res = findOrder1(numCourses, prerequisites);
        System.out.print("[");
        for (int i = 0; i < res.length; i++){
            if (i == res.length - 1) System.out.print(res[i]);
            else System.out.print(res[i] + ",");
        }
        System.out.println("]");
    }
}
