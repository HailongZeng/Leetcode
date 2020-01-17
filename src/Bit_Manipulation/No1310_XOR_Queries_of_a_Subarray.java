//package Bit_Manipulation;
//
///**
// * @author HailongZeng
// * @date 1/5/20 5:14 下午
// */
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//
///**
// * Given the array arr of positive integers and the array queries where queries[i] = [Li, Ri], for each query i compute the XOR of elements from Li to Ri (that is, arr[Li] xor arr[Li+1] xor ... xor arr[Ri] ). Return an array containing the result for the given queries.
// *
// *
// * Example 1:
// *
// * Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
// * Output: [2,7,14,8]
// * Explanation:
// * The binary representation of the elements in the array are:
// * 1 = 0001
// * 3 = 0011
// * 4 = 0100
// * 8 = 1000
// * The XOR values for queries are:
// * [0,1] = 1 xor 3 = 2
// * [1,2] = 3 xor 4 = 7
// * [0,3] = 1 xor 3 xor 4 xor 8 = 14
// * [3,3] = 8
// * Example 2:
// *
// * Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
// * Output: [8,0,4,4]
// *
// *
// * Constraints:
// *
// * 1 <= arr.length <= 3 * 10^4
// * 1 <= arr[i] <= 10^9
// * 1 <= queries.length <= 3 * 10^4
// * queries[i].length == 2
// * 0 <= queries[i][0] <= queries[i][1] < arr.length
// */
//public class No1310_XOR_Queries_of_a_Subarray {
//
//    //a^a = 0, a^0 = a
//    public static int[] xorQueries(int[] arr, int[][] queries){
//        int n = arr.length;
//        int q = queries.length;
//        int[] xor = new int[n+1]; //xor[i] means arr[0]^arr[1]^...arr[i-1]
//        for (int i = 0; i < n; i++){
//            xor[i+1] = xor[i]^arr[i];
//        }
//        int[] res = new int[q];
//        for (int i = 0; i < q; i++){
//            int l = queries[i][0];
//            int r = queries[i][1];
//            res[i] = xor[l]^xor[r+1];
//        }
//        return res;
//    }
//
//    public static int[] stringToIntegerArray(String input) {
//        input = input.trim();
//        input = input.substring(1, input.length() - 1);
//        if (input.length() == 0) {
//            return new int[0];
//        }
//
//        String[] parts = input.split(",");
//        int[] output = new int[parts.length];
//        for(int index = 0; index < parts.length; index++) {
//            String part = parts[index].trim();
//            output[index] = Integer.parseInt(part);
//        }
//        return output;
//    }
//
//    public static int[][] stringToInt2dArray(String input) {
//        JsonArray jsonArray = JsonArray.readFrom(input);
//        if (jsonArray.size() == 0) {
//            return new int[0][0];
//        }
//
//        int[][] arr = new int[jsonArray.size()][];
//        for (int i = 0; i < arr.length; i++) {
//            JsonArray cols = jsonArray.get(i).asArray();
//            arr[i] = stringToIntegerArray(cols.toString());
//        }
//        return arr;
//    }
//
//    public static String integerArrayToString(int[] nums, int length) {
//        if (length == 0) {
//            return "[]";
//        }
//
//        String result = "";
//        for(int index = 0; index < length; index++) {
//            int number = nums[index];
//            result += Integer.toString(number) + ", ";
//        }
//        return "[" + result.substring(0, result.length() - 2) + "]";
//    }
//
//    public static String integerArrayToString(int[] nums) {
//        return integerArrayToString(nums, nums.length);
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            int[] arr = stringToIntegerArray(line);
//            line = in.readLine();
//            int[][] queries = stringToInt2dArray(line);
//
//            int[] ret = xorQueries(arr, queries);
//
//            String out = integerArrayToString(ret);
//
//            System.out.print(out);
//        }
//    }
//}
