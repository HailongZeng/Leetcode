package Tree;

/**
 * @author HailongZeng
 * @date 1/6/20 1:58 下午
 */

import java.util.Scanner;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *
 * Example:
 *
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class No96_Unique_Binary_Search_Trees {

    //Catalan Number(卡特兰数)   C0 = 1 and C_(n+1) = sum(C_i*C_(n-i)) i = 0,1,2...n
    //通项公式C_(n+1) = C(2n, n) / (n+1)  从2n中选n个的组合数个数，再除以n+1
    //dp[i]表示当有i个数字能组成BST的个数
    //dp[0]=1, dp[1]=dp[0]*dp[0]=1, dp[2]=dp[0]*dp[1]+dp[1]*dp[0], dp[3]=dp[0]*dp[2]+dp[1]*dp[1]+dp[2]*dp[0]
    public static int numTrees1(int n){
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1; //initialization
        for (int i = 2; i <= n; i++){
            for (int j = 0; j < i; j++){
                dp[i] += dp[j] * dp[i-j-1];
            }
        }
        return dp[n];
    }

    //dp[n]=C(2n,n)/(n+1)
    public static int numTrees2(int n){
        long res = 1;
        for (int i = n+1; i <= 2*n; i++){
            res = res * i / (i - n);
        };
        return (int)(res/(n+1));
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            System.out.println(numTrees2(n));
        }
    }

}
