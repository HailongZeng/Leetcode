package DP;

/**
 * @author HailongZeng
 * @date 1/11/20 9:40 上午
 */

/**
 * Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
 * Output: [[12,21,16],[27,45,33],[24,39,28]]
 * Example 2:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
 * Output: [[45,45,45],[45,45,45],[45,45,45]]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n, K <= 100
 * 1 <= mat[i][j] <= 100
 */
public class No1314_Matrix_Block_Sum {

    //brute force  time:O(K^2*m*n)  space:O(m*n)
    public static int[][] matrixBlockSum1(int[][] mat, int K){
        int m = mat.length, n = mat[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                for (int ni = i - K; ni <= i + K; ni++){
                    for (int nj = j - K; nj <= j + K; nj++){
                        if (ni >= 0 && ni < m && nj >= 0 && nj < n){
                            res[i][j] += mat[ni][nj];
                        }
                    }
                }
            }
        }
        return res;
    }

    //time:O(K^2*m*n)  space:O(m*n)
    //sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + mat[i][j]  以(0,0)为起始点，(i,j)为终点构成的矩形的元素和(前缀和矩阵)。注意特殊处理边界
    //求子矩阵和的时候，可以直接利用前缀和矩阵。设矩阵的左上角和右下角分别是(x1,y1)以及(x2,y2),则res=sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1]，注意特殊处理x1和y1为0的边界情况
    public static int[][] matrixBlockSum2(int[][] mat, int K){
        int m = mat.length, n = mat[0].length;
        for (int i = 1; i < m; i++){
            mat[i][0] += mat[i-1][0];
        }
        for (int j = 1; j < n; j++){
            mat[0][j] += mat[0][j-1];
        }
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                mat[i][j] += mat[i-1][j] + mat[i][j-1] - mat[i-1][j-1];
            }
        }
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                int x = Math.min(m-1, i+K);
                int y = Math.min(n-1, j+K);
                res[i][j] = mat[x][y];
                if (i-K-1 >= 0) res[i][j] -= mat[i-K-1][y];
                if (j-K-1 >= 0) res[i][j] -= mat[x][j-K-1];
                if (i-K-1 >= 0 && j-K-1 >= 0) res[i][j] += mat[i-K-1][j-K-1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int K = 1;
        int[][] res = matrixBlockSum1(mat, K);
        System.out.print("[");
        for (int i = 0; i < res.length; i++){
            System.out.print("[");
            for (int j = 0; j < res[0].length; j++){
                if (j == res[0].length - 1) System.out.print(res[i][j]);
                else System.out.print(res[i][j] + ",");
            }
            if (i == res.length - 1) System.out.print("]");
            else System.out.print("]" + ",");
        }
        System.out.println("]");
    }
}
