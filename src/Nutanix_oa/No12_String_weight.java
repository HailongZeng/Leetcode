package Nutanix_oa;

/**
 * @author HailongZeng
 * @date 1/10/20 3:57 下午
 */

/**
 * Given a string consisting of only A’s and B’s. We can transform the given string to another string by toggling any character. Thus many transformations of the given string are possible. The task is to find Weight of the maximum weight transformation.
 *
 * Weight of a sting is calculated using below formula.
 *
 *
 * Weight of string = Weight of total pairs +
 *                    weight of single characters -
 *                    Total number of toggles.
 *
 * Two consecutive characters are considered as pair only if they
 * are different.
 * Weight of a single pair (both character are different) = 4
 * Weight of a single character = 1
 */

/**
 * Input: str = "AA"
 * Output: 3
 * Transformations of given string are "AA", "AB", "BA" and "BB".
 * Maximum weight transformation is "AB" or "BA".  And weight
 * is "One Pair - One Toggle" = 4-1 = 3.
 *
 * Input: str = "ABB"
 * Output: 5
 * Transformations are "ABB", "ABA", "AAB", "AAA", "BBB",
 * "BBA", "BAB" and "BAA"
 * Maximum weight is of original string 4+1 (One Pair + 1
 * character)
 */
public class No12_String_weight {

    public static void main(String[] args) {
        String str = "AAAAABB";
        System.out.println("Maximum weight of a"
                + " transformation of "
                + str + " is "
                + getMaxWeight(str));
    }

    private static int getMaxWeight(String str) {
        int n = str.length();
        //create and initialize lookup table
        int[] lookup = new int[n];
        for (int i = 0; i < n; i++){
            lookup[i] = -1;
        }
        //call recursive function
        return getMaxRec(str, 0, str.length(), lookup);
    }

    private static int getMaxRec(String str, int i, int n, int[] lookup) {
        //base case
        if (i >= n) return 0;
        //If this subproblem is already solved
        if (lookup[i] != -1) return lookup[i];
        //Don't make pair, so weight gained is 1
        int res = 1 + getMaxRec(str, i+1, n, lookup);
        //If we can make pair
        if (i+1 < n){
            //If elements are dissimilar, weight gained is 4
            if (str.charAt(i) != str.charAt(i+1)){
                res = Math.max(4 + getMaxRec(str, i+2, n, lookup), res);
            }else{
                res = Math.max(3 + getMaxRec(str, i+2, n, lookup), res);
            }
        }
        return lookup[i] = res;
    }
}
