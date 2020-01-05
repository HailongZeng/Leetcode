package Backtracking;

/**
 * @author HailongZeng
 * @date 12/18/19 11:07 上午
 */

import java.util.Scanner;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 *
 *    5 3 * * 7 * * * *
 *    6 * * 1 9 5 * * *
 *    * 9 8 * * * * 6 *
 *    8 * * * 6 * * * 3
 *    4 * * 8 * 3 * * 1
 *    7 * * * 2 * * * 6
 *    * 6 * * * * 2 8 *
 *    * * * 4 1 9 * * 5
 *    * * * * 8 * * 7 9
 * A sudoku puzzle...
 *    5 3 4 6 7 8 9 1 2
 *    6 7 2 1 9 5 3 4 8
 *    1 9 8 3 4 2 5 6 7
 *    8 5 9 7 6 1 4 2 3
 *    4 2 6 8 5 3 7 9 1
 *    7 1 3 9 2 4 8 5 6
 *    9 6 1 5 3 7 2 8 4
 *    2 8 7 4 1 9 6 3 5
 *    3 4 5 2 8 6 1 7 9
 * ...and its solution numbers marked in red.
 *
 * Note:
 *
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 */
public class No37_Sudoku_Solver {

    public static void solveSudoku(char[][] board){
        helper(board, 0, 0);
    }

    public static boolean helper(char[][] board, int rowIndex, int colIndex){
        if (rowIndex == 9) return true;
        if (colIndex >= 9) return helper(board, rowIndex+1, 0);
        if (board[rowIndex][colIndex] != '.') return helper(board, rowIndex, colIndex);
        for (char ch = '1'; ch <= '9'; ch++){
            if (!isValid(board, rowIndex, colIndex, ch)) continue;
            board[rowIndex][colIndex] = ch;
            if (helper(board, rowIndex, colIndex+1)) return true;
            board[rowIndex][colIndex] = '.';
        }
        return false;
    }

    public static boolean isValid(char[][] board, int rowIndex, int colIndex, char ch){
        for (int i = 0; i < 9; i++){
            if (board[rowIndex][i] == ch) return false;
            if (board[i][colIndex] == ch) return false;
            if (board[3*(rowIndex/3)+i/3][3*(colIndex/3)+i%3] == ch) return false;
        }
        return true;
    }

    public static void printArray(char[][] board){
        System.out.println("[");
        for (int i = 0; i < board.length; i++){
            System.out.print("[");
            for (int j = 0; j < board[0].length; j++){
                if (j == board[0].length - 1) System.out.println(board[i][j] + "]");
                else System.out.print(board[i][j] + ",");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++){
            String[] tmp = st.nextLine().split("");
            for (int j = 0; j < 9; j++){
                board[i][j] = tmp[j].charAt(0);
            }
        }
        printArray(board);
        solveSudoku(board);
    }
}
