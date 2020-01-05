package Array;

import java.util.Scanner;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * Example:
 *
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */
public class No209_Minimum_Size_Subarray_Sum {

    //O(n) two-pointer
    public static int minSubArrayLen1(int s, int[] nums){
        int j = -1; //left pointer  (j, i]
        int sum = 0;
        int res = nums.length + 1;
        for (int i = 0; i < nums.length; i++){ //right pointer
            sum += nums[i];
            while (sum >= s && j < i) {
                res = Math.min(res, i - j);
                j++;
                sum -= nums[j];
            }
        }
        return res == nums.length + 1 ? 0 : res;
    }

    //O(n logn)
    public static int minSubArrayLen2(int s, int[] nums){
        int n = nums.length;
        if (n == 0) return 0;
        int res = n + 1;

        //build prefix sum array
        int[] prefix = new int[n + 1];
        for (int i = 1; i < n + 1; i++){
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }

        //for each iteration i, we try to use binary search to find prefix[i] + s, which means the sum of subarray between i and left will be more than s
        for (int i = 0; i < n; i++){
            int left = i + 1;
            int right = n;
            int target = prefix[i] + s;
            while (left <= right){
                int mid = left + (right - left) / 2;
                if (prefix[mid] < target) {
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
            if (left > n) break;
            res = Math.min(res, left - i);
        }
        return res == n + 1 ? 0 : res;
    }

    public static void main(String[] args){
        Scanner st = new Scanner(System.in);
        int s = st.nextInt();
        int N = st.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++){
            nums[i] = st.nextInt();
        }
        System.out.println(minSubArrayLen1(s, nums));
    }
}
