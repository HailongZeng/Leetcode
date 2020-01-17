package Array;

/**
 * @author HailongZeng
 * @date 1/11/20 9:11 上午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * We are given a list nums of integers representing a list compressed with run-length encoding.
 *
 * Consider each adjacent pair of elements [a, b] = [nums[2*i], nums[2*i+1]] (with i >= 0).  For each such pair, there are a elements with value b in the decompressed list.
 *
 * Return the decompressed list.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [2,4,4,4]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 100
 * nums.length % 2 == 0
 * 1 <= nums[i] <= 100
 */
public class No1313_Decompress_Run_Length_Encoded_List {

    public static int[] decompressRLElist(int[] nums){
        int length = 0;
        for (int i = 0; i < nums.length; i += 2) length += nums[i];
        int[] res = new int[length];
        int idx = 0;
        for (int i = 0; i < nums.length; i += 2){//a
            for (int j = 0; j < nums[i]; j++){
                res[idx++] = nums[i+1];  //nums[i+1]--->b
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] nums = stringToArray(line);
            int[] res = decompressRLElist(nums);
            String s = arrayToString(res);
            System.out.println(s);
        }
    }

    private static String arrayToString(int[] res) {
        if (res.length == 0) return "[]";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++){
            sb.append(res[i] + ", ");
        }
        return "[" + sb.substring(0, sb.length() - 2) + "]";
    }

    private static int[] stringToArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < nums.length; i++){
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }
}
