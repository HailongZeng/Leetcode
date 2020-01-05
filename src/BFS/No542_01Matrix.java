package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 * Example 2:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,1,1]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,2,1]]
 *
 *
 * Note:
 *
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right
 */
public class No542_01Matrix {

    //dimension reduction: i*cols + j represents the index of the element at (i,j)
    public static int[][] updateMatrix(int[][] matrix){
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return matrix;
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == 0){
                    q.offer(i*n+j);  //push the index of element 0 into queue
                }else if(matrix[i][j] == 1){
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!q.isEmpty()){
            int top = q.poll();
            int row = top / n;
            int col = top % n;
            for (int i = 0; i < dirs.length; i++){
                int newRow = row + dirs[i][0];
                int newCol = col + dirs[i][1];
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && matrix[newRow][newCol] > 0 && matrix[newRow][newCol] > matrix[row][col] + 1){
                    matrix[newRow][newCol] = matrix[row][col] + 1;
                    q.offer(newRow * n + newCol);
                }
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix){
        System.out.println("[");
        for (int i = 0; i < matrix.length; i++){
            System.out.print("  [");
            for (int j = 0; j < matrix[0].length; j++){
                if (j == matrix[0].length - 1){
                    System.out.print(matrix[i][j] + "]");
                }else{
                    System.out.print(matrix[i][j] + ",");
                }
            }
            System.out.println();
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[][] matrix1 = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int[][] res1 = updateMatrix(matrix1);
        printMatrix(res1);
        int[][] matrix2 = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };
        int[][] res2 = updateMatrix(matrix2);
        printMatrix(res2);
    }
}
