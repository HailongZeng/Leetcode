package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/12/20 9:07 上午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 *
 */
public class No3_Valid_Parentheses {

    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (char c: s.toCharArray()){
            if (c == '(' || c == '[' || c == '{') st.add(c);
            else{
                if (st.isEmpty()) return false;
                else if (c == ')'){
                    if (st.peek() == '(') {
                        st.pop();
                    }else return false;
                }
                else if (c == ']'){
                    if (st.peek() == '['){
                        st.pop();
                    }else return false;
                }
                else if (c == '}'){
                    if (st.peek() == '{'){
                        st.pop();
                    }else return false;
                }
            }
        }
        return st.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            boolean res = isValid(line);
            System.out.println(res);
        }
    }
}
