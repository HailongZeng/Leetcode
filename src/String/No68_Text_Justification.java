package String;

/**
 * @author HailongZeng
 * @date 12/18/19 6:56 下午
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * Note:
 *
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 * Example 1:
 *
 * Input:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * Example 2:
 *
 * Input:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be",
 *              because the last line must be left-justified instead of fully-justified.
 *              Note that the second line is also left-justified becase it contains only one word.
 * Example 3:
 *
 * Input:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 */
public class No68_Text_Justification {

    //Rules:
    //1.Greedy as many words as possible
    //2.Not the last line: spaces between words should be distributed as evenly as possible, as left as possible if not even
    //3.The last line/Only 1 word in the line: no extra space between words

    //Step0: Decide how many words could be put in the same line
    //Step1: Modify the spaces between words: Not the last line//The last line、only one word in a line

    public static List<String> fullJustify(String[] words, int maxWidth){
        List<String> res = new ArrayList<>();
        int n = words.length;
        int index = 0;
        while (index < n){
            int totalChars = words[index].length();//since each line has at least one word, we need to initialize the totalChars as the word length first
            int last = index + 1; //explore the next word
            while (last < n){
                if (totalChars + words[last].length() + 1 > maxWidth) break;
                totalChars += 1 + words[last].length();
                last++;
            }
            int gaps = last - index - 1;
            StringBuilder sb = new StringBuilder();
            if (last == n || gaps == 0) {//the last line or only one word in this line
                for (int i = index; i < last; i++){
                    sb.append(words[i]);
                    sb.append(' ');
                }
                sb.deleteCharAt(sb.length()-1); //delete the last space
                while (sb.length() < maxWidth){
                    sb.append(' ');
                }
            } else{
                int spaces = (maxWidth - totalChars) / gaps;
                int rest = (maxWidth - totalChars) % gaps;
                for (int i = index; i < last - 1; i++){
                    sb.append(words[i]);
                    sb.append(' ');
                    for (int j = 0; j < spaces + (i - index < rest ? 1 : 0); j++){
                        sb.append(' ');
                    }
                }
                sb.append(words[last - 1]);
            }
            res.add(sb.toString());
            index = last; //update the words index processing
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String[] words = st.nextLine().split(" ");
            int maxWidth = Integer.parseInt(st.nextLine());
            List<String> res = fullJustify(words, maxWidth);
            System.out.println(res);
        }
    }
}
