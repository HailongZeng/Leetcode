package DP;

/**
 * @author HailongZeng
 * @date 1/8/20 8:13 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Note:
 *
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 *
 *
 * Example 1:
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 *
 * Example 2:
 *
 * Input: [1, 2, 3, 5]
 *
 * Output: false
 *
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
//same with leetcode698 and leetcode473
public class No416_Partition_Equal_Subset_Sum {

    //dfs, O(n!)
    public static boolean canPartition1(int[] nums) {
        if (nums.length < 2) return false;
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        Arrays.sort(nums);
        int n = nums.length;
        int target = sum / 2;
        if (nums[n-1] > target) return false;
        return dfs(nums, 0, target, 0, 0, new boolean[n]);
    }

    public static boolean dfs(int[] nums, int pos, int target, int tmpSum, int groupId, boolean[] visited){
        if (groupId == 2) return true;
        if (tmpSum == target) return dfs(nums, 0, target, 0, groupId+1, visited);
        if (tmpSum > target) return false;
        for (int i = pos; i < nums.length; i++){
            if (visited[i]) continue;
            if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            visited[i] = true;
            if (dfs(nums, i+1, target, tmpSum+nums[i], groupId, visited)) return true;
            visited[i] = false;
        }
        return false;
    }

    //dp
    //time:O(N*2^N)   space:O(2^N)
    public static boolean canPartition2(int[] nums) {
        if (nums.length < 2) return false;
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        int n = nums.length;
        boolean[] dp = new boolean[1<<n];
        dp[0] = true;
        int[] total = new int[1<<n];
        for (int state = 0; state < (1<<n); state++){
            if (!dp[state]) continue;
            for (int i = 0; i < n; i++) {
                int future = state | (1 << i);
                if (state != future && !dp[future]){
                    if (nums[i] <= target - (total[state] % target)) {
                        dp[future] = true;
                        total[future] = total[state] + nums[i];
                    } else {
                        break;
                    }
                }
            }
        }
        return dp[(1<<n)-1];
    }

    //dp
    //time:O(n*sum/2)  space:O(sum/2)
    public static boolean canPartition3(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if (sum % 2 != 0) return false;
        int target = sum >> 1;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num: nums){
            for (int i = target; i >= num; i--){
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }

    private static int[] stringToArray(String input) {
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

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] nums = stringToArray(line);
            boolean res = canPartition1(nums);
            System.out.println(res);
        }
    }
}
