package Array;

/**
 * @author HailongZeng
 * @date 12/17/19 11:03 上午
 */

import java.util.Scanner;

/**
 * On an 8 x 8 chessboard, there is one white rook.  There also may be empty squares, white bishops, and black pawns.  These are given as characters 'R', '.', 'B', and 'p' respectively. Uppercase characters represent white pieces, and lowercase characters represent black pieces.
 *
 * The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south), then moves in that direction until it chooses to stop, reaches the edge of the board, or captures an opposite colored pawn by moving to the same square it occupies.  Also, rooks cannot move into the same square as other friendly bishops.
 *
 * Return the number of pawns the rook can capture in one move.
 *
 * Example 1:
 * Input: [[".",".",".",".",".",".",".","."],
 *         [".",".",".","p",".",".",".","."],
 *         [".",".",".","R",".",".",".","p"],
 *         [".",".",".",".",".",".",".","."],
 *         [".",".",".",".",".",".",".","."],
 *         [".",".",".","p",".",".",".","."],
 *         [".",".",".",".",".",".",".","."],
 *         [".",".",".",".",".",".",".","."]]
 * Output: 3
 * Explanation:
 * In this example the rook is able to capture all the pawns.
 *
 * Example 2:
 * Input: [[".",".",".",".",".",".",".","."],
 *         [".","p","p","p","p","p",".","."],
 *         [".","p","p","B","p","p",".","."],
 *         [".","p","B","R","B","p",".","."],
 *         [".","p","p","B","p","p",".","."],
 *         [".","p","p","p","p","p",".","."],
 *         [".",".",".",".",".",".",".","."],
 *         [".",".",".",".",".",".",".","."]]
 * Output: 0
 * Explanation:
 * Bishops are blocking the rook to capture any pawn.
 *
 * Example 3:
 *
 * Input: [[".",".",".",".",".",".",".","."],
 *         [".",".",".","p",".",".",".","."],
 *         [".",".",".","p",".",".",".","."],
 *         ["p","p",".","R",".","p","B","."],
 *         [".",".",".",".",".",".",".","."],
 *         [".",".",".","B",".",".",".","."],
 *         [".",".",".","p",".",".",".","."],
 *         [".",".",".",".",".",".",".","."]]
 * Output: 3
 * Explanation:
 * The rook can capture the pawns at positions b5, d6 and f5.
 *
 * Note:
 *
 * board.length == board[i].length == 8
 * board[i][j] is either 'R', '.', 'B', or 'p'
 * There is exactly one cell with board[i][j] == 'R'
 */
public class No999_Available_Captures_for_Rook {

    public static int numRookCaptures(char[][] board){
        int x = 0;
        int y = 0;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == 'R') {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        int res = 0;
        int[] dirs = {1, 0, -1, 0, 1};
        for (int i = 0; i < 4; i++){
            int dx = dirs[i];
            int dy = dirs[i+1];
            int tmpx = x;
            int tmpy = y;
            while (tmpx >= 0 && tmpx < board.length && tmpy >= 0 && tmpy < board[0].length){
                if (board[tmpx][tmpy] == 'p') {
                    res += 1;
                    break;
                }
                if (board[tmpx][tmpy] == 'B') break;
                tmpx += dx;
                tmpy += dy;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int numOfCase = Integer.parseInt(st.nextLine());
        for (int i = 0; i < numOfCase; i++){
            char[][] board = new char[8][8];
            for (int j = 0; j < 8; j++){
                String[] a = st.nextLine().split("");
                for (int k = 0; k < 8; k++){
                    board[j][k] = a[k].charAt(0);
                }
            }
            System.out.println(numRookCaptures(board));
        }
    }
}
