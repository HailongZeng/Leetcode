package DFS;

/**
 * @author HailongZeng
 * @date 1/13/20 4:57 下午
 */

import java.util.*;

/**
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.
 *
 * Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".
 *
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.
 *
 *
 *
 * Example 1:
 *
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 *
 * Input 2: ball coordinate (rowBall, colBall) = (4, 3)
 * Input 3: hole coordinate (rowHole, colHole) = (0, 1)
 *
 * Output: "lul"
 *
 * Explanation: There are two shortest ways for the ball to drop into the hole.
 * The first way is left -> up -> left, represented by "lul".
 * The second way is up -> left, represented by 'ul'.
 * Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".
 *
 * Example 2:
 *
 * Input 1: a maze represented by a 2D array
 *
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 *
 * Input 2: ball coordinate (rowBall, colBall) = (4, 3)
 * Input 3: hole coordinate (rowHole, colHole) = (3, 0)
 *
 * Output: "impossible"
 *
 * Explanation: The ball cannot reach the hole.
 *
 *
 *
 * Note:
 *
 * There is only one ball and one hole in the maze.
 * Both the ball and hole exist on an empty space, and they will not be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.
 */
public class No499_The_MazeIII {

    //bfs
    //time:O(m*n*max(m,n)) space:O(mn)
    public static String findShortestWay1(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int[] row: dist){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        char[] way = {'l', 'u', 'r', 'd'};
        Queue<int[]> q = new LinkedList<>();
        Map<Integer, String> map = new HashMap<>(); //key is the index (i,j)-->i*n+j， value is the corresponding lexicographically smallest string
        dist[ball[0]][ball[1]] = 0;
        q.offer(new int[]{ball[0], ball[1], 0});
        while (!q.isEmpty()){
            int[] s = q.poll();
            for (int i = 0; i < dirs.length; i++){
                int x = s[0], y = s[1], d = s[2];
                String path = map.getOrDefault(x*n + y, "");
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0 && (x != hole[0] || y != hole[1])){
                    x += dirs[i][0];
                    y += dirs[i][1];
                    d++;
                }
                if (x != hole[0] || y != hole[1]){//回滚，因为撞墙了
                    x -= dirs[i][0];
                    y -= dirs[i][1];
                    d--;
                }
                path += way[i];
                if (dist[x][y] > d){
                    dist[x][y] = d;
                    map.put(x*n + y, path);
                    if (x != hole[0] || y != hole[1]) q.offer(new int[]{x, y, d});
                }else if(dist[x][y] == d && path.compareTo(map.getOrDefault(x*n+y, "")) < 0){
                    map.put(x*n+y, path);
                    if (x != hole[0] || y != hole[1]) q.offer(new int[]{x, y, d});
                }
            }
        }
        String res = map.getOrDefault(hole[0]*n + hole[1], "");
        return res.isEmpty() ? "impossible" : res;
    }

    private static int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private static char[] way = {'l', 'u', 'r', 'd'};
    public static String findShortestWay2(int[][] maze, int[] ball, int[] hole){
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int[] row: dist){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        Map<Integer, String> map = new HashMap<>();
        dist[ball[0]][ball[1]] = 0;
        helper(maze, ball[0], ball[1], hole, dist, map);
        String res = map.getOrDefault(hole[0]*n + hole[1], "");
        return res.isEmpty() ? "impossible" : res;
    }

    private static void helper(int[][] maze, int i, int j, int[] hole, int[][] dist, Map<Integer, String> map) {
        if (i == hole[0] && j == hole[1]) return;
        int m = maze.length, n = maze[0].length;
        for (int k = 0; k < dirs.length; i++){
            int x = i, y = j, d = dist[x][y];
            String path = map.getOrDefault(x*n+y, "");
            while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0 && (x != hole[0] || y != hole[1])){
                x += dirs[k][0];
                y += dirs[k][1];
                d++;
            }
            if (x != hole[0] || y != hole[1]){
                x -= dirs[k][0];
                y -= dirs[k][1];
                d--;
            }
            path += way[k];
            if (dist[x][y] > d){
                dist[x][y] = d;
                map.put(x*n+y, path);
                helper(maze, x, y, hole, dist, map);
            }else if(dist[x][y] == d && path.compareTo(map.getOrDefault(x*n+y, "")) < 0){
                map.put(x*n+y, path);
                helper(maze, x, y, hole, dist, map);
            }
        }
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        int[] ball = {0, 4};
        int[] hole = {4, 4};
        String res = findShortestWay1(maze, ball, hole);
        System.out.println(res);
    }
}
