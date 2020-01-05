package Backtracking;

/**
 * @author HailongZeng
 * @date 12/18/19 10:54 上午
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * Example:
 *
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */
public class No52_N_QueensII {

    public static int totalNQueens(int n) {
        int[] res = {0};
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++){
            Arrays.fill(board[i], '.');
        }
        helper(res, board, 0);
        return res[0];
    }

    public static void helper(int[] res, char[][] board, int rowIndex){
        if (rowIndex == board.length){
            res[0]++;
            return;
        }
        for (int colIndex = 0; colIndex < board[0].length; colIndex++){
            if (isValid(board, rowIndex, colIndex)){
                board[rowIndex][colIndex] = 'Q';
                helper(res, board, rowIndex+1);
                board[rowIndex][colIndex] = '.';
            }
        }
    }

    public static boolean isValid(char[][] board, int rowIndex, int colIndex){
        for (int i = rowIndex - 1; i >= 0; i--){
            if (board[i][colIndex] == 'Q') return false;
        }
        for (int i = rowIndex - 1, j = colIndex - 1; i >= 0 && j >= 0; i--, j--){
            if (board[i][j] == 'Q') return false;
        }
        for (int i = rowIndex - 1, j = colIndex + 1; i >= 0 && j < board[0].length; i--, j++){
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            System.out.println(totalNQueens(n));
        }
    }
}
