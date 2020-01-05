package Math;

/**
 * @author HailongZeng
 * @date 1/4/20 4:57 下午
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * In a deck of cards, each card has an integer written on it.
 *
 * Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:
 *
 * Each group has exactly X cards.
 * All the cards in each group have the same integer.
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,4,4,3,2,1]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[3,3],[4,4]
 * Example 2:
 *
 * Input: [1,1,1,2,2,2,3,3]
 * Output: false
 * Explanation: No possible partition.
 * Example 3:
 *
 * Input: [1]
 * Output: false
 * Explanation: No possible partition.
 * Example 4:
 *
 * Input: [1,1]
 * Output: true
 * Explanation: Possible partition [1,1]
 * Example 5:
 *
 * Input: [1,1,2,2,2,2]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[2,2]
 *
 * Note:
 *
 * 1 <= deck.length <= 10000
 * 0 <= deck[i] < 10000
 */
public class No914_X_of_a_Kind_in_a_Deck_of_Cards {

    public static boolean hasGroupSizeX(int[] deck){
        Map<Integer, Integer> map = new HashMap<>();
        for (int d: deck){
            map.put(d, map.getOrDefault(d, 0) + 1);
        }
        int X = map.get(deck[0]);
        for (int key: map.keySet()){
            X = gcd(X, map.get(key));
        }
        return X >= 2;
    }

    public static int gcd(int a, int b){
        if (b == 0) return a;
        return gcd(b, a%b);
    }

    public static void main(String[] args) {
        System.out.println(1^2^3^2);
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int[] deck = new int[n];
            for (int j = 0; j < n; j++){
                deck[j] = st.nextInt();
            }
            System.out.println(hasGroupSizeX(deck));
        }
    }
}
