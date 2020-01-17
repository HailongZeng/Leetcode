package DP;

/**
 * @author HailongZeng
 * @date 1/13/20 6:23 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.
 *
 * A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.
 *
 * Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [3,2,4,1], K = 2
 * Output: 20
 * Explanation:
 * We start with [3, 2, 4, 1].
 * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
 * We merge [4, 1] for a cost of 5, and we are left with [5, 5].
 * We merge [5, 5] for a cost of 10, and we are left with [10].
 * The total cost was 20, and this is the minimum possible.
 * Example 2:
 *
 * Input: stones = [3,2,4,1], K = 3
 * Output: -1
 * Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
 * Example 3:
 *
 * Input: stones = [3,5,1,2,6], K = 3
 * Output: 25
 * Explanation:
 * We start with [3, 5, 1, 2, 6].
 * We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
 * We merge [3, 8, 6] for a cost of 17, and we are left with [17].
 * The total cost was 25, and this is the minimum possible.
 *
 *
 * Note:
 *
 * 1 <= stones.length <= 30
 * 2 <= K <= 30
 * 1 <= stones[i] <= 100
 */
//similar problem: LC312 Brust Balloons
//if (n-1) % (K-1) != 0  cannot be merged   there are (n-1)/(K-1) merges
/*
Total cost:  Sum(A[i] * m[i])  m[i] is the number of times the i-th element got merged
Merge cost of range [i,m] ~ [m+1, j]--->sum(A[i],A[i+1],A[i+2]...,A[j])，independent of previous merges
dp[i][j][k]---min cost to merge A[i]~A[j] into k piles
init: dp[i][j][1] = 0  #no cost to merge one into one  dp[i][j][k] = INF
1.dp[i][j][k] = min(dp[i][m][1]+dp[m+1][j][k-1])  i <= m < j,  2 <= k <= K
2.dp[i][j][1] = dp[i][j][K] + sum(A[i]~A[j])
answer:dp[0][n-1][1]  #merge the whole array into one

优化1：in order to merge left part into 1 pile([i,m]), (len-1) % (K-1) == 0，so m++ change to be m += (K - 1)  so, time-->O(n^3*K) change to be O(n^3)
优化2：the number of piles the right part can be merged into can be determined-->(len-1) % (K-1) + 1.
dp[i][j]---represent the min cost to merge A[i]~A[j] to (j-i) % (K-1) + 1 piles
init: dp[i][i] = 0  dp[i][j] = inf
dp[i][j] = min(dp[i][j], dp[i][m] + dp[m+1][j])  # m = i + Z*(K-1)
            + sum(A[i]~A[j])  if (j-i) % (K-1) == 0
Ans: dp[0][n-1]
time: O(n^3)-->O(n^3/K)  space:O(n^2*k)-->O(n^2)
*/
public class No1000_Minimum_Cost_to_Merge_Stones {

    private static final int INF = 0x3f3f3f3f;
    public static int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if ((n-1) % (K-1) != 0) return -1;
        int[] sum = new int[n+1];
        for (int i = 0; i < n; i++){
            sum[i+1] = sum[i] + stones[i];
        }
        int[][] dp = new int[n][n];
        for (int[] row: dp){
            Arrays.fill(row, INF);
        }
        for (int i = 0; i < n; i++){
            dp[i][i] = 0;
        }
        for (int l = 2; l <= n; l++){
            for (int i = 0; i <= n-l; i++){
                int j = i+l-1;
                for (int m = i; m < j; m += K-1){
                    dp[i][j] = Math.min(dp[i][j], dp[i][m] + dp[m+1][j]);
                }
                if ((l-1) % (K-1) == 0){
                    dp[i][j] += sum[j+1]-sum[i];
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] stones = stringToArray(line);
            int K = Integer.parseInt(io.readLine());
            int res = mergeStones(stones, K);
            System.out.println(res);
        }
    }

    public static int[] stringToArray(String input){
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++){
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }
}
