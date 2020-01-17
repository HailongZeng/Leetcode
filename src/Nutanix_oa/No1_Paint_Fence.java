package Nutanix_oa;

/**
 * @author HailongZeng
 * @date 1/10/20 9:40 上午
 */

/**
 * There are certain parts of a fence which need to be painted. Tom wants to divide work in such a way that each person gets exactly 1 contiguous part of the fence to paint.
 *
 * Calculate number of ways in which Tom can divide the work. 2 ways are distinct if they don't have exactly the same number of of pieces allocated.
 *
 * Input:
 * n = Number of parts of fence
 * arr = State of the fence. 1 indicates that the part needs to be painted, 0 means it does not need to be painted.
 *
 * Test Cases:
 * n = 5, arr = 10101
 * Output = 4
 *
 * The ways to divide the fence are:
 * 10|10|1
 * 1|010|1
 * 10|1|01
 * 1|01|01
 *
 * n = 2, arr = 00
 * Output = 0
 * The fence does not need any painting
 */

public class No1_Paint_Fence {

    public static int countWays(int[] arr, int n){
        int[] pos = new int[n];
        int p = 0, i;
        for (i = 0; i < n; i++){
            if (arr[i] == 1){
                pos[p] = i+1;
                p++;
            }
        }
        //if array contains only 0s
        if (p == 0) return 0;
        int ways = 1;
        for (i = 0; i < p - 1; i++){
            ways *= pos[i+1] - pos[i];
        }
        return ways;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 0, 1};
        int n = arr.length;
        int ways = countWays(arr, n);
        System.out.println(ways);
    }
}
