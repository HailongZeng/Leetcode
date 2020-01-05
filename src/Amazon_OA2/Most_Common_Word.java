package Amazon_OA2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * LeetCode 819
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
 *
 * Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.
 *
 *
 *
 * Example:
 *
 * Input:
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * Output: "ball"
 * Explanation:
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 * Note that words in the paragraph are not case sensitive,
 * that punctuation is ignored (even if adjacent to words, such as "ball,"),
 * and that "hit" isn't the answer even though it occurs more because it is banned.
 *
 *
 * Note:
 *
 * 1 <= paragraph.length <= 1000.
 * 0 <= banned.length <= 100.
 * 1 <= banned[i].length <= 10.
 * The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
 * paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 * There are no hyphens or hyphenated words.
 * Words only consist of letters, never apostrophes or other punctuation symbols.
 */
public class Most_Common_Word {

    public static String mostCommonWord(String paragraph, String[] banned){
        paragraph += ".";
        HashSet<String> s = new HashSet<>();
        for (String b: banned) s.add(b);
        HashMap<String, Integer> count = new HashMap<>();
        StringBuilder word = new StringBuilder();
        int resfreq = 0;
        String res = "";
        for (char c: paragraph.toCharArray()){
            if (Character.isLetter(c)){
                word.append(Character.toLowerCase(c));
            } else if (word.length() > 0){
                String finalWord = word.toString();
                if (!s.contains(finalWord)){
                    count.put(finalWord, count.getOrDefault(finalWord, 0) + 1);
                    if (count.get(finalWord) > resfreq){
                        res = finalWord;
                        resfreq = count.get(finalWord);
                    }
                }
                word = new StringBuilder();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};
        System.out.println(mostCommonWord(paragraph, banned));
    }
}
