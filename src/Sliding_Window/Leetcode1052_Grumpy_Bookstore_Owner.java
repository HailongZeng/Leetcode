package Sliding_Window;

/**
 * @author HailongZeng
 * @date 1/10/20 4:17 下午
 */
//leetcode 1052
/**
 * Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.
 *
 * On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.
 *
 * The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.
 *
 * Return the maximum number of customers that can be satisfied throughout the day.
 *
 *
 *
 * Example 1:
 *
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *
 *
 * Note:
 *
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 */
public class Leetcode1052_Grumpy_Bookstore_Owner {

    //brute force  time:O(n^2)  time:O(1)
    public static int maxSatisfied1(int[] customers, int[] grumpy, int X){
        int n = customers.length;
        int res = 0;
        for (int i = 0; i <= n - X; i++){
            int tmp = 0;
            for (int j = 0; j < i; j++){
                if (grumpy[j] == 0){
                    tmp += customers[j];
                }
            }
            for (int j = i; j < i + X; j++){
                tmp += customers[j];
            }
            for (int j = i + X; j < n; j++){
                if (grumpy[j] == 0){
                    tmp += customers[j];
                }
            }
            res = Math.max(res, tmp);
        }
        return res;
    }

    //sliding window  time:O(n)  space:O(1)
    public static int maxSatisfied2(int[] customers, int[] grumpy, int X){
        int n = customers.length;
        int res = 0;
        int window = 0;
        int best_window = 0;
        for (int i = 0; i < n; i++){
            if (grumpy[i] == 0) res += customers[i];
            else window += grumpy[i] == 1 ? customers[i] : 0;
            if (i >= X){
                window -= grumpy[i - X] == 1 ? customers[i-X] : 0;
            }
            best_window = Math.max(best_window, window);
        }
        return res + best_window;
    }

    public static void main(String[] args) {
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int X = 3;
        int res = maxSatisfied1(customers, grumpy, X);
        System.out.println(res);
    }
}
