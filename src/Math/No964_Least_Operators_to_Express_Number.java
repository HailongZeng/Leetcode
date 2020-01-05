package Math;

/**
 * @author HailongZeng
 * @date 1/4/20 10:25 上午
 */

import java.util.*;

/**
 * Given a single positive integer x, we will write an expression of the form x (op1) x (op2) x (op3) x ... where each operator op1, op2, etc. is either addition, subtraction, multiplication, or division (+, -, *, or /).  For example, with x = 3, we might write 3 * 3 / 3 + 3 - 3 which is a value of 3.
 *
 * When writing such an expression, we adhere to the following conventions:
 *
 * The division operator (/) returns rational numbers.
 * There are no parentheses placed anywhere.
 * We use the usual order of operations: multiplication and division happens before addition and subtraction.
 * It's not allowed to use the unary negation operator (-).  For example, "x - x" is a valid expression as it only uses subtraction, but "-x + x" is not because it uses negation.
 * We would like to write an expression with the least number of operators such that the expression equals the given target.  Return the least number of operators used.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 3, target = 19
 * Output: 5
 * Explanation: 3 * 3 + 3 * 3 + 3 / 3.  The expression contains 5 operations.
 * Example 2:
 *
 * Input: x = 5, target = 501
 * Output: 8
 * Explanation: 5 * 5 * 5 * 5 - 5 * 5 * 5 + 5 / 5.  The expression contains 8 operations.
 * Example 3:
 *
 * Input: x = 100, target = 100000000
 * Output: 3
 * Explanation: 100 * 100 * 100 * 100.  The expression contains 3 operations.
 *
 *
 * Note:
 *
 * 2 <= x <= 100
 * 1 <= target <= 2 * 10^8
 */
public class No964_Least_Operators_to_Express_Number {

    //space:O(logn)   time:O(logn)   n=O(x*log_x(t))
    private static Map<Integer, Integer> map = new HashMap<>();
    public static int leastOpsExpressTarget1(int x, int target){
        return helper(x, target);
    }

    public static int helper(int x, int t){
        if (t == 0) return 0;
        if (t < x) return Math.min(2*t-1, 2*(x-t));  //1.x/x+x/x+..+x/x  2*t-1   2.x-(x/x)*(x-t)  1+2*(x-t)-1=2*(x-t)
        if (map.containsKey(t)) return map.get(t);
        int k = (int)Math.floor(Math.log(t)/Math.log(x));
        int p = (int)Math.pow(x, k);
        if (t == p) return k-1; //t=x*x*x...*x   x^(k)
        int res = helper(x, t-p) + k;//t-p<t   t=x^(k)+(t-p)
        int l = p*x-t;
        if (l < t){
            res = Math.min(res, helper(x, l) + k + 1);  //p*x-t<t  x^(k+1)-l=t
        }
        map.put(t, res);
        return res;
    }

    //Dijkstra  time:O(nlogn)  space:O(nlog(n))   n=x*log(t)/log(x)
    public static int leastOpsExpressTarget2(int x, int target){
        //int[] = {从target到当前num所需最小操作数,当前num}
        Queue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                return o1[0] - o2[0];
            }
        });
        pq.offer(new int[]{0,target});
        Set<Integer> seen = new HashSet<>();
        while (!pq.isEmpty()){
            int[] t = pq.poll();
            int ops = t[0];
            int num = t[1];
            if (num == 0) return ops - 1;
            if (seen.contains(num)) continue;
            seen.add(num);
            int n = (int)Math.floor(Math.log10(num) / Math.log10(x));
            int l = num - (int)Math.pow(x, n);
            int[] lArray = {ops+(n == 0 ? 2 : n), l};
            pq.offer(lArray);
            int r = (int)Math.pow(x, n+1) - num;
            int[] rArray = {ops + n + 1, r};
            pq.offer(rArray);
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int x = st.nextInt();
            int target = st.nextInt();
            System.out.println(leastOpsExpressTarget2(x, target));
        }
    }
}
