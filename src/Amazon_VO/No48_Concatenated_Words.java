package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/15/20 10:28 上午
 */

import java.util.*;

/**
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 *
 * Example:
 *
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 *  "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * Note:
 *
 * The number of elements of the given array will not exceed 10,000
 * The length sum of elements in the given array will not exceed 600,000.
 * All the input string will only include lower case letters.
 * The returned elements order does not matter.
 */
//leetcode472  --->结合leetcode139
//更优的解法：借助trietree数据结构
public class No48_Concatenated_Words {

    //trietree
    public static class TrieNode {
        TrieNode[] sons;
        boolean isEnd;
        public TrieNode() {
            sons = new TrieNode[26];
        }
    }

    public static List<String> findAllConcatenatedWordsInADict3(String[] words) {
        List<String> res = new ArrayList<String>();
        if (words == null || words.length == 0) {
            return res;
        }
        TrieNode root = new TrieNode();
        for (String word : words) { // construct Trie tree
            if (word.length() == 0) {
                continue;
            }
            addWord(word, root);
        }
        for (String word : words) { // test word is a concatenated word or not
            if (word.length() == 0) {
                continue;
            }
            if (testWord(word.toCharArray(), 0, root, 0)) {
                res.add(word);
            }
        }
        return res;
    }

    public static boolean testWord(char[] chars, int index, TrieNode root, int count) { // count means how many words during the search path
        TrieNode cur = root;
        int n = chars.length;
        for (int i = index; i < n; i++) {
            if (cur.sons[chars[i] - 'a'] == null) {
                return false;
            }
            if (cur.sons[chars[i] - 'a'].isEnd) {
                if (i == n - 1) { // no next word, so test count to get result.
                    return count >= 1;
                }
                if (testWord(chars, i + 1, root, count + 1)) {
                    return true;
                }
            }
            cur = cur.sons[chars[i] - 'a'];
        }
        return false;
    }

    public static void addWord(String str, TrieNode root) {
        char[] chars = str.toCharArray();
        TrieNode cur = root;
        for (char c : chars) {
            if (cur.sons[c - 'a'] == null) {
                cur.sons[c - 'a'] = new TrieNode();
            }
            cur = cur.sons[c - 'a'];
        }
        cur.isEnd = true;
    }

    //dp
    public static List<String> findAllConcatenatedWordsInADict1(String[] words) {
        List<String> res = new ArrayList<>();
        if (words.length < 2) return res;
        Arrays.sort(words, Comparator.comparing(String::length)); //对单词按长度进行排序
        Set<String> set = new HashSet<>(); //每次往set里加当前word作为set的一部分，因为判断某单词是否能够有更小的单词组成，所有set中的元素必须保证比当前word小
        for (String word: words){
            if (canBreak(word, set)){
                res.add(word);
            }
        }
        return res;
    }

    //dp[i]---s.substring(0, i) 可以被break
    //dp[i] = dp[j] && s(j, i) is one word in wordDict
    public static boolean canBreak(String s, Set<String> set){
        if (set.isEmpty()){
            set.add(s);
            return false;
        }
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for (int i = 0; i <= n; i++){
            for (int j = 0; j < i; j++){
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        set.add(s);
        return dp[n];
    }


    //dfs
    public static List<String> findAllConcatenatedWordsInADict2(String[] words) {
        List<String> res = new LinkedList<>();
        // corner case
        if(words == null || words.length == 0) return res;

        // 1. Create HashSet
        Set<String> set = new HashSet<>();
        int minLen = Integer.MAX_VALUE;
        for(String word : words){
            if(word.length() > 0){
                set.add(word);
                minLen = Math.min(minLen, word.length());
            }
        }

        for(String word : words){
            if(canCompose(word, 0, minLen, set)) res.add(word);
        }
        return res;
    }

    public static boolean canCompose(String word, int start, int minLen, Set<String> set){
        for(int i = start + minLen; i <= word.length() - minLen; i++){
            if(set.contains(word.substring(start, i)) && (set.contains(word.substring(i)) || canCompose(word, i, minLen, set))) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        List<String> res = findAllConcatenatedWordsInADict1(words);
        System.out.println(res);
    }
}
