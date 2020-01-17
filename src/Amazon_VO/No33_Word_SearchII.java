package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/14/20 2:03 下午
 */

import java.util.ArrayList;
import java.util.List;

/**
 *Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 *
 *
 * Example:
 *
 * Input:
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 *
 * Output: ["eat","oath"]
 *
 *
 * Note:
 *
 * All inputs are consist of lowercase letters a-z.
 * The values of words are distinct.
 */
//leetcode212
public class No33_Word_SearchII {

    public static class TrieNode{
        TrieNode[] children;
        String word;
        public TrieNode(){
            children = new TrieNode[26];
            word = null;
        }
    }

    public static TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();
        for (String w: words){
            TrieNode node = root;
            for (char c: w.toCharArray()){
                int n = c - 'a';
                if (node.children[n] == null){
                    node.children[n] = new TrieNode();
                }
                node = node.children[n];
            }
            node.word = w;
        }
        return root;
    }

    public static List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0) return res;
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    public static void dfs(char[][] board, int i, int j, TrieNode node, List<String> res){
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        char c = board[i][j];
        int n = c - 'a';
        if (c == '#' || node.children[n] == null) return;
        node = node.children[n];
        board[i][j] = '#';
        if (node.word != null){
            res.add(node.word);
            node.word = null; //one time search
        }
        dfs(board, i+1, j, node, res);
        dfs(board, i-1, j, node, res);
        dfs(board, i, j+1, node, res);
        dfs(board, i, j-1, node, res);
        board[i][j] = c;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        List<String> res = findWords(board, words);
        System.out.println(res);
    }
}
