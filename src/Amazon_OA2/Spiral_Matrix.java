package Amazon_OA2;

/**
 * LeetCode 59
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 *
 * Example:
 *
 * Input: 3
 * Output:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */
public class Spiral_Matrix {

    public static int[][] generateMatrix(int n){
        int[][] res = new int[n][n];
        int c = 1;
        helper(res, 0, n - 1, c);
        return res;
    }

    public static void helper(int[][] res, int l, int r, int c){
        if (l > r) return;
        if (l == r) {
            res[l][r] = c++;
            return;
        }
        int row = l;
        int col = l;
        for (; col < r; col++) res[row][col] = c++;
        for (; row < r; row++) res[row][col] = c++;
        for (; col > l; col--) res[row][col] = c++;
        for (; row > l; row--) res[row][col] = c++;
        helper(res, l + 1, r - 1, c);
    }

    public static void printArray(int[][] res){
        int m = res.length;
        int n = res[0].length;
        System.out.println("[");
        for (int i = 0; i < m; i++){
            System.out.print("[");
            for (int j = 0; j < n; j++){
                if (j == n - 1) System.out.print(res[i][j]);
                else System.out.print(res[i][j] + ",");
            }
            if (i == m - 1) System.out.println("]");
            else System.out.println("]" + ",");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[][] res = generateMatrix(4);
        printArray(res);
    }
}
