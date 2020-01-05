package Amazon_OA2;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * You have a map that marks the location of a treasure island. Some of the map area has jagged rocks and dangerous reefs. Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure out a shortest route to the treasure island.
 *
 * Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from the top-left corner of the map and can move one block up, down, left or right at a time. The treasure island is marked as X in a block of the matrix. X will not be at the top-left corner. Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks. You cannot leave the map area. Other areas O are safe to sail in. The top-left corner is always safe. Output the minimum number of steps to get to the treasure.
 *
 * Example:
 *
 * Input:
 * [['O', 'O', 'O', 'O'],
 *  ['D', 'O', 'D', 'O'],
 *  ['O', 'O', 'O', 'O'],
 *  ['X', 'D', 'D', 'O']]
 *
 * Output: 5
 * Explanation: Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3, 0) The minimum route takes 5 steps.
 */
public class Treasure_Island {

    private static final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    //BFS
    public static int treasureIsland(char[][] map){
        if (map == null || map.length == 0 || map[0].length == 0) return -1;
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0));
        map[0][0] = 'D';   //mark as visited
        for (int steps = 1; !q.isEmpty(); steps++){
            for (int sz = q.size(); sz > 0; sz--){
                Point p = q.poll();
                for (int[] dir: dirs){
                    int r = p.r + dir[0];
                    int c = p.c + dir[1];
                    if (isSafe(map, r, c)){
                        if (map[r][c] == 'X') return steps;
                        map[r][c] = 'D';
                        q.add(new Point(r, c));
                    }
                }
            }
        }
        return -1;
    }

    public static boolean isSafe(char[][] map, int r, int c){
        return r >= 0 && r < map.length && c >= 0 && c < map[0].length && map[r][c] != 'D';
    }

    public static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        char[][] map = {
                {'O', 'O', 'O', 'O'},
                {'D', 'O', 'D', 'O'},
                {'O', 'O', 'O', 'O'},
                {'X', 'D', 'D', 'O'}
        };
        System.out.println(treasureIsland(map));
    }
}
