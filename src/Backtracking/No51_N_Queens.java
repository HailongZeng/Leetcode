package Backtracking;

/**
 * @author HailongZeng
 * @date 12/17/19 2:10 下午
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 * Output: [
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
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
public class No51_N_Queens {

    public static List<List<String>> solveNQueens(int n){
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        //initialize the board
        for (int i = 0; i < board.length; i++){
            Arrays.fill(board[i], '.');
        }
        helper(res, board, 0);
        return res;
    }

    public static void helper(List<List<String>> res, char[][] board, int rowIndex){
        if (rowIndex == board.length){
            res.add(generate(board));
            return;
        }
        for (int colIndex = 0; colIndex < board[0].length; colIndex++){
            if (isValid(board, rowIndex, colIndex)){
                board[rowIndex][colIndex] = 'Q';
                helper(res, board, rowIndex+1);
                board[rowIndex][colIndex] = '.';
            }
        }
        return;
    }

    public static boolean isValid(char[][] board, int rowIndex, int colIndex){
        //since we checked row by row, we just need to check the row before rowIndex here
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

    public static List<String> generate(char[][] board){
        List<String> cur = new ArrayList<>();
        for (int i = 0; i < board.length; i++){
            cur.add(String.valueOf(board[i]));
        }
        return cur;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            System.out.println(solveNQueens(n));
        }
    }
}
