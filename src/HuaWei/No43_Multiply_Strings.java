package HuaWei;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 *
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class No43_Multiply_Strings {

    public static String multiply(String num1, String num2){
        if (isZero(num1) || isZero(num2)) return "0";
        int[] a1 = new int[num1.length()];
        int[] a2 = new int[num2.length()];
        int[] product = new int[num1.length() + num2.length()];
        for (int i = a1.length - 1; i >= 0; i--){
            for (int j = a2.length - 1; j >= 0; j--){
                int thisProduct = Character.getNumericValue(num1.charAt(i)) * Character.getNumericValue((num2.charAt(j)));
                product[i + j + 1] += thisProduct % 10;
                if (product[i + j + 1] >= 10){
                    product[i + j + 1] %= 10;
                    product[i + j]++;
                }
                product[i + j] += thisProduct / 10;
                if (product[i + j] >= 10){
                    product[i + j] %= 10;
                    product[i + j - 1]++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < product.length; i++){
            if (i == 0 && product[i] == 0){
                continue;
            }
            sb.append(product[i]);
        }
        return sb.toString();
    }

    public static boolean isZero(String num){
        for (char c: num.toCharArray()){
            if (c != '0'){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String num11 = "2", num12 = "3";
        String num21 = "123", num22 = "456";
        System.out.println(multiply(num11, num12));
        System.out.println(multiply(num21, num22));
    }
}
