package Amazon_OA2;

/**
 * Given a matrix with r rows and c columns, find the maximum score of a path starting at [0, 0] and ending at [r-1, c-1]. The score of a path is the minimum value in that path. For example, the score of the path 8 → 4 → 5 → 9 is 4.
 *
 * Don't include the first or final entry. You can only move either down or right at any point in time.
 *
 * Example 1:
 *
 * Input:
 * [[5, 1],
 *  [4, 5]]
 *
 * Output: 4
 * Explanation:
 * Possible paths:
 * 5 → 1 → 5 => min value is 1
 * 5 → 4 → 5 => min value is 4
 * Return the max value among minimum values => max(4, 1) = 4.
 * Example 2:
 *
 * Input:
 * [[1, 2, 3]
 *  [4, 5, 1]]
 *
 * Output: 4
 * Explanation:
 * Possible paths:
 * 1-> 2 -> 3 -> 1
 * 1-> 2 -> 5 -> 1
 * 1-> 4 -> 5 -> 1
 * So min of all the paths = [2, 2, 4]. Note that we don't include the first and final entry.
 * Return the max of that, so 4.
 */
public class Path_With_Maximum_Score {

    public static int maxScore(int[][] matrix){
        if (matrix.length == 0 || matrix[0].length == 0 || matrix == null) return 0;
        int r = matrix.length;
        int c = matrix[0].length;
        int[][] dp = new int[r][c];  // the max score from [0][0] to [i][j]
        //dp[i][j] = Math.max(Math.min(dp[i-1][j], matrix[i][j]), Math.min(dp[i][j-1], matrix[i][j]))
        dp[0][0] = Integer.MAX_VALUE;
        for (int i = 1; i < r; i++) dp[i][0] = Math.min(dp[i - 1][0], matrix[i][0]);
        for (int i = 1; i < c; i++) dp[0][i] = Math.min(dp[0][i - 1], matrix[0][i]);
        for (int i = 1; i < r; i++){
            for (int j = 1; j < c; j++){
                if (i == r - 1 && j == c - 1) dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                else{
                    int score1 = Math.min(dp[i][j - 1], matrix[i][j]);  //left
                    int score2 = Math.min(dp[i-1][j], matrix[i][j]); //up
                    dp[i][j] = Math.max(score1, score2);
                }
            }
        }
        return dp[r-1][c-1];
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{5, 1}, {4, 5}};
        int[][] matrix2 = {{1, 2, 3}, {4, 5, 1}};
        System.out.println(maxScore(matrix1));
        System.out.println(maxScore(matrix2));
    }
}
