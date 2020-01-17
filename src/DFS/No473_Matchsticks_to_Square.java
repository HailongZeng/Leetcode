package DFS;

/**
 * @author HailongZeng
 * @date 1/8/20 4:13 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 *
 * Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.
 *
 * Example 1:
 *
 * Input: [1,1,2,2,2]
 * Output: true
 *
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * Example 2:
 *
 * Input: [3,3,3,3,4]
 * Output: false
 *
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 * Note:
 *
 * The length sum of the given matchsticks is in the range of 0 to 10^9.
 * The length of the given matchstick array will not exceed 15.
 */
//backtracking, very same with leetcode698
public class No473_Matchsticks_to_Square {

    public static boolean makesquare(int[] nums){
        if (nums.length < 4) return false;
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if (sum % 4 != 0) return false;
        Arrays.sort(nums);
        return dfs(nums, 0, sum / 4, 0, 1, new boolean[nums.length]);
    }

    //time:O(n!)  space:O(n)
    private static boolean dfs(int[] nums, int pos, int target, int tmpSum, int groupId, boolean[] visited) {
        if (groupId == 4) return true;
        if (tmpSum == target) return dfs(nums, 0, target, 0, groupId + 1, visited);
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
            boolean res = makesquare(nums);
            System.out.println(res);
        }
    }

}
