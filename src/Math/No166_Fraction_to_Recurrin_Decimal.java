package Math;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * Example 1:
 *
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 *
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 *
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 */
import java.util.*;
public class No166_Fraction_to_Recurrin_Decimal {

    public static String fractionToDecimal(int numerator, int denominator){
        if (numerator == 0) return "0";
        StringBuilder sb = new StringBuilder();
        if ((numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0)) sb.append("-");
        long divisor = Math.abs((long)numerator);
        long dividend = Math.abs((long)denominator);
        sb.append(divisor/dividend);
        long remainder = divisor & dividend;
        if (remainder == 0) return sb.toString();
        sb.append(".");
        HashMap<Long, Integer> map = new HashMap<>(); //record the position of the remainder occurred
        while (remainder != 0){
            if (map.containsKey(remainder)){
                sb.insert(map.get(remainder), "(");
                sb.append(")");
                break;
            }
            map.put(remainder, sb.length());
            remainder *= 10;
            sb.append(remainder / dividend);
            remainder %= dividend;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int numerator = st.nextInt();
            int denominator = st.nextInt();
            System.out.println(fractionToDecimal(numerator, denominator));
        }
    }
}
