package Math;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author HailongZeng
 * @date 12/15/19 9:37 上午
 */
public class No670_Maximum_Swap {

    public static int maximumSwap(int num){
        char[] A = String.valueOf(num).toCharArray();
        int[] last = new int[10]; //mark the last position of digit occurred
        for (int i = 0; i < A.length; i++){
            last[A[i]-'0'] = i;
        }
        for (int i = 0; i < A.length; i++){
            for (int d = 9; d > A[i] - '0'; d--){
                if (last[d] > i){
                    char tmp = A[i];
                    A[i] = A[last[d]];
                    A[last[d]] = tmp;
                    return Integer.valueOf(new String(A));
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int num = st.nextInt();
            System.out.println(maximumSwap(num));
        }
    }
}
