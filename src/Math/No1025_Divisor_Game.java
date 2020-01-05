package Math;

/**
 * @author HailongZeng
 * @date 12/15/19 12:42 上午
 */

import java.util.Scanner;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
 *
 * Choosing any x with 0 < x < N and N % x == 0.
 * Replacing the number N on the chalkboard with N - x.
 * Also, if a player cannot make a move, they lose the game.
 *
 * Return True if and only if Alice wins the game, assuming both players play optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: 2
 * Output: true
 * Explanation: Alice chooses 1, and Bob has no more moves.
 * Example 2:
 *
 * Input: 3
 * Output: false
 * Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 *
 *
 * Note:
 *
 * 1 <= N <= 1000
 */
public class No1025_Divisor_Game {

    //when N is odd, the player first move will lose if the other player use the optimal method.--->the factor of a odd must be odd, so when the other player play, the number will be even, then the player can take 1 move, which means the player first move will always take odd number, which means he will take 1 in the end. 1--who take this number will lose
    //when N is even, the player first move will win
    public static boolean divisorGame1(int N){
        return N % 2 == 0;
    }

    //dp[i] represents if the number is i, then the player will win with optimal play method
    public static boolean divisorGame2(int N){
        if (N < 2) return false;
        boolean[] dp = new boolean[N + 1];
        dp[2] = true;
        for (int i = 3; i <= N; i++){
            for (int j = 1; j < i / j; j++){
                if (i % j == 0 && dp[i-j] == false){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[N];
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int numOfTest = st.nextInt();
        for (int i = 0; i < numOfTest; i++){
            int N = st.nextInt();
            System.out.println(divisorGame2(N));
        }
    }
}
