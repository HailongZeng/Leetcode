package DFS;

import java.util.Scanner;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Explanation:
 *
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
public class No130_Surrounded_Regions {

    /*
    判断边界元素为'O'的时候的连通分量，并把连通分量标记为'G'
     */
    public static void solve(char[][] board){
        if (board == null || board.length == 0 || board[0].length == 0) return;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++){
            dfs(board, i, 0);
            dfs(board, i, n-1);
        }
        for (int i = 0; i < n; i++){
            dfs(board, 0, i);
            dfs(board, m-1, i);
        }
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == 'G') board[i][j] = 'O';
            }
        }
        return;
    }

    public static void dfs(char[][] board, int x, int y){
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'O') return;
        board[x][y] = 'G';
        dfs(board, x-1, y);
        dfs(board, x+1, y);
        dfs(board, x, y-1);
        dfs(board, x, y+1);
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        String[] a = st.nextLine().split(" ");
        int m = Integer.parseInt(a[0]);
        int n = Integer.parseInt(a[1]);
        char[][] board = new char[m][n];
        for (int i = 0; i < m; i++){
            String[] tmp = st.nextLine().split(" ");
            for (int j = 0; j < n; j++){
                board[i][j] = tmp[j].charAt(0);
            }
        }
        solve(board);
        for (int i = 0; i < m; i++){
            System.out.println();
            for (int j = 0; j < n; j++){
                System.out.print(board[i][j] + " ");
            }
        }
    }
}
