package Amazon;

/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 *
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 *
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 *
 * If it cannot be done, return -1.
 *
 * Example 1:
 *
 *
 *
 * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by A and B: before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
 *
 * Example 2:
 *
 * Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 * Output: -1
 * Explanation:
 * In this case, it is not possible to rotate the dominoes to make one row of values equal.
 *
 *
 * Note:
 *
 * 1 <= A[i], B[i] <= 6
 * 2 <= A.length == B.length <= 20000
 */
public class No1007_Minimum_Domino_Rotations_For_Equal_Row {

    public static int minDominoRotations(int[] A, int[] B){
        int res1 = helper(A[0], B[0], A, B);
        int res2 = helper(B[0], A[0], A, B);
        if (res2 != -1) res2 += 1;
        if (res1 != -1 && res2 != -1) return res1 < res2 ? res1 : res2;
        if (res1 == -1) return res2;
        if (res2 == -1) return res1;
        return -1;
    }

    public static int helper(int a, int b, int[] A, int[] B){
        boolean aFlag = true;
        boolean bFlag = true;
        int ra = 0;
        int rb = 0;
        for (int i = 1; i < A.length; i++){
            if (!aFlag && !bFlag) return -1;
            if (aFlag){
                if (A[i] != a && B[i] != a){
                    aFlag = false;
                } else if (B[i] == a && A[i] != a) ra++;
            }
            if (bFlag){
                if (A[i] != b && B[i] != b){
                    bFlag = false;
                } else if (A[i] == b && B[i] != b) rb++;
            }
        }
        if (aFlag && bFlag) return ra < rb ? ra : rb;
        if (aFlag) return ra;
        if (bFlag) return rb;
        return -1;
    }

    public static void main(String[] args) {
        int[] A1 = {2, 1, 2, 4, 2, 2};
        int[] B1 = {5, 2, 6, 2, 3, 3};
        int[] A2 = {3, 5, 1, 2, 3};
        int[] B2 = {3, 6, 3, 3, 4};
        System.out.println(minDominoRotations(A1, B1));
        System.out.println(minDominoRotations(A2, B2));
    }
}
