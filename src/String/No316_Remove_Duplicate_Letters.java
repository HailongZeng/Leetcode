package String;

import java.util.Scanner;
import java.util.Stack;

/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Example 1:
 *
 * Input: "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: "cbacdcbc"
 * Output: "acdb"
 */
public class No316_Remove_Duplicate_Letters {

    public static String removeDuplicateLetters(String s){
        int[] last = new int[26];
        int[] seen = new int[26];
        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            last[ch - 'a'] = i;  //记录最后出现该字符的位置
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if (seen[ch - 'a']++ > 0) continue;
            while (!stack.isEmpty() && ch < stack.peek() && last[stack.peek() - 'a'] > i){
                seen[stack.pop() - 'a'] = 0;
            }
            stack.push(ch);
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
            String s = st.nextLine();
            System.out.println(removeDuplicateLetters(s));
        }
    }
}
