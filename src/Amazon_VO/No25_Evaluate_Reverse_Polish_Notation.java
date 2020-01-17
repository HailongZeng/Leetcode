package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/13/20 9:06 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * Note:
 *
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 * Example 1:
 *
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 *
 * Input: ["4", "13", "5", "/", "+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 *
 * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * Output: 22
 * Explanation:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
//leetcode150
public class No25_Evaluate_Reverse_Polish_Notation {

    public static int evalRPN(String[] tokens) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> opeStack = new Stack<>();
        for (String s: tokens){
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                if (numStack.size() >= 2){
                    int x = numStack.pop();
                    int y = numStack.pop();
                    int ans = operation(x, y, s);
                    numStack.push(ans);
                }else{
                    opeStack.push(s);
                }
            }else{
                numStack.push(Integer.parseInt(s));
                if (numStack.size() >= 2){
                    if (!opeStack.isEmpty()){
                        String tmp = opeStack.pop();
                        int x = numStack.pop();
                        int y = numStack.pop();
                        int ans = operation(x, y, tmp);
                        numStack.push(ans);
                    }
                }
            }
        }
        return numStack.pop();
    }

    public static int operation(int x, int y, String s){
        if (s.equals("+")) return y+x;
        else if (s.equals("-")) return y-x;
        else if (s.equals("*")) return y*x;
        else if (s.equals("/")) return y/x;
        throw new RuntimeException("operation is wrong");
    }

    //the format of line: [10,6,9,3,+,-11,*,/,*,17,5,+]
    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            line = line.trim();
            line = line.substring(1, line.length() - 1);
            String[] tokens = line.split(",");
            int res = evalRPN(tokens);
            System.out.println(res);
        }
    }
}
