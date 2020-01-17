package Nutanix_oa;

/**
 * @author HailongZeng
 * @date 1/10/20 10:08 上午
 */

/**
 *
 */
//leetcode45  Jump GameII
public class No3_Frog_Hopping {
    public static int jump(int[] nums){
        int res = 0;
        if (nums == null || nums.length <= 1) return res;
        int step = 0; //record the step
        int reach = 0;
        int target = 0; //record the next target
        for (int i = 0; i < nums.length - 1; i++){
            reach = Math.max(reach, i+nums[i]);
            if (i == target){
                step++;
                target = reach; //update the target
                if (target > nums.length - 1) break;
            }
        }
        return step;
    }
}
