package Array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author HailongZeng
 * @date 1/14/20 12:21 上午
 */
public class No456_132Pattern {

    //time:O(n)  space:O(n)
    public static boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            min[i] = Math.min(min[i-1], nums[i]);//[0, i]数组中的最小值
        }
        for (int j = nums.length - 1, k = nums.length; j >= 0; j--){
            if (nums[j] > min[j]){//nums[j] > nums[i] = min[j], i < j
                while (k < nums.length && nums[k] <= min[j]){//find the one nums[k] > min[j] = nums[i]   k > j > i
                    k++;
                }
                if (k < nums.length && nums[k] < nums[j]) return true;
                nums[--k] = nums[j]; //nums[k-1] <= min[j]  num[k] > min[j]
            }
        }
        return false;
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

    public static void main(String[] args) throws IOException, IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null) {
            int[] nums = stringToArray(line);
            boolean res = find132pattern(nums);
            System.out.println(res);
        }
    }
}
