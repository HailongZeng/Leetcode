package Amazon;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
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
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class No127_Word_Ladder {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList){
        Set<String> dict = new HashSet<String>(wordList);
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        if (!dict.contains(endWord)) return 0;
        startSet.add(beginWord);
        endSet.add(endWord);
        int res = 2;
        while(!startSet.isEmpty()){
            HashSet<String> next = new HashSet<String>();
            dict.removeAll(startSet);
            for (String oldWord: startSet){
                for (int i = 0; i < oldWord.length(); i++){
                    char[] ch = oldWord.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++){
                        if (c == oldWord.charAt(i)) continue;
                        ch[i] = c;
                        String newWord = new String(ch);
                        if (endSet.contains(newWord)) return res;
                        if (dict.contains(newWord)){
                            next.add(newWord);
                        }
                    }
                }
            }
            startSet = (next.size() < endSet.size()) ? next : endSet; //switch to small one traverse from other end
            endSet = (startSet == next) ? endSet : next;
            res++;
        }
        return 0;
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
            System.out.println(ladderLength(beginWord, endWord, wordList));
        }
    }
}
