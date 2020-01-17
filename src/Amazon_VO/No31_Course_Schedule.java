package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/14/20 11:18 上午
 */

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
//leetcode207
public class No31_Course_Schedule {

    //dfs  visit来记录访问状态  0表示未访问过 -1表示有冲突 1表示访问过
    //time:O(n)  space:O(n)
    public static boolean canFinish1(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) throw new IllegalArgumentException("illegal prerequisites array");
        int len = prerequisites.length;
        if (numCourses == 0 || len == 0) return true;
        //track visited courses
        int[] visit = new int[numCourses];
        //use the map to store what courses depend on a course
        Map<Integer, ArrayList<Integer>> m = new HashMap<>(); //邻接链表
        for (int[] a: prerequisites){
            if (!m.containsKey(a)) m.put(a[1], new ArrayList<>());
            m.get(a[1]).add(a[0]);
        }
        for (int i = 0; i < numCourses; i++){
            if (!canFinishDFS(m, visit, i)) return false;
        }
        return true;
    }

    private static boolean canFinishDFS(Map<Integer, ArrayList<Integer>> m, int[] visit, int i) {
        if (visit[i] == -1) return false;
        if (visit[i] == 1) return true;
        visit[i] = -1;
        for (int a: m.get(i)){
            if (!canFinishDFS(m, visit, a)) return false;
        }
        visit[i] = 1;
        return true;
    }

    //time:O(n)  space:O(n)
    //bfs
    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) throw new IllegalArgumentException("illegal prerequisites array");
        int len = prerequisites.length;
        if (numCourses == 0 || len == 0) return true;
        int[] indegree = new int[numCourses];
        Map<Integer, ArrayList<Integer>> m = new HashMap<>(); //邻接链表
        for (int[] a: prerequisites){
            indegree[a[0]]++;
            if (!m.containsKey(a[1])) m.put(a[1], new ArrayList<>());
            m.get(a[1]).add(a[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++){
            if (indegree[i] == 0) q.offer(i);
        }
        while (!q.isEmpty()){
            int t = q.poll();
            for (int tmp: m.get(t)){
                indegree[tmp]--;
                if (indegree[tmp] == 0) q.offer(tmp);
            }
        }
        for (int i = 0; i < numCourses; i++){
            if (indegree[i] != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        boolean res = canFinish1(numCourses, prerequisites);
        System.out.println(res);
    }
}
