package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/12/20 7:42 下午
 */

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class No12_Word_Search {

    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        if (word == null || word.length() == 0) return false;
        int m = board.length, n = board[0].length;
        if (m*n < word.length()) return false;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == word.charAt(0)){
                    if (dfs(board, word, i, j, 0)) return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, String word, int r, int c, int k){
        if (k == word.length()) return true;
        if (r >= board.length || r < 0 || c >= board[0].length || c < 0 || board[r][c] != word.charAt(k)) return false;
        board[r][c] ^= 256;
        boolean exist = dfs(board, word, r+1, c, k+1) || dfs(board, word, r, c+1, k+1) || dfs(board, word, r-1, c, k+1) || dfs(board, word, r, c-1, k+1);
        board[r][c] ^= 256;
        return exist;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'},
        };
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        boolean res1 = exist(board, word1);
        boolean res2 = exist(board, word2);
        boolean res3 = exist(board, word3);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}
