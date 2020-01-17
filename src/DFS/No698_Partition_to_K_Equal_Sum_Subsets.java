package DFS;

/**
 * @author HailongZeng
 * @date 1/8/20 7:37 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 *
 * Note:
 *
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 */
public class No698_Partition_to_K_Equal_Sum_Subsets {

    //dfs and backtracking
    //time:O(k^(N-k)k!), where N is the length of nums, and k is as given. As we skip additional zeros in groups, naively we will make O(k!) calls to search, and an additional O(k^(N-k)) calls after every element of groups is nonzero
    public static boolean canPartitionKSubsets1(int[] nums, int k){
        if (nums.length < k) return false;
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) return false;
        int target = sum / k;
        Arrays.sort(nums);
        int row = nums.length - 1;
        if (nums[row] > target) return false; //pruning
        while (row >= 0 && nums[row] == target){//pruning again
            row--;
            k--;
        }
        return search(new int[k], row, nums, target);
    }

    private static boolean search(int[] groups, int row, int[] nums, int target) {
        if (row < 0) return true;
        int v = nums[row--];
        for (int i = 0; i < groups.length; i++){
            if (groups[i] + v <= target){
                groups[i] += v;
                if (search(groups, row, nums, target)) return true;
                groups[i] -= v;
            }
            if (groups[i] == 0) break;
        }
        return false;
    }

    public static boolean canPartitionKSubsets2(int[] nums, int k) {//same with leetcode473
        if (nums.length < k) return false;
        int sum = 0;
        for (int num: nums){
            sum += num;
        }
        if (sum % k != 0) return false;
        Arrays.sort(nums);
        return dfs(nums, 0, sum / k, 0, 1, k, new boolean[nums.length]);
    }

    public static boolean dfs(int[] nums, int pos, int target, int tmpSum, int groupId, int k, boolean[] visited){
        if (groupId == k) return true;
        if (tmpSum == target) return dfs(nums, 0, target, 0, groupId+1, k, visited);
        if (tmpSum > target) return false;
        for (int i = pos; i < nums.length; i++){
            if (visited[i]) continue;
            if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            visited[i] = true;
            if (dfs(nums, i+1, target, tmpSum+nums[i], groupId, k, visited)) return true;
            visited[i] = false;
        }
        return false;
    }

    //time:O(N*2^N)  space:O(2^N)
    public static boolean canParitionKSubsets3(int[] nums, int k){
        if (nums.length < k) return false;
        int N = nums.length;
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        int target = sum / k;
        if (sum % k > 0 || nums[N - 1] > target) return false;

        boolean[] dp = new boolean[1 << N];
        dp[0] = true;
        int[] total = new int[1 << N];

        for (int state = 0; state < (1 << N); state++) {//2^N states
            if (!dp[state]) continue;
            for (int i = 0; i < N; i++) {
                int future = state | (1 << i);  //set the i-th bit
                if (state != future && !dp[future]) {//explore the state
                    if (nums[i] <= target - (total[state] % target)) {
                        dp[future] = true;
                        total[future] = total[state] + nums[i];
                    } else {
                        break;
                    }
                }
            }
        }
        return dp[(1 << N) - 1];
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
            line = io.readLine();
            int k = Integer.parseInt(line);
            boolean res = canPartitionKSubsets2(nums, k);
            System.out.println(res);
        }
    }
}
