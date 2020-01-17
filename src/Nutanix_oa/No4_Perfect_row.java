package Nutanix_oa;

/**
 * @author HailongZeng
 * @date 1/10/20 10:16 上午
 */

/**
 * This problem is a variation of standard Longest Increasing Subsequence (LIS) problem. Let the input array be arr[] of length n. We need to construct two arrays lis[] and lds[] using Dynamic Programming solution of LIS problem. lis[i] stores the length of the Longest Increasing subsequence ending with arr[i]. lds[i] stores the length of the longest Decreasing subsequence starting from arr[i]. Finally, we need to return the max value of lis[i] + lds[i] – 1 where i is from 0 to n-1.
 */
public class No4_Perfect_row {

    /**
     * lis[i] ==> Longest Increasing subsequence ending with arr[i]
     * lds[i] ==> Longest decreasing subsequence starting with arr[i]
     * @param arr
     * @param n
     * @return
     */
    public static int lbs(int[] arr, int n){
        int i, j;
        int[] lis = new int[n];
        for (i = 0; i < n; i++){
            lis[i] = 1;
        }
        for (i = 1; i < n; i++){
            for (j = 0; j < i; j++){
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1){
                    lis[i] = lis[j] + 1;
                }
            }
        }

        int[] lds = new int[n];
        for (i = 0; i < n; i++){
            lds[i] = 1;
        }
        for (i = n-2; i >= 0; i--){
            for (j = n-1; j > i; j--){
                if (arr[i] > arr[j] && lds[i] < lds[j] + 1){
                    lds[i] = lds[j] + 1;
                }
            }
        }

        int max = lis[0] + lds[0] - 1;
        for (i = 1; i < n; i++) {
            if (lis[i] + lds[i] - 1 > max){
                max = lis[i] + lds[i] - 1;
            }
        }
        return max;
    }

    public static void main (String[] args) {
        int arr[] = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5,
                13, 3, 11, 7, 15};
        int n = arr.length;
        System.out.println("Length of LBS is "+ lbs( arr, n ));
    }

}
