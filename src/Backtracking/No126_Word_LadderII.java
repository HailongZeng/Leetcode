package Backtracking;

/**
 * @author HailongZeng
 * @date 12/18/19 7:53 下午
 */

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class No126_Word_LadderII {

    //First using Bi-BFS to construct a map, key is a String, value is a List, which contains all the string in dict can be transformed from keyString
    //Then do dfs to find all the path
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList){
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        startSet.add(beginWord);
        endSet.add(endWord);
        Map<String, List<String>> map = new HashMap<>();
        boolean reverse = false;
        biBFS(startSet, endSet, dict, map, reverse);
        List<String> list = new ArrayList<>();
        list.add(beginWord);
        dfs(res, list, beginWord, endWord, map);
        return res;
    }

    public static void dfs(List<List<String>> res, List<String> list, String beginWord, String endWord, Map<String, List<String>> map){
        if (beginWord == endWord){
            res.add(new ArrayList(list));
            return;
        }
        if (!map.containsKey(beginWord)) return;

        for (String word: map.get(beginWord)){
            list.add(word);
            dfs(res, list, word, endWord, map);
            list.remove(list.size()-1);
        }
    }

    public static void biBFS(Set<String> startSet, Set<String> endSet, Set<String> dict, Map<String, List<String>> map, boolean reverse){
        if (startSet.size() > endSet.size()){
            biBFS(endSet, startSet, dict, map, !reverse);
            return;
        }
        boolean found = false;
        dict.removeAll(startSet); //Stop hit-->hot-->hit
        Set<String> next = new HashSet<>();
        //BFS
        for (String oldWord: startSet){
            for (int i = 0; i < oldWord.length(); i++){//change one character of oldWord to generate a newWord
                char[] ch = oldWord.toCharArray(); //changed to array
                for (char c = 'a'; c <= 'z'; c++){
                    if (oldWord.charAt(i) == c) continue;
                    ch[i] = c;
                    String newWord = String.valueOf(ch);
                    if (dict.contains(newWord)){
                        if (endSet.contains(newWord)){
                            found = true; //finish bfs
                        }else{
                            next.add(newWord);
                        }
                        String key = reverse ? newWord : oldWord;
                        String val = reverse ? oldWord : newWord;
                        if (!map.containsKey(key)) map.put(key, new ArrayList<>());
                        map.get(key).add(val);
                    }
                }
            }
        }

        if (!found && !next.isEmpty()){
            biBFS(next, endSet, dict, map, reverse);
        }

    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String beginWord = st.nextLine();
            String endWord = st.nextLine();
            String[] s = st.nextLine().split(" ");
            List<String> wordList = new ArrayList<>();
            for (int j = 0; j < s.length; j++){
                wordList.add(s[j]);
            }
            System.out.println(findLadders(beginWord, endWord, wordList));
        }
    }
}
