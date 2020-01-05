package Math;

/**
 * @author HailongZeng
 * @date 1/3/20 11:57 下午
 */

import java.util.Scanner;

/**
 * Given an array A of 0s and 1s, divide the array into 3 non-empty parts such that all of these parts represent the same binary value.
 *
 * If it is possible, return any [i, j] with i+1 < j, such that:
 *
 * A[0], A[1], ..., A[i] is the first part;
 * A[i+1], A[i+2], ..., A[j-1] is the second part, and
 * A[j], A[j+1], ..., A[A.length - 1] is the third part.
 * All three parts have equal binary value.
 * If it is not possible, return [-1, -1].
 *
 * Note that the entire part is used when considering what binary value it represents.  For example, [1,1,0] represents 6 in decimal, not 3.  Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,0,1,0,1]
 * Output: [0,3]
 * Example 2:
 *
 * Input: [1,1,0,1,1]
 * Output: [-1,-1]
 *
 *
 * Note:
 *
 * 3 <= A.length <= 30000
 * A[i] == 0 or A[i] == 1
 */
public class No927_Three_Equal_Parts {

    public static int[] threeEqualParts(int[] A) {
        int[] IMP = new int[]{-1,-1};
        int N = A.length;
        //1.first let us count the number of 1
        int count = 0;
        for (int a: A){
            if (a == 1) count++;
        }
        if (count == 0) return new int[]{0, N-1};
        if (count % 3 != 0) return IMP;
        int n = count / 3;
        //2.calculate the value of three equal part---by calculate the last part
        int i = N - 1;
        while (n > 0){
            if (A[i] == 1) n--;
            i--;
        }
        System.out.println(i);
        //3.Compare the first part and the last part
        int k = i+1;
        int j = 0;
        while (A[j] == 0){
            j++;
        }
        System.out.println(j);
        while (k < N){
            if (A[k] != A[j]) return IMP;
            k++;
            j++;
        }
        int l = j-1;
        //4.Compare the second part and the last part
        k = i+1;
        while (A[j] == 0){
            j++;
        }
        while (k < N){
            if (A[k] != A[j]) return IMP;
            k++;
            j++;
        }
        return new int[]{l, j};
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int[] A = new int[n];
            for (int j = 0; j < n; j++){
                A[j] = st.nextInt();
            }
            int[] res = threeEqualParts(A);
            System.out.println(res[0] + "," + res[1]);
        }
    }
}
