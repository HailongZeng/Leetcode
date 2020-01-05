package Parentheses;

/**
 * @author HailongZeng
 * @date 12/21/19 7:35 下午
 */

import java.util.*;

/**
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
 *
 * Example 1:
 *
 * Input: "2-1-1"
 * Output: [0, 2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Example 2:
 *
 * Input: "2*3-4*5"
 * Output: [-34, -14, -10, -10, 10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 */
public class No241_Different_Ways_to_Add_Parentheses {

    //Time: O(2^n)  space: O(2^n)
    private static Map<String, List<Integer>> memo = new HashMap<String, List<Integer>>();
    public static List<Integer> diffWaysToCompute(String input){
        List<Integer> res = new ArrayList<>();
        if (memo.containsKey(input)) return memo.get(input);
        for (int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*'){
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i+1));
                for (int l: left){
                    for (int r: right){
                        if (ch == '+') res.add(l+r);
                        else if (ch == '-') res.add(l-r);
                        else res.add(l*r);
                    }
                }
            }
        }
        if (res.size() == 0) res.add(Integer.parseInt(input)); //only a number
        memo.put(input, res);
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++) {
            String input = st.nextLine();
            System.out.println(diffWaysToCompute(input));
        }
    }
}
