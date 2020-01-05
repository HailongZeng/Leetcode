package Amazon;

/**
 * @author HailongZeng
 * @date 12/19/19 10:29 上午
 */

import java.util.Scanner;

/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
 *
 * Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 *
 * You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 *
 *
 *
 * Example 1:
 * Input: graph = [
 *   [1,1,0],
 *   [0,1,0],
 *   [1,1,1]
 * ]
 * Output: 1
 * Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
 *
 * Example 2:
 * Input: graph = [
 *   [1,0,1],
 *   [1,1,0],
 *   [0,1,1]
 * ]
 * Output: -1
 * Explanation: There is no celebrity.
 *
 *
 * Note:
 *
 * The directed graph is represented as an adjacency matrix, which is an n x n matrix where a[i][j] = 1 means person i knows person j while a[i][j] = 0 means the contrary.
 * Remember that you won't have direct access to the adjacency matrix.
 */
public class No277_Find_the_Celebrity {

    public static int findCelebrity(int[][] matrix){
        int res = -1;
        for (int i = 0; i < matrix.length; i++){
            int sum = 0; // calculate the sum of each row, if sum == 1, labelled as celebrity and return, if not, continue
            for (int j = 0; j < matrix[0].length; j++){
                sum += matrix[i][j];
            }
            if (sum == 1) {
                res = i;
                break;
            }
        }
        return res;
    }

    public static void printArray(int[][] matrix){
        System.out.println("[");
        for (int i = 0; i < matrix.length; i++){
            System.out.print("[");
            for (int j = 0; j < matrix[0].length; j++){
                if (j == matrix[0].length - 1) System.out.print(matrix[i][j]);
                else System.out.print(matrix[i][j] + ",");
            }
            System.out.println("]");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int[][] matrix = new int[n][n];
            for (int j = 0; j < n; j++){
                for (int k = 0; k < n; k++){
                    matrix[j][k] = st.nextInt();
                }
            }
            printArray(matrix);
            System.out.println(findCelebrity(matrix));
        }
    }
}
