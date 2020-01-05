package Amazon;

import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 * Examples:
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class No394_Decode_String {

    public static String decodeString(String s){
        StringBuilder tail = new StringBuilder();
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        int n = s.length();
        for (int i = 0; i < n; i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                int num = c - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))){
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                numStack.push(num);
            } else if (c == '['){
                strStack.push(tail.toString());
                tail = new StringBuilder();
            } else if (c == ']'){
                int repeatTimes = numStack.pop();
                StringBuilder tmp = new StringBuilder(strStack.pop());
                for (int j = 0; j < repeatTimes; j++){
                    tmp.append(tail.toString());
                }
                tail = tmp;
            } else{
                tail.append(c);
            }
        }
        return tail.toString();
    }

    public static void main(String[] args) {
        String s1 = "3[a]2[bc]", s2 = "3[a2[c]]", s3 = "2[abc]3[cd]ef", s4 = "100[leetcode]";
        System.out.println(decodeString(s1));
        System.out.println(decodeString(s2));
        System.out.println(decodeString(s3));
//        System.out.println(decodeString(s4));
    }
}
