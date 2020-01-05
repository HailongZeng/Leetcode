package Amazon_OA2;

/**
 * LeetCode 240
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example:
 *
 * Consider the following matrix:
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 *
 * Given target = 20, return false.
 */
public class Search_a_2D_MatrixII {

    public static boolean searchMatrix1(int[][] matrix, int target){
        if (matrix.length == 0 || matrix[0].length == 0 || matrix == null) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++){
            int left = 0;
            int right = n - 1;
            if (matrix[i][right] < target) continue;
            while (left <= right){
                int mid = left + (right - left) / 2;
                if (matrix[i][mid] == target) return true;
                else if (matrix[i][mid] > target) right = mid - 1;
                else left = mid + 1;
            }
        }
        return false;
    }

    public static boolean searchMatrix2(int[][] matrix, int target){
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int row = m - 1;
        int col = 0;
        while (row >= 0 && col < n){
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) row--;
            else col++;
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target1 = 5;
        int target2 = 20;
        System.out.println(searchMatrix1(matrix, target1));
        System.out.println(searchMatrix1(matrix, target2));
        System.out.println(searchMatrix2(matrix, target1));
        System.out.println(searchMatrix2(matrix, target2));
    }
}
