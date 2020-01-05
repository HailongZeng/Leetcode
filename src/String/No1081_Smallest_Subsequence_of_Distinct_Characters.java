package String;

import java.util.*;

/**
 * Return the lexicographically smallest subsequence of text that contains all the distinct characters of text exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: "cdadabcc"
 * Output: "adbc"
 * Example 2:
 *
 * Input: "abcd"
 * Output: "abcd"
 * Example 3:
 *
 * Input: "ecbacba"
 * Output: "eacb"
 * Example 4:
 *
 * Input: "leetcode"
 * Output: "letcod"
 *
 *
 * Note:
 *
 * 1 <= text.length <= 1000
 * text consists of lowercase English letters.
 */
//单调栈
public class No1081_Smallest_Subsequence_of_Distinct_Characters {

    public static String smallestSubsequence(String text){
        HashMap<Character, Integer> count = new HashMap<>();
        for (char c: text.toCharArray()){
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        Set<Character> used = new HashSet<>();
        Stack<Character> stack = new Stack<>();
        for (char ch: text.toCharArray()){
            if (used.contains(ch)){
                count.put(ch, count.get(ch) - 1);
                continue;
            }
            while (stack.size() > 0 && ch < stack.peek() && count.get(stack.peek()) > 1){
                char peek = stack.pop();
                count.put(peek, count.get(peek) - 1);
                used.remove(peek);
            }
            stack.push(ch);
            used.add(ch);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            String text = st.nextLine();
            System.out.println(smallestSubsequence(text));
        }
    }
}
