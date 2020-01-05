package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/4/20 10:51 下午
 */

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * Example 2:
 *
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class No140_Word_BreakII {

    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        Map<Integer, List<String>> map = new HashMap<>();
        return dfs(s, 0, words, map);
    }

    public static List<String> dfs(String s, int start, Set<String> words, Map<Integer, List<String>> map){
        if (map.containsKey(start)) return map.get(start);
        List<String> res = new ArrayList<>();
        if (start == s.length()){
            res.add("");
            return res;
        }
        String cur = s.substring(start);
        for (String word: words){
            if (cur.startsWith(word)){
                List<String> subList = dfs(s, start+word.length(), words, map);
                for (String sub: subList){
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        map.put(start, res);
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String s = st.nextLine();
            String[] words = st.nextLine().split(",");
            List<String> wordDict = Arrays.asList(words);
            System.out.println(wordBreak(s, wordDict));
        }
    }
}
